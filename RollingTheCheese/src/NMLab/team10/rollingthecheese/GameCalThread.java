package NMLab.team10.rollingthecheese;

import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.DisplayData;
import NMLab.team10.rollingthecheese.gameSetting.ServerGameSetting;
import NMLab.team10.rollingthecheese.gameSetting.SynMessageData;

public class GameCalThread extends Thread {

    RollingCheeseActivity father;
    ServerGameSetting setting;
    EventQueueCenter eventCenter = null;

    private SynMessageData synMessageData = null;// need to inspect if "null"
    private DisplayData displayData = null;

    private boolean pause = false;
    private boolean stop = false;

    public GameCalThread(RollingCheeseActivity father, ServerGameSetting sgs) {
        this.father = father;
        this.setting = sgs;
        eventCenter = new EventQueueCenter(sgs);
        synMessageData = new SynMessageData(sgs, eventCenter, Right);
    }

    public void run() {
        while (!stop) {
            while (pause) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // Step 1: (1)fetch cow events and create cow
            // (2)fetch cheese events and create cheese
            eventCenter.fetchCow();
            eventCenter.fetchCheese();

            // Step 2: (1)refresh local game display (2)prepare data for server
            // local game display
            synMessageData = new SynMessageData(setting, eventCenter, Right);

            // Step 3: fetch construction event
            eventCenter.fetchCons();

            // Step 4: (1)recover destruction states
            // (2)destructions take effect at night
            eventCenter.recoverDest();
            eventCenter.conductDest();

            // Step 5: fetch event
            eventCenter.fetchEvent();

            //Step 6: Time Elapsing
            eventCenter.timeElapsing();//cheese, cow and construction time
            setting.timeElapsing();//time++, milk++, takeAction()
        }
    }

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

    public static final boolean Right = false;
    public static final boolean Left = true;

}
