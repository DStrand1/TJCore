package TJCore.common.pipelike.rotation;

import gregtech.api.metatileentity.IDataInfoProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.block.BlockRotatedPillar.AXIS;

public class TileEntityRotationAxle extends TileEntity implements IDataInfoProvider {

    private boolean foundNet;
    public float prevAngle = 0.0f;
    public float angle = 0f;
    public float rotationSpeed = 0f;


    private RotationAxleFull axleWhole;
    TileEntityRotationAxle() {
        super();
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        // getUpdateTag() is called whenever the chunkdata is sent to the
        // client. In contrast getUpdatePacket() is called when the tile entity
        // itself wants to sync to the client. In many cases you want to send
        // over the same information in getUpdateTag() as in getUpdatePacket().
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        // Prepare a packet for syncing our TE to the client. Since we only have to sync the stack
        // and that's all we have we just write our entire NBT here. If you have a complex
        // tile entity that doesn't need to have all information on the client you can write
        // a more optimal NBT here.
        NBTTagCompound nbtTag = new NBTTagCompound();
        nbtTag.setFloat("speed", rotationSpeed);
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        // Here we get the packet from the server and read it into our client side tile entity
        this.readFromNBT(packet.getNbtCompound());
    }

    public void update(float newSpeed) {
        if (rotationSpeed != newSpeed) {
            rotationSpeed = newSpeed;
            markDirty();
            if (world != null) {
                IBlockState state = world.getBlockState(getPos());
                world.notifyBlockUpdate(getPos(), state, state, 2);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        rotationSpeed = compound.getFloat("speed");
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setFloat("speed", rotationSpeed);
        return compound;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if(!world.isRemote)
            connectToNet();
        markDirty();
    }

    public void connectToNet() {
        BlockPos pos = this.getPos();
        World world = this.getWorld();

        Axis a = world.getBlockState(pos).getValue(AXIS);
        foundNet = findNetToAttach(a);
        if (!foundNet) {
            axleWhole = new RotationAxleFull(a);
            axleWhole.addAxle(this);
        }
    }

    private boolean findNetToAttach(Axis axis) {
        RotationAxleFull toJoinA = getAdjacent(world, pos, axis == Axis.X ?  1 : 0, axis == Axis.Y ?  1 : 0, axis == Axis.Z ?  1 : 0);
        RotationAxleFull toJoinB = getAdjacent(world, pos, axis == Axis.X ? -1 : 0, axis == Axis.Y ? -1 : 0, axis == Axis.Z ? -1 : 0);
        if (toJoinA != null && toJoinB != null) {
            if (toJoinA.getSize() < toJoinB.getSize()) {
                toJoinB.incorperate(toJoinA);
                toJoinB.addAxle(this);
                for(TileEntityRotationAxle axle : toJoinA.getComponents())
                    axle.axleWhole = toJoinB;
                axleWhole = toJoinB;
                return true;
            } else {
                toJoinA.incorperate(toJoinB);
                toJoinA.addAxle(this);
                for(TileEntityRotationAxle axle : toJoinB.getComponents())
                    axle.axleWhole = toJoinA;
                axleWhole = toJoinA;
                return true;
            }
        } else if (toJoinA != null) {
            toJoinA.addAxle(this);
            for(TileEntityRotationAxle axle : toJoinA.getComponents())
                axle.axleWhole = toJoinA;
            axleWhole = toJoinA;
            return true;
        } else if (toJoinB != null) {
            toJoinB.addAxle(this);
            for(TileEntityRotationAxle axle : toJoinB.getComponents())
                axle.axleWhole = toJoinB;
            axleWhole = toJoinB;
            return true;
        }
        return false;
    }

    private @Nullable RotationAxleFull getAdjacent(World worldIn, BlockPos thisPos, int xChange, int yChange, int zChange) {
        BlockPos nextPos = new BlockPos(thisPos.getX() + xChange, thisPos.getY() + yChange, thisPos.getZ() + zChange);
        IBlockState nextState = worldIn.getBlockState(nextPos);

        IBlockState currentState = worldIn.getBlockState(thisPos);

        if (nextState.getBlock() instanceof BlockRotationAxle) {
            if(nextState.getValue(AXIS) == currentState.getValue(AXIS))
                return ((TileEntityRotationAxle) worldIn.getTileEntity(nextPos)).getAxleWhole();
        }
        return null;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    //@NotNull
    //@Override
    public List<ITextComponent> getDataInfo() {
        List<ITextComponent> list = new ArrayList<>();
        //list.add(new TextComponentTranslation("behavior.tricorder.torque",
                //new TextComponentTranslation(GTUtility.formatNumbers(this.getTorque())).setStyle(new Style().setColor(TextFormatting.DARK_PURPLE))
        //));
        return list;
    }

    public void updateAngle() {
        markDirty();
        prevAngle = angle;
        angle += rotationSpeed;
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }

    public RotationAxleFull getAxleWhole() {
        return axleWhole;
    }
}
