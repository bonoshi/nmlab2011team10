package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

public class Cheese {

    // Cheese a = new Cheese(Cheese.Normal, Cheese.Large, Cheese.Left);
    // public Cheese(byte type, byte size, boolean whichSide) {
    public Cheese(byte type, byte size) {
        ID = GlobalID++;
        setType(type);
        setSize(size);
        // setOwner(whichSide);
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

    private static short GlobalID = 0;
    private short ID;

    public short getID() {
        return ID;
    }

    // set by constructor
    private byte type;
    private byte size;
    private float radix;
    // private boolean owner;

    // set by initialization
    private float maxEndurance;
    private float endurance;
    private float speed;
    public float x;// central x
    public float y;

    // automatically initialize value
    private boolean joinBattle;
    private float prepareD;

    private short spinAngle;

    private boolean isDead;

    private boolean isPoison;
    private short poisonAmount;// for poison
    private byte poisonState;

    private byte fireState;
    private boolean isBump;

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

    public void decreEndurance(float endurance) {
        this.endurance -= endurance;
        if (this.endurance < 0)
            this.endurance = 0;
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

    public void setRadix(float radix) {
        this.radix = radix;
    }

    public float getRadix() {
        return radix;
    }

    // public void setOwner(boolean owner) {
    // this.owner = owner;
    // }
    //
    // public boolean isOwnerLeft() {
    // return owner;
    // }

    public void initialPara(House house, Projector projector, boolean whichSide) {

        spinAngle = 0;
        prepareD = 0;
        joinBattle = false;
        isDead = false;
        isPoison = false;
        poisonAmount = 0;
        poisonState = 0;
        fireState = 0;
        isBump = false;

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
                maxEndurance = CheeseParameter.Poison.Endurance;
                speed = CheeseParameter.Poison.Speed;
                switch (size) {
                    case Large: {
                        maxEndurance *= CheeseParameter.Poison.ENLarge;
                        break;
                    }
                    case Medium: {
                        maxEndurance *= CheeseParameter.Poison.ENMed;
                        break;
                    }
                    case Small: {
                        maxEndurance *= CheeseParameter.Poison.ENSmall;
                        break;
                    }
                    case Tiny: {
                        maxEndurance *= CheeseParameter.Poison.ENTiny;
                        break;
                    }
                }
                break;
            }
            case Sweaty: {
                maxEndurance = CheeseParameter.Sweat.Endurance;
                speed = CheeseParameter.Sweat.Speed;
                switch (size) {
                    case Large: {
                        maxEndurance *= CheeseParameter.Sweat.ENLarge;
                        break;
                    }
                    case Medium: {
                        maxEndurance *= CheeseParameter.Sweat.ENMed;
                        break;
                    }
                    case Small: {
                        maxEndurance *= CheeseParameter.Sweat.ENSmall;
                        break;
                    }
                    case Tiny: {
                        maxEndurance *= CheeseParameter.Sweat.ENTiny;
                        break;
                    }
                }
                break;
            }
            case Firing: {
                maxEndurance = CheeseParameter.Fire.Endurance;
                speed = CheeseParameter.Fire.Speed;
                switch (size) {
                    case Large: {
                        maxEndurance *= CheeseParameter.Fire.ENLarge;
                        break;
                    }
                    case Medium: {
                        maxEndurance *= CheeseParameter.Fire.ENMed;
                        break;
                    }
                    case Small: {
                        maxEndurance *= CheeseParameter.Fire.ENSmall;
                        break;
                    }
                    case Tiny: {
                        maxEndurance *= CheeseParameter.Fire.ENTiny;
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
                speed *= ProjectorParameter.BoardSpeed;
                break;
            }
            case Slide: {
                speed *= ProjectorParameter.SlideSpeed;
                break;
            }
            case Cannon: {
                speed *= ProjectorParameter.CannonSpeed;
                break;
            }
            case Rocket: {
                speed *= ProjectorParameter.RocketSpeed;
                break;
            }
        }

        x = projector.getCheeseX(prepareD, radix, whichSide);
        y = projector.getCheeseY(prepareD, radix, whichSide);

    }

    public float getNormalDamage() {
        switch (size) {
            case Large: {
                return CheeseParameter.Normal.DamageLarge;
            }
            case Medium: {
                return CheeseParameter.Normal.DamageMed;
            }
            case Small: {
                return CheeseParameter.Normal.DamageSmall;
            }
            case Tiny: {
                return CheeseParameter.Normal.DamageTiny;
            }
        }
        return 0.0F;
    }

    public float getPoisonDamage() {
        switch (size) {
            case Large: {
                return CheeseParameter.Poison.DamageLarge;
            }
            case Medium: {
                return CheeseParameter.Poison.DamageMed;
            }
            case Small: {
                return CheeseParameter.Poison.DamageTiny;
            }
            case Tiny: {
                return CheeseParameter.Poison.DamageSmall;
            }
        }
        return 0.0F;
    }

    public short getPoisonMaxCount() {
        switch (size) {
            case Large: {
                return CheeseParameter.Poison.PoisonLargeCount;
            }
            case Medium: {
                return CheeseParameter.Poison.PoisonMedCount;
            }
            case Small: {
                return CheeseParameter.Poison.PoisonSmallCount;
            }
            case Tiny: {
                return CheeseParameter.Poison.PoisonSmallCount;
            }
        }
        return 0;
    }

    public float getSweatyRange() {
        switch (size) {
            case Large: {
                return CheeseParameter.Sweat.RangeLarge;
            }
            case Medium: {
                return CheeseParameter.Sweat.RangeMed;
            }
            case Small: {
                return CheeseParameter.Sweat.RangeSmall;
            }
            case Tiny: {
                return CheeseParameter.Sweat.RangeTiny;
            }
        }
        return 0.0F;
    }

    public float getSweatyDamage() {
        switch (size) {
            case Large: {
                return CheeseParameter.Sweat.DamageLarge;
            }
            case Medium: {
                return CheeseParameter.Sweat.DamageMed;
            }
            case Small: {
                return CheeseParameter.Sweat.DamageTiny;
            }
            case Tiny: {
                return CheeseParameter.Sweat.DamageSmall;
            }
        }
        return 0.0F;
    }

    public float getFiringDamage() {
        switch (size) {
            case Large: {
                return CheeseParameter.Fire.DamageLarge;
            }
            case Medium: {
                return CheeseParameter.Fire.DamageMed;
            }
            case Small: {
                return CheeseParameter.Fire.DamageTiny;
            }
            case Tiny: {
                return CheeseParameter.Fire.DamageSmall;
            }
        }
        return 0.0F;
    }

    public float getMaxEndurance() {
        return maxEndurance;
    }

    // public float getUpperLeftX() {// for occqoo
    // return (x - radix);
    // }
    //
    // public float getUpperLeftY() {// for occqoo
    // return (y - radix);
    // }

    public void setPoisonAmount(short poisonAmount) {
        if (poisonAmount > this.poisonAmount)
            this.poisonAmount = poisonAmount;
    }

    public short getPoisonAmount() {
        return poisonAmount;
    }

    public void recoverPoison() {
        // for poison
        if (poisonAmount == 0) {
            isPoison = false;
            poisonState = 0;
        }
    }

    public void poisonDamage() {
        // for poison
        if (isPoison) {
            if (poisonAmount <= CheeseParameter.Poison.PoisonSmallCount) {
                endurance -= CheeseParameter.Poison.PoisonDecreSmall;
                poisonState = 1;
            } else if (poisonAmount <= CheeseParameter.Poison.PoisonMedCount) {
                endurance -= CheeseParameter.Poison.PoisonDecreMed;
                poisonState = 2;
            } else {
                endurance -= CheeseParameter.Poison.PoisonDecreLarge;
                poisonState = 3;
            }
        }
    }

    public void decrePoisonAmount() {
        // for poison
        if (poisonAmount > 0)
            poisonAmount--;
    }

    // public void refreshAngle(float d) {
    public void refreshAngle() {
        // angleChangePerSec = 2pi/360 * X * radix =
        // CheeseParameter.Normal.DistancePerSec;
        // float delta = (d / radix) / 57.296F;
        float delta = (speed / radix) / 57.296F;
        spinAngle += ((short) Math.round(delta));
        spinAngle %= 360;
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

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isPoison() {
        return isPoison;
    }

    public void setPoison(boolean isPoison) {
        this.isPoison = isPoison;
    }

    public void setPoisonState(byte poisonState) {
        this.poisonState = poisonState;
    }

    public byte getPoisonState() {
        return poisonState;
    }

    public void resetFireState() {
        this.fireState = 0;
    }

    public void setOnFire(byte fireState) {
        if (fireState > this.fireState)
            this.fireState = fireState;
    }

    public byte getFireState() {
        return fireState;
    }

    public void checkDead() {
        if (endurance <= 0)
            this.isDead = true;
    }

    public boolean checkCrowd(LinkedList<Cheese> list) {
        int size = list.size();
        if (size == 0) {
            return false;
        } else {
            Cheese c = list.get(size-1);
            if (Cheese.distance(this, c) >= (this.getRadix() + c.getRadix()))
                return false;
            return true;
        }
    }

    // useful for the only cheese at front-line
    public void moveByDistance(float d, boolean whichSide, Projector p) {
        if (!joinBattle) {
            float accumD = d + prepareD;
            float exceed = p.exceedAmount(accumD, radix);
            if (exceed < 0) {
                prepareD += d;
                x = p.getCheeseX(accumD, radix, whichSide);
                y = p.getCheeseY(accumD, radix, whichSide);
            } else {
                joinBattle = true;
                x = p.getBattleCheeseX(exceed, radix, whichSide);
                y = radix;
            }
        } else {
            if (whichSide) {
                x += d;
            } else {
                x -= d;
            }
        }
    }

    // useful for the only cheese at front-line
    public void backByDistance(float d, boolean whichSide, Projector p) {
        if (!joinBattle) {
            float accumD = prepareD - d;
            x = p.getCheeseX(accumD, radix, whichSide);
            y = p.getCheeseY(accumD, radix, whichSide);
        } else {
            if (whichSide) {
                x -= d;
                float exceed = (x - p.getBattleBorderX(d, whichSide));
                if (exceed < 0.0F) {
                    joinBattle = false;
                    float maxPrepareD = p.getMaxPrepareD(radix);
                    prepareD = maxPrepareD + exceed;
                    x = p.getCheeseX(prepareD, radix, whichSide);
                    y = p.getCheeseY(prepareD, radix, whichSide);
                }
            } else {
                x += d;
                float exceed = (p.getBattleBorderX(d, whichSide) - x);
                if (exceed < 0.0F) {
                    joinBattle = false;
                    float maxPrepareD = p.getMaxPrepareD(radix);
                    prepareD = maxPrepareD + exceed;
                    x = p.getCheeseX(prepareD, radix, whichSide);
                    y = p.getCheeseY(prepareD, radix, whichSide);
                }
            }
        }
    }

    public static float distance(Cheese a, Cheese b) {
        float dx = a.x - b.x;
        float dy = a.y - b.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public void setBump(boolean isBump) {
        this.isBump = isBump;
    }

    public boolean isBump() {
        return isBump;
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

