package TJCore.api.rotationnet.tile;

import TJCore.api.material.materials.properties.RotationPipeProperties;
import TJCore.api.rotationnet.RotationPipeType;
import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gregtech.api.util.GTUtility;
import gregtech.api.util.PerTickIntCounter;
import gregtech.common.pipelike.cable.tile.AveragingPerTickCounter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TileEntityRotationPipe extends TileEntityMaterialPipeBase<RotationPipeType, RotationPipeProperties> implements IDataInfoProvider {

    private final PerTickIntCounter maxTorqueCounter = new PerTickIntCounter(0);
    private final AveragingPerTickCounter averageTorqueCounter = new AveragingPerTickCounter(0, 20);

    @Override
    public Class<RotationPipeType> getPipeTypeClass() {
        return RotationPipeType.class;
    }

    @Override
    public boolean supportsTicking() {
        return false;
    }

    public int getCurrentMaxTorque() { return maxTorqueCounter.get(getWorld()); }

    public double getAverageTorque() { return averageTorqueCounter.getAverage(getWorld()); }

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
}
