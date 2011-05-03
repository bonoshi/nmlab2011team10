package NMLab.team10.rollingthecheese.event;

import java.util.LinkedList;

import NMLab.team10.rollingthecheese.gameSetting.Cheese;
import NMLab.team10.rollingthecheese.gameSetting.CheeseParameter;
import NMLab.team10.rollingthecheese.gameSetting.Cow;
import NMLab.team10.rollingthecheese.gameSetting.CowParameter;
import NMLab.team10.rollingthecheese.gameSetting.DestructParameter;
import NMLab.team10.rollingthecheese.gameSetting.DestructState;
import NMLab.team10.rollingthecheese.gameSetting.Farm;
import NMLab.team10.rollingthecheese.gameSetting.House;
import NMLab.team10.rollingthecheese.gameSetting.Projector;
import NMLab.team10.rollingthecheese.gameSetting.ServerGameSetting;

public class EventQueueCenter {

    public EventQueueCenter() {
        // TODO Auto-generated constructor stub
    }

    ServerGameSetting setting = null;

    public void setSetting(ServerGameSetting s) {
        setting = s;
    }

    public void fetchEvent() {
        for (int i = 0; i < 2; i++) {
            boolean whichSide = (i == 0) ? Left : Right;
            EventQueue eventQ = (whichSide) ? leftEventQueue : rightEventQueue;
            synchronized (eventQ) {
                while (eventQ.getSize() > 0) {
                    byte event = popEvent(whichSide);
                    if (event == EventEnum.PurchaseCow) {
                        EventQueue cowQue = (whichSide) ? leftCowQueue : rightCowQueue;
                        if (cowQue.getSize() > 0)// ensure only one cow in list!
                            continue;

                        LinkedList<Cow> cowList = (whichSide) ? setting.getLeftCowList() : setting.getLeftCowList();
                        int cowNum = cowList.size();
                        if (cowNum >= CowParameter.MaxCow) {
                            continue;
                        }

                        int milk = (whichSide) ? setting.getLeftMilk() : setting.getRightMilk();
                        if ((milk -= CowParameter.getPrice(cowNum)) >= 0) {
                            addCowEvent(event, whichSide);
                            cowQue.setWaitingTime(CowParameter.WaitingTime);
                        } else {
                            continue;
                        }

                        if (whichSide) {
                            setting.setLeftMilk(milk);
                        } else {
                            setting.setRightMilk(milk);
                        }

                    } else if ((event >= EventEnum.CheeseStart) && (event <= EventEnum.CheeseEnd)) {
                        int milk = (whichSide) ? setting.getLeftMilk() : setting.getRightMilk();
                        EventQueue cheeseQue = (whichSide) ? leftCheeseQueue : rightCheeseQueue;
                        if ((milk -= CheeseParameter.getPrice(event)) >= 0) {
                            if (cheeseQue.getSize() == 0)
                                cheeseQue.setWaitingTime(CheeseParameter.getTime(event));
                            addCheeseEvent(event, whichSide);
                        } else {
                            continue;
                        }

                        if (whichSide) {
                            setting.setLeftMilk(milk);
                        } else {
                            setting.setRightMilk(milk);
                        }

                    } else if (event == EventEnum.CancelCheese) {
                        EventQueue cheeseQue = (whichSide) ? leftCheeseQueue : rightCheeseQueue;
                        int milk = (whichSide) ? setting.getLeftMilk() : setting.getRightMilk();
                        while (cheeseQue.getSize() > 0) {
                            byte cheeseE = cheeseQue.pop();
                            milk += CheeseParameter.getPrice(cheeseE);
                        }

                        cheeseQue.setWaitingTime(0);
                        if (whichSide) {
                            setting.setLeftMilk(milk);
                        } else {
                            setting.setRightMilk(milk);
                        }

                    } else if ((event >= EventEnum.ConsStart) && (event <= EventEnum.ConsEnd)) {
                        addConsToQueue(event, whichSide);

                    } else if ((event >= EventEnum.DestStart) && (event <= EventEnum.DestEnd)) {
                        triggerDest(event, whichSide);

                    } else if (event == EventEnum.Quit) {
                        // bonoshi: not yet complete
                    } else if (event == EventEnum.Pause) {
                        // bonoshi: not yet complete
                    } else if (event == EventEnum.Surrender) {
                        // bonoshi: not yet complete
                    } else {
                        // should not happen
                    }
                }
            }
        }
    }

    public void fetchCheese() {
        for (int i = 0; i < 2; i++) {
            boolean whichSide = (i == 0) ? Left : Right;
            EventQueue cheeseQue = (whichSide) ? leftCheeseQueue : rightCheeseQueue;

            synchronized (cheeseQue) {
                if (cheeseQue.getSize() > 0 && cheeseQue.getWaitingTime() <= 0) {
                    // byte event = getLeftCheese();
                    // 時間到了直接拿cheese，若Construction正在進行projector則無論如何都不拿
                    // 根據不同cheese建立不同種類、威力、大小、速度……
                    // 加入cheese list
                    EventQueue consQue = (whichSide) ? leftConsQueue : rightConsQueue;
                    if(consQue.getSize()>0){
                        if(consQue.peak()==EventEnum.Projector)
                            continue;
                    }

                    boolean isSmallCrisis = (whichSide) ? setting.getLeftDestruct().smallCheese : setting
                            .getRightDestruct().smallCheese;
                    House house = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                    Projector projector = (whichSide) ? setting.getLeftProjector() : setting.getRightProjector();

                    byte event = popCheeseEvent(whichSide);
                    Cheese cheese = null;
                    cheese = CheeseParameter.createCheese(event, isSmallCrisis);
                    cheese.initialPara(house, projector);
                    if (whichSide) {
                        setting.getLeftCheeseList().add(cheese);
                    } else {
                        setting.getRightCheeseList().add(cheese);
                    }
                    if (cheeseQue.getSize() > 0)
                        cheeseQue.setWaitingTime(CheeseParameter.getTime(cheeseQue.peak()));
                    else
                        cheeseQue.setWaitingTime(0);
                }
            }
        }
    }

    public void fetchCons() {
        for (int i = 0; i < 2; i++) {
            boolean whichSide = (i == 0) ? Left : Right;
            EventQueue consQue = (whichSide) ? leftConsQueue : rightConsQueue;

            synchronized (consQue) {
                if (consQue.getSize() > 0 && consQue.getWaitingTime() <= 0) {

                    byte event = popConsEvent(whichSide);
                    switch (event) {
                        case EventEnum.Projector: {
                            Projector p = (whichSide) ? setting.getLeftProjector() : setting.getRightProjector();
                            p.upgrade();
                        }
                        case EventEnum.CheeseProd: {
                            House h = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                            h.upgradeProd();
                        }
                        case EventEnum.CheeseQual: {
                            House h = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                            h.upgradeQual();
                        }
                        case EventEnum.MilkProd: {
                            Farm f = (whichSide) ? setting.getLeftFarm() : setting.getRightFarm();
                            f.upgradeProd();
                        }
                    }

                    if (consQue.getSize() > 0) {
                        consQue.setWaitingTime(getConsTime(consQue.peak(), whichSide));
                    } else {
                        consQue.setWaitingTime(0);
                    }
                }
            }
        }
    }

    public int getConsPrice(byte event, boolean whichSide) {
        switch (event) {
            case EventEnum.Projector: {
                Projector p = (whichSide) ? setting.getLeftProjector() : setting.getRightProjector();
                return p.getUpMilk();
            }
            case EventEnum.CheeseProd: {
                House h = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                return h.getUpProdMilk();
            }
            case EventEnum.CheeseQual: {
                House h = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                return h.getUpQualMilk();
            }
            case EventEnum.MilkProd: {
                Farm f = (whichSide) ? setting.getLeftFarm() : setting.getRightFarm();
                return f.getUpProdMilk();
            }
        }
        return Integer.MAX_VALUE;
    }

    public int getConsTime(byte event, boolean whichSide) {
        switch (event) {
            case EventEnum.Projector: {
                Projector p = (whichSide) ? setting.getLeftProjector() : setting.getRightProjector();
                return p.getUpTime();
            }
            case EventEnum.CheeseProd: {
                House h = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                return h.getUpProdTime();
            }
            case EventEnum.CheeseQual: {
                House h = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                return h.getUpQualTime();
            }
            case EventEnum.MilkProd: {
                Farm f = (whichSide) ? setting.getLeftFarm() : setting.getRightFarm();
                return f.getUpProdTime();
            }
        }
        return 0;
    }

    public boolean getConsCanUpgrade(byte event, boolean whichSide) {
        switch (event) {
            case EventEnum.Projector: {
                Projector p = (whichSide) ? setting.getLeftProjector() : setting.getRightProjector();
                return p.canUpgrade();
            }
            case EventEnum.CheeseProd: {
                House h = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                return h.canUpgradeProd();
            }
            case EventEnum.CheeseQual: {
                House h = (whichSide) ? setting.getLeftHouse() : setting.getRightHouse();
                return h.canUpgradeQual();
            }
            case EventEnum.MilkProd: {
                Farm f = (whichSide) ? setting.getLeftFarm() : setting.getRightFarm();
                return f.canUpgrade();
            }
        }
        return false;
    }

    public void triggerDest(byte desEvent, boolean whichSide) {// trigger
        DestructState ds = null;
        int milk = 0;
        if (whichSide) {
            ds = setting.getLeftDestruct();
            milk = setting.getLeftMilk();
        } else {
            ds = setting.getRightDestruct();
            milk = setting.getRightMilk();
        }
        switch (desEvent) {
            case EventEnum.IntoTheWild: {
                if (!ds.fenseDisplay && (milk -= DestructParameter.FenseCost) >= 0) {
                    ds.fenseTriggered = true;
                    ds.fenseDisplay = true;
                } else {
                    return;
                }
            }
            case EventEnum.BlackOut: {
                if (!ds.powerDisplay && (milk -= DestructParameter.PowerCost) >= 0) {
                    ds.powerTriggered = true;
                    ds.powerDisplay = true;
                } else {
                    return;
                }
                break;
            }
            case EventEnum.MiceArmy: {
                if (!ds.smallCheeseDisplay && (milk -= DestructParameter.SmallCost) >= 0) {
                    ds.smallCheeseTriggered = true;
                    ds.smallCheeseDisplay = true;
                } else {
                    return;
                }
                break;
            }
            case EventEnum.LazyWeekend: {
                if (!ds.slowCheeseDisplay && (milk -= DestructParameter.SlowCost) >= 0) {
                    ds.slowCheeseTriggered = true;
                    ds.slowCheeseDisplay = true;
                } else {
                    return;
                }
                break;
            }
            case EventEnum.MilkLeak: {
                if (!ds.milkDisplay && (milk -= DestructParameter.MilkCost) >= 0) {
                    ds.milkTriggered = true;
                    ds.milkDisplay = true;
                } else {
                    return;
                }
                break;
            }
        }
        if (whichSide) {
            setting.setLeftMilk(milk);
        } else {
            setting.setRightMilk(milk);
        }
    }

    public void addConsToQueue(byte event, boolean whichSide) {
        EventQueue consQue = (whichSide) ? leftConsQueue : rightConsQueue;
        int milk = (whichSide) ? setting.getLeftMilk() : setting.getRightMilk();

        if (consQue.getSize() > 0) {
            if (consQue.findEvent(event)) {// cancel the construction
                boolean isHead = consQue.isHead(event);
                consQue.removeEvent(event);
                milk += getConsPrice(event, whichSide);
                if (whichSide) {
                    setting.setLeftMilk(milk);
                } else {
                    setting.setRightMilk(milk);
                }
                if (isHead) {
                    if (consQue.getSize() > 0) {
                        consQue.setWaitingTime(getConsTime(consQue.peak(), whichSide));
                    } else {
                        consQue.setWaitingTime(0);
                    }
                }
                return;
            }
        }

        if (!getConsCanUpgrade(event, whichSide))
            return;

        if ((milk -= getConsPrice(event, whichSide)) >= 0) {
            if (consQue.getSize() > 0) {
                consQue.setWaitingTime(getConsTime(consQue.peak(), whichSide));
            } else {
                consQue.setWaitingTime(0);
            }
            addCheeseEvent(event, whichSide);
        } else {
            return;
        }

        if (whichSide) {
            setting.setLeftMilk(milk);
        } else {
            setting.setRightMilk(milk);
        }
    }

    public void conductDest() {
        DestructState ds = null;
        for (int i = 0; i < 2; i++) {
            ds = (i == 0) ? setting.getLeftDestruct() : setting.getRightDestruct();
            if (ds.fenseTriggered && setting.isNight()) {
                ds.fenseTriggered = false;
                ds.fense = true;
                ds.fenseCountDown = DestructParameter.FenseTime;
            }
            if (ds.powerTriggered && setting.isNight()) {
                ds.powerTriggered = false;
                ds.power = true;
                ds.powerCountDown = DestructParameter.PowerTime;
            }
            if (ds.smallCheeseTriggered && setting.isNight()) {
                ds.smallCheeseTriggered = false;
                ds.smallCheese = true;
                ds.smallCheeseCountDown = DestructParameter.SmallTime;
            }
            if (ds.slowCheeseTriggered && setting.isNight()) {
                ds.slowCheeseTriggered = false;
                ds.slowCheese = true;
                ds.slowCheeseCountDown = DestructParameter.SlowTime;
            }
            if (ds.milkTriggered && setting.isNight()) {
                ds.milkTriggered = false;
                ds.milk = true;
                ds.milkCountDown = DestructParameter.MilkTime;
                LinkedList<Cow> cowList = (i == 0) ? setting.getLeftCowList() : setting.getRightCowList();
                for (int j = 0; j < cowList.size(); j++) {
                    Cow cow = cowList.get(j);
                    cow.setStatus(Cow.Leak);
                }
            }
        }
    }

    public void recoverDest() {
        // left right
        // GlobalParameter.FramePeriod
        // Effect:
        // 1. Into night => Those in display take effect and still display on
        // 2. At night => Take effect immediately
        // 3. Into date => Display off
        // 3.1. Fence and Power will off immediately
        // 3.2. Other will still in effect
        DestructState ds = null;
        for (int i = 0; i < 2; i++) {
            ds = (i == 0) ? setting.getLeftDestruct() : setting.getRightDestruct();
            if (ds.fense && (ds.fenseCountDown <= 0 || !setting.isNight())) {
                ds.fense = false;
                ds.fenseDisplay = false;
            }
            if (ds.power && (ds.powerCountDown <= 0 || !setting.isNight())) {
                ds.power = false;
                ds.powerDisplay = false;
            }
            if (ds.smallCheese && (ds.smallCheeseCountDown <= 0)) {
                ds.smallCheese = false;
                ds.smallCheeseDisplay = false;
            }
            if (ds.slowCheese && (ds.slowCheeseCountDown <= 0)) {
                ds.slowCheese = false;
                ds.slowCheeseDisplay = false;
            }
            if (ds.milk && (ds.milkCountDown <= 0)) {
                ds.milk = false;
                ds.milkDisplay = false;
                LinkedList<Cow> cowList = (i == 0) ? setting.getLeftCowList() : setting.getRightCowList();
                for (int j = 0; j < cowList.size(); j++) {
                    Cow cow = cowList.get(j);
                    cow.setStatus(Cow.Normal);
                }
            }
        }
    }

    public void fetchCow() {
        for (int i = 0; i < 2; i++) {
            boolean whichSide = (i == 0) ? Left : Right;
            EventQueue cowQueue = (whichSide) ? leftCowQueue : rightCowQueue;

            synchronized (cowQueue) {
                if (cowQueue.getSize() > 0 && cowQueue.getWaitingTime() <= 0) {
                    popCheeseEvent(whichSide);

                    Cow cow = new Cow();// bonoshi: need to initialize!!!!!!
                    if (whichSide) {
                        setting.getLeftCowList().add(cow);
                    } else {
                        setting.getRightCowList().add(cow);
                    }
                    if (cowQueue.getSize() > 0) {
                        cowQueue.setWaitingTime(CowParameter.WaitingTime);
                    } else {
                        cowQueue.setWaitingTime(0);
                    }
                }
            }
        }
    }

    public void addEvent(Byte i, boolean whichSide) {
        if (whichSide) {
            synchronized (leftEventQueue) {
                leftEventQueue.push(i);
            }
        } else {
            synchronized (rightEventQueue) {
                rightEventQueue.push(i);
            }
        }
    }

    public Byte popEvent(boolean whichSide) {
        Byte temp = 0;
        if (whichSide) {
            synchronized (leftEventQueue) {
                temp = leftEventQueue.pop();
            }
        } else {
            synchronized (rightEventQueue) {
                temp = rightEventQueue.pop();
            }
        }
        return temp;
    }

    public void addCheeseEvent(Byte i, boolean whichSide) {
        if (whichSide) {
            synchronized (leftCheeseQueue) {
                leftCheeseQueue.push(i);
            }
        } else {
            synchronized (rightCheeseQueue) {
                rightCheeseQueue.push(i);
            }
        }
    }

    public Byte popCheeseEvent(boolean whichSide) {
        Byte temp = 0;
        if (whichSide) {
            synchronized (leftCheeseQueue) {
                temp = leftCheeseQueue.pop();
            }
        } else {
            synchronized (rightCheeseQueue) {
                temp = rightCheeseQueue.pop();
            }
        }
        return temp;
    }

    public void addCowEvent(Byte i, boolean whichSide) {
        if (whichSide) {
            synchronized (leftCowQueue) {
                leftCowQueue.push(i);
            }
        } else {
            synchronized (rightCowQueue) {
                rightCowQueue.push(i);
            }
        }
    }

    public Byte popCowEvent(boolean whichSide) {
        Byte temp = 0;
        if (whichSide) {
            synchronized (leftCowQueue) {
                temp = leftCowQueue.pop();
            }
        } else {
            synchronized (rightCowQueue) {
                temp = rightCowQueue.pop();
            }
        }
        return temp;
    }

    public void addDesEvent(Byte i, boolean whichSide) {
        if (whichSide) {
            synchronized (leftDesQueue) {
                leftDesQueue.push(i);
            }
        } else {
            synchronized (rightDesQueue) {
                rightDesQueue.push(i);
            }
        }
    }

    public Byte popDesEvent(boolean whichSide) {
        Byte temp = 0;
        if (whichSide) {
            synchronized (leftDesQueue) {
                temp = leftDesQueue.pop();
            }
        } else {
            synchronized (rightDesQueue) {
                temp = rightDesQueue.pop();
            }
        }
        return temp;
    }

    public void addConsEvent(Byte i, boolean whichSide) {
        if (whichSide) {
            synchronized (leftConsQueue) {
                leftConsQueue.push(i);
            }
        } else {
            synchronized (rightConsQueue) {
                rightConsQueue.push(i);
            }
        }
    }

    public Byte popConsEvent(boolean whichSide) {
        Byte temp = 0;
        if (whichSide) {
            synchronized (leftConsQueue) {
                temp = leftConsQueue.pop();
            }
        } else {
            synchronized (rightConsQueue) {
                temp = rightConsQueue.pop();
            }
        }
        return temp;
    }

    private EventQueue leftEventQueue = new EventQueue();
    private EventQueue rightEventQueue = new EventQueue();

    private EventQueue leftCheeseQueue = new EventQueue();
    private EventQueue leftCowQueue = new EventQueue();
    private EventQueue leftDesQueue = new EventQueue();
    private EventQueue leftConsQueue = new EventQueue();
    private EventQueue rightCheeseQueue = new EventQueue();
    private EventQueue rightCowQueue = new EventQueue();
    private EventQueue rightDesQueue = new EventQueue();
    private EventQueue rightConsQueue = new EventQueue();

    public static final boolean Right = false;
    public static final boolean Left = true;

}
