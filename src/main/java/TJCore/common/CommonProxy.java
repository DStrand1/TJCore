package TJCore.common;

import TJCore.TJValues;
import TJCore.api.TJComponents;
import TJCore.api.TJOreDictionaryLoader;
import TJCore.common.blocks.TJMetaBlocks;
import TJCore.common.pipelike.BlockCableLongDistance;
import TJCore.common.pipelike.ItemBlockLongDistanceCable;
import TJCore.common.recipes.*;
import TJCore.common.recipes.chains.PetrochemRecipes;
import TJCore.common.recipes.circuits.CircuitRecipes;
import TJCore.common.recipes.compatrecipes.ArmorInfuserRecipes;
import TJCore.common.recipes.polymers.TJPolymers;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.unification.material.properties.WireProperties;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;

import static TJCore.api.material.TJMaterials.longDistanceWireMaterials;
import static TJCore.common.blocks.TJMetaBlocks.*;
import static gregtech.api.GTValues.V;

@Mod.EventBusSubscriber(modid = TJValues.MODID)
public class CommonProxy {

    public void preInit() {
        TJOreDictionaryLoader.init();
        TJMetaBlocks.registerTileEntity();
    }

    @SubscribeEvent
    public static void RegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(DRACONIC_CASING);
        registry.register(TURBINE_BLADES);
        registry.register(BLOCK_BEARING);
        for (int i = 0; i < longDistanceWireMaterials.length; i++) {
            for (BlockCableLongDistance cable : LONG_DIST_CABLES) {
                cable.addCableMaterial(longDistanceWireMaterials[i], new WireProperties(Math.toIntExact(V[i + 1]), 4, 0));
            }
        }
    }
    
    @SubscribeEvent
    public static void RegisterItemBlocks(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (BlockCableLongDistance cable : LONG_DIST_CABLES) registry.register(createItemBlock(cable, ItemBlockLongDistanceCable::new));
        registry.register(createItemBlock(TURBINE_BLADES, VariantItemBlock::new));
        registry.register(createItemBlock(BLOCK_BEARING, VariantItemBlock::new));
        registry.register(createItemBlock(DRACONIC_CASING, VariantItemBlock::new));
    }

    
    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        //noinspection ConstantConditions
        itemBlock.setRegistryName(block.getRegistryName());
        return itemBlock;
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        TJMetaBlocks.registerOreDict();
        TJOreDictionaryLoader.registerRecipes();
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void init(RegistryEvent.Register<IRecipe> event) {

        CircuitRecipes.registerCircuits();
        TJComponents.init();
        TJPolymers.registerPolymers();
        GTComponents.init();
        PetrochemRecipes.init();
        TJFuelRecipes.init();
        if (Loader.isModLoaded("draconicevolution")) {
            ArmorInfuserRecipes.register();
        }
        Ores.RegisterOres();
        MaterialRecipes.register();
        MachineRecipes.registerMachines();
        MultiblockHatches.init();
    }
}
