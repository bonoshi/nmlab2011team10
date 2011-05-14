package NMLab.team10.rollingthecheese.gameSetting;

import java.io.Serializable;

public class DestructStateMessage implements Serializable{
    private static final long serialVersionUID = 3774329768372521699L;
    
    public DestructStateMessage(DestructState ds) {
        this.fense = ds.fense;
        this.power = ds.power;
        this.smallCheese = ds.smallCheese;
        this.slowCheese = ds.slowCheese;
        this.milk = ds.milk;
    }

    public boolean fense = false;
    public boolean power = false;
    public boolean smallCheese = false;
    public boolean slowCheese = false;// produce slower
    public boolean milk = false;

}
