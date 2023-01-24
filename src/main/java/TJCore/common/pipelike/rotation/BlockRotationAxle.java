package TJCore.common.pipelike.rotation;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.raytracer.RayTracer;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.tool.IScrewdriverItem;
import gregtech.api.capability.tool.IWrenchItem;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.ICoverable;
import gregtech.api.items.toolitem.IToolStats;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.pipenet.tile.TileEntityPipeBase;
import gregtech.common.blocks.BlockFrame;
import gregtech.common.blocks.FrameItemBlock;
import gregtech.common.tools.DamageValues;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.PropertyFloat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static TJCore.TJValues.MODID;

public class BlockRotationAxle extends BlockRotatedPillar implements ITileEntityProvider {
    public static final PropertyFloat ROTATION = new PropertyFloat("rotation");
    public BlockRotationAxle()
    {
        super(Material.GRASS, MapColor.YELLOW);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Z));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    protected BlockStateContainer createBlockState() {
        return new ExtendedBlockState(this, new IProperty[]{AXIS}, new IUnlistedProperty[]{ROTATION});
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        state.withProperty(AXIS, state.getValue(AXIS));
        return true;
    }

    @Nullable
    @Override
    public TileEntityRotationAxle createNewTileEntity(World worldIn, int meta) {
        TileEntityRotationAxle axle =  new TileEntityRotationAxle();
        axle.updateAxleWhole();
        return axle;
    }
}
