package NMLab.team10.rollingthecheese.gameSetting;

import java.util.Iterator;
import java.util.LinkedList;

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

    private boolean isNight = false;

    // setting relating to players' properties. E.g., construction...
    // float leftStartBorder = 0F;
    // float rightStartBorder = 1600F;
    private Projector leftProjector = new Projector();
    private Projector rightProjector = new Projector();
    private House leftHouse = new House();
    private House rightHouse = new House();
    private Farm leftFarm = new Farm();
    private Farm rightFarm = new Farm();
    private int leftMilk = 100;
    private int rightMilk = 100;

    // setting relating to destruction done to farm
    private DestructState leftDestruct = new DestructState();
    private DestructState rightDestruct = new DestructState();

    // dynamic object
    private LinkedList<Cheese> leftCheeseList;
    private LinkedList<Cheese> rightCheeseList;
    private LinkedList<Cow> leftCowList;
    private LinkedList<Cow> rightCowList;
    private LinkedList<FireLine> leftFireList;
    private LinkedList<FireLine> rightFireList;

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

    public Projector getProjector(boolean whichSide) {
        if (whichSide) {
            return leftProjector;
        } else {
            return rightProjector;
        }
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

    public void timeElasping() {
        // Time++
        time += GlobalParameter.FramePeriod;
        isNight = GlobalParameter.isNight(time);
        // Milk
        milkIncrease(1.0F, Left);
        milkIncrease(1.0F, Right);
        // cheese time
        // construction time
        // cow time
        takeAction();
    }

    public void milkIncrease(float ratio, boolean whichSide) {
        LinkedList<Cow> list;
        byte prod = 0;
        if (whichSide) {
            list = leftCowList;
            prod = leftFarm.prod;
        } else {
            list = rightCowList;
            prod = rightFarm.prod;
        }
        float incre = 0.0F;
        switch (prod) {
            case Grazing: {
                ratio *= Farm.GrazingRatio;
                break;
            }
            case Husbandry: {
                ratio *= Farm.HormoneRatio;
                break;
            }
            case Mechanization: {
                ratio *= Farm.MechanizationRatio;
                break;
            }
            case Hormone: {
                ratio *= Farm.HormoneRatio;
                break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Cow cow = list.get(i);
            if (cow.getStatus() == Cow.Leak) {
                incre += (CowParameter.ProductionAmount) * (CowParameter.Crisis);
            } else {
                incre += CowParameter.ProductionAmount;
            }
        }
        if (whichSide) {
            leftMilk = leftMilk + Math.round(incre * ratio);
        } else {
            rightMilk = rightMilk + Math.round(incre * ratio);
        }
    }

    private void takeAction() {
        // First step
        clearDeadCheese(leftCheeseList);
        clearDeadCheese(rightCheeseList);

        // Second step
        move();

        // Third Step

        // Fourth Step
        checkCheeseDead(leftCheeseList);
        checkCheeseDead(rightCheeseList);
    }

    private void clearDeadCheese(LinkedList<Cheese> list) {
        for (Iterator<Cheese> iterator = list.iterator(); iterator.hasNext();) {
            Cheese cheese = (Cheese) iterator.next();
            if (cheese.isDead())
                iterator.remove();
        }
    }

    private void checkCheeseDead(LinkedList<Cheese> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).checkDead();
        }
    }

    private void move() {
        // first move heads of two sequence
        moveHead();

        // move following left cheese
        if (leftCheeseList.size() > 1) {
            for (int i = 1; i < leftCheeseList.size(); i++) {
                followCheese(leftCheeseList.get(i), leftCheeseList.get(i - 1), Left, this.getLeftProjector());
            }
        }

        // move following right cheese
        if (rightCheeseList.size() > 1) {
            for (int i = 1; i < rightCheeseList.size(); i++) {
                followCheese(rightCheeseList.get(i), rightCheeseList.get(i - 1), Right,
                        this.getRightProjector());
            }
        }
    }

    private void moveHead() {
        if (leftCheeseList.size() == 0 || rightCheeseList.size() == 0) {
            if (leftCheeseList.size() > 0) {
                Cheese leftC = leftCheeseList.getFirst();
                boolean leftJoinBattle = leftC.isJoinBattle();// for fire
                leftC.moveByDistance(leftC.getSpeed(), Left, this.getLeftProjector());
                if (!leftJoinBattle && leftC.isJoinBattle()) {
                    FireLine f = new FireLine(leftC, FireLine.getFireSFromCheese(leftC), this
                            .getLeftProjector().getBattleBorderX(leftC.getRadix(), Left));
                    leftFireList.add(f);

                }
                // fix if already bump into the enemy's house
                if (leftC.x > this.getRightHouse().getBoader(Right) + BumpOffset) {
                    leftC.x = this.getRightHouse().getBoader(Right) + BumpOffset;
                    leftC.setBump(true);
                } else {
                    leftC.setBump(false);
                }
            }
            if (rightCheeseList.size() > 0) {
                Cheese rightC = rightCheeseList.getFirst();
                boolean rightJoinBattle = rightC.isJoinBattle();// for fire
                rightC.moveByDistance(rightC.getSpeed(), Right, this.getRightProjector());
                if (!rightJoinBattle && rightC.isJoinBattle()) {
                    FireLine f = new FireLine(rightC, FireLine.getFireSFromCheese(rightC), this
                            .getRightProjector().getBattleBorderX(rightC.getRadix(), Right));
                    rightFireList.add(f);
                }
                if (rightC.x > this.getLeftHouse().getBoader(Left) + BumpOffset) {
                    rightC.x = this.getLeftHouse().getBoader(Left) + BumpOffset;
                    rightC.setBump(true);
                } else {
                    rightC.setBump(false);
                }
            }
            return;
        }
        Cheese leftC = leftCheeseList.getFirst();
        Cheese rightC = rightCheeseList.getFirst();
        float leftMoveAmount = leftC.getSpeed();
        float rightMoveAmount = rightC.getSpeed();
        int times = 4;

        boolean leftJoinBattle = leftC.isJoinBattle();
        boolean rightJoinBattle = rightC.isJoinBattle();

        for (int i = 0; i < times; i++) {
            // distance that can at most be traveled forward
            float distance = Cheese.distance(leftC, rightC) - (leftC.getRadix() + rightC.getRadix())
                    + BumpOffset;
            if (distance > -1.0F) {// move forward, -1.0F is for float error
                                   // tolerance
                // the exceed amount of distance if we just let two cheese go
                // under their speed
                float exceed = leftMoveAmount + rightMoveAmount - distance;
                if (exceed < 0.0F) {// no exceed => go straight!

                    // no need to calculate!
                    if (leftMoveAmount == 0 && rightMoveAmount == 0) {
                        break;
                    }

                    leftC.moveByDistance(leftMoveAmount, Left, this.getLeftProjector());
                    rightC.moveByDistance(rightMoveAmount, Right, this.getRightProjector());

                    // fix if already bump into the enemy's house
                    if (leftC.x > this.getRightHouse().getBoader(Right) + BumpOffset) {
                        leftC.x = this.getRightHouse().getBoader(Right) + BumpOffset;
                        // leftMoveAmount = leftC.x -
                        // (this.getRightHouse().getBoader(Right) + BumpOffset);
                        leftC.setBump(true);
                    } else {
                        // leftMoveAmount = 0;
                    }
                    if (rightC.x < this.getLeftHouse().getBoader(Left) - BumpOffset) {
                        rightC.x = this.getLeftHouse().getBoader(Left) - BumpOffset;
                        // rightMoveAmount = rightC.x -
                        // (this.getLeftHouse().getBoader(Left) - BumpOffset);
                        rightC.setBump(true);
                    } else {
                        // rightMoveAmount = 0;
                    }
                    leftMoveAmount = 0;
                    rightMoveAmount = 0;
                } else {
                    // when left is destroying the house on the right
                    if (leftC.x > this.getRightHouse().getBoader(Right)) {
                        leftC.setBump(true);
                        if (distance > rightMoveAmount) {
                            rightC.moveByDistance(rightMoveAmount, Right, this.getRightProjector());
                        } else {
                            rightC.moveByDistance(distance, Right, this.getRightProjector());
                            rightC.setBump(true);
                        }
                        // when right is destroying the house on the right
                    } else if (rightC.x < this.getLeftHouse().getBoader(Left)) {
                        rightC.setBump(true);
                        if (distance > leftMoveAmount) {
                            leftC.moveByDistance(leftMoveAmount, Left, this.getLeftProjector());
                        } else {
                            leftC.moveByDistance(distance, Left, this.getLeftProjector());
                            leftC.setBump(true);
                        }
                        leftC.moveByDistance(distance, Left, this.getLeftProjector());
                    } else {
                        float fracLeft = (distance * leftC.getSpeed())
                                / (leftC.getSpeed() + rightC.getSpeed());
                        float fracRight = distance - fracLeft;
                        leftC.moveByDistance(fracLeft, Left, this.getLeftProjector());
                        rightC.moveByDistance(fracRight, Right, this.getRightProjector());
                        leftMoveAmount -= fracLeft;
                        rightMoveAmount -= fracRight;
                        leftC.setBump(true);
                        rightC.setBump(true);
                    }
                }
            } else {// backward
                distance = -distance - 1.0F;
                float fracLeft = (distance * leftC.getSpeed()) / (leftC.getSpeed() + rightC.getSpeed());
                float fracRight = distance - fracLeft;
                leftC.backByDistance(fracLeft, Left, this.getLeftProjector());
                rightC.backByDistance(fracRight, Right, this.getRightProjector());
                leftMoveAmount += fracLeft;
                rightMoveAmount += fracRight;
                leftC.setBump(false);
                rightC.setBump(false);
            }
        }

        if (!leftJoinBattle && leftC.isJoinBattle()) {
            FireLine f = new FireLine(leftC, FireLine.getFireSFromCheese(leftC), this.getLeftProjector()
                    .getBattleBorderX(leftC.getRadix(), Left));
            leftFireList.add(f);
        }
        if (!rightJoinBattle && rightC.isJoinBattle()) {
            FireLine f = new FireLine(rightC, FireLine.getFireSFromCheese(rightC), this.getRightProjector()
                    .getBattleBorderX(rightC.getRadix(), Right));
            rightFireList.add(f);
        }

    }

    private void followCheese(Cheese back, Cheese front, boolean whichSide, Projector p) {
        boolean joinBattle = back.isJoinBattle();
        float moveAmount = back.getSpeed();
        // the exceed amount of distance if we just let two cheese go
        // under their speed

        int times = 2;

        for (int i = 0; i < times; i++) {
            float distance = Cheese.distance(back, front) - (back.getRadix() + front.getRadix())
                    + FollowOffset;
            if (distance > -1.0F) {
                if (moveAmount == 0) {
                    break;
                }
                float exceed = moveAmount - distance;
                if (exceed < 0.0F) {// just move at most as it can!
                    back.moveByDistance(moveAmount, whichSide, p);
                    moveAmount = 0;
                } else {
                    back.moveByDistance(distance, whichSide, p);
                    moveAmount -= distance;
                }
            } else {
                distance = -distance - 1.0F;
                back.backByDistance(distance, whichSide, p);
                moveAmount += distance;
            }
        }

        if (!joinBattle && back.isJoinBattle()) {
            FireLine f = new FireLine(back, FireLine.getFireSFromCheese(back), p.getBattleBorderX(
                    back.getRadix(), whichSide));
            if (whichSide) {
                leftFireList.add(f);
            } else {
                rightFireList.add(f);
            }
        }
    }

    public void setNight(boolean isNight) {
        this.isNight = isNight;
    }

    public boolean isNight() {
        return isNight;
    }

    public void setLeftFireList(LinkedList<FireLine> leftFireList) {
        this.leftFireList = leftFireList;
    }

    public LinkedList<FireLine> getLeftFireList() {
        return leftFireList;
    }

    public void setRightFireList(LinkedList<FireLine> rightFireList) {
        this.rightFireList = rightFireList;
    }

    public LinkedList<FireLine> getRightFireList() {
        return rightFireList;
    }

    public static final byte Grazing = MilkProdEnum.Grazing;
    public static final byte Husbandry = MilkProdEnum.Husbandry;
    public static final byte Mechanization = MilkProdEnum.Mechanization;
    public static final byte Hormone = MilkProdEnum.Hormone;

    public static final boolean Right = false;
    public static final boolean Left = true;

    public static final float BumpOffset = GlobalParameter.BumpOffset;
    public static final float FollowOffset = GlobalParameter.FollowOffset;
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
