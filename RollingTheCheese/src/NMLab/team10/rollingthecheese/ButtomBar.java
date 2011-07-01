package NMLab.team10.rollingthecheese;

import java.io.IOException;

import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.CheeseParameter;
import NMLab.team10.rollingthecheese.gameSetting.CowParameter;
import NMLab.team10.rollingthecheese.gameSetting.DestructParameter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class ButtomBar {

    public boolean isTouch;

    // private RollingCheeseActivity activity;

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

    static private ListContTwoButtom listControlN;
    static private ListContTwoButtom listControlC;
    static private ListContTwoButtom listControlS;
    static private ListContTwoButtom listControlF;
    static private ListContFourButtom listControlCon;
    static private ListContFourButtom listControlDe;

    private ButtomControl butContN;
    private ButtomControl butContC;
    private ButtomControl butContS;
    private ButtomControl butContF;
    private ButtomControl butContCon;
    private ButtomControl butContDe;
    private ButtomControl ButContCan;

    private EventQueueCenter eqc;
    private DisplayData displayData;

    static public void warmupButton(Canvas canvas){
        canvas.drawBitmap(listNBitmap, new Rect(0, 0, 1, 1), new Rect(0, 0, 1, 1), null);
        canvas.drawBitmap(listCBitmap, new Rect(0, 0, 1, 1), new Rect(0, 0, 1, 1), null);
        canvas.drawBitmap(listSBitmap, new Rect(0, 0, 1, 1), new Rect(0, 0, 1, 1), null);
        canvas.drawBitmap(listFBitmap, new Rect(0, 0, 1, 1), new Rect(0, 0, 1, 1), null);
        canvas.drawBitmap(listConBitmap, new Rect(0, 0, 1, 1), new Rect(0, 0, 1, 1), null);
        canvas.drawBitmap(listDeBitmap, new Rect(0, 0, 1, 1), new Rect(0, 0, 1, 1), null);
    }

    public ButtomBar(RollingCheeseActivity activity, EventQueueCenter eqc, DisplayData displayData) {
        // this.activity = activity;
        this.isTouch = false;

        this.eqc = eqc;
        this.displayData = displayData;

        listControlN = new ListContTwoButtom(0, listNBitmap, eqc, EventEnum.OriginalCheeseSmall,
                EventEnum.OriginalCheeseLarge);
        listControlC = new ListContTwoButtom(110, listCBitmap, eqc, EventEnum.CasuMarzuSmall,
                EventEnum.CasuMarzuLarge);
        listControlS = new ListContTwoButtom(220, listSBitmap, eqc, EventEnum.SweatyCheeseSmall,
                EventEnum.SweatyCheeseLarge);
        listControlF = new ListContTwoButtom(330, listFBitmap, eqc, EventEnum.FiringCheeseSmall,
                EventEnum.FiringCheeseLarge);

        listControlCon = new ListContFourButtom(365, listConBitmap, eqc, EventEnum.PurchaseCow,
                EventEnum.CheeseQual, EventEnum.Projector, EventEnum.CheeseProd);
        listControlDe = new ListContFourButtom(455, listDeBitmap, eqc, EventEnum.MilkLeak,
                EventEnum.MiceArmy, EventEnum.BlackOut, EventEnum.IntoTheWild);

        butContN = new ButtomControl(0, buttomNBitmap);
        butContC = new ButtomControl(110, buttomCBitmap);
        butContS = new ButtomControl(220, buttomSBitmap);
        butContF = new ButtomControl(330, buttomFBitmap);
        butContCon = new ButtomControl(440, buttomConBitmap);
        butContDe = new ButtomControl(550, buttomDeBitmap);
        ButContCan = new ButtomControl(660, buttomCanBitmap);

    }

    public static Resources r;
    public static Context context;
    private static Paint moneyPaint = new Paint();
    private static Paint numPaint = new Paint();
    private static Paint otherMoney = new Paint();

    public static void initBitmap(Context context) {
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

        moneyPaint.setColor(Color.DKGRAY);
        moneyPaint.setTextSize(15);

        numPaint.setColor(Color.RED);
        numPaint.setTextSize(15);

        otherMoney.setColor(Color.RED);
        otherMoney.setTextSize(15);
    }

    public void refreshFrame(){
        listControlN.refreshFrame();
        listControlC.refreshFrame();
        listControlS.refreshFrame();
        listControlF.refreshFrame();
        listControlCon.refreshFrame();
        listControlDe.refreshFrame();
    }

    public void draw(Canvas canvas) {
        listControlN.draw(canvas);
        listControlC.draw(canvas);
        listControlS.draw(canvas);
        listControlF.draw(canvas);
        listControlCon.draw(canvas);
        listControlDe.draw(canvas);

        if (listControlN.isOpen()) {
            canvas.drawText("$" + Integer.toString(CheeseParameter.getPrice(EventEnum.OriginalCheeseSmall)),
                    ListTwo_smallDeltaX, ListTwo_smallDeltaY, moneyPaint);
            canvas.drawText("$" + Integer.toString(CheeseParameter.getPrice(EventEnum.OriginalCheeseLarge)),
                    ListTwo_largeDeltaX, ListTwo_largeDeltaY, moneyPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().normalSmallN),
                    ListTwo_smallDeltaX + 20, ListTwo_smallDeltaY + 15, numPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().normalLargeN),
                    ListTwo_largeDeltaX + 43, ListTwo_largeDeltaY + 30, numPaint);
        }
        if (listControlC.isOpen()) {
            canvas.drawText("$" + Integer.toString(CheeseParameter.getPrice(EventEnum.CasuMarzuSmall)),
                    ListTwo_smallDeltaX + buttomStep, ListTwo_smallDeltaY, moneyPaint);
            canvas.drawText("$" + Integer.toString(CheeseParameter.getPrice(EventEnum.CasuMarzuLarge)),
                    ListTwo_largeDeltaX + buttomStep, ListTwo_largeDeltaY, moneyPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().poisonSmallN), ListTwo_smallDeltaX
                    + buttomStep + 20, ListTwo_smallDeltaY + 15, numPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().poisonLargeN), ListTwo_largeDeltaX
                    + buttomStep + 43, ListTwo_largeDeltaY + 30, numPaint);
        }
        if (listControlS.isOpen()) {
            canvas.drawText("$" + Integer.toString(CheeseParameter.getPrice(EventEnum.SweatyCheeseSmall)),
                    ListTwo_smallDeltaX + buttomStep * 2, ListTwo_smallDeltaY, moneyPaint);
            canvas.drawText("$" + Integer.toString(CheeseParameter.getPrice(EventEnum.SweatyCheeseLarge)),
                    ListTwo_largeDeltaX + buttomStep * 2, ListTwo_largeDeltaY, moneyPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().sweatySmallN), ListTwo_smallDeltaX
                    + buttomStep * 2 + 20, ListTwo_smallDeltaY + 15, numPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().sweatyLargeN), ListTwo_largeDeltaX
                    + buttomStep * 2 + 43, ListTwo_largeDeltaY + 30, numPaint);
        }
        if (listControlF.isOpen()) {
            canvas.drawText("$" + Integer.toString(CheeseParameter.getPrice(EventEnum.FiringCheeseSmall)),
                    ListTwo_smallDeltaX + buttomStep * 3, ListTwo_smallDeltaY, moneyPaint);
            canvas.drawText("$" + Integer.toString(CheeseParameter.getPrice(EventEnum.FiringCheeseLarge)),
                    ListTwo_largeDeltaX + buttomStep * 3, ListTwo_largeDeltaY, moneyPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().firingSmallN), ListTwo_smallDeltaX
                    + buttomStep * 3 + 20, ListTwo_smallDeltaY + 15, numPaint);
            canvas.drawText(Integer.toString(displayData.getButtonD().firingLargeN), ListTwo_largeDeltaX
                    + buttomStep * 3 + 43, ListTwo_largeDeltaY + 30, numPaint);
        }

        if (listControlCon.isOpen()) {
            String cowMoney = CowParameter.getPriceText(displayData.getCowList().size());
            String qualMoney = displayData.getHouse().getUpQualMilkText();
            String projMoney = displayData.getProjector().getUpMilkText();
            String prodMoney = displayData.getHouse().getUpProdMilkText();
            String CowP = Integer.toString(displayData.getButtonD().cowPercent);
            String qualP = Integer.toString(displayData.getButtonD().cheeseQualPercent);
            String projP = Integer.toString(displayData.getButtonD().projectorPercent);
            String houseP = Integer.toString(displayData.getButtonD().cheeseProdPercent);

            canvas.drawText("$" + cowMoney, 400, 125, otherMoney);
            canvas.drawText("$" + qualMoney, 500, 130, otherMoney);
            canvas.drawText("$" + projMoney, 400, 235, otherMoney);
            canvas.drawText("$" + prodMoney, 500, 230, otherMoney);
            if (displayData.getButtonD().cowPercent != 0 && displayData.getButtonD().cowPercent != 100)
                canvas.drawText(CowP + "%", 450, 125, otherMoney);
            if (displayData.getButtonD().cheeseQualPercent != 0
                    && displayData.getButtonD().cheeseQualPercent != 100)
                canvas.drawText(qualP + "%", 550, 130, otherMoney);
            if (displayData.getButtonD().projectorPercent != 0
                    && displayData.getButtonD().projectorPercent != 100)
                canvas.drawText(projP + "%", 450, 235, otherMoney);
            if (displayData.getButtonD().cheeseProdPercent != 0
                    && displayData.getButtonD().cheeseProdPercent != 100)
                canvas.drawText(houseP + "%", 550, 230, otherMoney);

        }
        if (listControlDe.isOpen()) {
            String poisonM = "$" + Integer.toString(DestructParameter.MilkCost);
            String smallM = "$" + Integer.toString(DestructParameter.SmallCost);
            String fenceM = "$" + Integer.toString(DestructParameter.FenseCost);
            String powerM = "$" + Integer.toString(DestructParameter.PowerCost);

            if (displayData.getButtonD().milkTriggered) {
                poisonM = "Be patient...";
            } else {
                if (displayData.getButtonD().milk)
                    poisonM = "On duty!";
            }
            if (displayData.getButtonD().smallCheeseTriggered) {
                smallM = "Be patient...";
            } else {
                if (displayData.getButtonD().smallCheese)
                    smallM = "On duty!";
            }
            if (displayData.getButtonD().fenseTriggered) {
                fenceM = "Be patient...";
            } else {
                if (displayData.getButtonD().fense)
                    fenceM = "On duty!";
            }
            if (displayData.getButtonD().powerTriggered) {
                powerM = "Be patient...";
            } else {
                if (displayData.getButtonD().power)
                    powerM = "On duty!";
            }

            canvas.drawText(poisonM, 510, 125, otherMoney);
            canvas.drawText(smallM, 610, 130, otherMoney);
            canvas.drawText(fenceM, 610, 235, otherMoney);
            canvas.drawText(powerM, 510, 230, otherMoney);

        }

        if (listControlDe.isOpen()) {

        }
        canvas.drawBitmap(buttomBar, 0, 0, null);

        butContN.draw(canvas);
        butContC.draw(canvas);
        butContS.draw(canvas);
        butContF.draw(canvas);
        butContCon.draw(canvas);
        butContDe.draw(canvas);
        ButContCan.draw(canvas);

    }

    public void onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            isTouch = false;
        }
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return;
        if (butContN.onTouch(event)) {
            listControlN.buttomPress();
            isTouch = true;
        }
        if (butContC.onTouch(event)) {
            listControlC.buttomPress();
            isTouch = true;
        }
        if (butContS.onTouch(event)) {
            listControlS.buttomPress();
            isTouch = true;
        }
        if (butContF.onTouch(event)) {
            listControlF.buttomPress();
            isTouch = true;
        }
        if (butContCon.onTouch(event)) {
            listControlCon.buttomPress();
            isTouch = true;
        }
        if (butContDe.onTouch(event)) {
            listControlDe.buttomPress();
            isTouch = true;
        }
        if (ButContCan.onTouch(event)) {
            try {
                eqc.addEvent(EventEnum.CancelCheese);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
