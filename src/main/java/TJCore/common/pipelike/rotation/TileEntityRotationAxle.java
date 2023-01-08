package TJCore.common.pipelike.rotation;

import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.util.GTUtility;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.animation.FastTESR;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TileEntityRotationAxle extends TileEntity implements IDataInfoProvider {

    private float rotation = 45.0f;
    private float torque = 0.0f;
    private int dir = 0;

    TileEntityRotationAxle() {}

    public float getRotation() { return rotation; }
    public float getTorque() { return rotation; }

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
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }
}
