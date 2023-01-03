package TJCore.api.rotationnet.net;

import TJCore.api.material.materials.properties.RotationPipeProperties;
import gregtech.api.pipenet.WorldPipeNet;
import net.minecraft.world.World;


import static gregtech.api.pipenet.WorldPipeNet.getDataID;

public class WorldRotationPipeNet extends WorldPipeNet<RotationPipeProperties, RotationPipeNet> {

    private static String DATA_ID_BASE = "tjcore.rotation_pipe_net";

    public static WorldRotationPipeNet getWorldPipeNet(World world) {
        String DATA_ID = getDataID(DATA_ID_BASE, world);
        WorldRotationPipeNet netWorldData = (WorldRotationPipeNet) world.loadData(WorldRotationPipeNet.class, DATA_ID);
        if(netWorldData == null) {
            netWorldData = new WorldRotationPipeNet(DATA_ID);
            world.setData(DATA_ID, netWorldData);
        }
        netWorldData.setWorldAndInit(world);
        return netWorldData;
    }

    public WorldRotationPipeNet(String name) { super(name); }

    @Override
    protected RotationPipeNet createNetInstance() {
        return new RotationPipeNet(this);
    }
}
