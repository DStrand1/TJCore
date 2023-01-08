package TJCore.common.pipelike.rotation.tile;

import TJCore.common.pipelike.rotation.tile.TileEntityRotationPipe;
import gregtech.api.metatileentity.interfaces.IHasWorldObjectAndCoords;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityRotationPipeTickable extends TileEntityRotationPipe implements ITickable, IHasWorldObjectAndCoords {

    @Override
    public void update() {
        getCoverableImplementation().update();
    }

    @Override
    public boolean supportsTicking() {
        return true;
    }

    @Override
    public World world() {
        return null;
    }

    @Override
    public BlockPos pos() {
        return null;
    }
}
