package NMLab.team10.rollingthecheese.gameSetting;

public class CowParameter {
    public static final int TimeInterval = 10000;// ms

    public static final int ProductionAmount = 50;

    public static final int BasePrice = 200;

    public static final int IncrePrice = 200;

    public static final float Crisis = 0.6F;

    public static int getPrice(int num) {
        if (num >= MaxCow) {
            return Integer.MAX_VALUE;
        } else {
            return ((num - 1) * IncrePrice + BasePrice);
        }
    }

    public static String getPriceText(int num) {
        if (num >= MaxCow) {
            return "MAX";
        } else {
            return Integer.toString(getPrice(num));
        }
    }

    public static final int MaxCow = 5;
}
