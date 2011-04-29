package NMLab.team10.rollingthecheese;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class WelcomeDrawThread extends Thread {
    WelcomeView father;
    SurfaceHolder holder;
    int sleepSpan = 100;
    public boolean isRunning;
    
    public WelcomeDrawThread(WelcomeView father,SurfaceHolder holder){
        this.father = father;
        this.holder = holder;
        this.isRunning = true;
        
    }
    public void run(){
        Canvas canvas = null;           
        while(isRunning){
            try{
                canvas = holder.lockCanvas(null);
                synchronized(holder){
                    father.doDraw(canvas);              
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                if(canvas != null){
                    holder.unlockCanvasAndPost(canvas);
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
