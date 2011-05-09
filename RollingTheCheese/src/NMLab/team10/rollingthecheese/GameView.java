package NMLab.team10.rollingthecheese;

import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.Cheese;
import NMLab.team10.rollingthecheese.gameSetting.ServerGameSetting;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

public class GameView extends View {

    RollingCheeseActivity father;
    GameDrawThread gameDrawThread;
    ScrollThread scroll;

    private GameCalThread gct = null;
    DisplayData displayData = null;
    EventQueueCenter eqc = null;

    private static Bitmap backgroundBitmap;
    private static Bitmap farmBitmap;
    private static Bitmap grassBitmap;
    private static Bitmap houseBitmap;
    private static Bitmap wood_slideBitmap;
    private static Bitmap wood_slideBitmap_m;
    private static Bitmap skyBitmap;
    private static Bitmap buttomBitmap;

    private static Bitmap clould1;

    VelocityTracker vTracker;

    public GameView(RollingCheeseActivity father) {

        super(father);
        this.father = father;
        GameView.initBitmap(father);
        initPaint();
        scroll = new ScrollThread();
        scroll.start();

        // for game calculation center
        ServerGameSetting sgs = new ServerGameSetting();
        GameCalThread game = new GameCalThread(father, sgs);
        displayData = game.getDisplayData();
        eqc = game.getEventCenter();

        gameDrawThread = new GameDrawThread(this, game);
        gameDrawThread.isRunning = true;
        gameDrawThread.start();
        vTracker = VelocityTracker.obtain();

    }

    public static Resources r;
    public static Context context;

    static public void initBitmap(Context context) {
        GameView.context = context;
        GameView.r = context.getResources();
        backgroundBitmap = BitmapFactory.decodeResource(r, R.drawable.background);
        buttomBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom);
        farmBitmap = BitmapFactory.decodeResource(r, R.drawable.farm);
        grassBitmap = BitmapFactory.decodeResource(r, R.drawable.grass);
        houseBitmap = BitmapFactory.decodeResource(r, R.drawable.house);
        wood_slideBitmap = BitmapFactory.decodeResource(r, R.drawable.wood_slide);
        wood_slideBitmap_m = BitmapFactory.decodeResource(r, R.drawable.wood_slide_mirror);
        skyBitmap = BitmapFactory.decodeResource(r, R.drawable.sky);

        clould1 = BitmapFactory.decodeResource(r, R.drawable.clould).copy(Bitmap.Config.ARGB_8888, true);
        // alpha value = 255 => ¤£³z©ú//
        modifyAlpah(clould1, 80);
    }

    private void initPaint() {
        paintInfo.setColor(Color.RED);
        paintInfo.setTextSize(30.0F);

        paintMilk.setColor(Color.BLACK);
        paintMilk.setTextSize(10.0F);
        paintMilk.setTextAlign(Paint.Align.CENTER);
        paintMilk.setAntiAlias(true);
    }

    // @Override
    /*
     * public void onDraw(Canvas canvas){ super.onDraw(canvas); }
     */

    Paint paintInfo = new Paint();
    Paint paintMilk = new Paint();

    float cloud_x = 0;
    float cloud_y = 80;

    @Override
    public void onDraw(Canvas canvas) {

        int newX;

        newX = scroll.posX;

        String scrollPosition = Integer.toString(newX);

        canvas.drawBitmap(skyBitmap, newX / 4 - 40, 0, null);
        canvas.translate(0, 50);
        canvas.drawBitmap(backgroundBitmap, newX / 2 - 80, 0, null);
        canvas.drawBitmap(farmBitmap, newX / 2 - 80, 0, null);
        canvas.translate(0, -50);
        canvas.drawBitmap(houseBitmap, newX - 100, 200, null);
        canvas.drawBitmap(houseBitmap, newX + 1420, 200, null);
        canvas.drawBitmap(wood_slideBitmap, newX - 150, 275, null);
        canvas.drawBitmap(wood_slideBitmap_m, newX + 1470, 275, null);
        canvas.drawBitmap(grassBitmap, newX - 160, 430, null);

        // below is for all dynamic objects
        if (!displayData.hasNewData()) {
            return;
        } else {
            displayData.acceptData();
        }

        moveCloud();
        modifyWind();

        // for absolute coordinate system graphics
        canvas.translate(newX, 0);
        // for scene object
        canvas.drawBitmap(clould1, cloud_x - 200, cloud_y, null);

        // for game object
        displayData.drawCheese(Cheese.Left, canvas);
        displayData.drawCheese(Cheese.Right, canvas);
        
        // end of absolute coordinate system graphics
        canvas.translate(-newX, 0);

        // for floating text
        canvas.drawBitmap(buttomBitmap, 5, 10, null);
        canvas.drawText("Scroll Pos: " + scrollPosition, 570, 450, paintInfo);
        canvas.drawText("(x,y)=(" + this.x + "," + this.y + ")", 570, 420, paintInfo);
        canvas.drawText(Integer.toString(displayData.getLeftMilk()), 494, 61, paintMilk);
        canvas.drawText("Time=" + gct.getDisplayData().getTime(), 570, 30, paintInfo);

    }

    int x = 0;
    int y = 0;
    int wind = 2;

    private void moveCloud() {
        cloud_x += wind;
        cloud_x %= 1800;
        double rand2 = Math.random();
        if (rand2 > 0.9) {
            cloud_y += 1;
        } else if (rand2 < 0.1) {
            cloud_y -= 1;
        }
        if (cloud_y > 100) {
            cloud_y -= 1;
        } else if (cloud_y < 60) {
            cloud_y += 1;
        }
    }

    int windCount = 0;

    private void modifyWind() {
        windCount++;
        windCount %= 10;
        if (windCount == 0) {
            double rand = Math.random();
            if (rand > 0.95) {
                wind += 1;
            } else if (rand < 0.05) {
                wind -= 1;
            }
            if (wind > 2) {
                wind -= 1;
            } else if (wind < -2) {
                wind += 1;
            }
        }
    }

    private static void modifyAlpah(Bitmap b, int alpha) {
        alpha &= 0xFF;
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                int temp = b.getPixel(i, j);
                if (temp == 0x00000000)
                    continue;
                temp &= 0x00FFFFFF;
                temp |= (alpha << 24);
                b.setPixel(i, j, temp);
            }
        }
    }

    @SuppressWarnings("unused")
    private void modifyAlpahByRatio(Bitmap b, float r) {
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                int pixel = b.getPixel(i, j);
                if (pixel == 0x00000000)
                    continue;
                int alpha = pixel >> 24;
                alpha = (int) (alpha * r);
                if (alpha > 255)
                    alpha = 255;
                pixel &= 0x00FFFFFF;
                pixel |= (alpha << 24);
                b.setPixel(i, j, pixel);
            }
        }
    }

    boolean touchScreen = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (y < 100 && !touchScreen) {
                if (x < 100) {
                    eqc.addEvent(EventEnum.OriginalCheeseSmall, Cheese.Left);
                    touchScreen = true;
                }
            }
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
            touchScreen = false;
        }
        this.x = x;
        this.y = y;
        return true;
    }

    public void setGct(GameCalThread gct) {
        this.gct = gct;
    }

    public GameCalThread getGct() {
        return gct;
    }

}
