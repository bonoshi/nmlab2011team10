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
        Canvas canvas = null;
        while(isRunning){
           /* try{
                Canvas canvas2 = new Canvas();
                father.doDraw(canvas2);
                //BitmapDrawable allBitmapDrawable = new BitmapDrawable();
                //canvas = surfaceHolder.lockCanvas(null);
                //synchronized(surfaceHolder){
                 //   canvas.drawBitmap(allBitmapDrawable.getBitmap(), 0,0, null);             
                //}
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                if(canvas != null){
                   // surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            try{
                Thread.sleep(sleepSpan);        
            }
            catch(Exception e){
                e.printStackTrace();          
            }*/
            //Canvas canvas2 = new Canvas();
            //father.doDraw(canvas2);
            //Log.e("while","loop");
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
