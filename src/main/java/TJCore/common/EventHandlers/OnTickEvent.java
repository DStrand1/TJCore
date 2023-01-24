package TJCore.common.EventHandlers;

import TJCore.TJValues;
import TJCore.common.pipelike.rotation.world.WorldAxleFull;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = TJValues.MODID)
public class OnTickEvent {

    @SubscribeEvent
    public static void onTick(TickEvent.ServerTickEvent event) {
        WorldAxleFull.update();
    }
}
