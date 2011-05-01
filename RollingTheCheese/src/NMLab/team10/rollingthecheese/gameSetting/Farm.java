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

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isOwnerLeft() {// for occqoo
        return owner;
    }

    byte prod;
    private boolean owner;
}

class FarmParameter {
    static final int TimeInterval = 10000;// ms
}