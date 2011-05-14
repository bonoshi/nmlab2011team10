package NMLab.team10.rollingthecheese.gameSetting;

import java.io.Serializable;

public class CowMessage implements Serializable{
    private static final long serialVersionUID = 3245038001729360189L;

    public CowMessage(Cow c) {
        ID = c.getID();
        status = c.getStatus();
    }

    public byte getStatus() {
        return status;
    }

    public short getID() {
        return ID;
    }

    private short ID;

    private byte status;
//    private float x;//x for drawing
//    private float y;
//    private byte animation;//animation
//    private boolean headLeft;//direction
}
