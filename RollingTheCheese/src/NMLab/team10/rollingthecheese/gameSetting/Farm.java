package NMLab.team10.rollingthecheese.gameSetting;

public class Farm {
    public Farm() {
        this.setProd(MilkProdType.Grazing);
    }
    public MilkProdType getProd() {
        return prod;
    }

    public void setProd(MilkProdType prod) {
        this.prod = prod;
    }
    MilkProdType prod;
}

class FarmParameter{
    static final int TimeInterval = 10000;//ms
}