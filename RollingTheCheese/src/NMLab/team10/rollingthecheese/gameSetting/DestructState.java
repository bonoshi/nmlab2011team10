package NMLab.team10.rollingthecheese.gameSetting;

public class DestructState {

    public DestructState() {
        // TODO Auto-generated constructor stub
    }

    public boolean fense = false;
    public boolean power = false;
    public boolean smallCheese = false;
    public boolean slowCheese = false;// produce slower
    public boolean milk = false;

    public boolean fenseTriggered = false;
    public boolean powerTriggered = false;
    public boolean smallCheeseTriggered = false;
    public boolean slowCheeseTriggered = false;// produce slower
    public boolean milkTriggered = false;

    public int fenseCountDown = 0;
    public int powerCountDown = 0;
    public int smallCheeseCountDown = 0;
    public int slowCheeseCountDown = 0;
    public int milkCountDown = 0;

}
