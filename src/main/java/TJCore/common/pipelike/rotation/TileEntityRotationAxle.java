package TJCore.common.pipelike.rotation;

import TJCore.common.pipelike.rotation.world.WorldAxleFull;
import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.util.GTUtility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.block.BlockRotatedPillar.AXIS;

public class TileEntityRotationAxle extends TileEntity implements IDataInfoProvider {

    //private float rotationSpeed = 0.0f;
    public float prevAngle = 0.0f;
    public float angle = 0;
    private float torque = 0.0f;

    private RotationAxleFull axleWhole;
    TileEntityRotationAxle() {
        super();
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
        boolean foundNet = findNetToAttach(a);
        if (!foundNet) {
            axleWhole = new RotationAxleFull();
            WorldAxleFull.addAxleWhole(axleWhole);
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

    public float getTorque() { return torque; }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @NotNull
    @Override
    public List<ITextComponent> getDataInfo() {
        List<ITextComponent> list = new ArrayList<>();
        list.add(new TextComponentTranslation("behavior.tricorder.torque",
                new TextComponentTranslation(GTUtility.formatNumbers(this.getTorque())).setStyle(new Style().setColor(TextFormatting.DARK_PURPLE))
        ));
        return list;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }

    public RotationAxleFull getAxleWhole() {
        return axleWhole;
    }


    public void setTorque(float torque) {
        this.torque = torque;
    }
}
