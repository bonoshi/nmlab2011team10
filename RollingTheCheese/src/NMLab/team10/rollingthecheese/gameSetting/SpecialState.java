package NMLab.team10.rollingthecheese.gameSetting;

public class SpecialState {
    public static byte setFense(byte state, boolean fense) {
        if (fense) {
            return (byte) ((state&(~1))|1);
        } else {
            return (byte) (state&(~1));
        }
    }
    public static byte setPower(byte state, boolean power) {
        if (power) {
            return (byte) ((state&(~2))|2);
        } else {
            return (byte) (state&(~2));
        }
    }
    public static boolean isFense(byte state) {
        return !((state & 1) == 0);
    }
    public static boolean isPower(byte state) {
        return !((state & 2) == 0);
    }
}
