package NMLab.team10.rollingthecheese.gameSetting;

public class Cow {

    public Cow() {
        // x = ;
        // y = ;
        amount = CowParameter.ProductionAmount;
        interval = CowParameter.TimeInterval;
    }

    byte status = Normal;
    private float x;// central x
    private float y;
    public int amount;// throughput
    public int interval;// time interval;

    public void setLeak() {
        this.status = Leak;
    }

    public void setNormal() {
        this.status = Normal;
    }

    public float getUpperLeftX() {
        return x;// bonoshi: size??margin??
    }

    public float getUpperLeftY() {
        return y;//
    }

    public static final byte Normal = CowStatusEnum.NORMAL;
    public static final byte Leak = CowStatusEnum.LEAK;
}

class CowParameter {
    public static final int TimeInterval = 10000;//ms

    public static final int ProductionAmount = 50;

    public static final float Grazing = 1.0F;
    public static final float Husbandry = 1.8F;
    public static final float Mechanization = 3.0F;
    public static final float GrowthHormone = 4.0F;

    public static final float Crisis = 0.6F;
}

class CowStatusEnum {
    public static final byte NORMAL = 0;
    public static final byte LEAK = 1;
}
