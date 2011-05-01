package NMLab.team10.rollingthecheese.gameSetting;

public class CheeseDisplay {
    public CheeseDisplay(Cheese c) {
        type = c.getType();
        size = c.getSize();
        HPPercent = (byte) Math.round(100F * c.getEndurance() / c.getMaxEndurance());
        if (HPPercent == 0 && c.getEndurance() > 0.0F)
            HPPercent = 1;
        spinAngle = c.getSpinAngle();
        x = c.getUpperLeftX();
        y = c.getUpperLeftY();
    }

    public byte getType() {//Normal, Casumarzu, Sweaty, Firing
        return type;
    }
    public byte getSize() {// Large, Medium, Small, Tiny
        return size;
    }
    public int getHPPercent() {
        return HPPercent;
    }
    public short getSpinAngle() {
        return spinAngle;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    private byte type;
    private byte size;
    private byte HPPercent;
    private short spinAngle;
    private float x;// x for drawing
    private float y;
    // public animation
}
