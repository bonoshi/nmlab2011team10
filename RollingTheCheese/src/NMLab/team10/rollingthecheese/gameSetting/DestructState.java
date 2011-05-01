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
    public boolean fenseDisplay = false;
    public boolean powerDisplay = false;
    public boolean smallCheeseDisplay = false;
    public boolean slowCheeseDisplay = false;// produce slower
    public boolean milkDisplay = false;

    public DestructButton clone(){
        DestructButton d = new DestructButton();
        d.fenseDisplay = fenseDisplay;
        d.powerDisplay = powerDisplay;
        d.smallCheeseDisplay = smallCheeseDisplay;
        d.slowCheeseDisplay = slowCheeseDisplay;
        d.milkDisplay = milkDisplay;
        return d;
    }

}

class DestructParameter{
    public static final int FenseCost = 1000;
    public static final int PowerCost = 1000;
    public static final int SmallCost = 1000;
    public static final int SlowCost = 1000;
    public static final int MilkCost = 1000;
}