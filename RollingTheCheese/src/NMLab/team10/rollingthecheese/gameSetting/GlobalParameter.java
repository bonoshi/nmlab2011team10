package NMLab.team10.rollingthecheese.gameSetting;

public class GlobalParameter {
    public static final int TimePerDay = 60000;// 60sec = 60000ms
    public static final int FramePeriod = 30;// 30ms

    public static final int HouseMaxHp = 2000;

    public static final boolean isNight(int time){
        return (time%TimePerDay > 29999);
    }
}

