package tjcore.common.metatileentities.multi.fusion;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.util.ResourceLocation;
import tjcore.api.metatileentity.multiblock.FusionMultiBlockController;

public class MetaTileEntityFusionICFR extends FusionMultiBlockController {
    public MetaTileEntityFusionICFR(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return null;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return null;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return null;
    }
}
