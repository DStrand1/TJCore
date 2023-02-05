package tjcore.common.recipes.fusion;

import gregtech.api.recipes.GTRecipeHandler;

import static gregtech.api.recipes.RecipeMaps.*;

public class MaterialSynthesisFusionRecipes {
    public void init() {
        removePreexistingRecipes();
    }

    private void removePreexistingRecipes() {
        GTRecipeHandler.removeAllRecipes(FUSION_RECIPES);
    }
}
