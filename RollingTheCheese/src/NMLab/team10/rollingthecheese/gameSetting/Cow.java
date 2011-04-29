package NMLab.team10.rollingthecheese.gameSetting;

public class Cow {

    public Cow() {
        // x = ;
        // y = ;
        amount = CowParameter.ProductionAmount;
        interval = CowParameter.TimeInterval;
    }

    CowStatus status = Normal;
    private float x;// central x
    private float y;
    public int amount;// throughput
    public int interval;// time interval;

    public void setLeak() {
        this.status = Leak;
    }

    public void setNormal() {
        this.status = Leak;
    }

    public float getUpperLeftX() {
        return x;// bonoshi: size??margin??
    }

    public float getUpperLeftY() {
        return y;//
    }

    public static final CowStatus Normal = CowStatus.NORMAL;
    public static final CowStatus Leak = CowStatus.LEAK;
}

class CowParameter {
    static final int ProductionAmount = 50;
    static final int TimeInterval = 10000;// ms

    static final float GRAZING = 1.0F;
    static final float HUSBANDRY = 1.8F;
    static final float MECHANIZATION = 3.0F;
    static final float GROWTH_HORMONE = 4.0F;

    static final float CRISIS = 0.6F;
}

enum CowStatus {
    NORMAL, LEAK
}
