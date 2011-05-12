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
    private static Bitmap normalBitmap;
    private static Bitmap blueBitmap;
    private static Bitmap softBitmap;
    private static Bitmap spicyBitmap;
    private static Bitmap constructBitmap;
    private static Bitmap destructBitmap;
    private static Bitmap normalButtomListBitmap;
    private static Bitmap blueButtomListBitmap;
    private static Bitmap softButtomListBitmap;
    private static Bitmap spicyButtomListBitmap;
    private static Bitmap constructButtomListBitmap;
    private static Bitmap destructButtomListBitmap;
    

    private ButtomListControl normalListControl;
    private ButtomListControl blueListControl;
    private ButtomListControl softListControl;
    private ButtomListControl spicyListControl;
    private ButtomListControl constructListControl;
    private ButtomListControl destructListControl;
    
    private ButtomControl normalButtomControl;
    private ButtomControl blueButtomControl;
    private ButtomControl softButtomControl;
    private ButtomControl spicyButtomControl;
    private ButtomControl constructButtomControl;
    private ButtomControl destructButtomControl;
    private ButtomControl cancelButtomControl;
    
    private EventQueueCenter eqc;

    public ButtomBar(RollingCheeseActivity activity, EventQueueCenter eqc){
        this.activity = activity;
        this.isTouch = false;
        
        ButtomBar.initBitmap(activity);
        this.eqc = eqc;
        
        normalListControl = new ButtomListControl(0,normalButtomListBitmap,eqc);
        normalListControl.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        normalListControl.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);
        blueListControl = new ButtomListControl(110,blueButtomListBitmap,eqc);
        blueListControl.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        blueListControl.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);
        softListControl = new ButtomListControl(220,softButtomListBitmap,eqc);
        softListControl.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        softListControl.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);
        spicyListControl = new ButtomListControl(330,spicyButtomListBitmap,eqc);
        spicyListControl.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        spicyListControl.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);

                
        normalButtomControl = new ButtomControl(0,normalBitmap);
        blueButtomControl = new ButtomControl(110,blueBitmap);
        softButtomControl = new ButtomControl(220,softBitmap);
        spicyButtomControl = new ButtomControl(330,spicyBitmap);
        constructButtomControl = new ButtomControl(440,constructBitmap);
        destructButtomControl = new ButtomControl(550,destructBitmap);

    }

    public static Resources r;
    public static Context context;
    public static void initBitmap(Context context){
        ButtomBar.context = context;
        ButtomBar.r = context.getResources();
        buttomBar = BitmapFactory.decodeResource(r, R.drawable.buttombar);
        normalBitmap = BitmapFactory.decodeResource(r, R.drawable.normalcheesebuttomani);
        blueBitmap = BitmapFactory.decodeResource(r, R.drawable.bluebuttomani);
        softBitmap = BitmapFactory.decodeResource(r, R.drawable.softbuttomani);
        spicyBitmap = BitmapFactory.decodeResource(r, R.drawable.spicybuttomani);
        constructBitmap = BitmapFactory.decodeResource(r, R.drawable.constructbuttomani);
        destructBitmap = BitmapFactory.decodeResource(r, R.drawable.destructbuttomani);
        
        normalButtomListBitmap = BitmapFactory.decodeResource(r, R.drawable.normalbuttomlistani);
        blueButtomListBitmap = BitmapFactory.decodeResource(r, R.drawable.bluelistani);
        softButtomListBitmap = BitmapFactory.decodeResource(r, R.drawable.softlistani);
        spicyButtomListBitmap = BitmapFactory.decodeResource(r, R.drawable.spicylistani);
        
    }

    public void draw(Canvas canvas){
        normalListControl.draw(canvas);
        blueListControl.draw(canvas);
        softListControl.draw(canvas);
        spicyListControl.draw(canvas);
        
        canvas.drawBitmap(buttomBar, 0,0, null);
        
        normalButtomControl.draw(canvas);
        blueButtomControl.draw(canvas);
        softButtomControl.draw(canvas);
        spicyButtomControl.draw(canvas);
        constructButtomControl.draw(canvas);
        destructButtomControl.draw(canvas);
    }


    public void onTouch(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            isTouch = false;
        }
        if(event.getAction()!=MotionEvent.ACTION_DOWN)return;
        if(normalButtomControl.onTouch(event)){
            normalListControl.buttomPress();
            isTouch = true;
        }
        if(blueButtomControl.onTouch(event)){
            blueListControl.buttomPress();
            isTouch = true;
        }
        if(softButtomControl.onTouch(event)){
            softListControl.buttomPress();
            isTouch = true;
        }
        if(spicyButtomControl.onTouch(event)){
            spicyListControl.buttomPress();
            isTouch = true;
        }
        if(constructButtomControl.onTouch(event)){
            //normalListControl.buttomPress();
            isTouch = true;
        }
        if(destructButtomControl.onTouch(event)){
            //normalListControl.buttomPress();
            isTouch = true;
        }
        
        normalListControl.onTouch(event);
        blueListControl.onTouch(event);
        softListControl.onTouch(event);
        spicyListControl.onTouch(event);
        return;
    }

}





