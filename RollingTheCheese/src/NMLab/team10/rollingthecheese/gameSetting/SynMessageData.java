package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

import NMLab.team10.rollingthecheese.event.EventQueueCenter;

public class SynMessageData {

    int time;// current time of a day
    byte climate;
    byte background;

    Projector leftProjector;
    Projector rightProjector;
    House leftHouse;
    House rightHouse;
    Farm leftFarm;
    Farm rightFarm;
    int leftMilk;
    int rightMilk;
    byte leftHouseHpPercent;
    byte rightHouseHpPercent;

    LinkedList<CheeseMessage> leftCheeseList = new LinkedList<CheeseMessage>();
    LinkedList<CheeseMessage> rightCheeseList = new LinkedList<CheeseMessage>();
    LinkedList<CowMessage> leftCowList = new LinkedList<CowMessage>();
    LinkedList<CowMessage> rightCowList = new LinkedList<CowMessage>();
    LinkedList<FireLineMessage> fireLineList = new LinkedList<FireLineMessage>();

    // DestructButton leftDestructB = new DestructButton();
    // DestructButton rightDestructB = new DestructButton();
    // ConstructButton leftConstructB = new ConstructButton();
    // ConstructButton rightConstructB = new ConstructButton();
    // CheeseButton leftCheeseB = new CheeseButton();
    // CheeseButton rightCheeseB = new CheeseButton();

    byte leftSpecialState = 0;
    byte rightSpecialState = 0;

    ButtonD buttonD = new ButtonD();

    public void refreshData(ServerGameSetting s, EventQueueCenter eqc, boolean whichSide) {

        synchronized (s) {

            time = s.getTime();
            climate = s.getClimate();
            background = s.getBackground();

            leftProjector = s.getLeftProjector().clone();
            rightProjector = s.getRightProjector().clone();
            leftHouse = s.getLeftHouse().clone();
            rightHouse = s.getRightHouse().clone();
            leftFarm = s.getLeftFarm().clone();
            rightFarm = s.getRightFarm().clone();
            leftMilk = s.getLeftMilk();
            rightMilk = s.getRightMilk();

            cloneCheeseList(leftCheeseList, s.getLeftCheeseList());
            cloneCheeseList(rightCheeseList, s.getRightCheeseList());
            cloneCowList(leftCowList, s.getLeftCowList());
            cloneCowList(rightCowList, s.getRightCowList());
            cloneFireList(fireLineList, s.getFileLineList());

            if (whichSide) {
                this.buttonD = ButtonD.createLeftButtonD(eqc, s.getLeftDestruct());
            } else {
                this.buttonD = ButtonD.createRightButtonD(eqc, s.getRightDestruct());
            }

            // for button display
            // leftDestructB = s.getLeftDestruct().clone();
            // rightDestructB = s.getRightDestruct().clone();
            // leftConstruct
            // rightConstruct

            // leftCheeseList = s.getLeftCheeseList();
            // rightCheeseList = s.getRightCheeseList();
            // leftCowList = s.getLeftCowList();
            // rightCowList = s.getRightCowList();
        }
    }

    private void cloneCheeseList(LinkedList<CheeseMessage> toList, LinkedList<Cheese> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new CheeseMessage(fromList.get(i)));
        }
    }

    private void cloneCowList(LinkedList<CowMessage> toList, LinkedList<Cow> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new CowMessage(fromList.get(i)));
        }
    }

    private void cloneFireList(LinkedList<FireLineMessage> toList, LinkedList<FireLine> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new FireLineMessage(fromList.get(i)));
        }
    }

    public static final boolean Right = false;
    public static final boolean Left = true;
}
