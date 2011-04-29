package NMLab.team10.rollingthecheese;

import android.view.SurfaceView;
import android.content.Context;                        
import android.content.res.Resources;                   
import android.graphics.Bitmap;                        
import android.graphics.BitmapFactory;                 
import android.graphics.Canvas;                                               
import android.view.SurfaceHolder;                      


public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    GameDrawThread gameDrawThread;
    Bitmap backgroundBitmap;
    RollingCheeseActivity father;
    
    public GameView(RollingCheeseActivity father){
        super(father);
        this.father = father;
        getHolder().addCallback(this);
        initBitmap(father);
        gameDrawThread = new GameDrawThread(this, getHolder());
        
       
    }
    
    public void initBitmap(Context context){
        Resources r = context.getResources();
        backgroundBitmap = BitmapFactory.decodeResource(r, R.drawable.cheesegame);
        
    }
    public void doDraw(Canvas canvas){
        canvas.drawBitmap(backgroundBitmap, 0,0, null);
       
        
    }
    
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){}
    public void surfaceCreated(SurfaceHolder holder){
        if(!father.gameThread.isAlive()){
            father.gameThread.isRunning = true;
            father.gameThread.start();
        }
        if(!gameDrawThread.isAlive()){         
            gameDrawThread.isRunning = true;
            gameDrawThread.start();
        }
        
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(gameDrawThread.isAlive()){             
            gameDrawThread.isRunning = false;
      
        }
        if(father.gameThread.isAlive()){             
            father.gameThread.isRunning = false;
        }           
    }
    
    
}
