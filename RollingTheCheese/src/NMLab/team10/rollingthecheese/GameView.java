package NMLab.team10.rollingthecheese;


import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    GameDrawThread gameDrawThread;
    Bitmap backgroundBitmap;
    RollingCheeseActivity father;
    
    Scroll scroll;
    private class Scroll{
        private static final int leftEdge = -800;
        private static final int rightEdge = 0;
        private static final int quantizeConst = 40; 
            
             

        public boolean isPressing;
        public boolean prev_isPressing;
        public boolean isRecovering;
        
        public int posX;
        public double V;
        public int fingerX;
        public int prev_fingerX;
        public double recover_a;
        public long prev_time;
        
        public Scroll(){
            posX = 0;
            prev_isPressing = false;
            isPressing = false;
            isRecovering = false;
            recover_a=0;
            V = 0;
            fingerX = 0;
            prev_fingerX = 0;
            prev_time = 0;
            
        }
        
        private int decadeDrag(){
            if(posX>rightEdge + 3*quantizeConst||posX<leftEdge - 3*quantizeConst){
                return 0;   
            //}else if(posX>rightEdge + 2*quantizeConst||posX<leftEdge - 2*quantizeConst){
             //   return (fingerX-prev_fingerX)/5;
            //}else if(posX>rightEdge + 1*quantizeConst||posX<leftEdge - 1*quantizeConst){
             //   return (fingerX-prev_fingerX)/3;
            }else{
                return (fingerX-prev_fingerX)/10;
            }
        }
        public int doCalc(){
            //Log.e("scroll posX",String.format("%d", posX));
            long cur_time = System.nanoTime();
            long delta_time = cur_time - prev_time;
            prev_time = cur_time;
            if(isPressing){
                if(prev_isPressing == false){      // run when finger down
                    prev_isPressing = true;                   
                    prev_fingerX = fingerX;
                    isRecovering = false;
                    recover_a = 0;
                    V = 0;
                    return posX;
                }else{                             //run when move
                    if((posX>rightEdge && fingerX-prev_fingerX > 0)
                     || (posX<leftEdge && fingerX-prev_fingerX < 0)){
                        posX+= decadeDrag();
                        
                    }else{
                        posX+= (fingerX - prev_fingerX);
                    
                    }
                       
                    V = (double)(fingerX - prev_fingerX)/(double)delta_time;
                    
                    prev_fingerX = fingerX;
                    return posX;
                }
            }else{                                 //run when finger up
                prev_isPressing = false;
                posX += V*delta_time;
                if(!isRecovering){
                    if(posX>rightEdge&&V>0 ){
                        V = -0.000000004 * (double)(posX-rightEdge);
                        recover_a = 0.000000000000000008 * (double)(posX-rightEdge);
                        isRecovering = true;
                    }else if(posX<leftEdge&&V<0){
                        V = -0.000000004 * (double)(posX-leftEdge);
                        recover_a = 0.000000000000000008 * (double)(posX-leftEdge);
                        isRecovering = true;
                    }
                }
                
                posX+=(V*delta_time)+0.5*recover_a*delta_time*delta_time;
                if(V*(V+recover_a*delta_time)<0){
                    isRecovering = false;
                    recover_a = 0;
                    V=0;
                }else{                     
                    V+=recover_a*delta_time;
                }
                
                return posX;
            }
            //prev_time = cur_time;
            //Log.e("Scroll",String.format("p:%d", posX));
            //return posX;
            
        }
    }
    
    public GameView(RollingCheeseActivity father){
        super(father);
        this.father = father;
        getHolder().addCallback(this);
        initBitmap(father);
        gameDrawThread = new GameDrawThread(this, getHolder());
        scroll = new Scroll();
       
    }
    
    public void initBitmap(Context context){
        Resources r = context.getResources();
        backgroundBitmap = BitmapFactory.decodeResource(r, R.drawable.cheesegame);
        
    }
    public void doDraw(Canvas canvas){
        int tranX = scroll.doCalc();
        Log.e("",String.format("%d ", tranX));
        canvas.translate(tranX, 0);
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
     
    
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int)event.getX();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            scroll.isPressing = true;
            scroll.fingerX = x;
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            scroll.fingerX = x;
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            scroll.isPressing = false;
        }
        return true;
    }
    
    
    
    
}
