package NMLab.team10.rollingthecheese;

import java.util.Date;

import NMLab.team10.rollingthecheese.displayData.CheeseDisplay;
import NMLab.team10.rollingthecheese.displayData.CloudDisplay;
import NMLab.team10.rollingthecheese.displayData.CowDisplay;
import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.displayData.Climate;
import NMLab.team10.rollingthecheese.displayData.FireLineDisplay;
import NMLab.team10.rollingthecheese.displayData.HouseDisplay;
import NMLab.team10.rollingthecheese.displayData.SkyDisplay;
import NMLab.team10.rollingthecheese.displayData.SmokeDisplay;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;

import NMLab.team10.rollingthecheese.gameSetting.Cheese;
import NMLab.team10.rollingthecheese.gameSetting.FireLine;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;
import NMLab.team10.rollingthecheese.gameSetting.Projector;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

public class GameView extends View {

    RollingCheeseActivity father;
    GameDrawThread gameDrawThread;
    ScrollThread scroll;

    static public DisplayData displayData = null;

    private static Bitmap backgroundBitmap;
    private static Bitmap farmBitmap;
    private static Bitmap grassBitmap;

    private ButtomBar buttomBar;

    private boolean hasBeenInit = true;

    VelocityTracker vTracker;
    static final int nightBack = 255;
    static final int morningBack = 0;
    static Paint backPaint;
    static ColorMatrixColorFilter backCMCF;
    static Paint testPaint;
    static LightingColorFilter testCMCF;

    public GameView(RollingCheeseActivity father) {
        super(father);
        this.father = father;
        initPaint();

        scroll = new ScrollThread();
        gameDrawThread = new GameDrawThread(this);

        vTracker = VelocityTracker.obtain();
    }

    public void startNewGame(){
        gameDrawThread.setNewlyCreated(true);
    }

    public void resume() {
        gameDrawThread.setPause(false);
        scroll.setPause(false);
    }

    public void interrupt(){
        gameDrawThread.interrupt();
        scroll.interrupt();
    }

    public void pause() {
        gameDrawThread.setPause(true);
        scroll.setPause(true);
    }

    public void stop() {
        gameDrawThread.setRunning(false);
        scroll.setRunning(false);
    }

    public void start() {
        scroll.start();
        gameDrawThread.start();
    }

    public void initialOnePlayer() {
        buttomBar = new ButtomBar(father, father.gameCalThread.getEventCenter(),
                RollingCheeseActivity.getDisplayData());
        displayData = RollingCheeseActivity.getDisplayData();
        CowDisplay.initialGameView(this);
        hasBeenInit = true;
    }

    public void initialServer() {
        buttomBar = new ButtomBar(father, father.gameCalThread.getEventCenter(),
                RollingCheeseActivity.getDisplayData());
        displayData = RollingCheeseActivity.getDisplayData();
        CowDisplay.initialGameView(this);
        hasBeenInit = true;
    }

    public void initialClient() {
        buttomBar = new ButtomBar(father, new EventQueueCenter(father),
                RollingCheeseActivity.getDisplayData());
        displayData = RollingCheeseActivity.getDisplayData();
        CowDisplay.initialGameView(this);
        hasBeenInit = true;
    }

    public static Resources r;
    public static Context context;

    static public void initBitmap(Context context) {
        GameView.context = context;
        GameView.r = context.getResources();
        CheeseDisplay.initBitmap();
        backgroundBitmap = BitmapFactory.decodeResource(r, R.drawable.background);
        farmBitmap = BitmapFactory.decodeResource(r, R.drawable.farm);
        grassBitmap = BitmapFactory.decodeResource(r, R.drawable.grass);

        // clould1 = BitmapFactory.decodeResource(r,
        // R.drawable.clould).copy(Bitmap.Config.ARGB_8888, true);
        // alpha value = 255 => ���z��//
        // modifyAlpah(clould1, 80);

        // nightM = new float[20];
        // for (int i = 0; i < 20; i++) {
        // nightM[i] = 0;
        // }
        // nightM[18] = 1;
        //
        // mornM = new float[20];
        // for (int i = 0; i < 20; i++) {
        // mornM[i] = 0;
        // }
        // mornM[0] = 1;
        // mornM[6] = 1;
        // mornM[12] = 1;
        // mornM[18] = 1;
        backPaint = new Paint();
        backPaint.setColor(GameView.closeAlphaByRatio(0, 0, 1));

        testPaint = new Paint();

        SkyDisplay.initial();
        SkyDisplay.Star.initial();
        SmokeDisplay.initial();
        CloudDisplay.Cloud.initial();
        HouseDisplay.initial();
        CowDisplay.initial();
        FireLineDisplay.initial();
        Projector.initBitmap();
        ButtomBar.initBitmap(context);
    }

    private void refreshPaint() {
        int time = displayData.getTime();
        if (time > GlobalParameter.Dusk) {
            if (time > GlobalParameter.Night) {
                backPaint.setARGB(255, 0, 0, 0);
            } else {
                float ratio = (time - GlobalParameter.Dusk) / GlobalParameter.Dusk2Night;
                int backColor = GameView.closeAlphaByRatio(morningBack, nightBack, ratio);
                backPaint.setColor(backColor);
            }
        } else {
            if (time > GlobalParameter.Noon) {
                backPaint.setARGB(0, 0, 0, 0);
            } else if (time > GlobalParameter.Morning) {
                backPaint.setARGB(0, 0, 0, 0);
            } else {
                float ratio = time / GlobalParameter.Night2Morning;
                int backColor = GameView.closeAlphaByRatio(nightBack, morningBack, ratio);
                backPaint.setColor(backColor);
            }
        }
    }

    private void initPaint() {
        paintInfo.setColor(Color.RED);
        paintInfo.setTextSize(30.0F);

        paintMilk.setColor(Color.BLACK);
        paintMilk.setTextSize(10.0F);
        paintMilk.setTextAlign(Paint.Align.CENTER);
        paintMilk.setAntiAlias(true);

        // paintTest.setColor(Color.argb(100, 255, 0, 0));
    }

    Paint paintInfo = new Paint();
    Paint paintMilk = new Paint();
    // Paint paintTest = new Paint();

    Date timeLastFrame = new Date(System.currentTimeMillis());

    static public float PacketPerSec = 0;
    static public String pssString = "";
    static public long lastUpPTime = System.currentTimeMillis();

    static public void refreshPPS(boolean isServer) {
        PacketPerSec = 1000 / (System.currentTimeMillis() - lastUpPTime);
        if (PacketPerSec < 0.1F)
            PacketPerSec = 0.1F;
        pssString = Float.toString(PacketPerSec);
        if (isServer) {
            pssString = "S:" + pssString;
        } else {
            pssString = "C:" + pssString;
        }
        lastUpPTime = System.currentTimeMillis();
    }

    int refreshPaintCount = 0;

    public void refreshAllPaint() {
        SkyDisplay.refreshPaint();
        CloudDisplay.Cloud.refreshPaint();
        refreshPaint();
    }

    @Override
    public void onDraw(Canvas canvas) {

        if (hasBeenInit) {
            if (displayData.hasNewData()) {
                hasBeenInit = false;
                refreshAllPaint();
            }
            return;
        }

        int newX;

        newX = scroll.posX;

        if (newX < -860) {
            newX = -860;
        }
        if (newX > 60) {
            newX = 60;
        }

        // if (++refreshPaintCount == GlobalParameter.RefreshPaintCount) {
        // refreshPaintCount = 0;
        refreshAllPaint();
        // }

        // String scrollPosition = Integer.toString(newX);

        if (!displayData.getDSM(!displayData.isLeft).power) {
            if (displayData.getTime() < GlobalParameter.Night) {
                SkyDisplay.drawSky(canvas, newX / 2 - 80);
                canvas.translate(0, 50);
                canvas.drawBitmap(backgroundBitmap, newX / 2 - 80, 0, null);
                canvas.drawBitmap(farmBitmap, newX / 2 - 80, 0, null);
                CowDisplay.draw(canvas, newX);
                canvas.translate(0, -50);
                canvas.drawRect(0, 0, 800, 480, backPaint);
            }

            canvas.translate(newX, 0);
            displayData.drawHouse(canvas);

            displayData.drawPorj(canvas);

            // below is for all dynamic objects
            // if (!displayData.hasNewData()) {
            // return;
            // } else {
            // displayData.acceptData();
            // }
            // canvas.translate(-newX, 0);

            // for absolute coordinate system graphics
            // canvas.translate(newX, 0);
            // for scene object

            // for game object
            displayData.drawCheese(Cheese.Left, canvas);
            displayData.drawCheese(Cheese.Right, canvas);
            displayData.drawFireLine(FireLine.Left, canvas);
            displayData.drawFireLine(FireLine.Right, canvas);

            // end of absolute coordinate system graphics
            canvas.translate(-newX, 0);
            canvas.drawBitmap(grassBitmap, newX - 160, 460, null);
        }

        SkyDisplay.drawStar(canvas, newX / 2 - 80);
        CloudDisplay.updateCloud();
        Climate.modifyWind(displayData.getClimate());
        CloudDisplay.draw(canvas, newX / 2 - 80);

        canvas.translate(newX, 0);
        displayData.drawStatus(canvas);
        canvas.translate(-newX, 0);
        buttomBar.draw(canvas);

        // for floating text
        // canvas.drawText("Scroll Pos: " + scrollPosition, 570, 450,
        // paintInfo);
        // canvas.drawText("(x,y)=(" + this.x + "," + this.y + ")", 570, 420,
        // paintInfo);
        canvas.drawText("$" + Integer.toString(displayData.getMilk()), 670, 460, paintInfo);
        // canvas.drawText("Time=" + displayData.getTime(), 570, 30, paintInfo);
        Date timeCurrentFrame = new Date(System.currentTimeMillis());
        float fps = 1000 / (timeCurrentFrame.getTime() - timeLastFrame.getTime());
        timeLastFrame = timeCurrentFrame;
        canvas.drawText("FPS=" + fps, 570, 60, paintInfo);
        canvas.drawText("P/sec=" + pssString, 570, 90, paintInfo);
        // canvas.drawText("Cow Num=" + displayData.getCowList().size() ,570,
        // 120, paintInfo);

    }

    public void refreshButtonFrame(){
        buttomBar.refreshFrame();
    }

    int x = 0;
    int y = 0;

    static public int closeAlphaByRatio(int src, int dst, float ratio) {
        int alpha = (int) ((dst - src) * ratio + src);

        int temp = 0;
        temp |= (alpha << 24);
        return temp;
    }

    static public int closeRGBByRatio(int src, int dst, float ratio) {
        int sr = (src & 0x00FF0000) >> 16;
        int sg = (src & 0x0000FF00) >> 8;
        int sb = src & 0x000000FF;
        int dr = (dst & 0x00FF0000) >> 16;
        int dg = (dst & 0x0000FF00) >> 8;
        int db = dst & 0x000000FF;

        int rr = (int) ((dr - sr) * ratio + sr);
        int rg = (int) ((dg - sg) * ratio + sg);
        int rb = (int) ((db - sb) * ratio + sb);

        int temp = 0xFF000000;
        temp |= (rr << 16);
        temp |= (rg << 8);
        temp |= rb;
        return temp;
    }

    static public void copyRGBtoAlpha(Bitmap b, int RGB) {
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                int pixel = b.getPixel(i, j);
                int blue = pixel & 0x000000FF;
                int red = (pixel & 0x00FF0000) >> 16;
                int green = (pixel & 0x0000FF00) >> 8;
                int white = (green + red + blue) / 3;
                int temp = RGB;
                temp |= (white << 24);
                b.setPixel(i, j, temp);
            }
        }
    }

    static public void modifyAlpah(Bitmap b, int alpha) {
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

    static public void modifyAlpahByRatio(Bitmap b, float r) {
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

    static public void modifyBlueByRatio(Bitmap b, float r) {
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                int pixel = b.getPixel(i, j);
                if (pixel == 0x00000000)
                    continue;
                int blue = pixel & 0x000000FF;
                blue = (int) (blue * r);
                if (blue > 255)
                    blue = 255;
                pixel &= 0xFFFFFF00;
                pixel |= (blue << 0);
                b.setPixel(i, j, pixel);
            }
        }
    }

    static public void modifyRedByRatio(Bitmap b, float r) {
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                int pixel = b.getPixel(i, j);
                if (pixel == 0x00000000)
                    continue;
                int red = (pixel & 0x00FF0000) >> 16;
                red = (int) (red * r);
                if (red > 255)
                    red = 255;
                pixel &= 0xFF00FFFF;
                pixel |= (red << 16);
                b.setPixel(i, j, pixel);
            }
        }
    }

    static public void modifyGreenByRatio(Bitmap b, float r) {
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                int pixel = b.getPixel(i, j);
                if (pixel == 0x00000000)
                    continue;

                int green = (pixel & 0x0000FF00) >> 8;
                green = (int) (green * r);
                if (green > 255)
                    green = 255;
                pixel &= 0xFFFF00FF;
                pixel |= (green << 8);

                b.setPixel(i, j, pixel);
            }
        }
    }

    static public void modifyRGBByRatio(Bitmap b, float r) {
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                int pixel = b.getPixel(i, j);
                if (pixel == 0x00000000)
                    continue;

                int red = (pixel & 0x00FF0000) >> 16;
                red = (int) (red * r);
                if (red > 255)
                    red = 255;
                pixel &= 0xFF00FFFF;
                pixel |= (red << 16);

                int green = (pixel & 0x0000FF00) >> 8;
                green = (int) (green * r);
                if (green > 255)
                    green = 255;
                pixel &= 0xFFFF00FF;
                pixel |= (green << 8);

                int blue = pixel & 0x000000FF;
                blue = (int) (blue * r);
                if (blue > 255)
                    blue = 255;
                pixel &= 0xFFFFFF00;
                pixel |= (blue << 0);

                b.setPixel(i, j, pixel);
            }
        }
    }

    static public float[] closeColorMatrixByRatio(float[] a1, float[] a2, float ratio) {
        float temp[] = new float[20];
        for (int i = 0; i < 20; i++) {
            temp[i] = a1[i];
        }
        temp[0] = (a2[0] - a1[0]) * ratio + a1[0];
        temp[4] = (a2[4] - a1[4]) * ratio + a1[4];
        temp[6] = (a2[6] - a1[6]) * ratio + a1[6];
        temp[9] = (a2[9] - a1[9]) * ratio + a1[9];
        temp[12] = (a2[12] - a1[12]) * ratio + a1[12];
        temp[14] = (a2[14] - a1[14]) * ratio + a1[14];
        temp[18] = (a2[18] - a1[18]) * ratio + a1[18];
        return temp;
    }

    static public float[] modifyAlphaColorMatrixByRatio(float ratio) {
        float temp[] = new float[20];
        for (int i = 0; i < 20; i++) {
            temp[i] = 0;
        }
        temp[0] = 1;
        temp[6] = 1;
        temp[12] = 1;
        temp[18] = ratio;
        return temp;
    }

    boolean touchScreen = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        buttomBar.onTouch(event);
        if (buttomBar.isTouch)
            return true;

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
            touchScreen = false;
        }

        // /////////////////////// bobuway ////////////////////
        if (event.getAction() == MotionEvent.ACTION_UP && x > 750 && y > 430) {
            Log.e("", "debug_addcow");
            CowDisplay.debug_addCow();
        } else if (event.getAction() == MotionEvent.ACTION_UP && x < 50 && y > 430) {
            Log.e("", "debug_deletecow");
            CowDisplay.debug_deleteCow();
        }
        // ////////////////////////////////////////////////////

        this.x = x;
        this.y = y;
        return true;
    }

    public void setHasBeenInit(boolean hasBeenInit) {
        this.hasBeenInit = hasBeenInit;
    }

    public boolean isHasBeenInit() {
        return hasBeenInit;
    }

}
