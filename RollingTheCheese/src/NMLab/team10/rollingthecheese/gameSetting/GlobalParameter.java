package NMLab.team10.rollingthecheese.gameSetting;

public class GlobalParameter {
    public static final int TimePerDay = 60000;// 60sec = 60000ms
    public static final int TimeHalfDay = 30000;// 60sec = 60000ms
    public static final int FramePeriod = 30;// 30ms

    public static final float BumpOffset = 0.2F;
    public static final float FollowOffset = 2.0F;

    public static final int initialMilk = 10000;

    public static final int HouseMaxHp = 2000;

    public static final float MapWidth = 1600.0F;
    public static final float MapHeight = 480.0F;

    public static final boolean isNight(int time){
        return (time%TimePerDay > TimeHalfDay);
    }

    public static byte getTimePeriod(int time){
        if(time>30000){
            if(time>35000){
                return Night;
            }else{
                return Dusk;
            }
        }else{
            if(time>20000){
                return Morning;
            }else if(time>10000){
                return Noon;
            }
            else{
                return Morning;
            }
        }
    }
    
    public static final byte Morning = 0;
    public static final byte Noon = 1;
    public static final byte Dusk = 2;
    public static final byte Night = 3;
}

