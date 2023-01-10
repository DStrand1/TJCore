package TJCore;

import TJCore.common.CommonProxy;
import TJCore.common.blocks.TJMetaBlocks;
import TJCore.common.metaitem.TJMetaItems;
import TJCore.common.metatileentities.TJMetaTileEntities;
import codechicken.lib.CodeChickenLib;
import gregicality.science.GregicalityScience;
import gregicality.science.api.utils.GCYSLog;
import gregicality.science.common.block.GCYSMetaBlocks;
import gregicality.science.common.items.GCYSMetaItems;
import gregtech.api.GTValues;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = TJValues.MODID,
        name = "TJCore",
        version = TJValues.VERSION,
        acceptedMinecraftVersions = "[1.12, 1.13)",
        dependencies = "required:forge@[14.23.5.2847,);" + CodeChickenLib.MOD_VERSION_DEP + GTValues.MOD_VERSION_DEP + "after:gcys;after:forestry;after:jei@[4.15.0,);after:crafttweaker;before:ctm")
public class TJCore {

    @SidedProxy(modId = "tjcore", clientSide = "TJCore.client.ClientProxy", serverSide = "TJCore.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onModConstruction(FMLConstructionEvent event) {
        GTValues.HT = true;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TJMetaItems.init();
        TJMetaTileEntities.init();
        TJSounds.registerSounds();
        TJMetaBlocks.init();
        proxy.preInit();

        // TODO GCYS
        GCYSLog.init(event.getModLog());

        GCYSMetaItems.initMetaItems();
        GCYSMetaBlocks.init();

        GregicalityScience.proxy.preLoad();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        //proxy.init(event);

    }
}
