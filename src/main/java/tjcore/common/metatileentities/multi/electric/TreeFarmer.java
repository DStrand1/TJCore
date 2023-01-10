package tjcore.common.metatileentities.multi.electric;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.util.ResourceLocation;
import tjcore.common.TJTextures;
import tjcore.common.recipes.recipemaps.TJRecipeMaps;

import javax.annotation.Nonnull;

public class TreeFarmer extends RecipeMapMultiblockController {

    public TreeFarmer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, TJRecipeMaps.TREE_FARMER_RECIPES);

    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, false, false, true);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("BBBBB", "FFFFF", "FFFFF", "FFFFF", "AFFFA")
                .aisle("BBBBB", "FAAAF", "FAAAF", "FAAAF", "FFFFF")
                .aisle("BBBBB", "FAAAF", "FAAAF", "FAAAF", "FFMFF")
                .aisle("BBBBB", "FAAAF", "FAAAF", "FAAAF", "FFFFF")
                .aisle("BBBBB", "FFCFF", "FFFFF", "FFFFF", "AFFFA")
                .where('A', air())
                .where('F', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF)))
                .where('B', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF)).or((autoAbilities()).setMaxGlobalLimited(7)))
                .where('C', selfPredicate())
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .build();
    }

    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) { return TJTextures.TreeFarmerController; }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntityID) {
        return new TreeFarmer(this.metaTileEntityId);
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.MULTIBLOCK_WORKABLE_OVERLAY;
    }
}
