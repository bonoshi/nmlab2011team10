package NMLab.team10.rollingthecheese;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;


public class ButtomBar {

    public boolean isTouch;
    private static final int buttomW = 110;
    private static final int buttomH = 85;



    private RollingCheeseActivity activity;

    private static Bitmap buttomBar;
    private static Bitmap normalButtom;
    private static Bitmap normalButtomList;

    private ButtomListControl normalList;
    private ButtomControl normalB;


    public ButtomBar(RollingCheeseActivity activity){
        this.activity = activity;
        this.isTouch = false;
        ButtomBar.initBitmap(activity);
        normalList = new ButtomListControl(0,normalButtomList);
        normalB = new ButtomControl(0,normalButtom);

    }

    public static Resources r;
    public static Context context;
    public static void initBitmap(Context context){
        ButtomBar.context = context;
        ButtomBar.r = context.getResources();

        buttomBar = BitmapFactory.decodeResource(r, R.drawable.buttombar);
        normalButtom = BitmapFactory.decodeResource(r, R.drawable.normalcheesebuttomani);
        normalButtomList = BitmapFactory.decodeResource(r, R.drawable.normalbuttomlistani);
    }

    public void draw(Canvas canvas){
        normalList.draw(canvas);
        canvas.drawBitmap(buttomBar, 0,0, null);
        normalB.draw(canvas);
    }


    public boolean onTouch(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            isTouch = false;
        }
        if(normalB.onTouch(event)){
            normalList.buttomPress();
            isTouch = true;
            return true;
        }
        if(normalList.onTouch(event)){
            return true;
        }
        return false;
    }

}





