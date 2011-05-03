package NMLab.team10.rollingthecheese.gameSetting;

import NMLab.team10.rollingthecheese.event.EventQueueCenter;

public class ButtonD{
    //Cow Button => get from cow queue
    public byte cowPercent = 100;

    //CheeseButton => get from cheese queue
    public byte normalSmallP = 100;//percent
    public byte normalMedP = 100;
    public byte normalLargeP = 100;
    public byte poisonSmallP = 100;
    public byte poisonMedP = 100;
    public byte poisonLargeP = 100;
    public byte sweatySmallP = 100;
    public byte sweatyMedP = 100;
    public byte sweatyLargeP = 100;
    public byte firingSmallP = 100;
    public byte firingMedP = 100;
    public byte firingLargeP = 100;

    public byte normalSmallN = 0;//num
    public byte normalMedN = 0;
    public byte normalLargeN = 0;
    public byte poisonSmallN = 0;
    public byte poisonMedN = 0;
    public byte poisonLargeN = 0;
    public byte sweatySmallN = 0;
    public byte sweatyMedN = 0;
    public byte sweatyLargeN = 0;
    public byte firingSmallN = 0;
    public byte firingMedN = 0;
    public byte firingLargeN = 0;

    //Construct Button => get from construct queue
    public byte projectorPercent = 100;
    public byte cheeseProdPercent = 100;
    public byte cheeseQualPercent = 100;
    public byte MilkProdPercent = 100;

    //Destruction Button => get from GameSetting => DestructState => Display
    public boolean fenseDisplay = false;
    public boolean powerDisplay = false;
    public boolean smallCheeseDisplay = false;
    public boolean slowCheeseDisplay = false;
    public boolean milkDisplay = false;

    public static ButtonD createLeftButtonD(EventQueueCenter eqc, DestructState ds){
        ButtonD b = new ButtonD();
        b.fenseDisplay = ds.fenseDisplay;
        b.powerDisplay = ds.powerDisplay;
        b.smallCheeseDisplay = ds.smallCheeseDisplay;
        b.slowCheeseDisplay = ds.slowCheeseDisplay;
        b.milkDisplay = ds.milkDisplay;
        return b;
    }

    public static ButtonD createRightButtonD(EventQueueCenter eqc, DestructState ds){
        ButtonD b = new ButtonD();
        b.fenseDisplay = ds.fenseDisplay;
        b.powerDisplay = ds.powerDisplay;
        b.smallCheeseDisplay = ds.smallCheeseDisplay;
        b.slowCheeseDisplay = ds.slowCheeseDisplay;
        b.milkDisplay = ds.milkDisplay;
        return b;
    }

    //Cancel Button => by occqoo
    //public boolean cowButton = false;
}
