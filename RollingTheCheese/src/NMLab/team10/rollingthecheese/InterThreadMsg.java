package NMLab.team10.rollingthecheese;



public class InterThreadMsg {
    static public final int startGameView=0;
    static public final int MESSAGE_DEVICE_NAME = 1;
    static public final int ConnectionLost=2;//connection lost during the game
    static public final int ConnectionFail=3;//connection fail when initialed
    static public final int MESSAGE_READ=4;
    static public final int MESSAGE_WRITE=5;
    static public final int MESSAGE_STATE_CHANGE=6;
    static public final int scan = 7;
    static public final int discoverable = 8;
    static public final int LinkingErrorInGame = 9;
}
