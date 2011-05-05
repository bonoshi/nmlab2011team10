package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

import NMLab.team10.rollingthecheese.event.EventQueueCenter;

public class SynMessageData {

    int time;// current time of a day
    byte climate;
    byte background;

    Projector leftProjector;
    Projector rightProjector;
    HouseMessage leftHouse;
    HouseMessage rightHouse;
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
    LinkedList<FireLineMessage> leftFireList = new LinkedList<FireLineMessage>();
    LinkedList<FireLineMessage> rightFireList = new LinkedList<FireLineMessage>();

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
            leftHouse = new HouseMessage(s.getLeftHouse());
            rightHouse = new HouseMessage(s.getRightHouse());
            leftFarm = s.getLeftFarm().clone();
            rightFarm = s.getRightFarm().clone();
            leftMilk = s.getLeftMilk();
            rightMilk = s.getRightMilk();

            createCheeseMessage(leftCheeseList, s.getLeftCheeseList());
            createCheeseMessage(rightCheeseList, s.getRightCheeseList());
            createCowMessage(leftCowList, s.getLeftCowList());
            createCowMessage(rightCowList, s.getRightCowList());
            createFireMessage(leftFireList, s.getLeftFireList());
            createFireMessage(rightFireList, s.getRightFireList());

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

    private void createCheeseMessage(LinkedList<CheeseMessage> toList, LinkedList<Cheese> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new CheeseMessage(fromList.get(i), Cheese.getID()));
        }
    }

    private void createCowMessage(LinkedList<CowMessage> toList, LinkedList<Cow> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new CowMessage(fromList.get(i), Cow.getID()));
        }
    }

    private void createFireMessage(LinkedList<FireLineMessage> toList, LinkedList<FireLine> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new FireLineMessage(fromList.get(i), FireLine.getID()));
        }
    }

    public static final boolean Right = false;
    public static final boolean Left = true;
}
