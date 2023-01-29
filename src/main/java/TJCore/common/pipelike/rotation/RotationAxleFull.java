package TJCore.common.pipelike.rotation;

import TJCore.api.axle.ISpinnable;
import TJCore.common.pipelike.rotation.world.WorldAxleFull;
import net.minecraft.util.EnumFacing;

import java.util.*;

public class RotationAxleFull implements ISpinnable {
    private List<TileEntityRotationAxle> components = new ArrayList<>();
    private final float speedDecrement = 0.025f;
    public float revolutionsPerSecond = 0.0f;
    private float torque;
    public float angle;
    EnumFacing.Axis axis;
    public RotationAxleFull(EnumFacing.Axis axis) {
        this.axis = axis;
        WorldAxleFull.addAxleWhole(this);
    }

    public void addAxle(TileEntityRotationAxle axle){
        axle.axleWhole = this;
        components.add(axle);
        updateAll();
    }

    //TODO: we get there when we get there
    public void updateAll() {
        for (TileEntityRotationAxle axle: components) {
            axle.update(revolutionsPerSecond * (float) Math.PI * 2 / 20, angle);
        }
        angle += revolutionsPerSecond * (float) Math.PI * 2 / 20;
        if (revolutionsPerSecond > speedDecrement) revolutionsPerSecond -= speedDecrement;
        else if (revolutionsPerSecond < 0-speedDecrement) revolutionsPerSecond += speedDecrement;
        else revolutionsPerSecond = 0;
    }

    public int getSize() {
        return components.size();
    }

    public void incorperate(RotationAxleFull toAdd) {
        if (toAdd == this) {return;}
        float maxSpeed = Math.max(revolutionsPerSecond, toAdd.revolutionsPerSecond);
        torque = (toAdd.torque * (toAdd.revolutionsPerSecond / maxSpeed)) + (torque * (revolutionsPerSecond / maxSpeed));
        revolutionsPerSecond = maxSpeed;
        for(TileEntityRotationAxle axle : toAdd.getComponents()) {
            addAxle(axle);
        }
        toAdd.components.clear();
    }

    public List<TileEntityRotationAxle> getComponents() {
        return components;
    }

    public void destroyAll() {

    }

    @Override
    public void pushRotation(float newSpeed, float newTorque) {
        float maxSpeed = Math.max(revolutionsPerSecond, newSpeed);
        torque = (newTorque * (newSpeed / maxSpeed)) + (torque * (revolutionsPerSecond / maxSpeed));
        revolutionsPerSecond = maxSpeed;
    }

    @Override
    public float pullTorque() {
        float torq = torque;
        torque = 0;
        return torq;
    }

    @Override
    public float getRevolutionsPerSecond() {
        return revolutionsPerSecond;
    }
}
