package tjcore.common.metatileentities.multi.fusion;

import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;
import tjcore.api.metatileentity.multiblock.FusionMultiBlockController;
import tjcore.common.blocks.BlockTurbineBlades;
import tjcore.common.blocks.TJMetaBlocks;
import tjcore.common.blocks.stellarator.BlockStellaratorCoil;
import tjcore.common.metatileentities.multi.electric.ExposureChamber;

import javax.annotation.Nonnull;

import java.util.Arrays;

import static gregtech.api.recipes.RecipeMaps.FUSION_RECIPES;

public class MetaTileEntityFusionStellarator extends AbstractMaterialFusion {
    public MetaTileEntityFusionStellarator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    public TraceabilityPredicate dumpValve() {
        return null;
    }

    @Override
    protected void updateFormedValid() {

    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.DOWN)
                .aisle( "###########GG##########",
                        "##########RYGG#########",
                        "########G#GGGRB########",
                        "#####GGGG#GGGGGG#######",
                        "#####GRG######GG#Y#####",
                        "###GYGGG########Y##G###",
                        "##GRGG###########GGGG##",
                        "#GG##############GBGGG#",
                        "#BGG#############G#GRG#",
                        "###################BGG#",
                        "#GGGG#############YYY##",
                        "#######################",
                        "##YYY#############GGGG#",
                        "#GGB###################",
                        "#GRG#G#############GGB#",
                        "#GGGBG##############GG#",
                        "##GGGG###########GGRG##",
                        "###G##Y########GGGYG###",
                        "#####Y#GG######GRG#####",
                        "#######GGGGGG#GGGG#####",
                        "########BRGGG#G########",
                        "#########GGYR##########",
                        "##########GG###########")

                .aisle( "#########GGRYG#########",
                        "########GG   RB########",
                        "#####GGBRGRYR  G#######",
                        "#####GRBRGIIIIBRBY#####",
                        "###GY  BII####IBBRGG###",
                        "##GR YRI######IYRBBRG##",
                        "##G RII########IBBR GG#",
                        "#G BBBB#########IRBB RG",
                        "#BRRI############GIR GG",
                        "GGIII############IBB GG",
                        "GYRRRI##########IYRRRGG",
                        "GYIIII###########IIIIYG",
                        "GGRRRYI##########IRRRYG",
                        "GG BBI############IIIGG",
                        "GG#RIG############IRRB#",
                        "GR BBRI#########BBBB G#",
                        "#GG RBBI########IIR G##",
                        "##GRBBRYI######IRY RG##",
                        "###GGRBBI####IIB  YG###",
                        "#####YBRBIIIIGRBRG#####",
                        "#######G  RYRGRBGG#####",
                        "########BR   GG########",
                        "#########GYRGG#########")

                .aisle( "##########GRYG#########",
                        "#######BBB   RBG#######",
                        "####B#GR       RB######",
                        "####BR    RYR    YR####",
                        "###GY    RIIIIR   BG###",
                        "##GR  RIII####IYR  RB##",
                        "##G  RY########I    B##",
                        "#R   II#########IR   RG",
                        "#B  RI###########IR  GG",
                        "GY   I###########I   R#",
                        "#R   RI#########IR   GG",
                        "GG   YI####S####IY   GG",
                        "GG   RI#########IR   R#",
                        "#R   I###########I   YG",
                        "GG  RI###########IR  B#",
                        "GR   RI#########II   R#",
                        "##B    I########YR   R#",
                        "##BR  RYI####IIIR  RG##",
                        "###GB   RIIIIR    YG###",
                        "####RY    RYR    RB####",
                        "######BR       RG#B####",
                        "#######GBR   BBB#######",
                        "#########GRYG##########")

                .aisle( "##########GRYG#########",
                        "#########G   R#########",
                        "######G#R RYR BG#######",
                        "#####GGGRIIIIIBRBY#####",
                        "###GYGRII######BBRGG###",
                        "##GR Y########IYRI RB##",
                        "##G RII########IIIR B##",
                        "#R BBB##########IRII RG",
                        "#B RIB############IR G#",
                        "GYIII#############GGGG#",
                        "#GRRRI###########IRRRG#",
                        "#GGGGYI#########IYGGGG#",
                        "#GRRRI###########IRRRG#",
                        "#GGGG#############IIIYG",
                        "#G RI############BIR B#",
                        "GR IIRI##########BBB R#",
                        "##B RIII########IIR G##",
                        "##BR IRYI########Y RG##",
                        "###GGRBB######IIRGYG###",
                        "#####YBRBIIIIIRGGG#####",
                        "#######GB RYR R#G######",
                        "#########R   G#########",
                        "#########GYRG##########")

                .aisle( "#########GGGG##########",
                        "#########GRYGG#########",
                        "#########GGGGRG########",
                        "############GGGG#######",
                        "#################Y#####",
                        "###GG###########Y#GG###",
                        "##GRG##############G###",
                        "##GGG###############GG#",
                        "#BRG################RG#",
                        "#G##################GG#",
                        "#GYYY#############GGG##",
                        "#######################",
                        "##GGG#############YYYG#",
                        "#GG##################G#",
                        "#GR################GRB#",
                        "#GG###############GGG##",
                        "###G##############GRG##",
                        "###GG#Y###########GG###",
                        "#####Y#################",
                        "#######GGGG############",
                        "########GRGGGG#########",
                        "#########GGYRG#########",
                        "##########GGGG#########")

                .where('S', selfPredicate())
                .where('#', any())
                .where(' ', air())
                .where('G', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)))
                .where('R', coils())
                .where('Y', states(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.HSS_G)))
                .where('I', states(MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.FUSION_CASING)))
                .where('B', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .build();
    }

    public TraceabilityPredicate coils() {
        return new TraceabilityPredicate(blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            Block block = blockState.getBlock();
            if (block instanceof BlockStellaratorCoil) {
                BlockStellaratorCoil.CoilType coilType = ((BlockStellaratorCoil) block).getState(blockState);
                Object currentCoil = blockWorldState.getMatchContext().getOrPut("CoilType", coilType);
                return currentCoil.equals(coilType);
            }
            return false;
        }, () -> ArrayUtils.addAll(
                Arrays.stream(BlockStellaratorCoil.CoilType.values())
                        .map(type -> new BlockInfo(TJMetaBlocks.STELLARATOR_COIL.getState(type), null))
                        .toArray(BlockInfo[]::new)));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.ASSEMBLING_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntityID) {
        return new MetaTileEntityFusionStellarator(this.metaTileEntityId);
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.MULTIBLOCK_WORKABLE_OVERLAY;
    }
}
