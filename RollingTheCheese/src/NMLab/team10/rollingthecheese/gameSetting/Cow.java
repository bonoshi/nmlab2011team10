package NMLab.team10.rollingthecheese.gameSetting;

public class Cow {//have display light type

    public Cow() {
        // x = ;
        // y = ;
        setAmount(CowParameter.ProductionAmount);
        setInterval(CowParameter.TimeInterval);
    }

    private byte status = Normal;
    private float x;// central x
    private float y;
    private int amount;// throughput
    private int interval;// time interval;
    private byte animation;//animation
    private boolean headingLeft;//direction

    public float getUpperLeftX() {
        return x;// bonoshi: size??margin??
    }

    public float getUpperLeftY() {
        return y;//
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }

    public void setAnimation(byte animation) {
        this.animation = animation;
    }

    public byte getAnimation() {
        return animation;
    }

    public void setHeadingLeft(boolean headingLeft) {
        this.headingLeft = headingLeft;
    }

    public boolean isHeadingLeft() {
        return headingLeft;
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
