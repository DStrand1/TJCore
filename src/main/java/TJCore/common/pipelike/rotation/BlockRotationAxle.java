package TJCore.common.pipelike.rotation;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockRotationAxle extends BlockDirectional implements ITileEntityProvider {

    public BlockRotationAxle() {
        super(Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntityRotationAxle createNewTileEntity(World worldIn, int meta) {
        return new TileEntityRotationAxle();
    }
}
