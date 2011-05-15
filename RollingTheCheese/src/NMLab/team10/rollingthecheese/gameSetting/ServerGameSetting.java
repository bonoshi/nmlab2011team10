package NMLab.team10.rollingthecheese.gameSetting;

import java.util.Iterator;
import java.util.LinkedList;

import NMLab.team10.rollingthecheese.byteEnum.BackGroundEnum;
import NMLab.team10.rollingthecheese.byteEnum.ClimateEnum;
import NMLab.team10.rollingthecheese.byteEnum.MilkProdEnum;

public class ServerGameSetting {

    public ServerGameSetting() {
        // TODO Auto-generated constructor stub
    }
    // setting relating to global display
    // String leftName = "LeftName";
    // String rightName = "RightName";
    private int time = 0;// current time of a day
    private byte climate = ClimateEnum.Cloudy;
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
    private int leftMilk = GlobalParameter.initialMilk;
    private int rightMilk = GlobalParameter.initialMilk;
    // setting relating to destruction done to farm
    private DestructState leftDestruct = new DestructState();
    private DestructState rightDestruct = new DestructState();
    // dynamic object
    private LinkedList<Cheese> leftCheeseList = new LinkedList<Cheese>();
    private LinkedList<Cheese> rightCheeseList = new LinkedList<Cheese>();
    private LinkedList<Cow> leftCowList = new LinkedList<Cow>();
    private LinkedList<Cow> rightCowList = new LinkedList<Cow>();
    private LinkedList<FireLine> leftFireList = new LinkedList<FireLine>();
    private LinkedList<FireLine> rightFireList = new LinkedList<FireLine>();

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
    long lastMilkTime = System.currentTimeMillis();

    public void timeElapsing() {
        // Time++
        time += GlobalParameter.FramePeriod;
        time %= GlobalParameter.TimePerDay;
        isNight = GlobalParameter.isNight(time);
        // Milk
        if (System.currentTimeMillis() - lastMilkTime > CowParameter.MilkInterval) {
            lastMilkTime = System.currentTimeMillis();
            milkIncrease(1.0F, Left);
            milkIncrease(1.0F, Right);
        }
        // Take action
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
        // First step: Clear the dead cheese from the list
        clearDeadCheese(leftCheeseList);
        clearDeadCheese(rightCheeseList);

        // Second step: Move Cheese
        leftHouse.setBump(false);
        rightHouse.setBump(false);
        headCase = HeadCase.NoBumpHappen;
        moveCheese();
        refreshAngle(leftCheeseList);
        refreshAngle(rightCheeseList);

        // Third Step: Bad effect
        firelineEffect();
        poisonEffect(leftCheeseList);
        poisonEffect(rightCheeseList);

        // Fourth Step: Check Dead
        checkCheeseDead(leftCheeseList);
        checkCheeseDead(rightCheeseList);

        // Fifth:Sweaty and poisonous cheese take action but ignore the death
        poisonousCheese();
        sweatyCheese();

        // Sixth Step: Check Dead
        checkCheeseDead(leftCheeseList);
        checkCheeseDead(rightCheeseList);

        // Seventh Step: Normal cheese and firing Cheese
        normalAndFiringCheese();

        // Eighth Step: Check Dead
        checkCheeseDead(leftCheeseList);
        checkCheeseDead(rightCheeseList);
    }

    private void clearDeadCheese(LinkedList<Cheese> list) {
        for (Iterator<Cheese> iterator = list.iterator(); iterator.hasNext();) {
            Cheese cheese = (Cheese) iterator.next();
            if (cheese.isDead()) {
                iterator.remove();
            }
        }
    }

    private void checkCheeseDead(LinkedList<Cheese> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).checkDead();
        }
    }

    private void refreshAngle(LinkedList<Cheese> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).refreshAngle();
        }
    }

    private void moveCheese() {
        // First step: move heads of two sequence
        moveHead();

        // Second step:
        // move following left cheese
        if (leftCheeseList.size() > 1) {
            for (int i = 1; i < leftCheeseList.size(); i++) {
                followPreviousCheese(leftCheeseList.get(i), leftCheeseList.get(i - 1), Left,
                        this.getLeftProjector());
            }
        }

        // move following right cheese
        if (rightCheeseList.size() > 1) {
            for (int i = 1; i < rightCheeseList.size(); i++) {
                followPreviousCheese(rightCheeseList.get(i), rightCheeseList.get(i - 1), Right,
                        this.getRightProjector());
            }
        }
    }
    byte headCase = 0;

    private void moveHead() {
        if (leftCheeseList.size() == 0 || rightCheeseList.size() == 0) {
            if (leftCheeseList.size() > 0) {
                Cheese leftC = leftCheeseList.getFirst();
                boolean leftJoinBattle = leftC.isJoinBattle();// for fire
                leftC.moveByDistance(leftC.getSpeed(), Left, this.getLeftProjector());
                if (!leftJoinBattle && leftC.isJoinBattle() && leftC.getType() == Cheese.Firing) {// for fire
                    FireLine f = new FireLine(leftC, FireLine.getFireSFromCheese(leftC), this.getLeftProjector().getBattleBorderX(leftC.getRadix(), Left));
                    leftFireList.add(f);

                }
                // fix if already bump into the enemy's house
                if (leftC.x > this.getRightHouse().getBoader(Right, leftC.getRadix()) - leftC.getRadix() + BumpOffset) {
                    leftC.x = this.getRightHouse().getBoader(Right, leftC.getRadix()) - leftC.getRadix() + BumpOffset;
                    leftC.setBump(true);
                    headCase = HeadCase.LeftBumpHouse;
                    rightHouse.setBump(true);
                } else {
                    leftC.setBump(false);
                }
            }
            if (rightCheeseList.size() > 0) {
                Cheese rightC = rightCheeseList.getFirst();
                boolean rightJoinBattle = rightC.isJoinBattle();// for fire
                rightC.moveByDistance(rightC.getSpeed(), Right, this.getRightProjector());
                if (!rightJoinBattle && rightC.isJoinBattle() && rightC.getType() == Cheese.Firing) {// for fire
                    FireLine f = new FireLine(rightC, FireLine.getFireSFromCheese(rightC), this.getRightProjector().getBattleBorderX(rightC.getRadix(), Right));
                    rightFireList.add(f);
                }
                if (rightC.x < this.getLeftHouse().getBoader(Left, rightC.getRadix()) + rightC.getRadix() - BumpOffset) {
                    rightC.x = this.getLeftHouse().getBoader(Left, rightC.getRadix()) + rightC.getRadix() - BumpOffset;
                    rightC.setBump(true);
                    headCase = HeadCase.RightBumpHouse;
                    leftHouse.setBump(true);
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

        final int times = 4;

        boolean leftJoinBattle = leftC.isJoinBattle();
        boolean rightJoinBattle = rightC.isJoinBattle();

        for (int i = 0; i < times; i++) {
            // distance that can at most be traveled forward
            float distance = Cheese.distance(leftC, rightC) - (leftC.getRadix() + rightC.getRadix())
                    + BumpOffset;
            if (distance > -1.0F) {// move forward, -1.0F is for float error
                // tolerance

                // no need to calculate!
                if (leftMoveAmount == 0 && rightMoveAmount == 0) {
                    break;
                }

                // when left is destroying the house on the right
                if (leftC.x > this.getRightHouse().getBoader(Right, leftC.getRadix()) - leftC.getRadix()) {
                    leftC.setBump(true);
                    if (distance > rightMoveAmount) {
                        rightC.moveByDistance(rightMoveAmount, Right, this.getRightProjector());
                        rightMoveAmount = 0;
                        leftMoveAmount = 0;
                    } else {
                        rightC.moveByDistance(distance, Right, this.getRightProjector());
                        rightC.setBump(true);
                        rightMoveAmount -= distance;
                        leftMoveAmount = 0;
                    }
                    // when right is destroying the house on the right
                } else if (rightC.x < this.getLeftHouse().getBoader(Left, rightC.getRadix()) + rightC.getRadix()) {
                    rightC.setBump(true);
                    if (distance > leftMoveAmount) {
                        leftC.moveByDistance(leftMoveAmount, Left, this.getLeftProjector());
                        leftMoveAmount = 0;
                        rightMoveAmount = 0;
                    } else {
                        leftC.moveByDistance(distance, Left, this.getLeftProjector());
                        leftC.setBump(true);
                        leftMoveAmount -= distance;
                        rightMoveAmount = 0;
                    }

                    // when there is no destroying house
                } else {

                    // the exceed amount of distance if we just let two cheese
                    // go
                    // under their speed
                    float exceed = leftMoveAmount + rightMoveAmount - distance;
                    if (exceed < 0.0F) {// no exceed => go straight!

                        leftC.moveByDistance(leftMoveAmount, Left, this.getLeftProjector());
                        rightC.moveByDistance(rightMoveAmount, Right, this.getRightProjector());

                        // fix if already bump into the enemy's house
                        if (leftC.x >= this.getRightHouse().getBoader(Right, leftC.getRadix()) - leftC.getRadix() + BumpOffset) {
                            leftC.x = this.getRightHouse().getBoader(Right, leftC.getRadix()) - leftC.getRadix() + BumpOffset;
                            // leftMoveAmount = leftC.x -
                            // (this.getRightHouse().getBoader(Right) +
                            // BumpOffset);
                            leftC.setBump(true);
                        }
                        if (rightC.x <= this.getLeftHouse().getBoader(Left) + rightC.getRadix() - BumpOffset) {
                            rightC.x = this.getLeftHouse().getBoader(Left) + rightC.getRadix() - BumpOffset;
                            // rightMoveAmount = rightC.x -
                            // (this.getLeftHouse().getBoader(Left) -
                            // BumpOffset);
                            rightC.setBump(true);
                        }
                        leftMoveAmount = 0;
                        rightMoveAmount = 0;

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
                        // fix if already bump into the enemy's house
                        if (leftC.x >= this.getRightHouse().getBoader(Right, leftC.getRadix()) - leftC.getRadix() + BumpOffset) {
                            leftC.x = this.getRightHouse().getBoader(Right, leftC.getRadix()) - leftC.getRadix() + BumpOffset;
                            leftMoveAmount = 0;
                        }
                        if (rightC.x <= this.getLeftHouse().getBoader(Left, rightC.getRadix()) + rightC.getRadix() - BumpOffset) {
                            rightC.x = this.getLeftHouse().getBoader(Left, rightC.getRadix()) + rightC.getRadix() - BumpOffset;
                            rightMoveAmount = 0;
                        }
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

        // finally we check if they bump
        if (leftC.isBump() && rightC.isBump()) {
            headCase = HeadCase.HeadBumpHead;
        } else if (leftC.isBump()) {
            headCase = HeadCase.LeftBumpHouse;
            rightHouse.setBump(true);
        } else if (rightC.isBump()) {
            headCase = HeadCase.RightBumpHouse;
            leftHouse.setBump(true);
        }

        if (!leftJoinBattle && leftC.isJoinBattle()) {
            FireLine f = new FireLine(leftC, FireLine.getFireSFromCheese(leftC), this.getLeftProjector().getBattleBorderX(leftC.getRadix(), Left));
            leftFireList.add(f);
        }

        if (!rightJoinBattle && rightC.isJoinBattle()) {
            FireLine f = new FireLine(rightC, FireLine.getFireSFromCheese(rightC), this.getRightProjector().getBattleBorderX(rightC.getRadix(), Right));
            rightFireList.add(f);
        }

    }

    private void followPreviousCheese(Cheese back, Cheese front, boolean whichSide, Projector p) {
        boolean joinBattle = back.isJoinBattle();
        float moveAmount = back.getSpeed();
        // the exceed amount of distance if we just let two cheese go
        // under their speed

        final int times = 3;

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

        if (front.isPoison()) {
            float distance = Cheese.distance(back, front) - (back.getRadix() + front.getRadix())
                    + FollowOffset;
            if (distance > -1.0F) {
                back.setPoisonAmount((short) (front.getPoisonAmount() - CheeseParameter.Poison.PoisonInfectDecay));
            }
        }

        if (!joinBattle && back.isJoinBattle() && back.getType() == Cheese.Firing) {
            FireLine f = new FireLine(back, FireLine.getFireSFromCheese(back), p.getBattleBorderX(
                    back.getRadix(), whichSide));
            if (whichSide) {
                leftFireList.add(f);
            } else {
                rightFireList.add(f);
            }
        }
    }

    private void firelineEffect() {
        // First Step: Remove the dead and reset fire effect of cheese
        removeDeadFireLine(leftFireList);
        removeDeadFireLine(rightFireList);
        // Second Step: Renew by relation with fire line, decay its strength
        // Third Step: Damage
        refreshAndDamageFireLine(leftFireList, Left, rightCheeseList);
        refreshAndDamageFireLine(rightFireList, Right, leftCheeseList);
    }

    private void removeDeadFireLine(LinkedList<FireLine> list) {
        for (Iterator<FireLine> iterator = list.iterator(); iterator.hasNext();) {
            FireLine fireLine = (FireLine) iterator.next();
            boolean temp = fireLine.isDead();
            if (temp) {
                iterator.remove();
            }
        }
    }

    private void refreshAndDamageFireLine(LinkedList<FireLine> list, boolean whichSide,
            LinkedList<Cheese> enemyCheeseList) {
        for (int i = 0; i < list.size(); i++) {
            FireLine f = list.get(i);
            // Second Step: Renew by relation with fire line, decay its strength
            f.move(whichSide);
            f.refresh();
            // Third Step: Damage
            f.fireDamage(enemyCheeseList, whichSide);
        }
    }

    private void poisonEffect(LinkedList<Cheese> list) {
        for (int i = 0; i < list.size(); i++) {
            Cheese c = list.get(i);
            c.recoverPoison();
            c.poisonDamage();
            c.decrePoisonAmount();
        }
    }

    private void poisonousCheese() {
        switch (headCase) {
            case HeadCase.HeadBumpHead: {
                Cheese left = leftCheeseList.getFirst();
                Cheese right = rightCheeseList.getFirst();
                if (!left.isDead() && left.getType() == CheeseEnum.Poison) {
                    if (right.getType() != CheeseEnum.Poison) {
                        right.setPoisonAmount(left.getPoisonMaxCount());
                        right.decreEndurance(left.getPoisonDamage());
                    }
                }
                if (!right.isDead() && right.getType() == CheeseEnum.Poison) {
                    if (left.getType() != CheeseEnum.Poison) {
                        left.setPoisonAmount(right.getPoisonMaxCount());
                        left.decreEndurance(right.getPoisonDamage());
                    }
                }
                break;
            }
            case HeadCase.LeftBumpHouse:
                Cheese left = leftCheeseList.getFirst();
                if (!left.isDead() && left.getType() == CheeseEnum.Poison) {
                    rightHouse.decreHP(left.getPoisonDamage());
                    left.decreEndurance(left.getPoisonDamage());
                }
                break;
            case HeadCase.RightBumpHouse:
                Cheese right = rightCheeseList.getFirst();
                if (!right.isDead() && right.getType() == CheeseEnum.Poison) {
                    leftHouse.decreHP(right.getPoisonDamage());
                    right.decreEndurance(right.getPoisonDamage());
                }
                break;
        }
        // if (leftCheeseList.size() == 0 || rightCheeseList.size() == 0) {
        // if (leftCheeseList.size() > 0) {
        // Cheese left = leftCheeseList.getFirst();
        // if (left.isBump() && !left.isDead()) {
        // rightHouse.decreHP(left.getPoisonDamage());
        // left.decreEndurance(left.getPoisonDamage());
        // }
        // }
        // if (rightCheeseList.size() > 0) {
        // Cheese right = rightCheeseList.getFirst();
        // if (right.isBump() && !right.isDead()) {
        // leftHouse.decreHP(right.getPoisonDamage());
        // right.decreEndurance(right.getPoisonDamage());
        // }
        // }
        // return;
        // }
    }

    private void sweatyCheese() {
        for (int i = 0; i < leftCheeseList.size(); i++) {
            Cheese c = leftCheeseList.get(i);
            if (c.getType() == CheeseEnum.Sweaty && !c.isDead()) {
                float range = c.getSweatyRange();
                float damage = c.getSweatyDamage();
                for (int j = 0; j < rightCheeseList.size(); j++) {
                    boolean toBreak = true;
                    if (Cheese.distance(rightCheeseList.get(j), c) < range) {
                        rightCheeseList.get(j).decreEndurance(damage);
                        toBreak = false;
                    }
                    if (rightHouse.getBoader(Right, c.getRadix()) - c.x < range) {
                        rightHouse.decreHP(damage);
                        toBreak = false;
                    }
                    if (toBreak) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < rightCheeseList.size(); i++) {
            Cheese c = rightCheeseList.get(i);
            if (c.getType() == CheeseEnum.Sweaty && !c.isDead()) {
                float range = c.getSweatyRange();
                float damage = c.getSweatyDamage();
                for (int j = 0; j < leftCheeseList.size(); j++) {
                    boolean toBreak = true;
                    if (Cheese.distance(leftCheeseList.get(j), c) < range) {
                        leftCheeseList.get(j).decreEndurance(damage);
                        toBreak = false;
                    }
                    if (c.x - leftHouse.getBoader(Left, c.getRadix()) < range) {
                        leftHouse.decreHP(damage);
                        toBreak = false;
                    }
                    if (toBreak) {
                        break;
                    }
                }
            }
        }
        switch (headCase) {
            case HeadCase.HeadBumpHead: {
                Cheese left = leftCheeseList.getFirst();
                Cheese right = rightCheeseList.getFirst();
                if (!left.isDead() && left.getType() == CheeseEnum.Sweaty) {
                    right.decreEndurance(left.getSweatyDamage());
                }
                if (!right.isDead() && right.getType() == CheeseEnum.Sweaty) {
                    left.decreEndurance(right.getSweatyDamage());
                }
                break;
            }
            case HeadCase.LeftBumpHouse:
                Cheese left = leftCheeseList.getFirst();
                if (!left.isDead() && left.getType() == CheeseEnum.Sweaty) {
                    rightHouse.decreHP(left.getSweatyDamage());
                    left.decreEndurance(left.getSweatyDamage());
                }
                break;
            case HeadCase.RightBumpHouse:
                Cheese right = rightCheeseList.getFirst();
                if (!right.isDead() && right.getType() == CheeseEnum.Sweaty) {
                    leftHouse.decreHP(right.getSweatyDamage());
                    right.decreEndurance(right.getSweatyDamage());
                }
                break;
        }
    }

    private void normalAndFiringCheese() {
        switch (headCase) {
            case HeadCase.HeadBumpHead: {
                Cheese left = leftCheeseList.getFirst();
                Cheese right = rightCheeseList.getFirst();
                if (!left.isDead()) {
                    if (left.getType() == CheeseEnum.Original) {
                        right.decreEndurance(left.getNormalDamage());
                    } else if (left.getType() == CheeseEnum.Firing) {
                        right.decreEndurance(left.getFiringDamage());
                        left.decreEndurance(left.getFiringDamage());
                    }
                }
                if (!right.isDead()) {
                    if (right.getType() == CheeseEnum.Original) {
                        left.decreEndurance(right.getNormalDamage());
                    } else if (right.getType() == CheeseEnum.Firing) {
                        left.decreEndurance(right.getFiringDamage());
                        right.decreEndurance(right.getFiringDamage());
                    }
                }
                break;
            }
            case HeadCase.LeftBumpHouse:
                Cheese left = leftCheeseList.getFirst();
                if (!left.isDead()) {
                    if (left.getType() == CheeseEnum.Original) {
                        rightHouse.decreHP(left.getNormalDamage());
                        left.decreEndurance(left.getNormalDamage());
                    } else if (left.getType() == Cheese.Firing) {
                        rightHouse.decreHP(left.getFiringDamage());
                        left.decreEndurance(left.getFiringDamage());
                    }
                }
                break;
            case HeadCase.RightBumpHouse:
                Cheese right = rightCheeseList.getFirst();
                if (!right.isDead()) {
                    if (right.getType() == CheeseEnum.Original) {
                        leftHouse.decreHP(right.getNormalDamage());
                        right.decreEndurance(right.getNormalDamage());
                    } else if (right.getType() == Cheese.Firing) {
                        leftHouse.decreHP(right.getFiringDamage());
                        right.decreEndurance(right.getFiringDamage());
                    }
                }
                break;
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

class HeadCase {

    public static final byte NoBumpHappen = 0;
    public static final byte HeadBumpHead = 1;
    public static final byte LeftBumpHouse = 2;
    public static final byte RightBumpHouse = 3;
}