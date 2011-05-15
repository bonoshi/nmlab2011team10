package NMLab.team10.rollingthecheese.displayData;

import java.util.Iterator;
import java.util.LinkedList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.byteEnum.CheeseProdEnum;
import NMLab.team10.rollingthecheese.byteEnum.CheeseQualityEnum;
import NMLab.team10.rollingthecheese.gameSetting.HouseMessage;

public class HouseDisplay {

    static Resources r = GameView.r;
    private static Bitmap houseBitmap;
    private static Bitmap houseBitmap_m;
    
    static public void initial(){
        r = GameView.r;
        houseBitmap = BitmapFactory.decodeResource(r, R.drawable.house);
        float[] mirrorX = { 1,0,0,
                            0,-1,0,
                            0,0,1
                            };
        Matrix matrix = new Matrix();
        matrix.setValues(mirrorX);
        
        houseBitmap_m = Bitmap.createBitmap(houseBitmap,0,0,houseBitmap.getWidth(),houseBitmap.getHeight(),matrix,false);
        
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

    public static final int addSmokeT = 20;

    int addSmokeCounter = 0;
    byte smokeType = 0;

    public void draw(boolean whichSide, Canvas canvas) {
        HouseMessage tempHouse = this.house;
        switch (tempHouse.getProd()) {
            case ForFun:
                if (whichSide) {
                    canvas.drawBitmap(houseBitmap, -97, 200, null);
                    
                } else {
                    canvas.drawBitmap(houseBitmap_m, 1420, 200, null);
                }
                break;
            case AfterHours:
                break;
            case Bakery:
                break;
            case FoodFactory:
                break;
            default:// no use
                break;
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
            if(type.isDead)
                iterator.remove();
        }
    }

    private void drawSmoke(Canvas canvas, boolean whichSide) {
        for (Iterator<SmokeDisplay> iterator = smokeList.iterator(); iterator.hasNext();) {
            SmokeDisplay temp = (SmokeDisplay) iterator.next();
            temp.draw(canvas, whichSide);
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
