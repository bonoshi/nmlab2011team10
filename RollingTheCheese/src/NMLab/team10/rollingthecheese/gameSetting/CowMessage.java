package NMLab.team10.rollingthecheese.gameSetting;

public class CowMessage {

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
