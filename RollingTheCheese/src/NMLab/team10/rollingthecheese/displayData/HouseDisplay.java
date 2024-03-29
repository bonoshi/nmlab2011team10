package NMLab.team10.rollingthecheese.displayData;

import java.util.Iterator;
import java.util.LinkedList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.byteEnum.CheeseProdEnum;
import NMLab.team10.rollingthecheese.byteEnum.CheeseQualityEnum;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;
import NMLab.team10.rollingthecheese.gameSetting.House;
import NMLab.team10.rollingthecheese.gameSetting.HouseMessage;
import NMLab.team10.rollingthecheese.gameSetting.HouseParameter;

public class HouseDisplay {

    static Resources r = GameView.r;
    private static Bitmap houseBitmap[][];
    // private static Bitmap houseBitmap_m[][];
    private static Bitmap onWorkingBitmap;
    // private static Bitmap onWorkingBitmap_m;
    private static byte HEALTH = 0;
    private static byte DAMAGED = 1;
    private static byte SERIOUS_DAMAGED = 2;
    private static byte NORMAL = 0;
    private static byte UPGRADE1 = 1;
    private static Matrix matrix;
    private static Paint hpPaint;
    private static Paint qualLeftPaint;
    private static Paint qualRightPaint;
    private static Paint onWorkPaint = null;

    static public void initial() {
        r = GameView.r;
        onWorkPaint = new Paint();
        onWorkPaint.setTextSize(15);
        onWorkPaint.setAntiAlias(true);
        onWorkPaint.setColor(Color.DKGRAY);
        hpPaint = new Paint();
        hpPaint.setColor(0xFFE7003E);
        hpPaint.setAntiAlias(true);
        qualLeftPaint = new Paint();
        qualRightPaint = new Paint();
        qualLeftPaint.setTextAlign(Paint.Align.LEFT);
        qualRightPaint.setTextAlign(Paint.Align.RIGHT);
        houseBitmap = new Bitmap[2][];

        qualLeftPaint.setColor(0xFF7109AA);
        qualLeftPaint.setTextSize(35);
        qualLeftPaint.setAntiAlias(true);
        qualRightPaint.setColor(0xFF7109AA);
        qualRightPaint.setTextSize(25);
        qualRightPaint.setAntiAlias(true);

        // houseBitmap_m = new Bitmap[2][];

        houseBitmap[NORMAL] = new Bitmap[3];
        // houseBitmap_m[NORMAL] = new Bitmap[3];
        Bitmap tmp = BitmapFactory.decodeResource(r, R.drawable.house_n);
        houseBitmap[NORMAL][HEALTH] = Bitmap.createBitmap(tmp, 0, 0, 280, 280);
        houseBitmap[NORMAL][DAMAGED] = Bitmap.createBitmap(tmp, 280, 0, 280, 280);
        houseBitmap[NORMAL][SERIOUS_DAMAGED] = Bitmap.createBitmap(tmp, 560, 0, 280, 280);
        tmp = null;

        houseBitmap[UPGRADE1] = new Bitmap[3];
        // houseBitmap_m[UPGRADE1] = new Bitmap[3];
        Bitmap tmp2 = BitmapFactory.decodeResource(r, R.drawable.house_2);
        houseBitmap[UPGRADE1][HEALTH] = Bitmap.createBitmap(tmp2, 0, 0, 280, 280);
        houseBitmap[UPGRADE1][DAMAGED] = Bitmap.createBitmap(tmp2, 280, 0, 280, 280);
        houseBitmap[UPGRADE1][SERIOUS_DAMAGED] = Bitmap.createBitmap(tmp2, 560, 0, 280, 280);
        matrix = new Matrix();

        float[] mirrorX = { -1, 0, 0, 0, 1, 0, 0, 0, 1 };

        matrix.setValues(mirrorX);
        // houseBitmap_m[NORMAL][HEALTH] =
        // Bitmap.createBitmap(houseBitmap[NORMAL][HEALTH],0,0,houseBitmap[NORMAL][HEALTH].getWidth(),houseBitmap[NORMAL][HEALTH].getHeight(),matrix,false);
        // houseBitmap_m[NORMAL][DAMAGED] =
        // Bitmap.createBitmap(houseBitmap[NORMAL][DAMAGED],0,0,houseBitmap[NORMAL][DAMAGED].getWidth(),houseBitmap[NORMAL][DAMAGED].getHeight(),matrix,false);
        // houseBitmap_m[NORMAL][SERIOUS_DAMAGED] =
        // Bitmap.createBitmap(houseBitmap[NORMAL][SERIOUS_DAMAGED],0,0,houseBitmap[NORMAL][SERIOUS_DAMAGED].getWidth(),houseBitmap[NORMAL][SERIOUS_DAMAGED].getHeight(),matrix,false);
        // houseBitmap_m[UPGRADE1][HEALTH] =
        // Bitmap.createBitmap(houseBitmap[UPGRADE1][HEALTH],0,0,houseBitmap[UPGRADE1][HEALTH].getWidth(),houseBitmap[UPGRADE1][HEALTH].getHeight(),matrix,false);
        // houseBitmap_m[UPGRADE1][DAMAGED] =
        // Bitmap.createBitmap(houseBitmap[UPGRADE1][DAMAGED],0,0,houseBitmap[UPGRADE1][DAMAGED].getWidth(),houseBitmap[UPGRADE1][DAMAGED].getHeight(),matrix,false);
        // houseBitmap_m[UPGRADE1][SERIOUS_DAMAGED] =
        // Bitmap.createBitmap(houseBitmap[UPGRADE1][SERIOUS_DAMAGED],0,0,houseBitmap[UPGRADE1][SERIOUS_DAMAGED].getWidth(),houseBitmap[UPGRADE1][SERIOUS_DAMAGED].getHeight(),matrix,false);
        onWorkingBitmap = BitmapFactory.decodeResource(r, R.drawable.onworking);
        // onWorkingBitmap_m =
        // Bitmap.createBitmap(onWorkingBitmap,0,0,onWorkingBitmap.getWidth(),onWorkingBitmap.getHeight(),matrix,false);

    }

    public HouseDisplay(HouseMessage h) {
        this.setHouse(h);
    }

    public void setHouse(HouseMessage house) {
        this.house = house;
    }

    public HouseMessage getHouse() {
        return house;
    }

    public void setAnimation(byte animation) {
        this.animation = animation;
    }

    public int getAnimation() {
        animation++;
        animation %= 4;
        return animation;
    }

    public byte getProd() {
        return house.getProd();
    }

    public static final int addSmokeT = 20;

    int addSmokeCounter = 0;
    byte smokeType = 0;

    public void drawHouse(boolean whichSide, Canvas canvas) {
        HouseMessage tempHouse = this.house;
        int state;
        if (tempHouse.getHPPercent() > 66)
            state = HEALTH;
        else if (tempHouse.getHPPercent() > 33)
            state = DAMAGED;
        else
            state = SERIOUS_DAMAGED;

        if (whichSide) {
            if (getHouse().getProd() == HouseMessage.Handmade) {
                canvas.drawBitmap(houseBitmap[NORMAL][state], HouseParameter.ForFunBorder - 227, 200, null);
            } else {
                canvas.drawBitmap(houseBitmap[UPGRADE1][state], HouseParameter.AfterHoursBorder - 227, 200,
                        null);
                // canvas.drawBitmap(houseBitmap[UPGRADE1][state], -97, 200,
                // null);
            }
        } else {

            if (getHouse().getProd() == HouseMessage.Handmade) {
                Bitmap tmpBitmap = Bitmap.createBitmap(houseBitmap[NORMAL][state], 0, 0,
                        houseBitmap[NORMAL][state].getWidth(), houseBitmap[NORMAL][state].getHeight(),
                        matrix, false);
                canvas.drawBitmap(tmpBitmap, House.getBoader(false, getProd()) - 53, 200, null);
                // canvas.drawBitmap(tmpBitmap, 1420, 200, null);
            } else {
                Bitmap tmpBitmap = Bitmap.createBitmap(houseBitmap[UPGRADE1][state], 0, 0,
                        houseBitmap[UPGRADE1][state].getWidth(), houseBitmap[UPGRADE1][state].getHeight(),
                        matrix, false);
                canvas.drawBitmap(tmpBitmap, House.getBoader(false, getProd()) - 53, 200, null);
                // canvas.drawBitmap(tmpBitmap, 1420, 200, null);
            }
        }

        switch (tempHouse.getQual()) {
            case Handmade:
                break;
            case CheeseMold:
                break;
            case FoodChemisty:
                break;
            case GMO:
                break;
            default:// no use
                break;
        }

        drawSmoke(canvas, whichSide);

        if (++addSmokeCounter > addSmokeT) {
            if (Math.random() > 0.95) {
                addSmokeCounter = 0;
                smokeList.add(new SmokeDisplay(whichSide, smokeType));
                smokeType++;
                smokeType %= 4;
            }
        }

        for (Iterator<SmokeDisplay> iterator = smokeList.iterator(); iterator.hasNext();) {
            SmokeDisplay type = (SmokeDisplay) iterator.next();
            if (type.isDead)
                iterator.remove();
        }
    }

    private void drawSmoke(Canvas canvas, boolean whichSide) {
        for (Iterator<SmokeDisplay> iterator = smokeList.iterator(); iterator.hasNext();) {
            SmokeDisplay temp = (SmokeDisplay) iterator.next();
            temp.draw(canvas, whichSide);
        }
    }

    public void drawStatus(boolean whichSide, Canvas canvas) {
        int NL = GameView.displayData.getButtonD().normalLargeP;
        int NS = GameView.displayData.getButtonD().normalSmallP;
        int PL = GameView.displayData.getButtonD().poisonLargeP;
        int PS = GameView.displayData.getButtonD().poisonSmallP;
        int SL = GameView.displayData.getButtonD().sweatyLargeP;
        int SS = GameView.displayData.getButtonD().sweatySmallP;
        int FL = GameView.displayData.getButtonD().firingLargeP;
        int FS = GameView.displayData.getButtonD().firingSmallP;
        if (GameView.displayData.isLeft && whichSide) {

            if (NL > 0 && NL < 100) {
                canvas.drawBitmap(onWorkingBitmap, 120, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseO[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 260, 260,
                        null);
                canvas.drawText(Integer.toString(NL) + "%", 270, 330, onWorkPaint);
            }
            if (NS > 0 && NS < 100) {
                canvas.drawBitmap(onWorkingBitmap, 120, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseO[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 260, 260,
                        null);
                canvas.drawText(Integer.toString(NS) + "%", 270, 330, onWorkPaint);
            }
            if (PL > 0 && PL < 100) {
                canvas.drawBitmap(onWorkingBitmap, 120, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseC[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 260, 260,
                        null);
                canvas.drawText(Integer.toString(PL) + "%", 270, 330, onWorkPaint);
            }
            if (PS > 0 && PS < 100) {
                canvas.drawBitmap(onWorkingBitmap, 120, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseC[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 260, 260,
                        null);
                canvas.drawText(Integer.toString(PS) + "%", 270, 330, onWorkPaint);
            }
            if (SL > 0 && SL < 100) {
                canvas.drawBitmap(onWorkingBitmap, 120, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseS[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 260, 260,
                        null);
                canvas.drawText(Integer.toString(SL) + "%", 270, 330, onWorkPaint);
            }
            if (SS > 0 && SS < 100) {
                canvas.drawBitmap(onWorkingBitmap, 120, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseS[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 260, 260,
                        null);
                canvas.drawText(Integer.toString(SS) + "%", 270, 330, onWorkPaint);
            }
            if (FL > 0 && FL < 100) {
                canvas.drawBitmap(onWorkingBitmap, 120, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseF[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 260, 260,
                        null);
                canvas.drawText(Integer.toString(FL) + "%", 270, 330, onWorkPaint);
            }
            if (FS > 0 && FS < 100) {
                canvas.drawBitmap(onWorkingBitmap, 120, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseF[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 260, 260,
                        null);
                canvas.drawText(Integer.toString(FS) + "%", 270, 330, onWorkPaint);
            }
        } else if (!GameView.displayData.isLeft && !whichSide) {
            Bitmap onWorkingBitmap_m = Bitmap.createBitmap(onWorkingBitmap, 0, 0, onWorkingBitmap.getWidth(),
                    onWorkingBitmap.getHeight(), matrix, false);
            if (NL > 0 && NL < 100) {
                canvas.drawBitmap(onWorkingBitmap_m, 1250, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseO[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 1350,
                        260, null);
                canvas.drawText(Integer.toString(NL) + "%", 1360, 330, onWorkPaint);
            }
            if (NS > 0 && NS < 100) {
                canvas.drawBitmap(onWorkingBitmap_m, 1250, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseO[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 1350,
                        260, null);
                canvas.drawText(Integer.toString(NS) + "%", 1360, 330, onWorkPaint);
            }
            if (PL > 0 && PL < 100) {
                canvas.drawBitmap(onWorkingBitmap_m, 1250, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseC[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 1350,
                        260, null);
                canvas.drawText(Integer.toString(PL) + "%", 1360, 330, onWorkPaint);
            }
            if (PS > 0 && PS < 100) {
                canvas.drawBitmap(onWorkingBitmap_m, 1250, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseC[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 1350,
                        260, null);
                canvas.drawText(Integer.toString(PS) + "%", 1360, 330, onWorkPaint);
            }
            if (SL > 0 && SL < 100) {
                canvas.drawBitmap(onWorkingBitmap_m, 1250, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseS[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 1350,
                        260, null);
                canvas.drawText(Integer.toString(SL) + "%", 1360, 330, onWorkPaint);
            }
            if (SS > 0 && SS < 100) {
                canvas.drawBitmap(onWorkingBitmap_m, 1250, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseS[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 1350,
                        260, null);
                canvas.drawText(Integer.toString(SS) + "%", 1360, 330, onWorkPaint);
            }
            if (FL > 0 && FL < 100) {
                canvas.drawBitmap(onWorkingBitmap_m, 1250, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseF[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 1350,
                        260, null);
                canvas.drawText(Integer.toString(FL) + "%", 1360, 330, onWorkPaint);
            }
            if (FS > 0 && FS < 100) {
                canvas.drawBitmap(onWorkingBitmap_m, 1250, 200, null);
                canvas.drawBitmap(CheeseDisplay.cheeseF[CheeseDisplay.Tiny][CheeseDisplay.HEALTHY], 1350,
                        260, null);
                canvas.drawText(Integer.toString(FS) + "%", 1360, 330, onWorkPaint);
            }
        }


        HouseMessage tempHouse = this.house;

        if (whichSide) {
            canvas.drawRect(0, 90,
                    tempHouse.getHPPercent() * House.getHouseMaxHP(tempHouse.getProd()) / 2500, 100, hpPaint);
        } else {
            canvas.drawRect(
                    GlobalParameter.MapWidth
                            - (tempHouse.getHPPercent() * 1.0F * House.getHouseMaxHP(tempHouse.getProd()) / 2500),
                    90, GlobalParameter.MapWidth, 100, hpPaint);
        }

        switch (tempHouse.getQual()) {
            case Handmade:
                if (whichSide)
                    canvas.drawText("*", 20, 135, qualLeftPaint);
                else
                    canvas.drawText("*", 1580, 135, qualRightPaint);
                break;
            case CheeseMold:
                if (whichSide)
                    canvas.drawText("**", 20, 135, qualLeftPaint);
                else
                    canvas.drawText("**", 1580, 135, qualRightPaint);
                break;
            case FoodChemisty:
                if (whichSide)
                    canvas.drawText("***", 20, 135, qualLeftPaint);
                else
                    canvas.drawText("***", 1580, 135, qualRightPaint);
                break;
            case GMO:
                if (whichSide)
                    canvas.drawText("****", 20, 135, qualLeftPaint);
                else
                    canvas.drawText("****", 1580, 135, qualRightPaint);
                break;
            default:// no use
                break;
        }

    }

    public static final byte ForFun = CheeseProdEnum.ForFun;
    public static final byte AfterHours = CheeseProdEnum.AfterHours;
    public static final byte Bakery = CheeseProdEnum.Bakery;
    public static final byte FoodFactory = CheeseProdEnum.FoodFactory;

    public static final byte Handmade = CheeseQualityEnum.Handmade;
    public static final byte CheeseMold = CheeseQualityEnum.CheeseMold;
    public static final byte FoodChemisty = CheeseQualityEnum.FoodChemisty;
    public static final byte GMO = CheeseQualityEnum.GMO;

    private HouseMessage house;
    private LinkedList<SmokeDisplay> smokeList = new LinkedList<SmokeDisplay>();

    private int animation = 0;

}
