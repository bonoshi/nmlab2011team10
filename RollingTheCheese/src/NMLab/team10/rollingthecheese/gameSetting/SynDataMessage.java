package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

public class SynDataMessage {

    public void refreshData(SynGameSetting s) {
        time = s.getTime();
        climate = s.getClimate();
        background = s.getBackground();

        leftProjector = s.getLeftProjector();
        rightProjector = s.getRightProjector();
        leftHouse = s.getLeftHouse();
        rightHouse = s.getRightHouse();
        leftFarm = s.getLeftFarm();
        rightFarm = s.getRightFarm();
        leftMilk = s.getLeftMilk();
        rightMilk = s.getRightMilk();

        leftDestruct = s.getLeftDestruct();
        rightDestruct = s.getRightDestruct();

        leftCheeseList = s.getLeftCheeseList();
        rightCheeseList = s.getRightCheeseList();
        leftCowList = s.getLeftCowList();
        rightCowList = s.getRightCowList();
    }
    
    long time;// current time of a day
    ClimateType climate;
    BackGroundType background;

    Projector leftProjector;
    Projector rightProjector;
    House leftHouse;
    House rightHouse;
    Farm leftFarm;
    Farm rightFarm;
    int leftMilk;
    int rightMilk;

    DestructState leftDestruct;
    DestructState rightDestruct;

    LinkedList<Cheese> leftCheeseList;
    LinkedList<Cheese> rightCheeseList;
    LinkedList<Cow> leftCowList;
    LinkedList<Cow> rightCowList;

}
