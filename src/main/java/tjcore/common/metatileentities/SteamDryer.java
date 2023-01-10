//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tjcore.common.metatileentities;

import gregicality.science.client.render.GCYSTextures;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import static gregicality.science.api.recipes.GCYSRecipeMaps.DRYER_RECIPES;

public class SteamDryer extends SteamMetaTileEntity {

    public SteamDryer(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, DRYER_RECIPES, GCYSTextures.DRYER_OVERLAY, isHighPressure);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new SteamDryer(metaTileEntityId, isHighPressure);
    }

    @Override
    protected boolean isBrickedCasing() {
        return true;
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(1, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(1, this, true);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        return createUITemplate(player)
                .slot(this.importItems, 0, 53, 25, GuiTextures.SLOT_STEAM.get(isHighPressure), GuiTextures.FURNACE_OVERLAY_1)
                .progressBar(workableHandler::getProgressPercent, 78, 25, 20, 18,
                        GuiTextures.PROGRESS_BAR_ARROW_STEAM.get(isHighPressure), MoveType.HORIZONTAL, workableHandler.getRecipeMap())
                .slot(this.exportItems, 0, 107, 25, true, false, GuiTextures.SLOT_STEAM.get(isHighPressure))
                .build(getHolder(), player);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected void randomDisplayTick(float x, float y, float z, EnumParticleTypes flame, EnumParticleTypes smoke) {
        super.randomDisplayTick(x, y + 0.5F, z, flame, smoke);
    }
}
