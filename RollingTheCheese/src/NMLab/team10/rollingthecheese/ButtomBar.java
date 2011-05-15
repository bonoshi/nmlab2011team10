package NMLab.team10.rollingthecheese;


import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.CheeseParameter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;


public class ButtomBar {

    public boolean isTouch;

    //private RollingCheeseActivity activity;

    private static Bitmap buttomBar;
    private static Bitmap buttomNBitmap;
    private static Bitmap buttomCBitmap;
    private static Bitmap buttomSBitmap;
    private static Bitmap buttomFBitmap;
    private static Bitmap buttomConBitmap;
    private static Bitmap buttomDeBitmap;
    private static Bitmap buttomCanBitmap;
    
    private static Bitmap listNBitmap;
    private static Bitmap listCBitmap;
    private static Bitmap listSBitmap;
    private static Bitmap listFBitmap;
    private static Bitmap listConBitmap;
    private static Bitmap listDeBitmap;
    
    
    private ListContTwoButtom listControlN;
    private ListContTwoButtom listControlC;
    private ListContTwoButtom listControlS;
    private ListContTwoButtom listControlF;
    private ListContFourButtom listControlCon;
    private ListContFourButtom listControlDe;
    
    private ButtomControl butContN;
    private ButtomControl butContC;
    private ButtomControl butContS;
    private ButtomControl butContF;
    private ButtomControl butContCon;
    private ButtomControl butContDe;
    private ButtomControl ButContCan;
    
    private EventQueueCenter eqc;

    public ButtomBar(RollingCheeseActivity activity, EventQueueCenter eqc){
        //this.activity = activity;
        this.isTouch = false;
        
        ButtomBar.initBitmap(activity);
        this.eqc = eqc;
        
        listControlN = new ListContTwoButtom(0,listNBitmap,eqc,EventEnum.OriginalCheeseSmall,EventEnum.OriginalCheeseLarge);
        listControlC = new ListContTwoButtom(110,listCBitmap,eqc,EventEnum.CasuMarzuSmall,EventEnum.CasuMarzuLarge);
        listControlS = new ListContTwoButtom(220,listSBitmap,eqc,EventEnum.SweatyCheeseSmall,EventEnum.SweatyCheeseLarge);
        listControlF = new ListContTwoButtom(330,listFBitmap,eqc,EventEnum.FiringCheeseSmall,EventEnum.FiringCheeseLarge);
        
        listControlCon = new ListContFourButtom(345, listConBitmap, eqc, EventEnum.PurchaseCow, 
                                                                        EventEnum.PurchaseCow,
                                                                        EventEnum.PurchaseCow,
                                                                        EventEnum.PurchaseCow);
        listControlDe = new ListContFourButtom(455, listDeBitmap, eqc, EventEnum.OriginalCheeseSmall, 
                                                                        EventEnum.OriginalCheeseMed,
                                                                        EventEnum.OriginalCheeseMed,
                                                                        EventEnum.OriginalCheeseLarge);
                
        butContN = new ButtomControl(0,buttomNBitmap);
        butContC = new ButtomControl(110,buttomCBitmap);
        butContS = new ButtomControl(220,buttomSBitmap);
        butContF = new ButtomControl(330,buttomFBitmap);
        butContCon = new ButtomControl(440,buttomConBitmap);
        butContDe = new ButtomControl(550,buttomDeBitmap);
        ButContCan = new ButtomControl(660, buttomCanBitmap);

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
        buttomCanBitmap = BitmapFactory.decodeResource(r, R.drawable.buttom_cancel);
        
        listNBitmap = BitmapFactory.decodeResource(r, R.drawable.list_original);
        listCBitmap = BitmapFactory.decodeResource(r, R.drawable.list_casumarzu);
        listSBitmap = BitmapFactory.decodeResource(r, R.drawable.list_sweaty);
        listFBitmap = BitmapFactory.decodeResource(r, R.drawable.list_firing);
        listConBitmap = BitmapFactory.decodeResource(r, R.drawable.list_construct);
        listDeBitmap = BitmapFactory.decodeResource(r, R.drawable.list_destruct);
        
    }

    public void draw(Canvas canvas){
        listControlN.draw(canvas);
        listControlC.draw(canvas);
        listControlS.draw(canvas);
        listControlF.draw(canvas);
        listControlCon.draw(canvas);
        listControlDe.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setTextSize(9);
        float smallDeltaX = 40f;
        float smallDeltaY = 115f;
        float largeDeltaX = 30f;
        float largeDeltaY = 220f;
        float buttomStep = 110f;
        if(listControlN.isOpen()){
            canvas.drawText(Integer.toString(CheeseParameter.getPrice(EventEnum.OriginalCheeseSmall)), smallDeltaX,smallDeltaY, paint);
            canvas.drawText(Integer.toString(CheeseParameter.getPrice(EventEnum.OriginalCheeseLarge)), largeDeltaX,largeDeltaY, paint);
        }
        if(listControlC.isOpen()){
            canvas.drawText(Integer.toString(CheeseParameter.getPrice(EventEnum.CasuMarzuSmall)), smallDeltaX+buttomStep,smallDeltaY, paint);
            canvas.drawText(Integer.toString(CheeseParameter.getPrice(EventEnum.CasuMarzuLarge)), largeDeltaX+buttomStep,largeDeltaY, paint);
        }
        if(listControlS.isOpen()){
            canvas.drawText(Integer.toString(CheeseParameter.getPrice(EventEnum.SweatyCheeseSmall)), smallDeltaX+buttomStep*2,smallDeltaY, paint);
            canvas.drawText(Integer.toString(CheeseParameter.getPrice(EventEnum.SweatyCheeseLarge)), largeDeltaX+buttomStep*2,largeDeltaY, paint);
        }
        if(listControlF.isOpen()){
            canvas.drawText(Integer.toString(CheeseParameter.getPrice(EventEnum.FiringCheeseSmall)), smallDeltaX+buttomStep*3,smallDeltaY, paint);
            canvas.drawText(Integer.toString(CheeseParameter.getPrice(EventEnum.FiringCheeseLarge)), largeDeltaX+buttomStep*3,largeDeltaY, paint);
        }
        
        
        canvas.drawBitmap(buttomBar, 0,0, null);
        
        butContN.draw(canvas);
        butContC.draw(canvas);
        butContS.draw(canvas);
        butContF.draw(canvas);
        butContCon.draw(canvas);
        butContDe.draw(canvas);
        ButContCan.draw(canvas);
        
        
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
            listControlCon.buttomPress();
            isTouch = true;
        }
        if(butContDe.onTouch(event)){
            listControlDe.buttomPress();
            isTouch = true;
        }
        if(ButContCan.onTouch(event)){
            eqc.addEvent(EventEnum.CancelCheese);
            isTouch = true;
        }
        
        listControlN.onTouch(event);
        listControlC.onTouch(event);
        listControlS.onTouch(event);
        listControlF.onTouch(event);
        listControlCon.onTouch(event);
        listControlDe.onTouch(event);
        return;
    }
}





