package TJCore.common.metatileentities.multi.electric.generator;

import TJCore.TJValues;
import TJCore.api.axle.IRotationProvider;
import TJCore.api.axle.ISpinnable;
import TJCore.api.block.ITurbineBladeStats;
import TJCore.common.pipelike.rotation.RotationAxleFull;
import TJCore.common.pipelike.rotation.TileEntityRotationAxle;
import gregtech.api.GTValues;
import TJCore.common.blocks.BlockBearing;
import TJCore.common.blocks.BlockTurbineBlades;
import TJCore.common.blocks.TJMetaBlocks;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Nullable;
import scala.Int;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static TJCore.common.recipes.recipemaps.TJRecipeMaps.*;
import static gregtech.api.GTValues.V;
import static gregtech.api.unification.material.Materials.Steam;

public class MetaTileEntityModularSteamTurbine extends MultiblockWithDisplayBase implements IRotationProvider {

    private int turbineTier;
    private int bearingTier;
    private int steamToConsume;
    private int maxSteamToConsume;
    private float rps;
    private float torque;
    private float speedDecrement = 0.025f;
    private RotationAxleFull axleWhole;
    private IFluidTank tankIn;
    private BlockPos bearingPos;

    public MetaTileEntityModularSteamTurbine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        //this.recipeMapWorkable.setMaximumOverclockVoltage(V[turbineTier + 2]);
    }

    @Override
    protected void updateFormedValid() {

    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            textList.add(new TextComponentString("Blade Type: " + turbineTier));
            textList.add(new TextComponentString("Bearing Type: " + bearingTier));
            textList.add(new TextComponentString("Rotations Per Second: " + rps));
            textList.add(new TextComponentString("Steam (mb) / tick: " + steamToConsume));
            textList.add(new TextComponentString("Maximum Steam (mb) / tick: " + maxSteamToConsume));
            textList.add(new TextComponentString("Torque: " + torque));
            textList.add(new TextComponentString(axleWhole != null ? "Connected to Axle" : "Not Connected to Axle"));
        }
        super.addDisplayText(textList);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type1 = context.get("BladesType");
        if (type1 instanceof ITurbineBladeStats) {
            this.turbineTier = ((ITurbineBladeStats) type1).getTier();
        } else {
            this.turbineTier = 0;
        }
        Object type2 = context.get("BearingType");
        if (type2 instanceof BlockBearing.BearingType) {
            this.bearingTier = ((BlockBearing.BearingType) type2).getTier();
        } else {
            this.bearingTier = 0;
        }
        this.tankIn = getAbilities(MultiblockAbility.IMPORT_FLUIDS).get(0);
        System.out.println();
    }


    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.DOWN)
                .aisle(
                        "  EEE   ",
                        " EEEEE  ",
                        "  EEE   ")
                .aisle(
                        " EE EE  ",
                        "PPBBBE  ",
                        " EE EE  ")
                .aisle(
                        " E   E S",
                        " EBRBE S",
                        " E   E S")
                .aisle(
                        " EE EE S",
                        " EBBBPPP",
                        " EE EE T")
                .aisle(
                        "  EEE  S",
                        " EEEEE S",
                        "  EEE  S")
                .where(' ', TraceabilityPredicate.ANY)
                .where('~', TraceabilityPredicate.AIR)
                .where('E', states(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.STEAM_CASING)))
                .where('T', selfPredicate())
                .where('S', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(1).setMaxGlobalLimited(1))
                        .or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMinGlobalLimited(1).setMaxGlobalLimited(1)))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('R', bearings())
                .where('B', blades())
                .build();
    }

    public TraceabilityPredicate blades() {
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            Block block = blockState.getBlock();
            if (block instanceof BlockTurbineBlades) {
                BlockTurbineBlades.TurbineBladesType bladesType = ((BlockTurbineBlades) block).getState(blockState);
                    Object currentBlades = blockWorldState.getMatchContext().getOrPut("BladesType", bladesType);
                return currentBlades.equals(bladesType);
            }
            return false;
        }, () -> ArrayUtils.addAll(
                Arrays.stream(BlockTurbineBlades.TurbineBladesType.values())
                        .map(type -> new BlockInfo(TJMetaBlocks.TURBINE_BLADES.getState(type), null))
                        .toArray(BlockInfo[]::new)));
    }

    public TraceabilityPredicate bearings() {
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            Block block = blockState.getBlock();
            if (block instanceof BlockBearing) {
                bearingPos = blockWorldState.getPos();
                BlockBearing.BearingType bearingType = ((BlockBearing) block).getState(blockState);
                Object currentBearing = blockWorldState.getMatchContext().getOrPut("BearingType", bearingType);
                return true;
            }
            return false;
        }, () -> ArrayUtils.addAll(
                Arrays.stream(BlockBearing.BearingType.values())
                        .map(type -> new BlockInfo(TJMetaBlocks.BLOCK_BEARING.getState(type), null))
                        .toArray(BlockInfo[]::new)));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityModularSteamTurbine(metaTileEntityId);
    }

    @Override
    public void pushRotation(float rotationSpeed, float torque) {
        if (!getWorld().isRemote && axleWhole != null) {
            axleWhole.pushRotation(rotationSpeed, torque);
        }
    }


    @Override
    public void joinNet() {
        if (bearingPos != null) {
            BlockPos[] posArr = new BlockPos[]{bearingPos.north(), bearingPos.south(), bearingPos.east(), bearingPos.west()};
            for (BlockPos pos : posArr) {
                if (getWorld().getTileEntity(pos) instanceof TileEntityRotationAxle) {
                    if (this.axleWhole == null) {
                        this.axleWhole = ((TileEntityRotationAxle) getWorld().getTileEntity(pos)).getAxleWhole();
                    } else {
                        this.axleWhole.incorperate(((TileEntityRotationAxle) getWorld().getTileEntity(pos)).getAxleWhole());
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote && isStructureFormed()) {
            super.update();
            if (axleWhole == null) joinNet();
            else {
                pushRotation(rps, torque);
            }
            if (tankIn.getFluid() != null) {
                if (tankIn.getFluid().isFluidEqual(Steam.getFluid(1))) {
                    int steamQuantity = tankIn.getFluidAmount();
                    float maxRPS = (float) Math.min(Math.pow(4, bearingTier), Math.pow(4, turbineTier + 1));
                    steamToConsume = (int) Math.pow(4, turbineTier + 2) * 16;
                    maxSteamToConsume = steamToConsume;
                    steamToConsume = Math.min(steamToConsume, steamQuantity);
                    tankIn.drain(steamToConsume, true);
                    this.rps = this.torque > 0.025 ? maxRPS : 0;
                    this.torque = (float) Math.min(Math.pow(4, bearingTier) * 2, Math.pow(4, turbineTier)) * steamToConsume / maxSteamToConsume;
                }
            }
        }
        if (rps > speedDecrement) rps -= speedDecrement * 4 * (5-bearingTier);
        else if (rps < 0-speedDecrement) rps += speedDecrement * 4 * (5-bearingTier);
        else rps = 0;
    }
}
