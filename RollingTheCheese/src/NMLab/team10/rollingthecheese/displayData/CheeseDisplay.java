package NMLab.team10.rollingthecheese.displayData;
import android.R.integer;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import android.graphics.Canvas;
import android.text.StaticLayout;
import android.util.Log;
import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.gameSetting.Cheese;
import NMLab.team10.rollingthecheese.gameSetting.CheeseEnum;
import NMLab.team10.rollingthecheese.gameSetting.CheeseMessage;
import NMLab.team10.rollingthecheese.gameSetting.CheeseParameter;
import NMLab.team10.rollingthecheese.gameSetting.CheeseSizeEnum;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;

public class CheeseDisplay {


    static Bitmap cheeseO[][];

    static final byte LARGE = 0;
    static final byte MIDDLE = 1;
    static final byte SMALL = 2;
    static final byte TINY = 3;




    static final int HEALTHY = 0;
    static final int LITTLE_DAMAGED = 1;
    static final int SERIOUS_DAMAGED = 2;
    static final int DEAD = 3;
    static final int cheesePictureStep = 120;
    static final int cheesePictureSize = 110;




    public static void initBitmap(){
        Resources r = GameView.r;

        Bitmap cheeseOtmp = BitmapFactory.decodeResource(r,R.drawable.cheese_original);
        Matrix matrix = new Matrix();
        matrix.postScale(0.8f, 0.8f);
        
        cheeseO = new Bitmap[4][4];
        cheeseO[LARGE][HEALTHY] = Bitmap.createBitmap(cheeseOtmp,0,0,cheesePictureSize,cheesePictureSize);//,matrix,true);
        cheeseO[LARGE][LITTLE_DAMAGED] = Bitmap.createBitmap(cheeseOtmp,cheesePictureStep,0,cheesePictureSize,cheesePictureSize);//,matrix,true);
        cheeseO[LARGE][SERIOUS_DAMAGED] = Bitmap.createBitmap(cheeseOtmp,cheesePictureStep*2,0,cheesePictureSize,cheesePictureSize);
        cheeseO[LARGE][DEAD]=Bitmap.createBitmap(cheeseOtmp,cheesePictureStep*3,0,cheesePictureSize,cheesePictureSize);
        
        
        cheeseO[MIDDLE][HEALTHY] = Bitmap.createBitmap(cheeseO[LARGE][HEALTHY],0,0,cheesePictureSize,cheesePictureSize,matrix,false);
        /* TO BE CONTINUE */
        
        

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

    short spinAngle;
    public CheeseDisplay(){
        spinAngle = 0;
    }
    public short getSpinAngle(){
        spinAngle+=3;
        return spinAngle;
        //return cheeseMessage.getSpinAngle();
    }

    public void draw(boolean whichSide, Canvas canvas){
        switch (getType()) {
            case Original: {
                
                Matrix matrix = new Matrix();
                int status;
                if(getHPPercent()==100)status = HEALTHY;
                else{
                    status = 3-getHPPercent()/33;
                }
                if(status == DEAD){
                    matrix.setTranslate(getX()-CheeseParameter.Normal.RadixLarge, getY()-CheeseParameter.Normal.RadixLarge);
                }else{
                    matrix.preTranslate(-CheeseParameter.Normal.RadixLarge, -CheeseParameter.Normal.RadixLarge);
                    if(whichSide == Cheese.Left)
                        matrix.postRotate(getSpinAngle());
                    else
                        matrix.postRotate(-getSpinAngle());
                    matrix.postTranslate(getX(),getY());
                }
                canvas.drawBitmap(cheeseO[getSize()][status],matrix, null);

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
