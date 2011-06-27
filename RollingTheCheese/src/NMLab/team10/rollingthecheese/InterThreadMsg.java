package NMLab.team10.rollingthecheese;



public class InterThreadMsg {
    static public final int startEasyGame = 0;
    static public final int startNormalGame = 1;
    static public final int startHardGame = 2;
    static public final int startCrazyGame = 3;
    static public final int clientConnect = 4;
    static public final int connectSuccess = 5;
    static public final int connectFail = 6;

    static public final int serverWait = 7;
    static public final int waitSuccess = 8;
    static public final int waitExceed = 9;

    static public final int LinkingErrorInGame = 10;
    static public final int endGame = 11;
    static public final int ToastDisplay = 13;
    static public final int ToastClose = 14;

    static public final int ServerFailToSend = 15;
    static public final int OptionalDataException = 16;
    static public final int ClassNotFoundException= 17;
    static public final int IOException= 18;
}
