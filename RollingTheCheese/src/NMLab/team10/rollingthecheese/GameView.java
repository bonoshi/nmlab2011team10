package NMLab.team10.rollingthecheese;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

public class GameView extends View  {

    RollingCheeseActivity father;
    GameDrawThread gameDrawThread;
    ScrollThread scroll;

    Bitmap backgroundBitmap;
    Bitmap farmBitmap;
    Bitmap grassBitmap;
    Bitmap houseBitmap;
    Bitmap wood_slideBitmap;
    Bitmap skyBitmap;
    Bitmap buttomBitmap;

    Bitmap testR;

    VelocityTracker vTracker;

    public GameView(RollingCheeseActivity father) {
        super(father);
        this.father = father;
        initBitmap(father);
        scroll = new ScrollThread();
        scroll.start();
        gameDrawThread = new GameDrawThread(this);
        gameDrawThread.isRunning = true;
        gameDrawThread.start();
        vTracker = VelocityTracker.obtain();



    }

    public void initBitmap(Context context) {
        Resources r = context.getResources();
        backgroundBitmap = BitmapFactory.decodeResource(r,
                R.drawable.background);
        buttomBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom);
        farmBitmap = BitmapFactory.decodeResource(r, R.drawable.farm);
        grassBitmap = BitmapFactory.decodeResource(r, R.drawable.grass);
        houseBitmap = BitmapFactory.decodeResource(r, R.drawable.house);
        wood_slideBitmap = BitmapFactory.decodeResource(r, R.drawable.wood_slide);
        skyBitmap = BitmapFactory.decodeResource(r, R.drawable.sky);

        testR = BitmapFactory.decodeResource(r, R.drawable.rectangle);

    }
    //@Override
    /*public void onDraw(Canvas canvas){
        super.onDraw(canvas);
    }*/
    @Override
    public void onDraw(Canvas canvas) {

        int newX;

        newX = scroll.posX;
        canvas.drawBitmap(skyBitmap,newX/4-40,0,null);
        canvas.drawBitmap(backgroundBitmap, newX/2-80,0,null);
        canvas.drawBitmap(farmBitmap, newX/2-80,0,null);
        canvas.drawBitmap(houseBitmap, newX - 100,200,null);
        canvas.drawBitmap(houseBitmap, newX + 1420,200,null);
        canvas.drawBitmap(wood_slideBitmap, newX -100,275, null);
        canvas.drawBitmap(wood_slideBitmap, newX +1420,275, null);
        canvas.drawBitmap(grassBitmap, newX-160,430, null);


        canvas.drawBitmap(buttomBitmap, 5, 10, null);
        //canvas.translate(newX, 0);
        canvas.drawBitmap(buttomBitmap, newX + 100, 200, null);
        //canvas.translate(-newX, 0);



    }

    
    int alpha = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            scroll.isPressing = true;
            scroll.fingerX = x;
            scroll.prev_fingerX = x;
            scroll.isRecovering = false;
            scroll.recover_a = 0;
            scroll.V = 0;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            scroll.fingerX = x;
            vTracker.addMovement(event);
            vTracker.computeCurrentVelocity(1);
            scroll.V = vTracker.getXVelocity();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            scroll.isPressing = false;
        }
        return true;
    }

}
