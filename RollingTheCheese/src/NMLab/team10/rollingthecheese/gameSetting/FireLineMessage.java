package NMLab.team10.rollingthecheese.gameSetting;

public class FireLineMessage {

    public FireLineMessage(FireLine f, short ID) {
        type = f.getType();
        startX = f.getStartX();
        setEndX(f.getEndX());
        this.ID = ID;
    }

    public byte getType() {// Small, Medium, Large
        return type;
    }
    public float getStartX() {
        return startX;
    }

    public void setID(short iD) {
        ID = iD;
    }

    public short getID() {
        return ID;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndX() {
        return endX;
    }

    private short ID;

    private byte type;
    private float startX;
    private float endX;
}
