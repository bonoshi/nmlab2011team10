package NMLab.team10.rollingthecheese;





public class GameDrawThread extends Thread {
    public boolean isRunning;
    GameView father;
    int sleepSpan = 20;
    
    
    public GameDrawThread(GameView father) {
        this.father = father;
    }

    public void run(){
        while(isRunning){ 
            father.postInvalidate(); // make GameView to do onDraw()
            try{
                Thread.sleep(sleepSpan);        
            }
            catch(Exception e){
                e.printStackTrace();          
            }
        }
    }
}
