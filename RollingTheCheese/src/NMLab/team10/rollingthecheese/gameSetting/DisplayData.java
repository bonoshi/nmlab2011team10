package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

public class DisplayData {

    public DisplayData(SynMessageData smd) {
        this.synMessageData = smd;
    }

    SynMessageData synMessageData;

    LinkedList<CheeseDisplay> leftCheeseList = new LinkedList<CheeseDisplay>();
    LinkedList<CheeseDisplay> rightCheeseList = new LinkedList<CheeseDisplay>();
    LinkedList<CowDisplay> leftCowList = new LinkedList<CowDisplay>();
    LinkedList<CowDisplay> rightCowList = new LinkedList<CowDisplay>();
    LinkedList<FireLineDisplay> leftFireList = new LinkedList<FireLineDisplay>();
    LinkedList<FireLineDisplay> rightFireList = new LinkedList<FireLineDisplay>();

}
