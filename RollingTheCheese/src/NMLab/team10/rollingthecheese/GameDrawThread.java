package NMLab.team10.rollingthecheese;

public class GameDrawThread extends Thread {
    public boolean isRunning;
    GameView father;

    int sleepSpan = 10;

    public GameDrawThread(GameView father) {
        this.father = father;
    }

    public void run() {
//      if(hasBeenInit){
//      if(displayData.hasNewData()){
//          hasBeenInit = false;
//      }
//      return;
//  }
        while(!GameView.displayData.hasNewData()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
