package TJCore.common.pipelike.rotation.net;

import TJCore.api.material.materials.properties.RotationPipeProperties;
import gregtech.api.pipenet.PipeNet;
import gregtech.api.pipenet.WorldPipeNet;
import net.minecraft.nbt.NBTTagCompound;

public class RotationPipeNet extends PipeNet<RotationPipeProperties> {
    public RotationPipeNet(WorldPipeNet<RotationPipeProperties, RotationPipeNet> world) { super(world); }

    @Override
    protected void writeNodeData(RotationPipeProperties rotationPipeProperties, NBTTagCompound nbtTagCompound) {

    }

    @Override
    protected RotationPipeProperties readNodeData(NBTTagCompound nbtTagCompound) {
        return null;
    }
}
