package NMLab.team10.rollingthecheese;

import java.io.IOException;
import java.util.Date;

import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.ServerGameSetting;
import NMLab.team10.rollingthecheese.gameSetting.SynMessageData;

public class GameCalThread extends Thread {

    RollingCheeseActivity father;
    ServerGameSetting setting;

    private EventQueueCenter eventCenter = null;
    private SynMessageData synMessageData = null;// need to inspect if "null"
    private DisplayData displayData = null;

    private boolean pause = true;
    private boolean stop = false;

    private boolean hasNewData = false;
    private boolean isTwoPlayer = false;

    public GameCalThread(RollingCheeseActivity father, ServerGameSetting sgs,
            boolean isTwoPlayer) {
        this.father = father;
        this.setting = sgs;
        this.isTwoPlayer = isTwoPlayer;
        eventCenter = new EventQueueCenter(sgs,father);
        synMessageData = new SynMessageData(sgs, eventCenter, Right);
        displayData = new DisplayData();
        father.displayData = displayData;
    }

    Date timeLast = null;

    public void run() {
        while (!stop) {
            while (pause) {
                if(stop){
                    return;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            timeLast = new Date(System.currentTimeMillis());

            // Step 1: (1)fetch cow events and create cow
            // (2)fetch cheese events and create cheese
            eventCenter.fetchCow();
            eventCenter.fetchCheese();

            // Step 2: (1)refresh local game display
            // (2)prepare data for server
            // (3)send to client
            // local game display
            synMessageData = new SynMessageData(setting, eventCenter, Right);
            displayData.refresh(synMessageData);
            if (isTwoPlayer) {
                try {
                    father.sendObject(synMessageData);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    this.pauseGameCal();
                    father.myHandler.obtainMessage(
                            InterThreadMsg.LinkingErrorInGame).sendToTarget();
                }
            }

            // Step 3: fetch construction event
            eventCenter.fetchCons();

            // Step 4: (1)recover destruction states
            // (2)destructions take effect at night
            eventCenter.recoverDest();
            eventCenter.conductDest();

            // Step 5: fetch event
            eventCenter.fetchEvent();

            // Step 6: Time Elapsing
            eventCenter.timeElapsing();// cheese, cow and construction time
            setting.timeElapsing();// time++, milk++, takeAction()
            while (true) {
                Date timeNow = new Date(System.currentTimeMillis());
                long interval = timeNow.getTime() - timeLast.getTime();
                if (interval < 30) {
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void refreshDisplayData(SynMessageData smd){//refresh by client blue-tooth
        displayData.refresh(smd);
    }

    //public void

    public void stopGameCal() {
        this.stop = true;
    }

    public void pauseGameCal() {
        this.pause = true;
    }

    public void resumeGameCal() {
        this.pause = false;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isStop() {
        return stop;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }

    public SynMessageData getSynMessageData() {
        return synMessageData;
    }

    public void setDisplayData(DisplayData displayData) {
        this.displayData = displayData;
    }

    public DisplayData getDisplayData() {
        return displayData;
    }

    // for blue-tooth only, hasNewData() for displayData is in the displayData
    public boolean hasNewData() {
        return hasNewData;
    }

    // for blue-tooth only, acceptData() for displayData is in the displayData
    public void acceptData() {
        this.hasNewData = false;
    }

    public EventQueueCenter getEventCenter() {
        return eventCenter;
    }

    public static final boolean Right = false;
    public static final boolean Left = true;

}
