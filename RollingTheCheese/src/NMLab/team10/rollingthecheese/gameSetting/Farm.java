package NMLab.team10.rollingthecheese.gameSetting;

import java.io.Serializable;

import NMLab.team10.rollingthecheese.byteEnum.MilkProdEnum;

public class Farm implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -1675786272926054905L;

    public Farm() {
        this.setProd(MilkProdEnum.Grazing);
    }

    public void upgradeProd() {
        switch (prod) {
            case Grazing:
                this.prod = Husbandry;
                break;
            case Husbandry:
                this.prod = Mechanization;
                break;
            case Mechanization:
                this.prod = Hormone;
                break;
            case Hormone:
                break;
            default:// no use
                break;
        }
    }
    public synchronized boolean canUpgrade(){
        return !(prod==Hormone);
    }

    public int getUpProdMilk() {
        switch (prod) {
            case Grazing:
                return FarmParameter.HusbandryMilk;
            case Husbandry:
                return FarmParameter.MechanizationMilk;
            case Mechanization:
                return FarmParameter.HormoneMilk;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public String getUpProdMilkText() {
        switch (prod) {
            case Grazing:
                return Integer.toString(FarmParameter.HusbandryMilk);
            case Husbandry:
                return Integer.toString(FarmParameter.MechanizationMilk);
            case Mechanization:
                return Integer.toString(FarmParameter.HormoneMilk);
            case Hormone:
                return "MAX";
            default:// no use
                return "NOUSE";
        }
    }

    public int getUpProdTime() {
        switch (prod) {
            case Grazing:
                return FarmParameter.HusbandryTime;
            case Husbandry:
                return FarmParameter.MechanizationTime;
            case Mechanization:
                return FarmParameter.HormoneTime;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public byte getProd() {
        return prod;
    }

    public void setProd(byte prod) {
        this.prod = prod;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isOwnerLeft() {// for occqoo
        return owner;
    }

    public Farm clone() {
        Farm f = new Farm();
        f.setProd(prod);
        f.setOwner(owner);
        return f;
    }

    byte prod;
    private boolean owner;

    public static final byte Grazing = MilkProdEnum.Grazing;
    public static final byte Husbandry = MilkProdEnum.Husbandry;
    public static final byte Mechanization = MilkProdEnum.Mechanization;
    public static final byte Hormone = MilkProdEnum.Hormone;

    public static final float GrazingRatio = 1.0F;
    public static final float HusbandryRatio = 1.8F;
    public static final float MechanizationRatio = 3.0F;
    public static final float HormoneRatio = 4.0F;

    public static FarmParameter P;
}
