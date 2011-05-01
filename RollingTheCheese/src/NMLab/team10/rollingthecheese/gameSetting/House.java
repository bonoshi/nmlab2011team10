package NMLab.team10.rollingthecheese.gameSetting;

public class House {
    public House() {
        this.setProd(CheeseProdEnum.ForFun);
        this.setQual(CheeseQualityEnum.Handmade);
    }
    public byte getProd() {
        return prod;
    }
    public void setProd(byte prod) {
        this.prod = prod;
    }
    public byte getQual() {
        return qual;
    }
    public void setQual(byte qual) {
        this.qual = qual;
    }

    byte prod;
    byte qual;

}


class HouseParameter {
    static final int TimeInterval = 10000;//ms
}
