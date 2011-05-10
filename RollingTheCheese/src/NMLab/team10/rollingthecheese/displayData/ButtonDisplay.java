package NMLab.team10.rollingthecheese.displayData;

import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueue;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.DestructState;

public class ButtonDisplay {
    // Cow Button => get from cow queue
    public byte cowPercent = 100;

    // CheeseButton => get from cheese queue
    public byte normalSmallP = 100;// percent
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

    public byte normalSmallN = 0;// num
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

    // Construct Button => get from construct queue
    public byte projectorPercent = 100;
    public byte cheeseProdPercent = 100;
    public byte cheeseQualPercent = 100;
    public byte MilkProdPercent = 100;

    // Destruction Button => get from GameSetting => DestructState => Display
    public boolean fenseDisplay = false;
    public boolean powerDisplay = false;
    public boolean smallCheeseDisplay = false;
    public boolean slowCheeseDisplay = false;
    public boolean milkDisplay = false;

    public static ButtonDisplay createButtonD(EventQueueCenter eqc, DestructState ds, boolean whichSide) {
        ButtonDisplay b = new ButtonDisplay();
        EventQueue cowQ = null;
        EventQueue chQ = null;
        EventQueue conQ = null;
        if (whichSide) {
            cowQ = eqc.getLeftCowQueue();
            chQ = eqc.getLeftCheeseQueue();
            conQ = eqc.getLeftConsQueue();
        } else {
            cowQ = eqc.getRightCowQueue();
            chQ = eqc.getRightCheeseQueue();
            conQ = eqc.getRightConsQueue();
        }
        // Cow Button
        // EventQueue cowQ = eqc.getLeftCowQueue();
        b.cowPercent = (cowQ.getSize() == 0) ? 100 : cowQ.getPercent();
        // CheeseButton
        // EventQueue chQ = eqc.getLeftCheeseQueue();
        b.normalSmallN = chQ.countEvent(EventEnum.OriginalCheeseSmall);
        b.normalSmallP = (b.normalSmallN == 0) ? 100 : ((chQ.peak() == EventEnum.OriginalCheeseSmall) ? chQ
                .getPercent() : 0);
        b.normalMedN = chQ.countEvent(EventEnum.OriginalCheeseMed);
        b.normalMedP = (b.normalMedN == 0) ? 100 : ((chQ.peak() == EventEnum.OriginalCheeseMed) ? chQ
                .getPercent() : 0);
        b.normalLargeN = chQ.countEvent(EventEnum.OriginalCheeseLarge);
        b.normalLargeP = (b.normalLargeN == 0) ? 100 : ((chQ.peak() == EventEnum.OriginalCheeseLarge) ? chQ
                .getPercent() : 0);
        b.poisonSmallN = chQ.countEvent(EventEnum.CasuMarzuSmall);
        b.poisonSmallP = (b.poisonSmallN == 0) ? 100 : ((chQ.peak() == EventEnum.CasuMarzuSmall) ? chQ
                .getPercent() : 0);
        b.poisonMedN = chQ.countEvent(EventEnum.CasuMarzuMed);
        b.poisonMedP = (b.poisonMedN == 0) ? 100 : ((chQ.peak() == EventEnum.CasuMarzuMed) ? chQ.getPercent()
                : 0);
        b.poisonLargeN = chQ.countEvent(EventEnum.CasuMarzuLarge);
        b.poisonLargeP = (b.poisonLargeN == 0) ? 100 : ((chQ.peak() == EventEnum.CasuMarzuLarge) ? chQ
                .getPercent() : 0);
        b.sweatySmallN = chQ.countEvent(EventEnum.SweatyCheeseSmall);
        b.sweatySmallP = (b.sweatySmallN == 0) ? 100 : ((chQ.peak() == EventEnum.SweatyCheeseSmall) ? chQ
                .getPercent() : 0);
        b.sweatyMedN = chQ.countEvent(EventEnum.SweatyCheeseMed);
        b.sweatyMedP = (b.sweatyMedN == 0) ? 100 : ((chQ.peak() == EventEnum.SweatyCheeseMed) ? chQ
                .getPercent() : 0);
        b.sweatyLargeN = chQ.countEvent(EventEnum.SweatyCheeseLarge);
        b.sweatyLargeP = (b.sweatyLargeN == 0) ? 100 : ((chQ.peak() == EventEnum.SweatyCheeseLarge) ? chQ
                .getPercent() : 0);
        b.firingSmallN = chQ.countEvent(EventEnum.FiringCheeseSmall);
        b.firingSmallP = (b.firingSmallN == 0) ? 100 : ((chQ.peak() == EventEnum.FiringCheeseSmall) ? chQ
                .getPercent() : 0);
        b.firingMedN = chQ.countEvent(EventEnum.FiringCheeseMed);
        b.firingMedP = (b.firingMedN == 0) ? 100 : ((chQ.peak() == EventEnum.FiringCheeseMed) ? chQ
                .getPercent() : 0);
        b.firingLargeN = chQ.countEvent(EventEnum.FiringCheeseLarge);
        b.firingLargeP = (b.firingLargeN == 0) ? 100 : ((chQ.peak() == EventEnum.FiringCheeseLarge) ? chQ
                .getPercent() : 0);
        // Construct
        // EventQueue coQ = eqc.getLeftConsQueue();
        b.projectorPercent = (conQ.findEvent(EventEnum.Projector)) ? ((conQ.peak() == EventEnum.Projector) ? conQ
                .getPercent() : 0)
                : 100;
        b.cheeseProdPercent = (conQ.findEvent(EventEnum.CheeseProd)) ? ((conQ.peak() == EventEnum.CheeseProd) ? conQ
                .getPercent() : 0)
                : 100;
        b.cheeseQualPercent = (conQ.findEvent(EventEnum.CheeseQual)) ? ((conQ.peak() == EventEnum.CheeseQual) ? conQ
                .getPercent() : 0)
                : 100;
        b.MilkProdPercent = (conQ.findEvent(EventEnum.MilkProd)) ? ((conQ.peak() == EventEnum.MilkProd) ? conQ
                .getPercent() : 0) : 100;
        // Destruction
        b.fenseDisplay = ds.fenseDisplay;
        b.powerDisplay = ds.powerDisplay;
        b.smallCheeseDisplay = ds.smallCheeseDisplay;
        b.slowCheeseDisplay = ds.slowCheeseDisplay;
        b.milkDisplay = ds.milkDisplay;
        return b;
    }

    // Cancel Button => by occqoo
    // public boolean cowButton = false;
}
