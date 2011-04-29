package NMLab.team10.rollingthecheese.gameSetting;

class CheeseParameter {

    class Normal {// normal cheese

        static final int Time = 2000;// ms
        static final int Endurance = 100;
        static final float Speed = 100F;// one pixel per 25ms
        static final float Radix = 45;// pixel
        static final int Cost = 50;

        static final float SizeLarge = 2.0F;
        static final float SizeMid = 1.0F;
        static final float SizeSmall = 0.6F;

        static final float CostLarge = 1.6F;
        static final float CostMid = 1.0F;
        static final float CostSmall = 0.7F;

        static final float Board = 1.0F;
        static final float Slide = 1.3F;
        static final float Cannon = 1.8F;
        static final float Rocket = 2.4F;

        static final float For_fun = 1.5F;
        static final float After_hours = 1.0F;
        static final float Bakery = 0.6F;
        static final float Food_factory = 0.2F;

        static final float Handmade = 1.0F;
        static final float Cheese_mold = 1.2F;
        static final float Food_chemisty = 1.5F;
        static final float GMO = 2.0F;

        static final float Size_Crisis = 0.7F;// ratio
        static final float Time_Crisis = 0.6F;// increment

    }
}

public class Cheese {

    // Cheese a = new Cheese(Cheese.Normal, Cheese.Large, Cheese.Left);
    public Cheese(CheeseType type, CheeseSize size, boolean whichSide) {
        setOwner(whichSide);
        setType(type);
        switch (type) {
            case NORMAL: {
                radix = CheeseParameter.Normal.Radix;
                switch (size) {
                    case LARGE:
                        radix *= CheeseParameter.Normal.SizeLarge;
                        break;
                    case MID:
                        radix *= CheeseParameter.Normal.SizeMid;
                        break;
                    case SMALL:
                        radix *= CheeseParameter.Normal.SizeSmall;
                        break;
                }
                break;
            }
            default:// not support
                break;
        }

    }

    private CheeseType type;
    private int endurance;
    private int maxEndurance;
    private float speed;
    private boolean owner;
    private float radix;
    public float x;// central x
    public float y;

    public void setType(CheeseType type) {
        this.type = type;
    }

    public CheeseType getType() {
        return type;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getEndurance() {
        return endurance;
    }

    public float getHPPercent() {
        return (((float) endurance) / maxEndurance);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isOwnerLeft() {
        return owner;
    }

    public void setMaxEndurance(int maxEndurance) {
        this.maxEndurance = maxEndurance;
    }

    public int getMaxEndurance() {
        return maxEndurance;
    }

    public float getUpperLeftX() {
        return (x - radix);
    }

    public float getUpperLeftY() {
        return (y - radix);
    }

    public static final CheeseType Normal = CheeseType.NORMAL;
    public static final CheeseType Casumarzu = CheeseType.CASUMARZU;
    public static final CheeseType Sweaty = CheeseType.SWEATY;
    public static final CheeseType Firing = CheeseType.FIRING;

    public static final CheeseSize Large = CheeseSize.LARGE;
    public static final CheeseSize Mid = CheeseSize.MID;
    public static final CheeseSize Small = CheeseSize.SMALL;

    public static final boolean Right = false;
    public static final boolean Left = true;
}

enum CheeseSize {
    LARGE, MID, SMALL
}

enum CheeseType {
    NORMAL, CASUMARZU, SWEATY, FIRING
}
