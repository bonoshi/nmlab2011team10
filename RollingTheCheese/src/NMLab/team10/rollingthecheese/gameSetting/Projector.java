package NMLab.team10.rollingthecheese.gameSetting;

import java.io.Serializable;

import NMLab.team10.rollingthecheese.byteEnum.ProjectorEnum;

public class Projector implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1161120424944559472L;

    public Projector() {
        this.setType(Board);
    }

    public synchronized float exceedAmount(float d, float radix) {
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.exceedAmount(d, radix));
            default:// no use
                return 0F;
        }
    }

    public synchronized float getCheeseX(float d, float radix, boolean whichSide) {
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.getCheeseX(d, radix, whichSide));
            default:// no use
                return 0F;
        }
    }

    public synchronized float getCheeseY(float d, float radix, boolean whichSide) {
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.getCheeseY(d, radix, whichSide));
            default:// no use
                return 0F;
        }
    }

    public synchronized float getBattleCheeseX(float d, float radix, boolean whichSide) {
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.getBattleCheeseX(d, radix, whichSide));
            default:// no use
                return 0F;
        }
    }

    public synchronized float getMaxPrepareD(float radix) {
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.getMaxPrepareD(radix));
            default:// no use
                return 0F;
        }
    }

    public synchronized float getBattleBorderX(float radix, boolean whichSide){
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.getBattleBorderX(radix, whichSide));
            default:// no use
                return 0F;
        }
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isOwnerLeft() {//for occqoo
        return owner;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getType() {//for occqoo
        return type;
    }

    public synchronized int getUpMilk() {
        switch (type) {
            case Board:
                return ProjectorParameter.SlideMilk;
            case Slide:
                return ProjectorParameter.CannonMilk;
            case Cannon:
                return ProjectorParameter.RocketMilk;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public synchronized String getUpMilkText() {//for occqoo
        switch (type) {
            case Board:
                return Integer.toString(ProjectorParameter.SlideMilk);
            case Slide:
                return Integer.toString(ProjectorParameter.CannonMilk);
            case Cannon:
                return Integer.toString(ProjectorParameter.RocketMilk);
            default:// no use
                return "MAX";
        }
    }

    public synchronized int getUpTime() {
        switch (type) {
            case Board:
                return ProjectorParameter.SlideTime;
            case Slide:
                return ProjectorParameter.CannonTime;
            case Cannon:
                return ProjectorParameter.RocketTime;
            default:// no use
                return 0;
        }
    }

    public synchronized boolean canUpgrade(){
        return !(type==Rocket);
    }

    public synchronized void upgrade() {
        switch (type) {
            case Board:
                this.type = ProjectorEnum.Slide;
                break;
            default:// no use
                break;
        }
    }

    public Projector clone(){
        Projector p = new Projector();
        p.setOwner(owner);
        p.setType(type);
        return p;
    }

    protected byte type;
    private boolean owner;

    public static final byte Board = ProjectorEnum.Board;
    public static final byte Slide = ProjectorEnum.Slide;
    public static final byte Cannon = ProjectorEnum.Cannon;
    public static final byte Rocket = ProjectorEnum.Rocket;

    public static final boolean Right = false;
    public static final boolean Left = true;

    public static ProjectorParameter P;
}
