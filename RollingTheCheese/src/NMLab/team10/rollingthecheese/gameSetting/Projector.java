package NMLab.team10.rollingthecheese.gameSetting;

class ProjectorParameter {

    static final int TimeInterval = 10000;//ms

    static class Board {
        static final float offset;// pixel
        static final float drawX;
        static final float drawY;
        static final float startX;
        static final float startY;
        static final float endX;
        static final float endY;
        static final float angelX;
        static final float angelY;
        static final float length;
        static final int upgradeMilk;
        // static final float point1X = 70;
        // static final float point1Y = 92;
        static {
            offset = 202;
            drawX = 110;
            drawY = 92;
            startX = 110;
            startY = 92;
            endX = 202;
            endY = 0;
            angelX = 0.3825F;
            angelY = 0.9239F;
            length = (1.414F * (endY - endX));
            upgradeMilk = 1000;
        }

        static float exceedAmount(float d, float radix) {
            float exceed = (float) (d - (length - radix / 1.414F));
            return exceed;
        }

        static float getCheeseX(float d, float radix) {
            float x = startX + d / 1.414F + radix / 1.414F;
            return x;
        }

        static float getCheeseY(float d, float radix) {
            float y = startY - d / 1.414F + radix / 1.414F;
            return y;
        }
    }
}

public class Projector {
    public Projector(boolean whichSide) {
        this.setType(Board);
        this.setOwner(whichSide);
    }

    public synchronized float exceedAmount(float d, float radix) {
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.exceedAmount(d, radix));
            default:// no use
                return 0F;
        }
    }

    public synchronized float getCheeseX(float d, float radix) {
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.getCheeseX(d, radix));
            default:// no use
                return 0F;
        }
    }

    public synchronized float getCheeseY(float d, float radix) {
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.getCheeseY(d, radix));
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


    public synchronized float getUpperLeftX(){//for occqoo
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.drawX);
            default:// no use
                return 0F;
        }
    }

    public synchronized float getUpperLeftY(){//for occqoo
        switch (type) {
            case Board:
                return (ProjectorParameter.Board.drawY);
            default:// no use
                return 0F;
        }
    }

    public synchronized int upgradeMilk() {
        switch (type) {
            case Board:
                return ProjectorParameter.Board.upgradeMilk;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public synchronized String upgradeMilkText() {//for occqoo
        switch (type) {
            case Board:
                return Integer.toString(ProjectorParameter.Board.upgradeMilk);
            default:// no use
                return "MAX";
        }
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

    protected byte type;
    private boolean owner;

    public static final byte Board = ProjectorEnum.Board;
    public static final byte Slide = ProjectorEnum.Slide;
    public static final byte Cannon = ProjectorEnum.Cannon;
    public static final byte Rocket = ProjectorEnum.Rocket;

    public static final boolean Right = false;
    public static final boolean Left = true;
}
