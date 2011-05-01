package NMLab.team10.rollingthecheese.gameSetting;

class CheeseParameter {// have display light type

    class Normal {// normal cheese

        public static final int Time = 2000;// ms
        public static final float Endurance = 100F;
        public static final float DistancePerSec = 100F;
        public static final float Speed = DistancePerSec / GlobalParameter.FramePeriod;// 1sec 100pixel
        public static final float Radix = 36;// pixel
        public static final int Cost = 50;

        // static final float SizeLarge = 2.0F;
        // static final float SizeMed = 1.0F;
        // static final float SizeSmall = 0.6F;
        public static final float SizeLarge = 1.0F;
        public static final float SizeMed = 0.7F;
        public static final float SizeSmall = 0.45F;

        public static final float CostLarge = 1.6F;
        public static final float CostMed = 1.0F;
        public static final float CostSmall = 0.7F;

        public static final float Board = 1.0F;
        public static final float Slide = 1.3F;
        public static final float Cannon = 1.8F;
        public static final float Rocket = 2.4F;

        public static final float For_fun = 1.5F;
        public static final float After_hours = 1.0F;
        public static final float Bakery = 0.6F;
        public static final float Food_factory = 0.2F;

        public static final float Handmade = 1.0F;
        public static final float Cheese_mold = 1.2F;
        public static final float Food_chemisty = 1.5F;
        public static final float GMO = 2.0F;

        public static final float Size_Crisis = 0.7F;// ratio
        public static final float Time_Crisis = 0.6F;// increment
    }

    class Poison {
        // for poison cheese and its contact infection
        public static final float PoisonDecreSmall = 4;
        public static final float PoisonDecreMed = 10;
        public static final float PoisonDecreLarge = 20;
        public static final int PoisonSmallCount = 2;// 1~2
        public static final int PoisonMedCount = 4;// 3~4
        public static final int PoisonLargeCount = 6;// 5~6
        public static final int PoisonDecayTime = 1000;// ms
    }

    class Sweat {
        // for sweaty cheese and its range and effect
    }

    class Fire {
        // for firing cheese and its effect
    }

}

public class Cheese {

    // Cheese a = new Cheese(Cheese.Normal, Cheese.Large, Cheese.Left);
    // public Cheese(byte type, byte size, boolean whichSide) {
    public Cheese(byte type, byte size) {
        // setOwner(whichSide);
        setType(type);
        setSize(size);
        switch (type) {
            case Normal: {
                radix = CheeseParameter.Normal.Radix;
                switch (size) {
                    case Large:
                        radix *= CheeseParameter.Normal.SizeLarge;
                        break;
                    case Medium:
                        radix *= CheeseParameter.Normal.SizeMed;
                        break;
                    case Small:
                        radix *= CheeseParameter.Normal.SizeSmall;
                        break;
                }
                break;
            }
            default:// not support
                break;
        }
    }

    private byte type;
    private byte size;
    private float endurance;
    private float maxEndurance;
    private float speed;
    private short spinAngle = 0;
    // private boolean owner;
    private float radix;
    public float x;// central x
    public float y;
    private int poisonCount = 0;

    public void setType(byte type) {
        this.type = type;
    }

    public byte getType() {// for occqoo
        return type;
    }

    public void setSize(byte size) {
        this.size = size;
    }

    public byte getSize() {
        return size;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {// for occqoo
        return speed;
    }

    // public void setOwner(boolean owner) {
    // this.owner = owner;
    // }
    //
    // public boolean isOwnerLeft() {//for occqoo
    // return owner;
    // }

    public void setMaxEndurance(float maxEndurance) {
        this.maxEndurance = maxEndurance;
    }

    public float getMaxEndurance() {
        return maxEndurance;
    }

    public float getUpperLeftX() {// for occqoo
        return (x - radix);
    }

    public float getUpperLeftY() {// for occqoo
        return (y - radix);
    }

    public void setPoisonCount(int poisonCount) {
        this.poisonCount = poisonCount;
    }

    public int getPoisonCount() {
        return poisonCount;
    }

    public void regreshAngle() {
        switch (type) {
            case Normal: {
                // angleChangePerSec = 2pi/360 * X * radix = CheeseParameter.Normal.DistancePerSec;
                float delta = (CheeseParameter.Normal.DistancePerSec / radix) / 57.296F;
                delta /= GlobalParameter.FramePeriod;
                spinAngle += ((short) Math.round(delta));
                if (spinAngle > 359)
                    spinAngle%=360;
            }
            default:// not support
                break;
        }
    }

    public short getSpinAngle() {
        return spinAngle;
    }

    public static final byte Normal = CheeseEnum.Normal;
    public static final byte Casumarzu = CheeseEnum.Poison;
    public static final byte Sweaty = CheeseEnum.Sweaty;
    public static final byte Firing = CheeseEnum.Firing;

    public static final byte Large = CheeseSizeEnum.Large;
    public static final byte Medium = CheeseSizeEnum.Medium;
    public static final byte Small = CheeseSizeEnum.Small;
    public static final byte Tiny = CheeseSizeEnum.Tiny;

    public static final boolean Right = false;
    public static final boolean Left = true;
}

class CheeseSizeEnum {
    public static final byte Large = 0;
    public static final byte Medium = 1;
    public static final byte Small = 2;
    public static final byte Tiny = 3;
}

class CheeseEnum {
    public static final byte Normal = 0;
    public static final byte Poison = 1;
    public static final byte Sweaty = 2;
    public static final byte Firing = 3;
}
