package TJCore.api.rotationnet;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockRotationPipe extends ItemBlock {
    public ItemBlockRotationPipe(BlockRotationPipe block) {
        super(block);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}
