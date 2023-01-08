package TJCore.common.pipelike.rotation;

import TJCore.api.material.materials.properties.RotationPipeProperties;
import TJCore.client.renderer.pipe.RotationPipeRenderer;
import TJCore.common.pipelike.rotation.net.WorldRotationPipeNet;
import TJCore.common.pipelike.rotation.tile.TileEntityRotationPipe;
import TJCore.common.pipelike.rotation.tile.TileEntityRotationPipeTickable;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.tool.IHammerItem;
import gregtech.api.capability.tool.IWrenchItem;
import gregtech.api.items.toolitem.IToolStats;
import gregtech.api.pipenet.block.material.BlockMaterialPipe;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.pipenet.tile.TileEntityPipeBase;
import gregtech.api.unification.material.Material;
import gregtech.common.tools.DamageValues;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class BlockRotationPipe extends BlockMaterialPipe<RotationPipeType, RotationPipeProperties, WorldRotationPipeNet> {

    private final SortedMap<Material, RotationPipeProperties> enabledMaterials = new TreeMap<>();




    public BlockRotationPipe(RotationPipeType rotationPipeType) {
        super(rotationPipeType);
        setHarvestLevel("wrench", 1);
    }

    public void addPipeMaterial(Material material, RotationPipeProperties properties) {
        Preconditions.checkNotNull(material, "material");
        Preconditions.checkNotNull(properties, "material %s itemPipeProperties was null", material);
        Preconditions.checkArgument(GregTechAPI.MATERIAL_REGISTRY.getNameForObject(material) != null, "material %s is not registered", material);
        this.enabledMaterials.put(material, properties);
    }

    public Collection<Material> getEnabledMaterials() { return Collections.unmodifiableSet(enabledMaterials.keySet()); }

    @Override
    public Class<RotationPipeType> getPipeTypeClass() {
        return RotationPipeType.class;
    }

    @Override
    public WorldRotationPipeNet getWorldPipeNet(World world) {
        return WorldRotationPipeNet.getWorldPipeNet(world);
    }

    @Override
    protected RotationPipeProperties createProperties(RotationPipeType rotationPipeType, Material material) {
        return rotationPipeType.modifyProperties(enabledMaterials.getOrDefault(material, getFallbackType()));
    }

    @Override
    protected RotationPipeProperties getFallbackType() {
        return enabledMaterials.values().iterator().next();
    }

    @Override
    public void getSubBlocks(@NotNull CreativeTabs creativeTabs, @NotNull NonNullList<ItemStack> items) {
        for(Material mat : enabledMaterials.keySet())
            items.add(getItem(mat));
    }

    @Override
    public EnumActionResult onPipeToolUsed(World world, BlockPos pos, ItemStack stack, EnumFacing side, IPipeTile<RotationPipeType, RotationPipeProperties> pipeTile, EntityPlayer entityPlayer) {
        IWrenchItem wrenchItem = stack.getCapability(GregtechCapabilities.CAPABILITY_WRENCH, null);

        if(wrenchItem != null) {
            if(wrenchItem.damageItem(1, true)) {
                if(!entityPlayer.world.isRemote) {
                    boolean isOpen = pipeTile.isConnected(side);
                    pipeTile.setConnection(side, !isOpen, false);
                    wrenchItem.damageItem(1, false);
                    IToolStats.onOtherUse(stack, world, pos);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean canPipesConnect(IPipeTile<RotationPipeType, RotationPipeProperties> selfTile, EnumFacing enumFacing, IPipeTile<RotationPipeType, RotationPipeProperties> otherTile) {
        boolean canConnect = selfTile instanceof TileEntityRotationPipe && otherTile instanceof TileEntityRotationPipe;

        return canConnect;
    }

    @Override
    public boolean canPipeConnectToBlock(IPipeTile<RotationPipeType, RotationPipeProperties> iPipeTile, EnumFacing enumFacing, TileEntity tile) {
        return tile != null;
    }

    @Override
    public boolean isHoldingPipe(EntityPlayer player) {
        if(player == null)
            return false;

        ItemStack stack = player.getHeldItemMainhand();
        return stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlockRotationPipe;
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        // loomle
    }

    @Override
    public TileEntityPipeBase<RotationPipeType, RotationPipeProperties> createNewTileEntity(boolean ticking) {
        return new TileEntityRotationPipe();
        //return new TileEntityRotationPipeTickable();
    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return RotationPipeRenderer.INSTANCE.getBlockRenderType();
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected Pair<TextureAtlasSprite, Integer> getParticleTexture(World world, BlockPos blockPos) {
        return RotationPipeRenderer.INSTANCE.getParticleTexture((IPipeTile<?, ?>) world.getTileEntity(blockPos));
    }
}
