package TJCore.api.rotationnet;

import TJCore.TJValues;
import TJCore.common.TJTextures;
import codechicken.lib.lighting.LightMatrix;
import codechicken.lib.render.BlockRenderer;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.block.BlockRenderingRegistry;
import codechicken.lib.render.block.ICCBlockRenderer;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Translation;
import codechicken.lib.vec.Vector3;
import codechicken.lib.vec.uv.IconTransformation;
import gregtech.api.pipenet.block.BlockPipe;
import gregtech.api.pipenet.block.IPipeType;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.ModCompatibility;
import gregtech.client.renderer.pipe.PipeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.pipelike.fluidpipe.FluidPipeType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.model.IModelState;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RotationPipeRenderer extends PipeRenderer {

    public static final RotationPipeRenderer INSTANCE = new RotationPipeRenderer();

    //public final ModelResourceLocation modelLocation;
    private String name;
    //private EnumBlockRenderType blockRenderType;
    private static final ThreadLocal<BlockRenderer.BlockFace> blockFaces = ThreadLocal.withInitial(BlockRenderer.BlockFace::new);

    private TextureAtlasSprite axleTexture;


    public RotationPipeRenderer() {
        super("tj_rotation_pipe", new ResourceLocation(TJValues.MODID, "axle_pipe"));
    }

    @Override
    public void registerIcons(TextureMap map) {
        axleTexture = map.registerSprite(new ResourceLocation(TJValues.MODID, "blocks/rotation_pipe/axle_side"));
    }

    //@Override
    //public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType transformType) {
    //    ItemStack stack = ModCompatibility.getRealItemStack(itemStack);
    //    if(!(stack.getItem() instanceof ItemBlockRotationPipe))
    //        return;

        //CCRenderState renderState = CCRenderState.instance();
    //}

    @Override
    public void buildRenderer(PipeRenderContext renderContext, BlockPipe<?, ?, ?> blockPipe, IPipeTile<?, ?> pipeTile, IPipeType<?> pipeType, @Nullable Material material) {
        if (material == null || !(pipeType instanceof RotationPipeType)) {
            return;
        }
        //renderContext.addOpenFaceRender(false, new IconTransformation(Textures.PIPE_NORMAL)).addSideRender(new IconTransformation(axleTexture));
        renderContext.addOpenFaceRender(false, new IconTransformation(axleTexture)).addSideRender(new IconTransformation(axleTexture));
        //renderContext.addSideRender(new IconTransformation(axleTexture));

    }



    //@Override
    //public boolean renderBlock(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
        /*
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.bind(buffer);
        renderState.setBrightness(world, pos);

        BlockRenderLayer renderLayer = MinecraftForgeClient.getRenderLayer();
        if(renderLayer == BlockRenderLayer.CUTOUT_MIPPED) {
            renderState.lightMatrix.locate(world, pos);
            BlockRenderContext renderContext = new BlockRenderContext(pos, renderState.lightMatrix);
            renderContext.color = 16777215;
            renderContext.addSideRenderer(new IconTransformation(TJTextures.RotationPipe));
            Cuboid6 box = new Cuboid6(new Vector3(0.375f, 0.375f, 0.0f), new Vector3(0.625f, 0.625f, 1.0f));
            for (EnumFacing renderedSide : EnumFacing.VALUES) {
                for (IVertexOperation[] vertexOperations : renderContext.axleSideRenderer) {
                    renderFace(renderState, vertexOperations, renderedSide, box);
                }
            }


            //BlockRenderer.renderCuboid(renderState, box, 0);
        }
        */
     //   return true;
    //}

    @Override
    public TextureAtlasSprite getParticleTexture(IPipeType<?> pipeType, @Nullable Material material) {
        return INSTANCE.axleTexture;
    }


}
