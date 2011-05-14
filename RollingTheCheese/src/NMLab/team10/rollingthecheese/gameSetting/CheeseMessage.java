package NMLab.team10.rollingthecheese.gameSetting;

import java.io.Serializable;

public class CheeseMessage implements Serializable{
    private static final long serialVersionUID = 879432701639638072L;
    
    public CheeseMessage(Cheese c) {
        ID = c.getID();
        type = c.getType();
        size = c.getSize();
        HPPercent = (byte) Math.ceil(100.0 * c.getEndurance() / c.getMaxEndurance());
        spinAngle = c.getSpinAngle();
        x = c.x;
        y = c.y;
        this.setFire(c.getFireState());
        this.setPoison(c.getPoisonState());
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
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public short getSpinAngle(){
        return spinAngle;
    }

    public void setID(short iD) {
        ID = iD;
    }

    public short getID() {
        return ID;
    }

    public void setFire(byte f){
        badState &= ~FireIndex;
        badState |= f;
    }

    public void setPoison(byte p){
        badState &= ~PoisonIndex;
        badState |= p<<2;
    }

    public void setIsBump(boolean b){
        badState &= ~BumpIndex;
        if(b){
            badState |= BumpIndex;
        }
    }

    private short ID;

    private byte type;
    private byte size;
    private byte HPPercent;
    private short spinAngle;
    private float x;// x for drawing
    private float y;
    //private byte animation;//poison, on fire, dying animation, each has four picture
    private byte badState;

    //for bad state
    public static final byte FireIndex = ((byte) 3);
    public static final byte PoisonIndex = ((byte) 3) << 2;
    public static final byte BumpIndex = ((byte) 1) << 4;
}
