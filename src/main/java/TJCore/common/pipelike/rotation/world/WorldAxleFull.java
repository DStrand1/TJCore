package TJCore.common.pipelike.rotation.world;

import TJCore.common.pipelike.rotation.AxleWhole;
import net.minecraft.world.World;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WorldAxleFull {
    private WeakReference<World> worldRef = new WeakReference<>(null);
    private static List<AxleWhole> axleFulls = new ArrayList<>();

    public static void update() {
        for(AxleWhole axleFull : axleFulls) {
            axleFull.updateAll();
        }
    }
    public static void addAxleWhole(AxleWhole axleFull) {
        axleFulls.add(axleFull);
    }
}
