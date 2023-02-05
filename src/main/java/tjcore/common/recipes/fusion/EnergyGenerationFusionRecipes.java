package tjcore.common.recipes.fusion;

import gregtech.api.recipes.GTRecipeHandler;

import static gregtech.api.recipes.RecipeMaps.*;

public class EnergyGenerationFusionRecipes {
    public void init() {
        removePreexistingRecipes();
    }

    private void removePreexistingRecipes() {
        GTRecipeHandler.removeAllRecipes(PLASMA_GENERATOR_FUELS);
    }
}
