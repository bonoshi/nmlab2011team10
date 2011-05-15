package NMLab.team10.rollingthecheese.gameSetting;

import java.io.Serializable;

public class AppMessage implements Serializable{

    private static final long serialVersionUID = -6438328658395438351L;

    public AppMessage() {
        smd = null;
    }

    private byte type;
    private SynMessageData smd;

    public void setType(byte type) {
        this.type = type;
    }
    public byte getType() {
        return type;
    }
    public void setSmd(SynMessageData smd) {
        this.smd = smd;
    }
    public SynMessageData getSmd() {
        return smd;
    }

}