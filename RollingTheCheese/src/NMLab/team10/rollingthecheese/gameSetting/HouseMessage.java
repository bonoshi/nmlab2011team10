package NMLab.team10.rollingthecheese.gameSetting;

public class HouseMessage {
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

    private byte prod;
    private byte qual;
    private byte HPPercent;
}
