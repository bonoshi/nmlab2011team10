package NMLab.team10.rollingthecheese;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameDrawThread extends Thread {
    public boolean isRunning;
    GameView father;
    SurfaceHolder surfaceHolder;
    int sleepSpan = 30;

    public GameDrawThread(GameView father,SurfaceHolder holder) {
        this.father = father;
        this.surfaceHolder = holder;
        
    }
    public void run(){
        Canvas canvas = null;
        while(isRunning){
            try{
                Canvas canvas2 = new Canvas();
                father.doDraw(canvas2);
                canvas = surfaceHolder.lockCanvas(null);
                synchronized(surfaceHolder){
                    canvas = canvas2;              
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                if(canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            try{
                Thread.sleep(sleepSpan);        
            }
            catch(Exception e){
                e.printStackTrace();          
            }
            
        }
    }
}
