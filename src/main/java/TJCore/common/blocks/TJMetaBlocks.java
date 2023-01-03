package TJCore.common.blocks;

import TJCore.api.rotationnet.BlockRotationPipe;
import TJCore.api.rotationnet.RotationPipeRenderer;
import TJCore.api.rotationnet.RotationPipeType;
import TJCore.api.rotationnet.tile.TileEntityRotationPipe;
import TJCore.api.rotationnet.tile.TileEntityRotationPipeTickable;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.client.model.SimpleStateMapper;
import gregtech.common.blocks.BlockBoilerCasing;
import TJCore.common.blocks.BlockTurbineBlades;
import TJCore.common.blocks.BlockBearing;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gregtech.common.blocks.MetaBlocks;

import static TJCore.TJValues.MODID;

public class TJMetaBlocks {
    
    public static DraconicCasings DRACONIC_CASING;
    public static BlockBearing BLOCK_BEARING;
    public static BlockTurbineBlades TURBINE_BLADES;

    public static BlockRotationPipe AXLE_PIPES;
    
    public static void init() {
        AXLE_PIPES = new BlockRotationPipe(RotationPipeType.NORMAL);
        AXLE_PIPES.setRegistryName(String.format("rotation_pipe_normal"));

        DRACONIC_CASING = new DraconicCasings();
        DRACONIC_CASING.setRegistryName("draconic_casing");
        TURBINE_BLADES = new BlockTurbineBlades();
        TURBINE_BLADES.setRegistryName("turbine_blades");
        BLOCK_BEARING = new BlockBearing();
        BLOCK_BEARING.setRegistryName("block_bearing");
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {

        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(AXLE_PIPES), stack -> RotationPipeRenderer.INSTANCE.getModelLocation());

        registerItemModel(DRACONIC_CASING);
        registerItemModel(TURBINE_BLADES);
        registerItemModel(BLOCK_BEARING);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            MetaBlocks.statePropertiesToString(state.getProperties())));
        }
    }

    public static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityRotationPipe.class, new ResourceLocation(MODID, "rotation_pipe_normal"));
        GameRegistry.registerTileEntity(TileEntityRotationPipeTickable.class, new ResourceLocation(MODID, "rotation_pipe_normal_tickable"));
    }

    public static void registerStateMappers() {
        IStateMapper normalStateMapper = new SimpleStateMapper(RotationPipeRenderer.INSTANCE.getModelLocation());
        ModelLoader.setCustomStateMapper(AXLE_PIPES, normalStateMapper);
    }

    public static void registerOreDict() {
        for(Material mat : AXLE_PIPES.getEnabledMaterials()) {
            ItemStack itemStack = AXLE_PIPES.getItem(mat);
            OreDictUnifier.registerOre(itemStack, AXLE_PIPES.getPrefix(), mat);
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> String getPropertyName(IProperty<T> property, Comparable<?> value) {
        return property.getName((T) value);
    }
}
