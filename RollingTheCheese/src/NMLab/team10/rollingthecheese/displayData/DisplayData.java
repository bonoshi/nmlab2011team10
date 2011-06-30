package NMLab.team10.rollingthecheese.displayData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import android.graphics.Canvas;

import NMLab.team10.rollingthecheese.gameSetting.Cheese;
import NMLab.team10.rollingthecheese.gameSetting.CheeseMessage;
import NMLab.team10.rollingthecheese.gameSetting.Cow;
import NMLab.team10.rollingthecheese.gameSetting.CowMessage;
import NMLab.team10.rollingthecheese.gameSetting.DestructStateMessage;
import NMLab.team10.rollingthecheese.gameSetting.Farm;
import NMLab.team10.rollingthecheese.gameSetting.FireLineMessage;
import NMLab.team10.rollingthecheese.gameSetting.House;
import NMLab.team10.rollingthecheese.gameSetting.HouseMessage;
import NMLab.team10.rollingthecheese.gameSetting.Projector;
import NMLab.team10.rollingthecheese.gameSetting.SynMessageData;

public class DisplayData {

    public boolean isLeft;

    public DisplayData() {
        hasNewData = false;
        this.smd = new SynMessageData();
    }

    public boolean hasNewData() {
        return hasNewData;
    }

    public void refresh(SynMessageData smd) {
        if (smd == null)
            return;
        this.smd = smd;
        // Cheese
        addAndRefreshCheese(leftCheeseList, smd.getLeftCheeseList(), leftCheeseMap);
        addAndRefreshCheese(rightCheeseList, smd.getRightCheeseList(), rightCheeseMap);
        // Cow
        addAndRefreshCow(leftCowList, smd.getLeftCowList(), leftCowMap, Cow.Left);
        deleteCow(leftCowList, smd.getLeftCowList(), leftCowMap);
        addAndRefreshCow(rightCowList, smd.getRightCowList(), rightCowMap, Cow.Right);
        deleteCow(rightCowList, smd.getRightCowList(), rightCowMap);
        // FireLine
        addAndRefreshFire(leftFireList, smd.getLeftFireList(), leftFireMap);
        deleteFire(leftFireList, smd.getLeftFireList(), leftFireMap);
        addAndRefreshFire(rightFireList, smd.getRightFireList(), rightFireMap);
        deleteFire(rightFireList, smd.getRightFireList(), rightFireMap);
        // HouseDisplay
        leftHouse.setHouse(smd.getLeftHouse());
        rightHouse.setHouse(smd.getRightHouse());
        // Flag
        hasNewData = true;
        isLeft = smd.getWhichSide();
    }

    public void acceptData() {
        hasNewData = false;
    }

    private boolean hasNewData;

    SynMessageData smd = null;

    LinkedList<CheeseDisplay> leftCheeseList = new LinkedList<CheeseDisplay>();
    LinkedList<CheeseDisplay> rightCheeseList = new LinkedList<CheeseDisplay>();
    LinkedList<CowDisplay> leftCowList = new LinkedList<CowDisplay>();
    LinkedList<CowDisplay> rightCowList = new LinkedList<CowDisplay>();
    LinkedList<FireLineDisplay> leftFireList = new LinkedList<FireLineDisplay>();
    LinkedList<FireLineDisplay> rightFireList = new LinkedList<FireLineDisplay>();

    HashMap<Short, CheeseDisplay> leftCheeseMap = new HashMap<Short, CheeseDisplay>();
    HashMap<Short, CheeseDisplay> rightCheeseMap = new HashMap<Short, CheeseDisplay>();
    HashMap<Short, CowDisplay> leftCowMap = new HashMap<Short, CowDisplay>();
    HashMap<Short, CowDisplay> rightCowMap = new HashMap<Short, CowDisplay>();
    HashMap<Short, FireLineDisplay> leftFireMap = new HashMap<Short, FireLineDisplay>();
    HashMap<Short, FireLineDisplay> rightFireMap = new HashMap<Short, FireLineDisplay>();

    HouseDisplay leftHouse = new HouseDisplay(new HouseMessage(new House()));
    HouseDisplay rightHouse = new HouseDisplay(new HouseMessage(new House()));

    private void addAndRefreshCheese(LinkedList<CheeseDisplay> dList, LinkedList<CheeseMessage> mList,
            HashMap<Short, CheeseDisplay> map) {
        LinkedList<CheeseMessage> newAdd = new LinkedList<CheeseMessage>();
        for (Iterator<CheeseMessage> iterator = mList.iterator(); iterator.hasNext();) {
            CheeseMessage cheeseMessage = (CheeseMessage) iterator.next();
            // refresh the existing data
            if (map.containsKey(cheeseMessage.getID())) {
                map.get(cheeseMessage.getID()).setCheeseMessage(cheeseMessage);
                // add data to the end of list
            } else {
                newAdd.add(cheeseMessage);
            }
        }
        if (newAdd.size() > 0) {
            synchronized (dList) {
                for (Iterator<CheeseMessage> iterator = newAdd.iterator(); iterator.hasNext();) {
                    CheeseMessage cheeseMessage = (CheeseMessage) iterator.next();
                    CheeseDisplay cheeseDisplay = new CheeseDisplay(cheeseMessage);
                    dList.add(cheeseDisplay);
                    map.put(cheeseMessage.getID(), cheeseDisplay);
                }
            }
        }
    }

    private void addAndRefreshCow(LinkedList<CowDisplay> dList, LinkedList<CowMessage> mList,
            HashMap<Short, CowDisplay> map, boolean whichSide) {
        LinkedList<CowMessage> newAdd = new LinkedList<CowMessage>();
        for (Iterator<CowMessage> iterator = mList.iterator(); iterator.hasNext();) {
            CowMessage cowMessage = (CowMessage) iterator.next();
            // refresh the existing data
            if (map.containsKey(cowMessage.getID())) {
                map.get(cowMessage.getID()).setCowMessage(cowMessage);
                // add data to the end of list
            } else {
                newAdd.add(cowMessage);
            }
        }
        if (newAdd.size() > 0) {
            synchronized (dList) {
                for (Iterator<CowMessage> iterator = newAdd.iterator(); iterator.hasNext();) {
                    CowMessage cowMessage = (CowMessage) iterator.next();
                    CowDisplay cowDisplay = new CowDisplay(cowMessage, whichSide);   // bobuway: add left or right
                    dList.add(cowDisplay);
                    map.put(cowMessage.getID(), cowDisplay);
                }
            }
        }
    }

    private void deleteCow(LinkedList<CowDisplay> dList, LinkedList<CowMessage> mList,
            HashMap<Short, CowDisplay> map) {
        synchronized (dList) {
            for (Iterator<CowDisplay> iterator = dList.iterator(); iterator.hasNext();) {
//                if (dList.size() == mList.size())
//                    return;
                CowDisplay cowDisplay = (CowDisplay) iterator.next();
                boolean notFound = true;
                for (Iterator<CowMessage> iterator2 = mList.iterator(); iterator2.hasNext();) {
                    CowMessage cowMessage = (CowMessage) iterator2.next();
                    if (cowDisplay.getCowMessage().getID() == cowMessage.getID()) {
                        notFound = false;
                        break;
                    }
                }
                if (notFound) {
                    iterator.remove();
                    map.remove(cowDisplay.getCowMessage().getID());
                }
            }
        }
    }

    private void addAndRefreshFire(LinkedList<FireLineDisplay> dList, LinkedList<FireLineMessage> mList,
            HashMap<Short, FireLineDisplay> map) {
        LinkedList<FireLineMessage> newAdd = new LinkedList<FireLineMessage>();
        for (Iterator<FireLineMessage> iterator = mList.iterator(); iterator.hasNext();) {
            FireLineMessage fireLineMessage = (FireLineMessage) iterator.next();
            // refresh the existing data
            if (map.containsKey(fireLineMessage.getID())) {
                map.get(fireLineMessage.getID()).setFireLineMessage(fireLineMessage);
                // add data to the end of list
            } else {
                newAdd.add(fireLineMessage);
            }
        }
        if (newAdd.size() > 0) {
            synchronized (dList) {
                for (Iterator<FireLineMessage> iterator = newAdd.iterator(); iterator.hasNext();) {
                    FireLineMessage fireLineMessage = (FireLineMessage) iterator.next();
                    FireLineDisplay fireLineDisplay = new FireLineDisplay(fireLineMessage);
                    dList.add(fireLineDisplay);
                    map.put(fireLineMessage.getID(), fireLineDisplay);
                }
            }
        }
    }

    private void deleteFire(LinkedList<FireLineDisplay> dList, LinkedList<FireLineMessage> mList,
            HashMap<Short, FireLineDisplay> map) {
        synchronized (dList) {
            for (Iterator<FireLineDisplay> iterator = dList.iterator(); iterator.hasNext();) {
                if (dList.size() == mList.size())
                    return;
                FireLineDisplay fireLineDisplay = (FireLineDisplay) iterator.next();
                boolean notFound = true;
                for (Iterator<FireLineMessage> iterator2 = mList.iterator(); iterator2.hasNext();) {
                    FireLineMessage fireLineMessage = (FireLineMessage) iterator2.next();
                    if (fireLineDisplay.getFireLineMessage().getID() == fireLineMessage.getID()) {
                        notFound = false;
                        break;
                    }
                }
                if (notFound) {
                    iterator.remove();
                    map.remove(fireLineDisplay.getFireLineMessage().getID());
                }
            }
        }
    }

    public int getTime() {
        return smd.getTime();
    }

    public byte getClimate() {
        return smd.getClimate();
    }

    public byte getBackground() {
        return smd.getBackground();
    }

    public Projector getProjector() {
        if (isLeft) {
            return smd.getLeftProjector();
        } else {
            return smd.getRightProjector();
        }
    }

    public Projector getProjector(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftProjector();
        } else {
            return smd.getRightProjector();
        }
    }

    public HouseMessage getHouse() {
        if (isLeft) {
            return smd.getLeftHouse();
        } else {
            return smd.getRightHouse();
        }
    }

    public HouseMessage getHouse(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftHouse();
        } else {
            return smd.getRightHouse();
        }
    }

    public Farm getFarm() {
        if (isLeft) {
            return smd.getLeftFarm();
        } else {
            return smd.getRightFarm();
        }

    }

    public Farm getFarm(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftFarm();
        } else {
            return smd.getRightFarm();
        }

    }

    public int getMilk() {
        if (isLeft) {
            return smd.getLeftMilk();
        } else {
            return smd.getRightMilk();
        }
    }

    public int getMilk(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftMilk();
        } else {
            return smd.getRightMilk();
        }
    }

    public byte getHouseHPPercent() {
        if (isLeft) {
            return smd.getLeftHouseHPPercent();
        } else {
            return smd.getRightHouseHPPercent();
        }
    }

    public byte getHouseHPPercent(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftHouseHPPercent();
        } else {
            return smd.getRightHouseHPPercent();
        }
    }

    public LinkedList<CheeseMessage> getCheeseList() {
        if (isLeft) {
            return smd.getLeftCheeseList();
        } else {
            return smd.getRightCheeseList();
        }
    }

    public LinkedList<CheeseMessage> getCheeseList(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftCheeseList();
        } else {
            return smd.getRightCheeseList();
        }
    }

    public LinkedList<CowMessage> getCowList() {
        if (isLeft) {
            return smd.getLeftCowList();
        } else {
            return smd.getRightCowList();
        }
    }

    public LinkedList<CowMessage> getCowList(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftCowList();
        } else {
            return smd.getRightCowList();
        }
    }

    public LinkedList<FireLineMessage> getFireList() {
        if (isLeft) {
            return smd.getLeftFireList();
        } else {
            return smd.getRightFireList();
        }
    }

    public LinkedList<FireLineMessage> getFireList(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftFireList();
        } else {
            return smd.getRightFireList();
        }
    }

    public ButtonDisplay getButtonD() {
        return smd.getButtonD();
    }

    public DestructStateMessage getDSM() {
        if (isLeft) {
            return smd.getLeftDSM();
        } else {
            return smd.getRightDSM();
        }
    }

    public DestructStateMessage getDSM(boolean _isLeft) {
        if (_isLeft) {
            return smd.getLeftDSM();
        } else {
            return smd.getRightDSM();
        }
    }

    public void drawCheese(boolean whichSide, Canvas canvas) {
        LinkedList<CheeseDisplay> cList = (whichSide) ? leftCheeseList : rightCheeseList;
        synchronized (cList) {
            for (Iterator<CheeseDisplay> iterator = cList.iterator(); iterator.hasNext();) {
                CheeseDisplay cheeseDisplay = (CheeseDisplay) iterator.next();
                if (cheeseDisplay.removeDead()) {
                    iterator.remove();
                } else {
                    cheeseDisplay.draw(whichSide, canvas);
                }
            }
        }
    }

    public void drawHouse(Canvas canvas) {
        leftHouse.drawHouse(Cheese.Left, canvas);
        rightHouse.drawHouse(Cheese.Right, canvas);
    }

    public void drawStatus(Canvas canvas) {
        leftHouse.drawStatus(Cheese.Left, canvas);
        rightHouse.drawStatus(Cheese.Right, canvas);
    }

    public void drawFireLine(boolean whichSide, Canvas canvas) {
        LinkedList<FireLineDisplay> fList = (whichSide) ? leftFireList : rightFireList;
        synchronized (fList) {
            for (Iterator<FireLineDisplay> iterator = fList.iterator(); iterator.hasNext();) {
                FireLineDisplay fireLineDisplay = (FireLineDisplay) iterator.next();
                fireLineDisplay.draw(whichSide, canvas);
            }
        }
    }

    public void drawPorj(Canvas canvas){
        smd.getLeftProjector().draw(true,canvas);
        smd.getRightProjector().draw(false,canvas);
    }
}
