package NMLab.team10.rollingthecheese;

public class GameDrawThread extends Thread {
    private boolean running;
    private boolean pause;
    GameView father;

    int sleepSpan = 25;

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
    }

    public void run() {
        // if(hasBeenInit){
        // if(displayData.hasNewData()){
        // hasBeenInit = false;
        // }
        // return;
        // }
        while (!GameView.displayData.hasNewData()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        while (running) {
            father.postInvalidate(); // make GameView to do onDraw()
            try {
                Thread.sleep(sleepSpan);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (pause) {
                try {
                    Thread.sleep(sleepSpan);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
