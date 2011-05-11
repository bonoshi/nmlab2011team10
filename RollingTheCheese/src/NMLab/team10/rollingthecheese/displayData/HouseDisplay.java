package NMLab.team10.rollingthecheese.displayData;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.byteEnum.CheeseProdEnum;
import NMLab.team10.rollingthecheese.byteEnum.CheeseQualityEnum;
import NMLab.team10.rollingthecheese.gameSetting.HouseMessage;

public class HouseDisplay {

    public static Resources r;
    private static Bitmap houseBitmap;

    static {
        houseBitmap = BitmapFactory.decodeResource(r, R.drawable.house);
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

    public void draw(boolean whichSide, Canvas canvas) {
        switch (house.getProd()) {
            case ForFun:
                if (whichSide) {
                    canvas.drawBitmap(houseBitmap, -100, 200, null);
                } else {
                    canvas.drawBitmap(houseBitmap, 1420, 200, null);
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
        switch (house.getQual()) {
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

    private int animation = 0;
}
