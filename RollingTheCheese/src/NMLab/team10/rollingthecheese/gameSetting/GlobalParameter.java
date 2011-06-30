package NMLab.team10.rollingthecheese.gameSetting;

public class GlobalParameter {
    public static final int TimePerDay = 60000;// 60sec = 60000ms
    public static final int FramePeriod = 40;// 30ms

    public static final float BumpOffset = 0.2F;
    public static final float FollowOffset = 2.0F;

    public static final int initialMilk = 10000;

    public static final int HouseMaxHp = 2000;

    public static final float MapWidth = 1600.0F;
    public static final float MapHeight = 480.0F;

    public static final boolean isNight(int time){
        return (time%TimePerDay > 40000);
    }

    public static final int RefreshPaintCount = 5;

    public static final int Morning = 10000;
    public static final int Noon = 25000;
    public static final int Dusk = 30000;
    public static final int Night = 40000;

    public static final float Night2Morning = 10000;
    public static final float Morn2Noon = 15000;
    public static final float Noon2Dusk = 5000;
    public static final float Dusk2Night = 10000;

}

