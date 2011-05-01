package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

class GlobalParameter {
    public static final long TimePerDay = 60000;// 60sec = 60000ms
    public static final int FramePeriod = 30;//30ms
}

public class ServerGameSetting {

    public ServerGameSetting() {
        // TODO Auto-generated constructor stub
    }

    // setting relating to global display
    // String leftName = "LeftName";
    // String rightName = "RightName";
    private int time = 0;// current time of a day
    private byte climate = ClimateEnum.Sunny;
    private byte background = BackGroundEnum.SpringFarm;

    // setting relating to players' properties. E.g., construction...
    // float leftStartBorder = 0F;
    // float rightStartBorder = 1600F;
    private Projector leftProjector = new Projector();
    private Projector rightProjector = new Projector();
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
    private LinkedList<FireLine> fileLineList;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public byte getClimate() {
        return climate;
    }

    public void setClimate(byte climate) {
        this.climate = climate;
    }

    public byte getBackground() {
        return background;
    }

    public void setBackground(byte background) {
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
    }

    public LinkedList<FireLine> getFileLineList() {
        return fileLineList;
    }

    public void setFileLineList(LinkedList<FireLine> fileLineList) {
        this.fileLineList = fileLineList;
    }

}

class BackGroundEnum {
    public static final byte SpringFarm = 0;
};

class ClimateEnum {
    public static final byte Sunny = 0;
    public static final byte Cloudy = 1;
    public static final byte Rainy = 2;
    public static final byte Snowy = 3;
};

class ProjectorEnum {// according to projector
    public static final byte Board = 0;
    public static final byte Slide = 1;
    public static final byte Cannon = 2;
    public static final byte Rocket = 3;
};

class CheeseProdEnum {// according to house
    public static final byte ForFun = 0;
    public static final byte AfterHours = 1;
    public static final byte Bakery = 2;
    public static final byte FoodFactory = 3;
};

class CheeseQualityEnum {// according to house
    public static final byte Handmade = 0;
    public static final byte CheeseMold = 1;
    public static final byte FoodChemisty = 2;
    public static final byte GMO = 3;
};

class MilkProdEnum {// according to farm
    public static final byte Grazing = 0;
    public static final byte Husbandry = 1;
    public static final byte Mechanization = 2;
    public static final byte Hormone = 3;
};

