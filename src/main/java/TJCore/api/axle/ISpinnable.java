package TJCore.api.axle;

public interface ISpinnable {
    void pushRotation(float rps, float torque);
    float pullTorque();
    float getRevolutionsPerSecond();
}

