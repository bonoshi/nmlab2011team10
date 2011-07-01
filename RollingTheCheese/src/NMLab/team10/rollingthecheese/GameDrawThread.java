package NMLab.team10.rollingthecheese;

import android.util.Log;

public class GameDrawThread extends Thread {
    private boolean running;
    private boolean pause;
    GameView father;
    private boolean isNewlyCreated;

    int sleepSpan = 40;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public GameDrawThread(GameView father) {
        this.father = father;
        running = true;
        pause = false;
        isNewlyCreated = true;
    }

    public void run() {
        // if(hasBeenInit){
        // if(displayData.hasNewData()){
        // hasBeenInit = false;
        // }
        // return;
        // }
        while (running) {
            while (isNewlyCreated) {
                //Log.e("Newly", "onNewlyCreated");
                if (GameView.displayData.hasNewData())
                    isNewlyCreated = false;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (pause) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (!running) {
                    return;
                }
            }
            father.refreshButtonFrame();
            father.postInvalidate(); // make GameView to do onDraw()
            if (pause) {
                while (pause) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        // e.printStackTrace();
                    }
                }
            } else {
                try {
                    Thread.sleep(sleepSpan);
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }
        }
    }

    public void setNewlyCreated(boolean isNewlyCreated) {
        this.isNewlyCreated = isNewlyCreated;
    }

    public boolean isNewlyCreated() {
        return isNewlyCreated;
    }
}
