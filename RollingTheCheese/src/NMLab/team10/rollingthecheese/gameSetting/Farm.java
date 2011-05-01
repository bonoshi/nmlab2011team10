package NMLab.team10.rollingthecheese.gameSetting;

public class Farm{
    public Farm() {
        this.setProd(MilkProdEnum.Grazing);
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

    public Farm clone(){
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

    public static FarmParameter P;
}

class FarmParameter {
    static final int TimeInterval = 10000;// ms

    static final int HusbandryMilk = 1000;
    static final int MechanizationMilk = 1000;
    static final int HormoneMilk = 1000;
}