package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

public class SynDisplayData {

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

    LinkedList<CheeseDisplay> leftCheeseList = new LinkedList<CheeseDisplay>();
    LinkedList<CheeseDisplay> rightCheeseList = new LinkedList<CheeseDisplay>();
    LinkedList<CowDisplay> leftCowList = new LinkedList<CowDisplay>();
    LinkedList<CowDisplay> rightCowList = new LinkedList<CowDisplay>();
    LinkedList<FireLineDisplay> fireLineList = new LinkedList<FireLineDisplay>();

    //DestructButton leftDestructB = new DestructButton();
    //DestructButton rightDestructB = new DestructButton();
    //ConstructButton leftConstructB = new ConstructButton();
    //ConstructButton rightConstructB = new ConstructButton();
    //CheeseButton leftCheeseB = new CheeseButton();
    //CheeseButton rightCheeseB = new CheeseButton();

    byte leftSpecialState = 0;
    byte rightSpecialState = 0;

    public void refreshData(ServerGameSetting s) {

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

            //for button display
            //leftDestructB = s.getLeftDestruct().clone();
            //rightDestructB = s.getRightDestruct().clone();
            //leftConstruct
            //rightConstruct

//            leftCheeseList = s.getLeftCheeseList();
//            rightCheeseList = s.getRightCheeseList();
//            leftCowList = s.getLeftCowList();
//            rightCowList = s.getRightCowList();
        }
    }

    private void cloneCheeseList(LinkedList<CheeseDisplay> toList, LinkedList<Cheese> fromList){
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new CheeseDisplay(fromList.get(i)));
        }
    }
    private void cloneCowList(LinkedList<CowDisplay> toList, LinkedList<Cow> fromList){
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new CowDisplay(fromList.get(i)));
        }
    }
    private void cloneFireList(LinkedList<FireLineDisplay> toList, LinkedList<FireLine> fromList){
        toList.clear();
        for (int i = 0; i < fromList.size(); i++) {
            toList.add(new FireLineDisplay(fromList.get(i)));
        }
    }
}
