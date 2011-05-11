package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

import NMLab.team10.rollingthecheese.displayData.ButtonDisplay;
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
    byte leftHouseHPPercent;
    byte rightHouseHPPercent;

    LinkedList<CheeseMessage> leftCheeseList = new LinkedList<CheeseMessage>();
    LinkedList<CheeseMessage> rightCheeseList = new LinkedList<CheeseMessage>();
    LinkedList<CowMessage> leftCowList = new LinkedList<CowMessage>();
    LinkedList<CowMessage> rightCowList = new LinkedList<CowMessage>();
    LinkedList<FireLineMessage> leftFireList = new LinkedList<FireLineMessage>();
    LinkedList<FireLineMessage> rightFireList = new LinkedList<FireLineMessage>();

    ButtonDisplay buttonD = new ButtonDisplay();
    DestructStateMessage leftDSM;
    DestructStateMessage rightDSM;

    public SynMessageData(ServerGameSetting s, EventQueueCenter eqc, boolean whichSide) {
        refreshData(s, eqc, whichSide);
    }

    private void refreshData(ServerGameSetting s, EventQueueCenter eqc, boolean whichSide) {

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
        
        leftDSM = new DestructStateMessage(s.getLeftDestruct());
        rightDSM = new DestructStateMessage(s.getRightDestruct());

        createCheeseMessage(leftCheeseList, s.getLeftCheeseList());
        createCheeseMessage(rightCheeseList, s.getRightCheeseList());
        createCowMessage(leftCowList, s.getLeftCowList());
        createCowMessage(rightCowList, s.getRightCowList());
        createFireMessage(leftFireList, s.getLeftFireList());
        createFireMessage(rightFireList, s.getRightFireList());

        if (whichSide) {
            this.buttonD = ButtonDisplay.createButtonD(eqc, s.getLeftDestruct(), whichSide);
        } else {
            this.buttonD = ButtonDisplay.createButtonD(eqc, s.getRightDestruct(), whichSide);
        }

    }

    private void createCheeseMessage(LinkedList<CheeseMessage> toList, LinkedList<Cheese> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new CheeseMessage(fromList.get(i)));
        }
    }

    private void createCowMessage(LinkedList<CowMessage> toList, LinkedList<Cow> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new CowMessage(fromList.get(i)));
        }
    }

    private void createFireMessage(LinkedList<FireLineMessage> toList, LinkedList<FireLine> fromList) {
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new FireLineMessage(fromList.get(i)));
        }
    }

    public int getTime() {
        return time;
    }

    public byte getClimate() {
        return climate;
    }

    public byte getBackground() {
        return background;
    }

    public Projector getLeftProjector() {
        return leftProjector;
    }

    public Projector getRightProjector() {
        return rightProjector;
    }

    public HouseMessage getLeftHouse() {
        return leftHouse;
    }

    public HouseMessage getRightHouse() {
        return rightHouse;
    }

    public Farm getLeftFarm() {
        return leftFarm;
    }

    public Farm getRightFarm() {
        return rightFarm;
    }

    public int getLeftMilk() {
        return leftMilk;
    }

    public int getRightMilk() {
        return rightMilk;
    }

    public byte getLeftHouseHPPercent() {
        return leftHouseHPPercent;
    }

    public byte getRightHouseHPPercent() {
        return rightHouseHPPercent;
    }

    public LinkedList<CheeseMessage> getLeftCheeseList() {
        return leftCheeseList;
    }

    public LinkedList<CheeseMessage> getRightCheeseList() {
        return rightCheeseList;
    }

    public LinkedList<CowMessage> getLeftCowList() {
        return leftCowList;
    }

    public LinkedList<CowMessage> getRightCowList() {
        return rightCowList;
    }

    public LinkedList<FireLineMessage> getLeftFireList() {
        return leftFireList;
    }

    public LinkedList<FireLineMessage> getRightFireList() {
        return rightFireList;
    }

    public ButtonDisplay getButtonD() {
        return buttonD;
    }
    
    public DestructStateMessage getLeftDSM() {
        return leftDSM;
    }
    
    public DestructStateMessage getRightDSM() {
        return rightDSM;
    }

    public static final boolean Right = false;
    public static final boolean Left = true;
}
