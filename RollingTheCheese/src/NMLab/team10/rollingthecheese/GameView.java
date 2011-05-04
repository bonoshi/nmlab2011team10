package NMLab.team10.rollingthecheese;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    
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
    
    
    public GameView(RollingCheeseActivity father) {
        super(father);
        this.father = father;
        getHolder().addCallback(this);
        initBitmap(father);
        gameDrawThread = new GameDrawThread(this, getHolder());
        scroll = new ScrollThread();
        scroll.start();

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
        
        
    }

    public void doDraw(Canvas canvas) {

        int newX;
        synchronized (scroll) {
            newX = scroll.posX;
        }
        canvas.drawBitmap(skyBitmap,newX/4-40,0,null);
        canvas.drawBitmap(backgroundBitmap, newX/2-80,0,null);
        canvas.drawBitmap(farmBitmap, newX/2-80,0,null);
        canvas.drawBitmap(houseBitmap, newX - 160+60,200,null);
        canvas.drawBitmap(wood_slideBitmap, newX -160+60,275, null);
        canvas.drawBitmap(grassBitmap, newX-160,430, null);
        canvas.drawBitmap(buttomBitmap, 5, 10, null);

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if (!father.gameThread.isAlive()) {
            father.gameThread.isRunning = true;
            father.gameThread.start();
        }
        if (!gameDrawThread.isAlive()) {
            gameDrawThread.isRunning = true;
            gameDrawThread.start();
        }

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (gameDrawThread.isAlive()) {
            gameDrawThread.isRunning = false;

        }
        if (father.gameThread.isAlive()) {
            father.gameThread.isRunning = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            scroll.isPressing = true;
            scroll.fingerX = x;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            scroll.fingerX = x;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            scroll.isPressing = false;
        }
        return true;
    }

}
