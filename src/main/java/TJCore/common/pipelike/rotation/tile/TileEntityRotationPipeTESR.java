package TJCore.common.pipelike.rotation.tile;

import TJCore.common.pipelike.rotation.RotationPipeType;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Transformation;
import codechicken.lib.vec.Translation;
import codechicken.lib.vec.uv.IconTransformation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileEntityRotationPipeTESR extends TileEntitySpecialRenderer<TileEntityRotationPipe> {

    @Override
    public void render(TileEntityRotationPipe te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);



        float angle = te.getCurrentRotation();
        te.incrementRot();


        //Vector3 translationCenter = new Vector3(-0.5f, 0.0f, -0.5f);
        //Vector3 translationBlock = blockPosToVector3(pipeTile.getPipePos());
        //Rotation rotation = new Rotation(angle, 0.0, 1.0f, 0.0f);
        Translation mainTranslation = te != null ? new Translation(te.getPipePos()) : new Translation(0.0f, 0.0f, 0.0f);
        Transformation transform = new Translation(-0.5f, 0.0f, -0.5f).with(new Rotation(angle, 0.0, 1.0f, 0.0f)).with(mainTranslation).with(new Translation(0.5f, 0.0f, 0.5f));



        if ((te.getConnections() & 63) == 0) {
            //renderContext.addOpenFaceRender(false, new IconTransformation(INSTANCE.axleEnd), new Rotation(angle, 0.0f, 1.0f, 0.0f));
            renderContext.addOpenFaceRender(false, new IconTransformation(INSTANCE.axleEnd), transform);
            return;
        }

        renderContext.addOpenFaceRender(false, new IconTransformation(INSTANCE.axleEnd), transform)
                .addSideRender(false, new IconTransformation(INSTANCE.axleSide));
    }
}
