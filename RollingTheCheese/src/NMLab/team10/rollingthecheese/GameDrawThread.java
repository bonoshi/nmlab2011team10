package NMLab.team10.rollingthecheese;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameDrawThread extends Thread {
    public boolean isRunning;
    GameView father;
    SurfaceHolder surfaceHolder;
    int sleepSpan = 30;
    
    
    public GameDrawThread(GameView father) {
        this.father = father;

        
    }

    public void run(){

        while(isRunning){
         
         
            father.postInvalidate();
            try{
                Thread.sleep(sleepSpan);        
            }
            catch(Exception e){
                e.printStackTrace();          
            }
        }
    }
}
