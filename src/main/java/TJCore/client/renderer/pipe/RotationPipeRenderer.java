package TJCore.client.renderer.pipe;

import TJCore.TJValues;
import TJCore.common.pipelike.rotation.RotationPipeType;
import TJCore.common.pipelike.rotation.tile.TileEntityRotationPipe;
import codechicken.lib.render.BlockRenderer;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.*;
import codechicken.lib.vec.uv.IconTransformation;
import gregtech.api.pipenet.block.BlockPipe;
import gregtech.api.pipenet.block.IPipeType;
import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.CubeRendererState;
import gregtech.client.renderer.pipe.PipeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.pipelike.cable.Insulation;
import gregtech.common.pipelike.itempipe.BlockItemPipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RotationPipeRenderer extends PipeRenderer {

    public static final RotationPipeRenderer INSTANCE = new RotationPipeRenderer();

    //public final ModelResourceLocation modelLocation;
    private String name;
    //private EnumBlockRenderType blockRenderType;
    private static final ThreadLocal<BlockRenderer.BlockFace> blockFaces = ThreadLocal.withInitial(BlockRenderer.BlockFace::new);

    private TextureAtlasSprite axleSide;
    private TextureAtlasSprite axleEnd;


    public RotationPipeRenderer() {
        super("tj_rotation_pipe", new ResourceLocation(TJValues.MODID, "axle_pipe"));
    }

    @Override
    public void registerIcons(TextureMap map) {
        axleSide = map.registerSprite(new ResourceLocation(TJValues.MODID, "blocks/rotation_pipe/axle_side"));
        axleEnd = map.registerSprite(new ResourceLocation(TJValues.MODID, "blocks/rotation_pipe/axle_end"));
    }

    private Vector3 blockPosToVector3(BlockPos pos) {
        return new Vector3(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void buildRenderer(PipeRenderContext renderContext, BlockPipe<?, ?, ?> blockPipe, IPipeTile<?, ?> pipeTile, IPipeType<?> pipeType, @Nullable Material material) {
        if (material == null || !(pipeType instanceof RotationPipeType)) {
            return;
        }
        float angle = 0.0f;
        if(pipeTile instanceof TileEntityRotationPipe) {
            angle = ((TileEntityRotationPipe) pipeTile).getCurrentRotation();
            ((TileEntityRotationPipe) pipeTile).incrementRot();
        }

        //Vector3 translationCenter = new Vector3(-0.5f, 0.0f, -0.5f);
        //Vector3 translationBlock = blockPosToVector3(pipeTile.getPipePos());
        //Rotation rotation = new Rotation(angle, 0.0, 1.0f, 0.0f);
        Translation mainTranslation = pipeTile != null ? new Translation(pipeTile.getPipePos()) : new Translation(0.0f, 0.0f, 0.0f);
        Transformation transform = new Translation(-0.5f, 0.0f, -0.5f).with(new Rotation(angle, 0.0, 1.0f, 0.0f)).with(mainTranslation).with(new Translation(0.5f, 0.0f, 0.5f));

        if ((renderContext.getConnections() & 63) == 0) {
            //renderContext.addOpenFaceRender(false, new IconTransformation(INSTANCE.axleEnd), new Rotation(angle, 0.0f, 1.0f, 0.0f));
            renderContext.addOpenFaceRender(false, new IconTransformation(INSTANCE.axleEnd), transform);
            return;
        }

        renderContext.addOpenFaceRender(false, new IconTransformation(INSTANCE.axleEnd), transform)
                .addSideRender(false, new IconTransformation(INSTANCE.axleSide));

    }

    @Override
    public TextureAtlasSprite getParticleTexture(IPipeType<?> pipeType, @Nullable Material material) {
        return INSTANCE.axleEnd;
    }


}
