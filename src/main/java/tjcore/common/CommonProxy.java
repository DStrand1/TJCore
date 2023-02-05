package tjcore.common;

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
import tjcore.TJValues;
import tjcore.api.TJComponents;
import tjcore.api.TJOreDictionaryLoader;
import tjcore.common.blocks.TJMetaBlocks;
import tjcore.common.pipelike.BlockCableLongDistance;
import tjcore.common.pipelike.ItemBlockLongDistanceCable;
import tjcore.common.recipes.*;
import tjcore.common.recipes.chains.PetrochemRecipes;
import tjcore.common.recipes.circuits.CircuitRecipes;
import tjcore.common.recipes.compatrecipes.ArmorInfuserRecipes;
import tjcore.common.recipes.polymers.TJPolymers;

import java.util.function.Function;

import static gregtech.api.GTValues.V;
import static tjcore.api.material.TJMaterials.longDistanceWireMaterials;
import static tjcore.common.blocks.TJMetaBlocks.*;

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
        registry.register(STELLARATOR_COIL);
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
