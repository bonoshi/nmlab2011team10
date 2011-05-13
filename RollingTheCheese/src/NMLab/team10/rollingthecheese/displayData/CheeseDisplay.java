package NMLab.team10.rollingthecheese.displayData;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import android.graphics.Canvas;
import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.gameSetting.CheeseEnum;
import NMLab.team10.rollingthecheese.gameSetting.CheeseMessage;
import NMLab.team10.rollingthecheese.gameSetting.CheeseParameter;
import NMLab.team10.rollingthecheese.gameSetting.CheeseSizeEnum;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;

public class CheeseDisplay {
 
    static Bitmap cheeseOL;
    
    static Bitmap cheeseOM;
    static Bitmap cheeseOS;
    static Bitmap cheeseOT;

    public static void initBitmap(){
        Resources r = GameView.r;
        Matrix matrix = new Matrix();
        Bitmap cheeseO = BitmapFactory.decodeResource(r, R.drawable.cheese_original);
        matrix.postScale(0.8f, 0.8f);
        cheeseOL = Bitmap.createBitmap(cheeseO,0,0,cheeseO.getWidth(),cheeseO.getWidth(),matrix,false);
        matrix.postRotate(30);
        matrix.postScale(0.8f, 0.8f);
        cheeseOM = Bitmap.createBitmap(cheeseO,0,0,cheeseO.getWidth(),cheeseO.getWidth(),matrix,false);
        matrix.postScale(0.8f, 0.8f);
        cheeseOS = Bitmap.createBitmap(cheeseO,0,0,cheeseO.getWidth(),cheeseO.getWidth(),matrix,false);
        matrix.postScale(0.8f, 0.8f);
        cheeseOT = Bitmap.createBitmap(cheeseO,0,0,cheeseO.getWidth(),cheeseO.getWidth(),matrix,false);
        
        
    }
    public CheeseDisplay(CheeseMessage cm) {
        this.setCheeseMessage(cm);
    }
    public void setCheeseMessage(CheeseMessage cheeseMessage) {
        this.cheeseMessage = cheeseMessage;
    }
    public CheeseMessage getCheeseMessage() {
        return cheeseMessage;
    }
    private CheeseMessage cheeseMessage;


    public byte getType() {//Normal, Casumarzu, Sweaty, Firing
        return cheeseMessage.getType();
    }
    public byte getSize() {// Large, Medium, Small, Tiny
        return cheeseMessage.getSize();
    }
    public int getHPPercent() {
        return cheeseMessage.getHPPercent();
    }
    public float getX() {
        return cheeseMessage.getX();
    }
    public float getY() {
        return GlobalParameter.MapHeight-cheeseMessage.getY();
    }

    public void draw(boolean whichSide, Canvas canvas){
        switch (getType()) {
            case Original: {
                switch (getSize()) {
                    case Large:
                        canvas.drawBitmap(cheeseOL, getX()-CheeseParameter.Normal.RadixLarge ,getY()-CheeseParameter.Normal.RadixLarge , null);
                        break;
                    case Medium:
                        canvas.drawBitmap(cheeseOM, getX()-CheeseParameter.Normal.RadixMed ,getY()-CheeseParameter.Normal.RadixMed , null);
                        break;
                    case Small:
                        canvas.drawBitmap(cheeseOS, getX()-CheeseParameter.Normal.RadixSmall ,getY()-CheeseParameter.Normal.RadixSmall , null);
                        break;
                    case Tiny:
                        canvas.drawBitmap(cheeseOT, getX()-CheeseParameter.Normal.RadixTiny ,getY()-CheeseParameter.Normal.RadixTiny , null);
                        break;
                }
                break;
            }
            case Casumarzu: {
                break;
            }
            case Sweaty: {
                break;
            }
            case Firing: {
                break;
            }
        }
    }

    public static final byte Original = CheeseEnum.Original;
    public static final byte Casumarzu = CheeseEnum.Poison;
    public static final byte Sweaty = CheeseEnum.Sweaty;
    public static final byte Firing = CheeseEnum.Firing;

    public static final byte Large = CheeseSizeEnum.Large;
    public static final byte Medium = CheeseSizeEnum.Medium;
    public static final byte Small = CheeseSizeEnum.Small;
    public static final byte Tiny = CheeseSizeEnum.Tiny;

}
