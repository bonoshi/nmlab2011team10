package NMLab.team10.rollingthecheese;

import java.io.IOException;
import java.util.Date;

import android.util.Log;

import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.AppMessage;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;
import NMLab.team10.rollingthecheese.gameSetting.ServerGameSetting;
import NMLab.team10.rollingthecheese.gameSetting.SynMessageData;

public class GameCalThread extends Thread {

    RollingCheeseActivity father;
    ServerGameSetting setting;

    private EventQueueCenter eventCenter = null;
    private SynMessageData synMessageData = null;// need to inspect if "null"
    private SynMessageData clientSMD = null;

    private boolean pause = true;
    private boolean stop = false;

    private boolean hasNewData = false;

    public GameCalThread(RollingCheeseActivity father, ServerGameSetting sgs) {
        this.father = father;
        this.setting = sgs;
        eventCenter = new EventQueueCenter(sgs, father);
        synMessageData = new SynMessageData(sgs, eventCenter, Left);
        clientSMD = new SynMessageData(sgs, eventCenter, Right);
    }

    public void sendEvent(byte event) {
        eventCenter.addEvent(event, Right);
    }

    Date timeLast = null;

    public void run() {
        while (!stop) {

            timeLast = new Date(System.currentTimeMillis());

            // Step 1: (1)fetch cow events and create cow
            // (2)fetch cheese events and create cheese
            eventCenter.fetchCow();
            eventCenter.fetchCheese();

            // Step 2: (1)refresh local game display
            // (2)prepare data for server
            // (3)send to client
            // local game display
            //Log.e("Newly", "onCalculated");
            synMessageData = new SynMessageData(setting, eventCenter, Left);
            if (RollingCheeseActivity.isTwoPlayer()) {
                clientSMD = new SynMessageData(setting, eventCenter, Right);
                AppMessage am = new AppMessage();
                am.setType(EventEnum.Data);
                am.setSmd(clientSMD);
                try {
                    // Toast toast = Toast.makeText(father, "Try to send",
                    // Toast.LENGTH_SHORT);
                    // toast.show();
                    father.sendMessage(am);
                    GameView.refreshPPS(true);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    father.myHandler.sendEmptyMessage(InterThreadMsg.ServerFailToSend);
                    e.printStackTrace();
                }
            }
            father.refreshDisplayData(synMessageData);

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

            if (pause) {
                while (pause) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                while (true) {
                    Date timeNow = new Date(System.currentTimeMillis());
                    long interval = timeNow.getTime() - timeLast.getTime();
                    if (pause) {
                        while (pause) {
                            try {
                                Thread.sleep(0);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    } else if (interval < GlobalParameter.FramePeriod) {
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
