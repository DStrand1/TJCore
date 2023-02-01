package TJCore.utils;

public class Math {

    public static int add(int a, int b) {
        if (b == 0)
            return a;
        else if(a == 0)
            return b;
        return add(a ^ b, (a & b) << 1);
    }

    public static long converge(int speed, int decrementSpeed) {
        int a = 0;
        a = speed > decrementSpeed ? speed - decrementSpeed : 0;
        if(speed < 0)
            a *= -1;
        return a;
    }
}
