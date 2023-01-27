package TJCore.api.axle;

public interface ISpinnable {
    void pushRotation(float rotationSpeed, float torque);
    float pullTorque();
    float getRotationSpeed();
}

