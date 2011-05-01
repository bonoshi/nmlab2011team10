package NMLab.team10.rollingthecheese.gameSetting;

public class Farm {
    public Farm() {
        this.setProd(MilkProdEnum.Grazing);
    }
    public byte getProd() {
        return prod;
    }

    public void setProd(byte prod) {
        this.prod = prod;
    }
    byte prod;
}

class FarmParameter{
    static final int TimeInterval = 10000;//ms
}