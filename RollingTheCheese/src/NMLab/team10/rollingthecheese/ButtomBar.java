package NMLab.team10.rollingthecheese;


import NMLab.team10.rollingthecheese.displayData.CowDisplay;
import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.CheeseParameter;
import NMLab.team10.rollingthecheese.gameSetting.CowParameter;
import NMLab.team10.rollingthecheese.gameSetting.DestructParameter;
import NMLab.team10.rollingthecheese.gameSetting.Farm;
import NMLab.team10.rollingthecheese.gameSetting.House;
import NMLab.team10.rollingthecheese.gameSetting.HouseMessage;
import NMLab.team10.rollingthecheese.gameSetting.Projector;
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
    
    
    static float ListTwo_smallDeltaX = 47f;
    static float ListTwo_smallDeltaY = 130f;
    static float ListTwo_largeDeltaX = 40f;
    static float ListTwo_largeDeltaY = 235f;
    
    static float buttomStep = 110f;
    
    
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
    private DisplayData displayData;

    public ButtomBar(RollingCheeseActivity activity, EventQueueCenter eqc,DisplayData displayData){
        //this.activity = activity;
        this.isTouch = false;
        
        ButtomBar.initBitmap(activity);
        this.eqc = eqc;
        this.displayData = displayData;
        
        listControlN = new ListContTwoButtom(0,listNBitmap,eqc,EventEnum.OriginalCheeseSmall,EventEnum.OriginalCheeseLarge);
        listControlC = new ListContTwoButtom(110,listCBitmap,eqc,EventEnum.CasuMarzuSmall,EventEnum.CasuMarzuLarge);
        listControlS = new ListContTwoButtom(220,listSBitmap,eqc,EventEnum.SweatyCheeseSmall,EventEnum.SweatyCheeseLarge);
        listControlF = new ListContTwoButtom(330,listFBitmap,eqc,EventEnum.FiringCheeseSmall,EventEnum.FiringCheeseLarge);
        
        listControlCon = new ListContFourButtom(365, listConBitmap, eqc, EventEnum.PurchaseCow, 
                                                                        EventEnum.CheeseProd,
                                                                        EventEnum.Projector,    
                                                                        EventEnum.CheeseQual);
        listControlDe = new ListContFourButtom(455, listDeBitmap, eqc, EventEnum.MilkLeak, 
                                                                        EventEnum.MiceArmy,
                                                                        EventEnum.BlackOut,
                                                                        EventEnum.IntoTheWild);
                
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
        Paint moneyPaint = new Paint();
        moneyPaint.setColor(Color.DKGRAY);
        moneyPaint.setTextSize(15);
        Paint numPaint = new Paint();
        numPaint.setColor(Color.RED);
        numPaint.setTextSize(15);
        Paint otherMoney = new Paint();
        otherMoney.setColor(Color.RED);
        otherMoney.setTextSize(15);
        
        if(listControlN.isOpen()){
            canvas.drawText("$"+Integer.toString(CheeseParameter.getPrice(EventEnum.OriginalCheeseSmall)), ListTwo_smallDeltaX,ListTwo_smallDeltaY, moneyPaint);
            canvas.drawText("$"+Integer.toString(CheeseParameter.getPrice(EventEnum.OriginalCheeseLarge)), ListTwo_largeDeltaX,ListTwo_largeDeltaY, moneyPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().normalSmallN)
                            ,ListTwo_smallDeltaX+20,ListTwo_smallDeltaY+15, numPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().normalLargeN)
                    ,ListTwo_largeDeltaX+43,ListTwo_largeDeltaY+30, numPaint);
        }
        if(listControlC.isOpen()){
            canvas.drawText("$"+Integer.toString(CheeseParameter.getPrice(EventEnum.CasuMarzuSmall)), ListTwo_smallDeltaX+buttomStep,ListTwo_smallDeltaY, moneyPaint);
            canvas.drawText("$"+Integer.toString(CheeseParameter.getPrice(EventEnum.CasuMarzuLarge)), ListTwo_largeDeltaX+buttomStep,ListTwo_largeDeltaY, moneyPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().poisonSmallN)
                    ,ListTwo_smallDeltaX+buttomStep+20,ListTwo_smallDeltaY+15, numPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().poisonLargeN)
                    ,ListTwo_largeDeltaX+buttomStep+43,ListTwo_largeDeltaY+30, numPaint);
        }
        if(listControlS.isOpen()){
            canvas.drawText("$"+Integer.toString(CheeseParameter.getPrice(EventEnum.SweatyCheeseSmall)), ListTwo_smallDeltaX+buttomStep*2,ListTwo_smallDeltaY, moneyPaint);
            canvas.drawText("$"+Integer.toString(CheeseParameter.getPrice(EventEnum.SweatyCheeseLarge)), ListTwo_largeDeltaX+buttomStep*2,ListTwo_largeDeltaY, moneyPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().sweatySmallN)
                    ,ListTwo_smallDeltaX+buttomStep*2+20,ListTwo_smallDeltaY+15, numPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().sweatyLargeN)
                    ,ListTwo_largeDeltaX+buttomStep*2+43,ListTwo_largeDeltaY+30, numPaint);
        }
        if(listControlF.isOpen()){
            canvas.drawText("$"+Integer.toString(CheeseParameter.getPrice(EventEnum.FiringCheeseSmall)), ListTwo_smallDeltaX+buttomStep*3,ListTwo_smallDeltaY, moneyPaint);
            canvas.drawText("$"+Integer.toString(CheeseParameter.getPrice(EventEnum.FiringCheeseLarge)), ListTwo_largeDeltaX+buttomStep*3,ListTwo_largeDeltaY, moneyPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().firingSmallN)
                    ,ListTwo_smallDeltaX+buttomStep*3+20,ListTwo_smallDeltaY+15, numPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().firingLargeN)
                    ,ListTwo_largeDeltaX+buttomStep*3+43,ListTwo_largeDeltaY+30, numPaint);
        }
        
        if(listControlCon.isOpen()){
            String farmMoney = displayData.getFarm().getUpProdMilkText();
            String houseMoney = displayData.getHouse().getUpProdMilkText();
            String projMoney = displayData.getProjector().getUpMilkText();
            String CowMoney = CowParameter.getPriceText(displayData.getCowList().size());
            String farmP = Integer.toString(displayData.getButtonD().MilkProdPercent);
            String houseP = Integer.toString(displayData.getButtonD().cheeseProdPercent);
            String projP = Integer.toString(displayData.getButtonD().projectorPercent);
            String CowP = Integer.toString(displayData.getButtonD().cowPercent);
                     
            
            canvas.drawText("$"+CowMoney,400,125,otherMoney);
            canvas.drawText("$"+farmMoney,500,130,otherMoney);
            canvas.drawText("$"+projMoney, 400, 235, otherMoney);
            canvas.drawText("$"+houseMoney, 500, 230, otherMoney);
            if(displayData.getButtonD().MilkProdPercent!=0&&displayData.getButtonD().MilkProdPercent!=100)
                canvas.drawText(farmP+"%",550,130,otherMoney);
            if(displayData.getButtonD().cheeseProdPercent!=0&&displayData.getButtonD().cheeseProdPercent!=100)
                canvas.drawText(houseP+"%", 550, 230, otherMoney);
            if(displayData.getButtonD().cowPercent!=0&&displayData.getButtonD().cowPercent!=100)
                canvas.drawText(CowP+"%",450,125,otherMoney);
            if(displayData.getButtonD().projectorPercent!=0&&displayData.getButtonD().projectorPercent!=100)
                canvas.drawText(projP+"%", 450, 235, otherMoney);
            
                      
        }
        if(listControlDe.isOpen()){
            String poisonM = "$"+Integer.toString(DestructParameter.MilkCost);
            String smallM = "$"+Integer.toString(DestructParameter.SmallCost);
            String fenceM = "$"+Integer.toString(DestructParameter.FenseCost);
            String powerM = "$"+Integer.toString(DestructParameter.PowerCost);
            
            if(displayData.getTime()>35000){
                if(displayData.getButtonD().milkDisplay)
                    poisonM = "On duty!";
                if(displayData.getButtonD().smallCheeseDisplay)
                    smallM = "On duty!";
                if(displayData.getButtonD().fenseDisplay)
                    fenceM = "On duty!";
                if(displayData.getButtonD().powerDisplay)
                    powerM = "On duty!";
           }else{
               if(displayData.getButtonD().milkDisplay)
                   poisonM = "Sunset...";
               if(displayData.getButtonD().smallCheeseDisplay)
                   smallM = "Sunset...";
               if(displayData.getButtonD().fenseDisplay)
                   fenceM = "Sunset...";
               if(displayData.getButtonD().powerDisplay)
                   powerM = "Sunset...";               
           }
                
            canvas.drawText(poisonM,510,125,otherMoney);
            canvas.drawText(smallM,610,130,otherMoney);
            canvas.drawText(fenceM, 510, 235, otherMoney);
            canvas.drawText(powerM, 610, 230, otherMoney);
            
            
        }
        
        if(listControlDe.isOpen()){
            
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





