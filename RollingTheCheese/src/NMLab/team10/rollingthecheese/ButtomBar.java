package NMLab.team10.rollingthecheese;


import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;


public class ButtomBar {

    public boolean isTouch;

    private RollingCheeseActivity activity;

    private static Bitmap buttomBar;
    private static Bitmap normalButtom;
    private static Bitmap blueButtom;


    private static Bitmap normalButtomList;

    private ButtomListControl normalList;
    private ButtomControl normalB;
    private ButtomControl blueB;
    private EventQueueCenter eqc;

    public ButtomBar(RollingCheeseActivity activity, EventQueueCenter eqc){
        this.activity = activity;
        this.isTouch = false;
        ButtomBar.initBitmap(activity);
        this.eqc = eqc;
        normalList = new ButtomListControl(0,normalButtomList,eqc);
        normalList.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        normalList.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);
        normalB = new ButtomControl(0,normalButtom);
        blueB = new ButtomControl(110,normalButtom);

    }

    public static Resources r;
    public static Context context;
    public static void initBitmap(Context context){
        ButtomBar.context = context;
        ButtomBar.r = context.getResources();

        buttomBar = BitmapFactory.decodeResource(r, R.drawable.buttombar);
        normalButtom = BitmapFactory.decodeResource(r, R.drawable.normalcheesebuttomani);
        blueButtom = normalButtom;
        normalButtomList = BitmapFactory.decodeResource(r, R.drawable.normalbuttomlistani);
    }

    public void draw(Canvas canvas){
        normalList.draw(canvas);
        canvas.drawBitmap(buttomBar, 0,0, null);
        normalB.draw(canvas);
        blueB.draw(canvas);
    }


    public void onTouch(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            isTouch = false;
        }
        if(event.getAction()!=MotionEvent.ACTION_DOWN)return;
        if(normalB.onTouch(event)){
            normalList.buttomPress();
            isTouch = true;
        }
        normalList.onTouch(event);
        return;
    }

}





