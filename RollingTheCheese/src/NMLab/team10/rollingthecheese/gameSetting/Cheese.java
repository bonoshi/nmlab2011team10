package NMLab.team10.rollingthecheese.gameSetting;

public class Cheese {

    // Cheese a = new Cheese(Cheese.Normal, Cheese.Large, Cheese.Left);
    // public Cheese(byte type, byte size, boolean whichSide) {
    public Cheese(byte type, byte size) {
        setType(type);
        setSize(size);
        //setOwner(whichSide);
        switch (type) {
            case Original: {
                switch (size) {
                    case Large:
                        radix = CheeseParameter.Normal.RadixLarge;
                        break;
                    case Medium:
                        radix = CheeseParameter.Normal.RadixMed;
                        break;
                    case Small:
                        radix = CheeseParameter.Normal.RadixSmall;
                        break;
                    case Tiny:
                        radix = CheeseParameter.Normal.RadixTiny;
                        break;
                }
                break;
            }
            case Casumarzu: {
                break;
            }
            case Sweaty: {
                break;
            }
            case Firing: {
                break;
            }
        }
    }

    // set by constructor
    private byte type;
    private byte size;
    private float radix;
    //private boolean owner;

    //set by initialization
    private float maxEndurance;
    private float endurance;
    private float speed;
    public float x;// central x
    public float y;

    //automatically initialize value
    private short spinAngle;
    private int poisonCount;
    private float prepareD;
    private boolean joinBattle;

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

//    public void setOwner(boolean owner) {
//        this.owner = owner;
//    }
//
//    public boolean isOwnerLeft() {
//        return owner;
//    }

    public void initialPara(House house, Projector projector) {

        spinAngle = 0;
        poisonCount = 0;
        prepareD = 0;
        joinBattle = false;

        switch (type) {
            case Original: {
                maxEndurance = CheeseParameter.Normal.Endurance;
                speed = CheeseParameter.Normal.Speed;
                switch (size) {
                    case Large: {
                        maxEndurance *= CheeseParameter.Normal.ENLarge;
                        break;
                    }
                    case Medium: {
                        maxEndurance *= CheeseParameter.Normal.ENMed;
                        break;
                    }
                    case Small: {
                        maxEndurance *= CheeseParameter.Normal.ENSmall;
                        break;
                    }
                    case Tiny: {
                        maxEndurance *= CheeseParameter.Normal.ENTiny;
                        break;
                    }
                }
                break;
            }
            case Casumarzu: {
                switch (size) {
                    case Large: {
                        break;
                    }
                    case Medium: {
                        break;
                    }
                    case Small: {
                        break;
                    }
                    case Tiny: {
                        break;
                    }
                }
                break;
            }
            case Sweaty: {
                switch (size) {
                    case Large: {
                        break;
                    }
                    case Medium: {
                        break;
                    }
                    case Small: {
                        break;
                    }
                    case Tiny: {
                        break;
                    }
                }
                break;
            }
            case Firing: {
                switch (size) {
                    case Large: {
                        break;
                    }
                    case Medium: {
                        break;
                    }
                    case Small: {
                        break;
                    }
                    case Tiny: {
                        break;
                    }
                }
                break;
            }
        }

        switch (house.getQual()) {
            case Handmade: {
                maxEndurance *= HouseParameter.Handmade;
                break;
            }
            case CheeseMold: {
                maxEndurance *= HouseParameter.CheeseMold;
                break;
            }
            case FoodChemisty: {
                maxEndurance *= HouseParameter.FoodChemisty;
                break;
            }
            case GMO: {
                maxEndurance *= HouseParameter.GMO;
                break;
            }
        }
        endurance = maxEndurance;

        switch (projector.type) {
            case Board: {
                speed*=ProjectorParameter.BoardSpeed;
                x = ProjectorParameter.Board.getCheeseX(prepareD, radix);
                y = ProjectorParameter.Board.getCheeseY(prepareD, radix);
                break;
            }
            case Slide: {
                speed*=ProjectorParameter.SlideSpeed;
                break;
            }
            case Cannon: {
                speed*=ProjectorParameter.CannonSpeed;
                break;
            }
            case Rocket: {
                speed*=ProjectorParameter.RocketSpeed;
                break;
            }
        }

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
            case Original: {
                // angleChangePerSec = 2pi/360 * X * radix =
                // CheeseParameter.Normal.DistancePerSec;
                float delta = (CheeseParameter.Normal.DistancePerSec / radix) / 57.296F;
                delta /= GlobalParameter.FramePeriod;
                spinAngle += ((short) Math.round(delta));
                if (spinAngle > 359)
                    spinAngle %= 360;
            }
            default:// not support
                break;
        }
    }

    public short getSpinAngle() {
        return spinAngle;
    }

    public void setJoinBattle(boolean joinBattle) {
        this.joinBattle = joinBattle;
    }

    public boolean isJoinBattle() {
        return joinBattle;
    }

    public static final byte Original = CheeseEnum.Original;
    public static final byte Casumarzu = CheeseEnum.Poison;
    public static final byte Sweaty = CheeseEnum.Sweaty;
    public static final byte Firing = CheeseEnum.Firing;

    public static final byte Large = CheeseSizeEnum.Large;
    public static final byte Medium = CheeseSizeEnum.Medium;
    public static final byte Small = CheeseSizeEnum.Small;
    public static final byte Tiny = CheeseSizeEnum.Tiny;

    public static final byte ForFun = CheeseProdEnum.ForFun;
    public static final byte AfterHours = CheeseProdEnum.AfterHours;
    public static final byte Bakery = CheeseProdEnum.Bakery;
    public static final byte FoodFactory = CheeseProdEnum.FoodFactory;

    public static final byte Handmade = CheeseQualityEnum.Handmade;
    public static final byte CheeseMold = CheeseQualityEnum.CheeseMold;
    public static final byte FoodChemisty = CheeseQualityEnum.FoodChemisty;
    public static final byte GMO = CheeseQualityEnum.GMO;

    public static final byte Board = ProjectorEnum.Board;
    public static final byte Slide = ProjectorEnum.Slide;
    public static final byte Cannon = ProjectorEnum.Cannon;
    public static final byte Rocket = ProjectorEnum.Rocket;

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
    public static final byte Original = 0;
    public static final byte Poison = 1;
    public static final byte Sweaty = 2;
    public static final byte Firing = 3;
}
