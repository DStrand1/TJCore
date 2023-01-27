package TJCore.api.axle;

public interface IRotationProvider {
    void pushRotation(float rotationSpeed, float torque);
    void joinNet();
}
