package TJCore.common.pipelike.rotation;

import TJCore.api.axle.ISpinnable;
import TJCore.common.pipelike.rotation.world.WorldAxleFull;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class RotationAxleFull implements ISpinnable {
    private List<TileEntityRotationAxle> components = new ArrayList<>();
    private final float speedDecrement = 0.01f;
    public float rotationSpeed = 0.0f;
    private float torque;
    EnumFacing.Axis axis;
    public RotationAxleFull(EnumFacing.Axis axis) {
        this.axis = axis;
        WorldAxleFull.addAxleWhole(this);
    }

    public void addAxle(TileEntityRotationAxle axle){
        components.add(axle);
    }

    //TODO: we get there when we get there
    public void updateAll() {
        syncAngle(components.get(0).angle);
        for (TileEntityRotationAxle axle: components) {
             axle.update(rotationSpeed);
        }
        if (rotationSpeed > speedDecrement) rotationSpeed -= speedDecrement;
        else if (rotationSpeed < 0-speedDecrement) rotationSpeed += speedDecrement;
        else rotationSpeed = 0;
    }

    public void syncAngle(float angle) {
        for (TileEntityRotationAxle axle: components) {
            axle.prevAngle = angle;
            axle.update(angle);
        }
    }

    public int getSize() {
        return components.size();
    }

    public void incorperate(RotationAxleFull toAdd) {
        if (toAdd == this) {return;}
        components.addAll(toAdd.getComponents());
        toAdd = null;
    }

    public List<TileEntityRotationAxle> getComponents() {
        return components;
    }

    public void destroyAll() {

    }

    @Override
    public void pushRotation(float speed, float torque) {
        rotationSpeed = Math.max(rotationSpeed, speed);
        this.torque += torque;
    }

    @Override
    public float pullTorque() {
        float torq = torque;
        torque = 0;
        return torq;
    }

    @Override
    public float getRotationSpeed() {
        return rotationSpeed;
    }
}
