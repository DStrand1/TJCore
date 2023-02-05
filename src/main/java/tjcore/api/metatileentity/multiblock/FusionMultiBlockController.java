package tjcore.api.metatileentity.multiblock;

import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.recipes.RecipeMaps.FUSION_RECIPES;

public abstract class FusionMultiBlockController extends RecipeMapMultiblockController {

    private float heat;

    public FusionMultiBlockController(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, FUSION_RECIPES);
    }

}
