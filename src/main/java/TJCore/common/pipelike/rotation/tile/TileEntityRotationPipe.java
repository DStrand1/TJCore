package TJCore.common.pipelike.rotation.tile;

import TJCore.api.material.materials.properties.RotationPipeProperties;
import TJCore.common.pipelike.rotation.RotationPipeType;
import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gregtech.api.util.GTUtility;
import gregtech.api.util.PerTickIntCounter;
import gregtech.common.pipelike.cable.tile.AveragingPerTickCounter;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.animation.FastTESR;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TileEntityRotationPipe extends TileEntityMaterialPipeBase<RotationPipeType, RotationPipeProperties> implements IDataInfoProvider {

    protected float rotation = 45.0f;
    private final PerTickIntCounter maxTorqueCounter = new PerTickIntCounter(0);
    private final AveragingPerTickCounter averageTorqueCounter = new AveragingPerTickCounter(0, 20);

    @Override
    public Class<RotationPipeType> getPipeTypeClass() {
        return RotationPipeType.class;
    }

    public void incrementRot() {
        rotation+=1.0f;
        if(rotation > 360.0f)
            rotation %= 360.0f;
    }

    @Override
    public boolean supportsTicking() {
        return false;
    }

    public int getCurrentMaxTorque() { return maxTorqueCounter.get(getWorld()); }

    public double getAverageTorque() { return averageTorqueCounter.getAverage(getWorld()); }

    public float getCurrentRotation() { return rotation; }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound) {
        super.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(@Nonnull NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @NotNull
    @Override
    public List<ITextComponent> getDataInfo() {
        List<ITextComponent> list = new ArrayList<>();
        list.add(new TextComponentTranslation("behavior.tricorder.torque",
                new TextComponentTranslation(GTUtility.formatNumbers(this.getAverageTorque())).setStyle(new Style().setColor(TextFormatting.DARK_PURPLE))
        ));
        return list;
    }



    @Override
    public void writeInitialSyncData(PacketBuffer packetBuffer) {

    }

    @Override
    public void receiveInitialSyncData(PacketBuffer packetBuffer) {

    }

    @Override
    public void receiveCustomData(int i, PacketBuffer packetBuffer) {

    }

    @Override
    public void renderTileEntityFast(TileEntityRotationPipe te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {

    }
}
