package NMLab.team10.rollingthecheese.gameSetting;

import java.io.Serializable;

public class FireLineMessage implements Serializable{
    private static final long serialVersionUID = -7131570211375105138L;
    
    public FireLineMessage(FireLine f) {
        ID = f.getID();
        type = f.getType();
        startX = f.getStartX();
        setEndX(f.getEndX());
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
