package NMLab.team10.rollingthecheese;

public class EntranceDrawThread extends Thread {
    public boolean isRunning;
    EntranceView father;
    public boolean drawflag = false;
    int sleepSpan = 100;
    
    public EntranceDrawThread(EntranceView father) {
        this.father = father;
        drawflag = false;
    }
    
    public void setDrawflag(){
        drawflag = true;
    }
    public void cancelDrawflag(){
        drawflag = false;
    }
    
    public void run() {
        isRunning = true;
        while (isRunning) {
            
               father.postInvalidate(); // make GameView to do onDraw()
            }
            try {
                Thread.sleep(sleepSpan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
}

