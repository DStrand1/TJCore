package TJCore.common.pipelike.rotation;

import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.metatileentity.MetaTileEntityHolder;
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
    public float prevAngle = 0f;
    public float angle = 0f;
    public float rotationSpeed = 0f;

    public int[] posRef = new int[3];

    boolean sync = false;
    private RotationAxleFull axleWhole;
    TileEntityRotationAxle() {
        super();
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    public void update(float newSpeed) {
        if (rotationSpeed != newSpeed) {
            rotationSpeed = newSpeed;
            syncAngle();
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
        sync = compound.getBoolean("sync");
        if (sync) {
            posRef = compound.getIntArray("posarray");
            if(world.isRemote) {
                TileEntityRotationAxle axle = (TileEntityRotationAxle) world.getTileEntity(new BlockPos(posRef[0], posRef[1], posRef[2]));
                angle = axle.angle;
            }
            sync = false;
        }
        rotationSpeed = compound.getFloat("speed");
        markDirty();
    }

    public void setPosRef(BlockPos posIn) {
        posRef[0] = posIn.getX();
        posRef[1] = posIn.getY();
        posRef[2] = posIn.getZ();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("sync", sync);
        if (sync) {
            compound.setIntArray("posarray", posRef);
            sync = false;
        }
        compound.setFloat("speed", rotationSpeed);
        return compound;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if(!world.isRemote) connectToNet();
        markDirty();
    }

    private void syncAngle() {
        if (world.isRemote) {
            TileEntityRotationAxle axle = (TileEntityRotationAxle) world.getTileEntity(axleWhole.getComponents().get(0).getPos());
            angle = axle.angle;
        }
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
            if(nextState.getValue(AXIS) == currentState.getValue(AXIS)) {
                TileEntityRotationAxle axle = ((TileEntityRotationAxle) worldIn.getTileEntity(nextPos));
                return axle.getAxleWhole();
            }
        }
        return null;
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
