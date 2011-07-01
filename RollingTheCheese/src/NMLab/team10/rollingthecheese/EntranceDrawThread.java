package NMLab.team10.rollingthecheese;

public class EntranceDrawThread extends Thread {
    private boolean isRunning;

    private boolean isPause = false;

    EntranceView father;
    public boolean drawflag = false;
    int sleepSpan = 100;

    public EntranceDrawThread(EntranceView father) {
        this.father = father;
        drawflag = false;
    }

    public void setDrawflag() {
        drawflag = true;
    }

    public void cancelDrawflag() {
        drawflag = false;
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            father.postInvalidate(); // make GameView to do onDraw()
            if (isPause) {
                while (isPause) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        // e.printStackTrace();
                    }
                }
            } else {
                try {
                    Thread.sleep(sleepSpan);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        }
    }

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
