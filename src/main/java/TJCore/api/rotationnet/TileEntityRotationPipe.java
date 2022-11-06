package TJCore.api.rotationnet;

import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.metatileentity.SyncedTileEntityBase;
import gregtech.api.util.GTUtility;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TileEntityRotationPipe extends SyncedTileEntityBase implements IDataInfoProvider {
    public int torque;

    public int getTorque() { return torque; }

    @NotNull
    @Override
    public List<ITextComponent> getDataInfo() {
        List<ITextComponent> list = new ArrayList<>();
        list.add(new TextComponentTranslation("behavior.tricorder.torque",
                new TextComponentTranslation(GTUtility.formatNumbers(this.getTorque())).setStyle(new Style().setColor(TextFormatting.DARK_PURPLE))
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
