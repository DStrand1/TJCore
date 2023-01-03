package TJCore.api.rotationnet.tile;

import TJCore.api.rotationnet.tile.TileEntityRotationPipe;
import net.minecraft.util.ITickable;

public class TileEntityRotationPipeTickable extends TileEntityRotationPipe implements ITickable {

    @Override
    public void update() {

    }

    @Override
    public boolean supportsTicking() {
        return true;
    }
}
