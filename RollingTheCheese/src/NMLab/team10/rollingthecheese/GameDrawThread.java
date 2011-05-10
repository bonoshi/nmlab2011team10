package NMLab.team10.rollingthecheese;

public class GameDrawThread extends Thread {
    public boolean isRunning;
    GameView father;
    GameCalThread gtc;
    int sleepSpan = 20;

    public GameDrawThread(GameView father, GameCalThread gct) {
        this.father = father;
        this.gtc = gct;
    }

    public void run() {
        father.setGct(gtc);
        gtc.start();
        gtc.resumeGameCal();
        while (isRunning) {
            father.postInvalidate(); // make GameView to do onDraw()
            try {
                Thread.sleep(sleepSpan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
