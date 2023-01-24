package TJCore.common.pipelike.rotation;

import java.util.ArrayList;
import java.util.List;

public class RotationAxleFull {
    private List<TileEntityRotationAxle> components = new ArrayList<>();
    private float rotationSpeed = 0.1f;
    private float torque;
    private float angle = 0.0f;
    public RotationAxleFull() {

    }

    public void addAxle(TileEntityRotationAxle axle){
        components.add(axle);
        axle.setAngle(angle);
    }

    //TODO: we get there when we get there
    public void updateAll() {
        for (TileEntityRotationAxle axle: components) {
            axle.prevAngle = axle.angle;
        }
        angle += rotationSpeed;
        for (TileEntityRotationAxle axle: components) {
            //if (!axle.getWorld().isRemote) {
                axle.setTorque(torque);
                axle.setAngle(angle);
            //}
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
}
