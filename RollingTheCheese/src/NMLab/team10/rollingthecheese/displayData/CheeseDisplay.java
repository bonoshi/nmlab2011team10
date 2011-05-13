package NMLab.team10.rollingthecheese.displayData;
import android.R.integer;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import android.graphics.Canvas;
import android.text.StaticLayout;
import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.gameSetting.Cheese;
import NMLab.team10.rollingthecheese.gameSetting.CheeseEnum;
import NMLab.team10.rollingthecheese.gameSetting.CheeseMessage;
import NMLab.team10.rollingthecheese.gameSetting.CheeseParameter;
import NMLab.team10.rollingthecheese.gameSetting.CheeseSizeEnum;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;

public class CheeseDisplay {
 
    static Bitmap cheeseOL[];
    static Bitmap cheeseOM[];
    static Bitmap cheeseOS[];
    static Bitmap cheeseOT[];
    static final int HEALTHY = 0;
    static final int LITTLE_DAMAGED = 1;
    static final int SERIOUS_DAMAGED = 2;
    static final int DEAD = 3;
    static final int cheesePictureStep = 120;
    static final int cheesePictureSize = 110;
    

    
    
    public static void initBitmap(){
        Resources r = GameView.r;
        
        cheeseOL = new Bitmap[4];
        Bitmap tmp = BitmapFactory.decodeResource(r, R.drawable.cheese_original);
        cheeseOL[HEALTHY] = Bitmap.createBitmap(tmp,0,0,cheesePictureSize,cheesePictureSize);
        cheeseOL[LITTLE_DAMAGED] = Bitmap.createBitmap(tmp,cheesePictureStep,0,cheesePictureSize,cheesePictureSize);
        cheeseOL[SERIOUS_DAMAGED] = Bitmap.createBitmap(tmp,cheesePictureStep*2,0,cheesePictureSize,cheesePictureSize);
        cheeseOL[DEAD]=Bitmap.createBitmap(tmp,cheesePictureStep*3,0,cheesePictureSize,cheesePictureSize);
        
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
    public short getSpinAngle(){
        return cheeseMessage.getSpinAngle();
    }

    public void draw(boolean whichSide, Canvas canvas){
        switch (getType()) {
            case Original: {
                switch (getSize()) {
                    case Large:
                        Matrix matrix = new Matrix();
                        if(whichSide == Cheese.Left)
                            matrix.postRotate(-getSpinAngle());
                        else 
                            matrix.postRotate(getSpinAngle());    
                    
                        int status;
                        if(getHPPercent()==100)status = HEALTHY;
                        else{
                            status = 3-getHPPercent()/33;
                        }
                        
                        Bitmap picture = Bitmap.createBitmap(cheeseOL[status],0,0,cheeseOL[status].getWidth(),cheeseOL[status].getHeight(),matrix,false);
                        canvas.drawBitmap(picture, getX()-CheeseParameter.Normal.RadixLarge ,getY()-CheeseParameter.Normal.RadixLarge , null);

                        break;
                    case Medium:
        //                canvas.drawBitmap(cheeseOM, getX()-CheeseParameter.Normal.RadixMed ,getY()-CheeseParameter.Normal.RadixMed , null);
                        break;
                    case Small:
        //                canvas.drawBitmap(cheeseOS, getX()-CheeseParameter.Normal.RadixSmall ,getY()-CheeseParameter.Normal.RadixSmall , null);
                        break;
                    case Tiny:
        //                canvas.drawBitmap(cheeseOT, getX()-CheeseParameter.Normal.RadixTiny ,getY()-CheeseParameter.Normal.RadixTiny , null);
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
