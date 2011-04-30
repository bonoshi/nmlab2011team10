package NMLab.team10.rollingthecheese.gameSetting;

public class House {
    public House() {
        this.setProd(CheeseProdType.ForFun);
        this.setQual(CheeseQualityType.Handmade);
    }
    public CheeseProdType getProd() {
        return prod;
    }
    public void setProd(CheeseProdType prod) {
        this.prod = prod;
    }
    public CheeseQualityType getQual() {
        return qual;
    }
    public void setQual(CheeseQualityType qual) {
        this.qual = qual;
    }

    CheeseProdType prod;
    CheeseQualityType qual;

}


class HouseParameter {
    static final int TimeInterval = 10000;//ms
}
