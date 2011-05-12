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
    private static Bitmap buttomNBitmap;
    private static Bitmap buttomCBitmap;
    private static Bitmap buttomSBitmap;
    private static Bitmap buttomFBitmap;
    private static Bitmap buttomConBitmap;
    private static Bitmap buttomDeBitmap;
    
    
    private static Bitmap listNBitmap;
    private static Bitmap listCBitmap;
    private static Bitmap listSBitmap;
    private static Bitmap listFBitmap;
    private static Bitmap listConBitmap;
    private static Bitmap listDeBitmap;
    

    private ButtomListControl listControlN;
    private ButtomListControl listControlC;
    private ButtomListControl listControlS;
    private ButtomListControl listControlF;
    private ButtomListControl listControlCon;
    private ButtomListControl listControlDe;
    
    private ButtomControl butContN;
    private ButtomControl butContC;
    private ButtomControl butContS;
    private ButtomControl butContF;
    private ButtomControl butContCon;
    private ButtomControl butContDe;
    private ButtomControl ButContCan;
    
    private EventQueueCenter eqc;

    public ButtomBar(RollingCheeseActivity activity, EventQueueCenter eqc){
        this.activity = activity;
        this.isTouch = false;
        
        ButtomBar.initBitmap(activity);
        this.eqc = eqc;
        
        listControlN = new ButtomListControl(0,listNBitmap,eqc);
        listControlN.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        listControlN.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);
        listControlC = new ButtomListControl(110,listCBitmap,eqc);
        listControlC.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        listControlC.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);
        listControlS = new ButtomListControl(220,listSBitmap,eqc);
        listControlS.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        listControlS.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);
        listControlF = new ButtomListControl(330,listFBitmap,eqc);
        listControlF.addButtomArea(100,160,EventEnum.OriginalCheeseSmall);
        listControlF.addButtomArea(190,290,EventEnum.OriginalCheeseLarge);

                
        butContN = new ButtomControl(0,buttomNBitmap);
        butContC = new ButtomControl(110,buttomCBitmap);
        butContS = new ButtomControl(220,buttomSBitmap);
        butContF = new ButtomControl(330,buttomFBitmap);
        butContCon = new ButtomControl(440,buttomConBitmap);
        butContDe = new ButtomControl(550,buttomDeBitmap);

    }

    public static Resources r;
    public static Context context;
    public static void initBitmap(Context context){
        ButtomBar.context = context;
        ButtomBar.r = context.getResources();
        buttomBar = BitmapFactory.decodeResource(r, R.drawable.buttombar);
        buttomNBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom_normal);
        buttomCBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom_casumarzu);
        buttomSBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom_sweaty);
        buttomFBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom_firing);
        buttomConBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom_construct);
        buttomDeBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom_destruct);
        
        listNBitmap = BitmapFactory.decodeResource(r, R.drawable.list_original);
        listCBitmap = BitmapFactory.decodeResource(r, R.drawable.list_casumarzu);
        listSBitmap = BitmapFactory.decodeResource(r, R.drawable.list_sweaty);
        listFBitmap = BitmapFactory.decodeResource(r, R.drawable.list_firing);
        
    }

    public void draw(Canvas canvas){
        listControlN.draw(canvas);
        listControlC.draw(canvas);
        listControlS.draw(canvas);
        listControlF.draw(canvas);
        
        canvas.drawBitmap(buttomBar, 0,0, null);
        
        butContN.draw(canvas);
        butContC.draw(canvas);
        butContS.draw(canvas);
        butContF.draw(canvas);
        butContCon.draw(canvas);
        butContDe.draw(canvas);
    }


    public void onTouch(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            isTouch = false;
        }
        if(event.getAction()!=MotionEvent.ACTION_DOWN)return;
        if(butContN.onTouch(event)){
            listControlN.buttomPress();
            isTouch = true;
        }
        if(butContC.onTouch(event)){
            listControlC.buttomPress();
            isTouch = true;
        }
        if(butContS.onTouch(event)){
            listControlS.buttomPress();
            isTouch = true;
        }
        if(butContF.onTouch(event)){
            listControlF.buttomPress();
            isTouch = true;
        }
        if(butContCon.onTouch(event)){
            //normalListControl.buttomPress();
            isTouch = true;
        }
        if(butContDe.onTouch(event)){
            //normalListControl.buttomPress();
            isTouch = true;
        }
        
        listControlN.onTouch(event);
        listControlC.onTouch(event);
        listControlS.onTouch(event);
        listControlF.onTouch(event);
        return;
    }

}





