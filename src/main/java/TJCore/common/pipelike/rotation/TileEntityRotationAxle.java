package TJCore.common.pipelike.rotation;

import TJCore.common.pipelike.rotation.world.WorldAxleFull;
import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.util.GTUtility;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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
    private float angle = 0;
    private float torque = 0.0f;

    private RotationAxleFull axleWhole;
    TileEntityRotationAxle() {
        super();
    }

    public void updateAxleWhole() {
        BlockPos pos = this.getPos();
        World world = this.getWorld();
        boolean foundAxle = false;
        switch (world.getBlockState(pos).getValue(AXIS)){
            case X: {
                RotationAxleFull toJoinA = getAdjacent(world, pos, 1, 0, 0);
                RotationAxleFull toJoinB = getAdjacent(world, pos, -1, 0, 0);
                if (toJoinA != null && toJoinB != null) {
                    if (toJoinA.getSize() < toJoinB.getSize()) {
                        toJoinB.incorperate(toJoinA);
                        toJoinB.addAxle(this);
                        foundAxle = true;
                    } else {
                        toJoinA.incorperate(toJoinB);
                        toJoinA.addAxle(this);
                        foundAxle = true;
                    }
                } else if (toJoinA != null) {
                    toJoinA.addAxle(this);
                    foundAxle = true;
                } else if (toJoinB != null) {
                    toJoinB.addAxle(this);
                    foundAxle = true;
                }
                break;
            }
            case Y: {
                RotationAxleFull toJoinA = getAdjacent(world, pos, 0, 1, 0);
                RotationAxleFull toJoinB = getAdjacent(world, pos, 0, -1, 0);
                if (toJoinA != null && toJoinB != null) {
                    if (toJoinA.getSize() < toJoinB.getSize()) {
                        toJoinB.incorperate(toJoinA);
                        toJoinB.addAxle(this);
                        foundAxle = true;
                    } else {
                        toJoinA.incorperate(toJoinB);
                        toJoinA.addAxle(this);
                        foundAxle = true;
                    }
                } else if (toJoinA != null) {
                    toJoinA.addAxle(this);
                    foundAxle = true;
                } else if (toJoinB != null) {
                    toJoinB.addAxle(this);
                    foundAxle = true;
                }
                break;
            }
            case Z: {
                RotationAxleFull toJoinA = getAdjacent(world, pos, 0, 0, 1);
                RotationAxleFull toJoinB = getAdjacent(world, pos, 0, 0, -1);
                if (toJoinA != null && toJoinB != null) {
                    if (toJoinA.getSize() < toJoinB.getSize()) {
                        toJoinB.incorperate(toJoinA);
                        toJoinB.addAxle(this);
                        foundAxle = true;
                    } else {
                        toJoinA.incorperate(toJoinB);
                        toJoinA.addAxle(this);
                        foundAxle = true;
                    }
                } else if (toJoinA != null) {
                    toJoinA.addAxle(this);
                    foundAxle = true;
                } else if (toJoinB != null) {
                    toJoinB.addAxle(this);
                    foundAxle = true;
                }
                break;
            }
        }
        if (!foundAxle) {
            axleWhole = new RotationAxleFull();
            WorldAxleFull.addAxleWhole(axleWhole);
            axleWhole.addAxle(this);
        }
    }

    private @Nullable RotationAxleFull getAdjacent(World worldIn, BlockPos thisPos, int xChange, int yChange, int zChange) {
        BlockPos nextPos = new BlockPos(thisPos.getX() + xChange, thisPos.getY() + yChange, thisPos.getZ() + zChange);
        IBlockState nextState = worldIn.getBlockState(nextPos);
        if (nextState.getBlock() instanceof BlockRotationAxle) {
            return ((TileEntityRotationAxle) worldIn.getTileEntity(nextPos)).getAxleWhole();
        }
        return null;
    }

    public float getTorque() { return torque; }

    public float getAngle() {
        prevAngle = angle;
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
    public boolean hasFastRenderer()
    {
        return true;
    }

    public RotationAxleFull getAxleWhole() {
        return axleWhole;
    }


    public void setTorque(float torque) {
        this.torque = torque;
    }
}
