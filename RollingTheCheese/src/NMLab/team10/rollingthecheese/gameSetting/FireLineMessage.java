package NMLab.team10.rollingthecheese.gameSetting;

public class FireLineMessage {

    public FireLineMessage(FireLine f) {
        type = f.getType();
        startX = f.getStartX();
        length = f.getLength();
    }

    public byte getType() {// Small, Medium, Large
        return type;
    }
    public float getStartX() {
        return startX;
    }
    public int getLength() {
        return length;
    }

    private byte type;
    private float startX;
    private int length;
}
