package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

class GlobalParameter {
    static final long TimePerDay = 60000;// 60sec = 60000ms
}

public class SynGameSetting {

    public SynGameSetting() {
        // TODO Auto-generated constructor stub
    }

    // setting relating to global display
    // String leftName = "LeftName";
    // String rightName = "RightName";
    private long time = 0;// current time of a day
    private ClimateType climate = ClimateType.Sunny;
    private BackGroundType background = BackGroundType.SpringFarm;

    // setting relating to players' properties. E.g., construction...
    // float leftStartBorder = 0F;
    // float rightStartBorder = 1600F;
    private Projector leftProjector = new Projector(Projector.Left);
    private Projector rightProjector = new Projector(Projector.Right);
    private House leftHouse = new House();
    private House rightHouse = new House();
    private Farm leftFarm = new Farm();
    private Farm rightFarm = new Farm();
    private int leftMilk = 0;
    private int rightMilk = 0;

    // setting relating to destruction done to farm
    private DestructState leftDestruct = new DestructState();
    private DestructState rightDestruct = new DestructState();

    // dynamic object
    private LinkedList<Cheese> leftCheeseList;
    private LinkedList<Cheese> rightCheeseList;
    private LinkedList<Cow> leftCowList;
    private LinkedList<Cow> rightCowList;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ClimateType getClimate() {
        return climate;
    }

    public void setClimate(ClimateType climate) {
        this.climate = climate;
    }

    public BackGroundType getBackground() {
        return background;
    }

    public void setBackground(BackGroundType background) {
        this.background = background;
    }

    public Projector getLeftProjector() {
        return leftProjector;
    }

    public void setLeftProjector(Projector leftProjector) {
        this.leftProjector = leftProjector;
    }

    public Projector getRightProjector() {
        return rightProjector;
    }

    public void setRightProjector(Projector rightProjector) {
        this.rightProjector = rightProjector;
    }

    public House getLeftHouse() {
        return leftHouse;
    }

    public void setLeftHouse(House leftHouse) {
        this.leftHouse = leftHouse;
    }

    public House getRightHouse() {
        return rightHouse;
    }

    public void setRightHouse(House rightHouse) {
        this.rightHouse = rightHouse;
    }

    public Farm getLeftFarm() {
        return leftFarm;
    }

    public void setLeftFarm(Farm leftFarm) {
        this.leftFarm = leftFarm;
    }

    public Farm getRightFarm() {
        return rightFarm;
    }

    public void setRightFarm(Farm rightFarm) {
        this.rightFarm = rightFarm;
    }

    public int getLeftMilk() {
        return leftMilk;
    }

    public void setLeftMilk(int leftMilk) {
        this.leftMilk = leftMilk;
    }

    public int getRightMilk() {
        return rightMilk;
    }

    public void setRightMilk(int rightMilk) {
        this.rightMilk = rightMilk;
    }

    public DestructState getLeftDestruct() {
        return leftDestruct;
    }

    public void setLeftDestruct(DestructState leftDestruct) {
        this.leftDestruct = leftDestruct;
    }

    public DestructState getRightDestruct() {
        return rightDestruct;
    }

    public void setRightDestruct(DestructState rightDestruct) {
        this.rightDestruct = rightDestruct;
    }

    public LinkedList<Cheese> getLeftCheeseList() {
        return leftCheeseList;
    }

    public void setLeftCheeseList(LinkedList<Cheese> leftCheeseList) {
        this.leftCheeseList = leftCheeseList;
    }

    public LinkedList<Cheese> getRightCheeseList() {
        return rightCheeseList;
    }

    public void setRightCheeseList(LinkedList<Cheese> rightCheeseList) {
        this.rightCheeseList = rightCheeseList;
    }

    public LinkedList<Cow> getLeftCowList() {
        return leftCowList;
    }

    public void setLeftCowList(LinkedList<Cow> leftCowList) {
        this.leftCowList = leftCowList;
    }

    public LinkedList<Cow> getRightCowList() {
        return rightCowList;
    }

    public void setRightCowList(LinkedList<Cow> rightCowList) {
        this.rightCowList = rightCowList;
    };

}

enum BackGroundType {
    SpringFarm
};

enum ClimateType {
    Sunny, Cloudy, Rainy, Snowy
};

enum ProjectorType {// according to projector
    Board, Slide, Cannon, Rocket
};

enum CheeseProdType {// according to house
    ForFun, AfterHours, Bakery, FoodFactory
};

enum CheeseQualityType {// according to house
    Handmade, CheeseMold, FoodChemisty, GMO
};

enum MilkProdType {// according to farm
    Grazing, Husbandry, Mechanization, Hormone
};

class DestructState {
    public DestructState() {
        // TODO Auto-generated constructor stub
    }

    public boolean fense = false;
    public boolean power = false;
    public boolean smallCheese = false;
    public boolean slowCheese = false;// produce slower
    public boolean milk = false;
    
}
