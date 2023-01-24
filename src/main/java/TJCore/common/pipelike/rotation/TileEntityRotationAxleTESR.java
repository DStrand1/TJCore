package TJCore.common.pipelike.rotation;

import TJCore.TJValues;
import codechicken.lib.render.CCModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.Properties;

import static net.minecraft.block.BlockRotatedPillar.AXIS;

public class TileEntityRotationAxleTESR extends FastTESR<TileEntityRotationAxle> {

    private final ModelRotationAxle model = new ModelRotationAxle();
    private static final ResourceLocation texture = new ResourceLocation(TJValues.MODID, "blocks/rotation_pipe/axle");
    @Override
    public void renderTileEntityFast(TileEntityRotationAxle te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        this.bindTexture(texture);
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        //GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        float angle;
        model.axle.rotateAngleX = 0;
        model.axle.rotateAngleY = 0;
        model.axle.rotateAngleZ = 0;
        model.axle.offsetX = 0.0f;
        model.axle.offsetY = 0.0f;
        model.axle.offsetZ = 0.0f;
        switch(te.getWorld().getBlockState(te.getPos()).getValue(AXIS)){
            case X:
                model.axle.rotateAngleZ = (float) Math.PI /2;
                model.axle.offsetX = 0.5f;
                angle = te.getAngle();
                model.axle.rotateAngleY = te.prevAngle + (angle - te.prevAngle) * partial;
                break;
            case Y:
                //model.axle.rotateAngleZ = (float) Math.PI /2;
                model.axle.offsetY = -0.5f;
                angle = te.getAngle();
                model.axle.rotateAngleY = te.prevAngle + (angle - te.prevAngle) * partial;
                break;
            case Z:
                model.axle.rotateAngleX = (float) Math.PI /2;
                model.axle.offsetZ = -0.5f;
                angle = te.getAngle();
                model.axle.rotateAngleZ = te.prevAngle + (angle - te.prevAngle) * partial;
                break;
        }
        model.axle.render(0.0625f);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.matrixMode(5890);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
    }
}
