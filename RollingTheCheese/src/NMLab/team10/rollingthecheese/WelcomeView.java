package NMLab.team10.rollingthecheese;

import android.view.MotionEvent;
import android.view.SurfaceView;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.graphics.Rect;

public class WelcomeView extends SurfaceView implements SurfaceHolder.Callback{
    WelcomeThread welcomeThread;
    WelcomeDrawThread welcomeDrawThread;
    Rect connectToOtheRect;
    Rect waitingConnectRect;
    Rect titleRect;
    RollingCheeseActivity father;
    Bitmap titleBitmap;
    Bitmap connectBitmap;
    Bitmap waitBitmap;
    public WelcomeView(RollingCheeseActivity father){
        super(father);
        getHolder().addCallback(this);
        initBitmap(father);
        initRect();
        this.father = father;
        welcomeThread = new WelcomeThread(this);
        welcomeDrawThread = new WelcomeDrawThread(this,getHolder());
    }

    public void initBitmap(Context context){
        Resources r = context.getResources();
        titleBitmap = BitmapFactory.decodeResource(r, R.drawable.title);
        connectBitmap = BitmapFactory.decodeResource(r , R.drawable.connectbottom);
        waitBitmap = BitmapFactory.decodeResource(r, R.drawable.waitbottom);

    }

    public void initRect(){
        titleRect = new Rect(200,230,600,280);
        connectToOtheRect = new Rect(215,340,375,390);
        waitingConnectRect = new Rect(425,340,585,390);

    }
    public void doDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(titleBitmap, 200,230, null);
        canvas.drawBitmap(connectBitmap, 215, 340,null);
        canvas.drawBitmap(waitBitmap,425,340, null);

    }
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){

    }
    public void surfaceCreated(SurfaceHolder holder){
        if(!welcomeThread.isAlive()){
            welcomeThread.isRunning = true;
            welcomeThread.start();
        }
        if(!welcomeDrawThread.isAlive()){
            welcomeDrawThread.isRunning = true;
            welcomeDrawThread.start();
        }

    }
    public void surfaceDestroyed(SurfaceHolder holder){
        if(welcomeThread.isAlive()){
            welcomeThread.isRunning = false;

        }
        if(welcomeDrawThread.isAlive()){
            welcomeDrawThread.isRunning = false;
        }
        //System.out.println("destroyed");

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            int x = (int)event.getX();
            int y = (int)event.getY();
            if(connectToOtheRect.contains(x, y)){
                father.myHandler.sendEmptyMessage(InterThreadMsg.scan);
            }else if(waitingConnectRect.contains(x, y)){
                father.myHandler.sendEmptyMessage(InterThreadMsg.discoverable);

            }else if(titleRect.contains(x,y)){
                father.myHandler.sendEmptyMessage(InterThreadMsg.startGameView);
            }
        }
        return true;
    }



}
