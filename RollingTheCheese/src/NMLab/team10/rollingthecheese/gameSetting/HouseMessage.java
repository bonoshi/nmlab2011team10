package NMLab.team10.rollingthecheese.gameSetting;


import java.io.Serializable;

import NMLab.team10.rollingthecheese.byteEnum.CheeseProdEnum;
import NMLab.team10.rollingthecheese.byteEnum.CheeseQualityEnum;

public class HouseMessage implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 8451845294245848891L;
    
    public HouseMessage(House h) {
        prod = h.getProd();
        qual = h.getQual();
        HPPercent = h.getHPPercent();
    }

    public void setProd(byte prod) {
        this.prod = prod;
    }

    public byte getProd() {
        return prod;
    }

    public void setQual(byte qual) {
        this.qual = qual;
    }

    public byte getQual() {
        return qual;
    }

    public void setHPPercent(byte hPPercent) {
        HPPercent = hPPercent;
    }

    public byte getHPPercent() {
        return HPPercent;
    }

    public float getSmokeX(boolean whichSide) {
        switch (prod) {
            case ForFun:
                if (whichSide) {
                    return HouseParameter.ForFunSmokeX;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.ForFunSmokeX);
                }
            case AfterHours:
                if (whichSide) {
                    return HouseParameter.AfterHoursSmokeX;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.AfterHoursSmokeX);
                }
            case Bakery:
                if (whichSide) {
                    return HouseParameter.BakerySmokeX;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.BakerySmokeX);
                }
            case FoodFactory:
                if (whichSide) {
                    return HouseParameter.FoodFactorySmokeX;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.FoodFactorySmokeX);
                }
            default:// no use
                return 0.0F;
        }
    }

    public float getSmokeY(boolean whichSide) {
        switch (prod) {
            case ForFun:
                return HouseParameter.ForFunSmokeY;
            case AfterHours:
                if (whichSide) {
                    return HouseParameter.AfterHoursSmokeY;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.AfterHoursSmokeY);
                }
            case Bakery:
                if (whichSide) {
                    return HouseParameter.BakerySmokeY;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.BakerySmokeY);
                }
            case FoodFactory:
                if (whichSide) {
                    return HouseParameter.FoodFactorySmokeY;
                } else {
                    return (GlobalParameter.MapWidth - HouseParameter.FoodFactorySmokeY);
                }
            default:// no use
                return 0.0F;
        }
    }

    private byte prod;
    private byte qual;
    private byte HPPercent;

    public static final byte ForFun = CheeseProdEnum.ForFun;
    public static final byte AfterHours = CheeseProdEnum.AfterHours;
    public static final byte Bakery = CheeseProdEnum.Bakery;
    public static final byte FoodFactory = CheeseProdEnum.FoodFactory;

    public static final byte Handmade = CheeseQualityEnum.Handmade;
    public static final byte CheeseMold = CheeseQualityEnum.CheeseMold;
    public static final byte FoodChemisty = CheeseQualityEnum.FoodChemisty;
    public static final byte GMO = CheeseQualityEnum.GMO;

}
