package TJCore.client;

import TJCore.api.rotationnet.RotationPipeRenderer;
import TJCore.common.CommonProxy;
import TJCore.common.TJTextures;
import TJCore.common.blocks.TJMetaBlocks;
import net.minecraft.util.Rotation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();

        RotationPipeRenderer.INSTANCE.preInit();

        TJTextures.preInit();
    }
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        TJMetaBlocks.registerStateMappers();
        TJMetaBlocks.registerItemModels();
    }
}
