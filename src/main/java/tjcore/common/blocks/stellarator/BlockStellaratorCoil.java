package tjcore.common.blocks.stellarator;

import gregtech.api.block.IStateHarvestLevel;
import gregtech.api.block.VariantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import tjcore.common.blocks.BlockBearing;

import javax.annotation.Nonnull;

public class BlockStellaratorCoil extends VariantBlock<BlockStellaratorCoil.CoilType> {

    public BlockStellaratorCoil() {
        super(Material.IRON);
        setTranslationKey("stellarator_coil");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setDefaultState(getState(CoilType.STELLARATOR_COIL_1));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CoilType implements IStringSerializable, IStateHarvestLevel {

        STELLARATOR_COIL_1("coil_1", 1, 3),
        STELLARATOR_COIL_2("coil_2", 2, 3),
        STELLARATOR_COIL_3("coil_3", 3, 3);

        private final String name;
        private final int tier;
        private final int harvestLevel;


        CoilType(String name, int tier, int harvestLevel) {
            this.tier = tier;
            this.name = name;
            this.harvestLevel = harvestLevel;
        }

        @Nonnull
        @Override
        public String getName() {
            return name;
        }

        public int getTier() {
            return tier;
        }

        @Override
        public int getHarvestLevel(IBlockState state) {
            return harvestLevel;
        }

        @Override
        public String getHarvestTool(IBlockState state) {
            return "wrench";
        }
    }
}
