package NMLab.team10.rollingthecheese;

import java.util.Date;

import NMLab.team10.rollingthecheese.displayData.CloudDisplay;
import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.displayData.Climate;
import NMLab.team10.rollingthecheese.displayData.SkyDisplay;
import NMLab.team10.rollingthecheese.displayData.SmokeDisplay;
import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.Cheese;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;
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
    public static DisplayData displayData = null;
    EventQueueCenter eqc = null;

    private static Bitmap backgroundBitmap;
    private static Bitmap farmBitmap;
    private static Bitmap grassBitmap;
    private static Bitmap wood_slideBitmap;
    private static Bitmap wood_slideBitmap_m;


    private ButtomBar buttomBar;

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


        buttomBar = new ButtomBar(father,eqc);
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
        farmBitmap = BitmapFactory.decodeResource(r, R.drawable.farm);
        grassBitmap = BitmapFactory.decodeResource(r, R.drawable.grass);
        wood_slideBitmap = BitmapFactory.decodeResource(r, R.drawable.wood_slide);
        wood_slideBitmap_m = BitmapFactory.decodeResource(r, R.drawable.wood_slide_mirror);

        // clould1 = BitmapFactory.decodeResource(r,
        // R.drawable.clould).copy(Bitmap.Config.ARGB_8888, true);
        // alpha value = 255 => ���z��//
        // modifyAlpah(clould1, 80);

        SkyDisplay.initial();
        SkyDisplay.Star.initial();
        SmokeDisplay.initial();
        CloudDisplay.Cloud.initial();
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

    Date timeLastFrame = new Date(System.currentTimeMillis());

    @Override
    public void onDraw(Canvas canvas) {

        int newX;

        newX = scroll.posX;

        if (newX < -860) {
            newX = -860;
        }
        if (newX > 60) {
            newX = 60;
        }

        String scrollPosition = Integer.toString(newX);
        SkyDisplay.draw(canvas, newX / 2 - 80);

        CloudDisplay.updateCloud();
        Climate.modifyWind(displayData.getClimate());
        CloudDisplay.draw(canvas, newX / 2 - 80);

        canvas.translate(0, 50);
        canvas.drawBitmap(backgroundBitmap, newX / 2 - 80, 0, null);
        canvas.drawBitmap(farmBitmap, newX / 2 - 80, 0, null);
        canvas.translate(0, -50);
        canvas.translate(newX, 0);
        displayData.drawHouse(canvas);
        canvas.translate(-newX, 0);
        canvas.drawBitmap(wood_slideBitmap, newX - 150, 275, null);
        canvas.drawBitmap(wood_slideBitmap_m, newX + 1470, 275, null);


        // below is for all dynamic objects
        if (!displayData.hasNewData()) {
            return;
        } else {
            displayData.acceptData();
        }

        Date timeCurrentFrame = new Date(System.currentTimeMillis());
        float fps = 1000 / (timeCurrentFrame.getTime() - timeLastFrame.getTime());
        timeLastFrame = timeCurrentFrame;

        // for absolute coordinate system graphics
        canvas.translate(newX, 0);
        // for scene object

        // for game object
        displayData.drawCheese(Cheese.Left, canvas);
        displayData.drawCheese(Cheese.Right, canvas);

        // end of absolute coordinate system graphics
        canvas.translate(-newX, 0);
        canvas.drawBitmap(grassBitmap, newX - 160, 460, null);
        buttomBar.draw(canvas);

        // for floating text
        canvas.drawText("Scroll Pos: " + scrollPosition, 570, 450, paintInfo);
        canvas.drawText("(x,y)=(" + this.x + "," + this.y + ")", 570, 420, paintInfo);
        canvas.drawText(Integer.toString(displayData.getLeftMilk()), 494, 61, paintMilk);
        canvas.drawText("Time=" + gct.getDisplayData().getTime(), 570, 30, paintInfo);
        canvas.drawText("FPS=" + fps, 570, 60, paintInfo);

    }

    int x = 0;
    int y = 0;

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

    boolean touchScreen = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        buttomBar.onTouch(event);
        if(buttomBar.isTouch)return true;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            /*if (y < 100 && !touchScreen) {
                if (x < 100) {
                    eqc.addEvent(EventEnum.OriginalCheeseSmall, Cheese.Left);
                    touchScreen = true;
                    buttomBar.test(event);
                } else if (x < 200) {
                    eqc.addEvent(EventEnum.OriginalCheeseMed, Cheese.Left);
                    touchScreen = true;

                } else if (x < 300) {
                    eqc.addEvent(EventEnum.OriginalCheeseLarge, Cheese.Left);
                    touchScreen = true;
                }
                if (x > 700) {
                    eqc.addEvent(EventEnum.OriginalCheeseSmall, Cheese.Right);
                    touchScreen = true;
                } else if (x > 600) {
                    eqc.addEvent(EventEnum.OriginalCheeseMed, Cheese.Right);
                    touchScreen = true;

                } else if (x > 500) {
                    eqc.addEvent(EventEnum.OriginalCheeseLarge, Cheese.Right);
                    touchScreen = true;
                }

            }*/

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

    public static final byte Morning = GlobalParameter.Morning;
    public static final byte Noon = GlobalParameter.Noon;
    public static final byte Dusk = GlobalParameter.Dusk;
    public static final byte Night = GlobalParameter.Night;

}
