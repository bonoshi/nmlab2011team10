package NMLab.team10.rollingthecheese.gameSetting;

public class CowDisplay {

    public CowDisplay(Cow c) {
        status = c.getStatus();
        x = c.getUpperLeftX();
        y = c.getUpperLeftY();
        animation = c.getAnimation();
        headLeft = c.isHeadLeft();
    }
    public byte getStatus() {
        return status;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public byte getAnimation() {
        return animation;
    }
    public boolean isHeadLeft() {
        return headLeft;
    }
    private byte status;
    private float x;//x for drawing
    private float y;
    private byte animation;//animation
    private boolean headLeft;//direction
}
