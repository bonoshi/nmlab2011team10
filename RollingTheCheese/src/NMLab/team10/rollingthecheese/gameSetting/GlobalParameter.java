package NMLab.team10.rollingthecheese.gameSetting;

public class GlobalParameter {
    public static final int TimePerDay = 60000;// 60sec = 60000ms
    public static final int TimeHalfDay = 30000;// 60sec = 60000ms
    public static final int FramePeriod = 30;// 30ms

    public static final float BumpOffset = 5.0F;
    public static final float FollowOffset = 2.0F;

    public static final int initialMilk = 1000;

    public static final int HouseMaxHp = 2000;

    public static final float MapWidth = 1600.0F;
    public static final float MapHeight = 480.0F;

    public static final boolean isNight(int time){
        return (time%TimePerDay > TimeHalfDay);
    }
}

