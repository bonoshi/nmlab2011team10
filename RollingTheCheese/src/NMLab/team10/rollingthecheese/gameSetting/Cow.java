package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

public class Cow {// have display light type

    public Cow(boolean whichSide) {
        LinkedList<Short> idList = (whichSide) ? LeftID : RightID;
        synchronized (idList) {
            ID = idList.getFirst();
            idList.remove();
        }
    }

    public static void cowDie(Cow cow, boolean whichSide) {
        LinkedList<Short> idList = (whichSide) ? LeftID : RightID;
        synchronized (idList) {
            idList.add(new Short(cow.ID));
        }
    }

    private static LinkedList<Short> LeftID = null;// the left ID that can be
                                                   // allocated
    private static LinkedList<Short> RightID = null;

    public static void initial() {
        LeftID = new LinkedList<Short>();
        RightID = new LinkedList<Short>();
        for (short i = 0; i < CowParameter.MaxCow; i++) {
            LeftID.add(i);
            RightID.add(i);
        }
    }

    private short ID;

    public short getID() {
        return ID;
    }

    private byte status = Normal;

    // private float x;// central x
    // private float y;
    // private int amount;// throughput
    // private int interval;// time interval;
    // private byte animation;//animation
    // private boolean headLeft;//direction
    // private boolean owner;

    // public float getUpperLeftX() {
    // return x;// bonoshi: size??margin??
    // }
    //
    // public float getUpperLeftY() {
    // return y;//
    // }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getStatus() {
        return status;
    }

    // public void setAmount(int amount) {
    // this.amount = amount;
    // }
    //
    // public int getAmount() {
    // return amount;
    // }
    //
    // public void setInterval(int interval) {
    // this.interval = interval;
    // }
    //
    // public int getInterval() {
    // return interval;
    // }
    //
    // public void setAnimation(byte animation) {
    // this.animation = animation;
    // }
    //
    // public byte getAnimation() {
    // return animation;
    // }
    //
    // public void setHeadLeft(boolean headLeft) {
    // this.headLeft = headLeft;
    // }
    //
    // public boolean isHeadLeft() {
    // return headLeft;
    // }
    //
    // public void setOwner(boolean owner) {
    // this.owner = owner;
    // }
    //
    // public boolean isOwnerLeft() {
    // return owner;
    // }

    public static final byte Normal = CowStatusEnum.NORMAL;
    public static final byte Leak = CowStatusEnum.LEAK;

    public static final boolean Right = false;
    public static final boolean Left = true;
}

class CowStatusEnum {
    public static final byte NORMAL = 0;
    public static final byte LEAK = 1;
}
