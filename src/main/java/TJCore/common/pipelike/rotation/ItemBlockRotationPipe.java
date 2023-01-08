package TJCore.common.pipelike.rotation;

import gregtech.api.pipenet.block.material.BlockMaterialPipe;
import gregtech.api.pipenet.block.material.ItemBlockMaterialPipe;
import TJCore.api.material.materials.properties.RotationPipeProperties;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemBlockRotationPipe extends ItemBlockMaterialPipe<RotationPipeType, RotationPipeProperties> {
    public ItemBlockRotationPipe(BlockRotationPipe block) {
        super(block);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        RotationPipeProperties props = blockPipe.createItemProperties(stack);
        tooltip.add(I18n.format("tjcore.rotation_pipe.max_torque", props.getMaxTorque()));

        if(flagIn.isAdvanced()) {
            tooltip.add("MetaItem Id: " + ((BlockMaterialPipe<?, ?, ?>)blockPipe).getPrefix().name + ((BlockMaterialPipe<?, ?, ?>)blockPipe).getItemMaterial(stack).toCamelCaseString());
        }
    }
}
