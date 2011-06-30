package NMLab.team10.rollingthecheese.gameSetting;

import NMLab.team10.rollingthecheese.byteEnum.CheeseProdEnum;
import NMLab.team10.rollingthecheese.byteEnum.CheeseQualityEnum;

public class House {
    public House() {
        this.setProd(CheeseProdEnum.ForFun);
        this.setQual(CheeseQualityEnum.Handmade);
        this.setHP(HouseParameter.ForFunHP);
        this.setHPPercent((byte) 100);
    }

    public void upgradeProd() {
        switch (prod) {
            case ForFun: {
                this.prod = AfterHours;
                HP += (HouseParameter.AfterHoursHP - HouseParameter.ForFunHP);
//                HP = (int) Math.ceil(getHPPercent() * getHouseMaxHP() / 100.0);
                break;
            }
            case AfterHours:
                this.prod = Bakery;
                HP += (HouseParameter.BakeryHP - HouseParameter.AfterHoursHP);
//                HP = (int) Math.ceil(getHPPercent() * getHouseMaxHP() / 100.0);
                break;
            case Bakery:
                this.prod = FoodFactory;
                HP += (HouseParameter.FoodFactoryHP - HouseParameter.BakeryHP);
//                HP = (int) Math.ceil(getHPPercent() * getHouseMaxHP() / 100.0);
                break;
            case FoodFactory:
                break;
            default:// no use
                break;
        }
    }

    public synchronized boolean canUpgradeProd() {
        return !(prod == FoodFactory);
    }

    public int getUpProdMilk() {
        switch (prod) {
            case ForFun:
                return HouseParameter.AfterHoursMilk;
            case AfterHours:
                return HouseParameter.BakeryMilk;
            case Bakery:
                return HouseParameter.FoodFactoryMilk;
            case FoodFactory:
                return Integer.MAX_VALUE;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public String getUpProdMilkText() {
        switch (prod) {
            case ForFun:
                return Integer.toString(HouseParameter.AfterHoursMilk);
            case AfterHours:
                return Integer.toString(HouseParameter.BakeryMilk);
            case Bakery:
                return Integer.toString(HouseParameter.FoodFactoryMilk);
            case FoodFactory:
                return "MAX";
            default:// no use
                return "NOUSE";
        }
    }

    public int getUpProdTime() {
        switch (prod) {
            case ForFun:
                return HouseParameter.AfterHoursTime;
            case AfterHours:
                return HouseParameter.BakeryTime;
            case Bakery:
                return HouseParameter.FoodFactoryTime;
            case FoodFactory:
                return Integer.MAX_VALUE;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public float getHouseMaxHP() {
        switch (prod) {
            case ForFun:
                return HouseParameter.ForFunHP;
            case AfterHours:
                return HouseParameter.AfterHoursHP;
            case Bakery:
                return HouseParameter.BakeryHP;
            case FoodFactory:
                return HouseParameter.FoodFactoryHP;
            default:// no use
                return 0;
        }
    }

    public static float getHouseMaxHP(byte prod) {
        switch (prod) {
            case ForFun:
                return HouseParameter.ForFunHP;
            case AfterHours:
                return HouseParameter.AfterHoursHP;
            case Bakery:
                return HouseParameter.BakeryHP;
            case FoodFactory:
                return HouseParameter.FoodFactoryHP;
            default:// no use
                return 0;
        }
    }

    public byte getProd() {
        return prod;
    }

    public void setProd(byte prod) {
        this.prod = prod;
    }

    public void upgradeQual() {
        switch (qual) {
            case Handmade:
                this.qual = CheeseMold;
                break;
            case CheeseMold:
                this.qual = FoodChemisty;
                break;
            case FoodChemisty:
                this.qual = GMO;
                break;
            case GMO:
                break;
            default:// no use
                break;
        }
    }

    public synchronized boolean canUpgradeQual() {
        return !(qual == GMO);
    }

    public int getUpQualMilk() {
        switch (qual) {
            case Handmade:
                return HouseParameter.CheeseMoldMilk;
            case CheeseMold:
                return HouseParameter.FoodChemistyMilk;
            case FoodChemisty:
                return HouseParameter.GMOMilk;
            case GMO:
                return Integer.MAX_VALUE;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public String getUpQualMilkText() {
        switch (qual) {
            case Handmade:
                return Integer.toString(HouseParameter.CheeseMoldMilk);
            case CheeseMold:
                return Integer.toString(HouseParameter.FoodFactoryMilk);
            case FoodChemisty:
                return Integer.toString(HouseParameter.GMOMilk);
            case GMO:
                return "MAX";
            default:// no use
                return "NOUSE";
        }
    }

    public int getUpQualTime() {
        switch (qual) {
            case Handmade:
                return HouseParameter.CheeseMoldTime;
            case CheeseMold:
                return HouseParameter.FoodChemistyTime;
            case FoodChemisty:
                return HouseParameter.GMOTime;
            default:// no use
                return 0;
        }
    }

    public byte getQual() {
        return qual;
    }

    public void setQual(byte qual) {
        this.qual = qual;
    }

    // public void setOwner(boolean owner) {
    // this.owner = owner;
    // }
    //
    // public boolean isOwnerLeft() {// for occqoo
    // return owner;
    // }

    public void setHP(float hP) {
        HP = hP;
    }

    public float getHP() {
        return HP;
    }

    public void decreHP(float a) {
        HP -= a;
        if (HP < 0)
            HP = 0;
        HPPercent = (byte) Math.ceil(100.0 * HP / getHouseMaxHP());
    }

    public void setHPPercent(byte hPPercent) {
        HPPercent = hPPercent;
    }

    public byte getHPPercent() {
        return HPPercent;
    }

    private byte prod;
    private byte qual;
    private byte HPPercent;
    private float HP;
    private boolean isBump;
    // private boolean isSweat;

    // private boolean owner;

    public static final byte ForFun = CheeseProdEnum.ForFun;
    public static final byte AfterHours = CheeseProdEnum.AfterHours;
    public static final byte Bakery = CheeseProdEnum.Bakery;
    public static final byte FoodFactory = CheeseProdEnum.FoodFactory;

    public static final byte Handmade = CheeseQualityEnum.Handmade;
    public static final byte CheeseMold = CheeseQualityEnum.CheeseMold;
    public static final byte FoodChemisty = CheeseQualityEnum.FoodChemisty;
    public static final byte GMO = CheeseQualityEnum.GMO;

    public float getBoader(boolean whichSide) {
        switch (prod) {
            case ForFun:
                if (whichSide) {
                    return HouseParameter.ForFunBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.ForFunBorder);
                }
            case AfterHours:
                if (whichSide) {
                    return HouseParameter.AfterHoursBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.AfterHoursBorder);
                }
            case Bakery:
                if (whichSide) {
                    return HouseParameter.BakeryBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.BakeryBorder);
                }
            case FoodFactory:
                if (whichSide) {
                    return HouseParameter.FoodFactoryBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.FoodFactoryBorder);
                }
            default:// no use
                return 0.0F;
        }
    }

    public static float getBoader(boolean whichSide, byte prod) {
        switch (prod) {
            case CheeseProdEnum.ForFun:
                if (whichSide) {
                    return HouseParameter.ForFunBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.ForFunBorder);
                }
            case CheeseProdEnum.AfterHours:
                if (whichSide) {
                    return HouseParameter.AfterHoursBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.AfterHoursBorder);
                }
            case CheeseProdEnum.Bakery:
                if (whichSide) {
                    return HouseParameter.BakeryBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.BakeryBorder);
                }
            case CheeseProdEnum.FoodFactory:
                if (whichSide) {
                    return HouseParameter.FoodFactoryBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.FoodFactoryBorder);
                }
            default:// no use
                return 0.0F;
        }
    }

    public float getBoader(boolean whichSide, float radix) {
        switch (prod) {
            case ForFun:
                if (whichSide) {
                    return HouseParameter.ForFunBorder + 0.25679F * radix;
                    //return HouseParameter.ForFunBorder + 0.257F * radix;
                } else {
                    return (GlobalParameter.MapWidth - (HouseParameter.ForFunBorder + 0.25679F * radix));
                }
            case AfterHours:
                if (whichSide) {
                    return HouseParameter.AfterHoursBorder + 0.25679F * radix;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.AfterHoursBorder);
                }
            case Bakery:
                if (whichSide) {
                    return HouseParameter.BakeryBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.BakeryBorder);
                }
            case FoodFactory:
                if (whichSide) {
                    return HouseParameter.FoodFactoryBorder;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.FoodFactoryBorder);
                }
            default:// no use
                return 0.0F;
        }
    }

    public void setBump(boolean isBump) {
        this.isBump = isBump;
    }

    public boolean isBump() {
        return isBump;
    }

    // public void setSweat(boolean isSweat) {
    // this.isSweat = isSweat;
    // }
    //
    // public boolean isSweat() {
    // return isSweat;
    // }

    public static HouseParameter P;
}
