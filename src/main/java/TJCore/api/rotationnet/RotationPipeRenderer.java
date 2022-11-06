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
import gregtech.api.util.ModCompatibility;
import gregtech.client.renderer.pipe.PipeRenderer;
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

import java.util.ArrayList;
import java.util.List;

public class RotationPipeRenderer implements ICCBlockRenderer, IItemRenderer {

    public static final RotationPipeRenderer INSTANCE = new RotationPipeRenderer("tj_rotation_pipe", new ResourceLocation(TJValues.MODID, "axle_pipe"));

    public final ModelResourceLocation modelLocation;
    private String name;
    private EnumBlockRenderType blockRenderType;
    private static final ThreadLocal<BlockRenderer.BlockFace> blockFaces = ThreadLocal.withInitial(BlockRenderer.BlockFace::new);

    private TextureAtlasSprite axleTexture;

    public RotationPipeRenderer(String name, ModelResourceLocation modelLocation) {
        this.name = name;
        this.modelLocation = modelLocation;
    }

    public RotationPipeRenderer(String name, ResourceLocation location) {
        this(name, new ModelResourceLocation(location, "normal"));
    }

    public void preInit() {
        blockRenderType = BlockRenderingRegistry.createRenderType(name);
        BlockRenderingRegistry.registerRenderer(blockRenderType, this);
        MinecraftForge.EVENT_BUS.register(this);
        TextureUtils.addIconRegister(this::registerIcons);
    }

    public ModelResourceLocation getModelLocation() { return modelLocation; }

    public EnumBlockRenderType getBlockRenderType() { return blockRenderType; }

    public void registerIcons(TextureMap map) {
        axleTexture = map.registerSprite(new ResourceLocation(TJValues.MODID, "blocks/pipe/axle_side"));
    }

    @Override
    public void renderItem(ItemStack itemStack, ItemCameraTransforms.TransformType transformType) {
        ItemStack stack = ModCompatibility.getRealItemStack(itemStack);
        if(!(stack.getItem() instanceof ItemBlockRotationPipe))
            return;

        CCRenderState renderState = CCRenderState.instance();
    }

    @Override
    public boolean renderBlock(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
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

        return true;
    }

    private void renderFace(CCRenderState state, IVertexOperation[] pipeline, EnumFacing face, Cuboid6 cube) {
        BlockRenderer.BlockFace blockFace = blockFaces.get();
        blockFace.loadCuboidFace(cube, face.getIndex());
        state.setPipeline(blockFace, 0, blockFace.verts.length, pipeline);
        state.render();
    }

    @Override
    public void handleRenderBlockDamage(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState iBlockState, TextureAtlasSprite textureAtlasSprite, BufferBuilder bufferBuilder) {

    }



    @Override
    public void renderBrightness(IBlockState iBlockState, float v) {

    }

    @Override
    public void registerTextures(TextureMap textureMap) {

    }



    @Override
    public IModelState getTransforms() {
        return null;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    public Pair<TextureAtlasSprite, Integer> getParticleTexture(TileEntityRotationPipe pipe) {
        TextureAtlasSprite sprite = getParticleTexture();
        return Pair.of(sprite, 5);
    }

    public TextureAtlasSprite getParticleTexture() {
        return INSTANCE.axleTexture;
    }

    public static class BlockRenderContext {
        private final BlockPos pos;
        private final LightMatrix lightMatrix;
        private int color;
        private final List<IVertexOperation[]> axleSideRenderer = new ArrayList<>();
        private final List<IVertexOperation[]> axleEndRenderer = new ArrayList<>();


        public BlockRenderContext(BlockPos pos, LightMatrix light) {

            this.pos = pos;
            this.lightMatrix = light;
        }

        public BlockRenderContext addEndRenderer(IVertexOperation... vertexOperations) {
            IVertexOperation[] baseVertexOperation = getBaseVertexOperation();
            baseVertexOperation = ArrayUtils.addAll(baseVertexOperation, vertexOperations);
            axleEndRenderer.add(baseVertexOperation);
            return this;
        }

        public BlockRenderContext addSideRenderer(IVertexOperation... vertexOperations) {
            IVertexOperation[] baseVertexOperation = getBaseVertexOperation();
            baseVertexOperation = ArrayUtils.addAll(baseVertexOperation, vertexOperations);
            axleSideRenderer.add(baseVertexOperation);
            return this;
        }

        public ColourMultiplier getColorOperation() { return new ColourMultiplier(color); }

        private IVertexOperation[] getBaseVertexOperation() {
            if(pos == null) {
                return lightMatrix == null ? new IVertexOperation[0] : new IVertexOperation[]{lightMatrix};
            }
            return lightMatrix == null ? new IVertexOperation[]{new Translation(pos)} : new IVertexOperation[]{new Translation(pos), lightMatrix};
        }
    }
}
