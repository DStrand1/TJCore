package TJCore.api.axle;

import TJCore.common.pipelike.rotation.RotationAxleFull;

public interface IRotationProvider {
    void pushRotation(float rotationSpeed, float torque);
    void joinNet();
    float getRotation();
    void setAxleWhole(RotationAxleFull axle);
}
