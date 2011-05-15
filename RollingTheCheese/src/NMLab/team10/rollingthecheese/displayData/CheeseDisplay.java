package NMLab.team10.rollingthecheese.displayData;

import android.R.integer;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import android.graphics.Canvas;
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
    static Bitmap cheeseOU[];
    static Bitmap cheeseC[][];
    static Bitmap cheeseCU[];
    static Bitmap cheeseS[][];
    static Bitmap cheeseSU[];
    static Bitmap cheeseF[][];
    static Bitmap cheeseFU[];
    static Bitmap sweatyBitmap;
    static final int sweatyWidth = 450;
    static final int sweatyHeight = 250;
    static final int sweatyRow = 5;
    static final int sweatyCol = 4;
    static final int sweatyDelX = -50;
    static final int sweatyDelY = -85;
    
    static final int HEALTHY = 0;
    static final int LITTLE_DAMAGED = 1;
    static final int SERIOUS_DAMAGED = 2;
    static final int DEAD = 3;
    static final int cheesePictureStep = 120;
    static final int cheesePictureSize = 110;

    static final int MaxDeadCount = 5;

    int deadCount = 0;
    float radix = 0;
    int frame=0;
    
    public static void initBitmap() {
        Resources r = GameView.r;

        Bitmap cheeseOtmp = BitmapFactory.decodeResource(r, R.drawable.cheese_original);
        cheeseOU = new Bitmap[4];
        cheeseOU[HEALTHY] = Bitmap.createBitmap(cheeseOtmp, 0, 0, cheesePictureSize, cheesePictureSize);// ,matrix,true);
        cheeseOU[LITTLE_DAMAGED] = Bitmap.createBitmap(cheeseOtmp, cheesePictureStep, 0, cheesePictureSize,
                cheesePictureSize);// ,matrix,true);
        cheeseOU[SERIOUS_DAMAGED] = Bitmap.createBitmap(cheeseOtmp, cheesePictureStep * 2, 0,
                cheesePictureSize, cheesePictureSize);
        cheeseOU[DEAD] = Bitmap.createBitmap(cheeseOtmp, cheesePictureStep * 3, 0, cheesePictureSize,
                cheesePictureSize);

        // Matrix matrix = new Matrix();
        // matrix.postScale(0.8f, 0.8f);

        cheeseO = new Bitmap[4][4];
        cheeseO[Large][HEALTHY] = Bitmap.createScaledBitmap(cheeseOU[HEALTHY],
                (int) CheeseParameter.Normal.RadixLarge * 2, (int) CheeseParameter.Normal.RadixLarge * 2,
                false);
        cheeseO[Large][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseOU[LITTLE_DAMAGED],
                (int) CheeseParameter.Normal.RadixLarge * 2, (int) CheeseParameter.Normal.RadixLarge * 2,
                false);
        cheeseO[Large][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseOU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Normal.RadixLarge * 2, (int) CheeseParameter.Normal.RadixLarge * 2,
                false);
        cheeseO[Large][DEAD] = Bitmap.createScaledBitmap(cheeseOU[DEAD],
                (int) CheeseParameter.Normal.RadixLarge * 2, (int) CheeseParameter.Normal.RadixLarge * 2,
                false);

        cheeseO[Medium][HEALTHY] = Bitmap.createScaledBitmap(cheeseOU[HEALTHY],
                (int) CheeseParameter.Normal.RadixMed * 2, (int) CheeseParameter.Normal.RadixMed * 2, false);
        cheeseO[Medium][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseOU[LITTLE_DAMAGED],
                (int) CheeseParameter.Normal.RadixMed * 2, (int) CheeseParameter.Normal.RadixMed * 2, false);
        cheeseO[Medium][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseOU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Normal.RadixMed * 2, (int) CheeseParameter.Normal.RadixMed * 2, false);
        cheeseO[Medium][DEAD] = Bitmap.createScaledBitmap(cheeseOU[DEAD],
                (int) CheeseParameter.Normal.RadixMed * 2, (int) CheeseParameter.Normal.RadixMed * 2, false);

        cheeseO[Small][HEALTHY] = Bitmap.createScaledBitmap(cheeseOU[HEALTHY],
                (int) CheeseParameter.Normal.RadixSmall * 2, (int) CheeseParameter.Normal.RadixSmall * 2,
                false);
        cheeseO[Small][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseOU[LITTLE_DAMAGED],
                (int) CheeseParameter.Normal.RadixSmall * 2, (int) CheeseParameter.Normal.RadixSmall * 2,
                false);
        cheeseO[Small][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseOU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Normal.RadixSmall * 2, (int) CheeseParameter.Normal.RadixSmall * 2,
                false);
        cheeseO[Small][DEAD] = Bitmap.createScaledBitmap(cheeseOU[DEAD],
                (int) CheeseParameter.Normal.RadixSmall * 2, (int) CheeseParameter.Normal.RadixSmall * 2,
                false);

        cheeseO[Tiny][HEALTHY] = Bitmap
                .createScaledBitmap(cheeseOU[HEALTHY], (int) CheeseParameter.Normal.RadixTiny * 2,
                        (int) CheeseParameter.Normal.RadixTiny * 2, false);
        cheeseO[Tiny][LITTLE_DAMAGED] = Bitmap
                .createScaledBitmap(cheeseOU[LITTLE_DAMAGED], (int) CheeseParameter.Normal.RadixTiny * 2,
                        (int) CheeseParameter.Normal.RadixTiny * 2, false);
        cheeseO[Tiny][SERIOUS_DAMAGED] = Bitmap
                .createScaledBitmap(cheeseOU[SERIOUS_DAMAGED], (int) CheeseParameter.Normal.RadixTiny * 2,
                        (int) CheeseParameter.Normal.RadixTiny * 2, false);
        cheeseO[Tiny][DEAD] = Bitmap
                .createScaledBitmap(cheeseOU[DEAD], (int) CheeseParameter.Normal.RadixTiny * 2,
                        (int) CheeseParameter.Normal.RadixTiny * 2, false);

        cheeseCU = new Bitmap[4];
        Bitmap cheeseCtmp = BitmapFactory.decodeResource(r, R.drawable.cheese_casumarzu);
        cheeseCU = new Bitmap[4];
        cheeseCU[HEALTHY] = Bitmap.createBitmap(cheeseCtmp, 0, 0, cheesePictureSize, cheesePictureSize);// ,matrix,true);
        cheeseCU[LITTLE_DAMAGED] = Bitmap.createBitmap(cheeseCtmp, cheesePictureStep, 0, cheesePictureSize,
                cheesePictureSize);
        cheeseCU[SERIOUS_DAMAGED] = Bitmap.createBitmap(cheeseCtmp, cheesePictureStep * 2, 0,
                cheesePictureSize, cheesePictureSize);
        cheeseCU[DEAD] = Bitmap.createBitmap(cheeseCtmp, cheesePictureStep * 3, 0, cheesePictureSize,
                cheesePictureSize);

        cheeseC = new Bitmap[4][4];
        cheeseC[Large][HEALTHY] = Bitmap.createScaledBitmap(cheeseCU[HEALTHY],
                (int) CheeseParameter.Poison.RadixLarge * 2, (int) CheeseParameter.Poison.RadixLarge * 2,
                false);
        cheeseC[Large][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseCU[LITTLE_DAMAGED],
                (int) CheeseParameter.Poison.RadixLarge * 2, (int) CheeseParameter.Poison.RadixLarge * 2,
                false);
        cheeseC[Large][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseCU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Poison.RadixLarge * 2, (int) CheeseParameter.Poison.RadixLarge * 2,
                false);
        cheeseC[Large][DEAD] = Bitmap.createScaledBitmap(cheeseCU[DEAD],
                (int) CheeseParameter.Poison.RadixLarge * 2, (int) CheeseParameter.Poison.RadixLarge * 2,
                false);

        cheeseC[Medium][HEALTHY] = Bitmap.createScaledBitmap(cheeseCU[HEALTHY],
                (int) CheeseParameter.Poison.RadixMed * 2, (int) CheeseParameter.Poison.RadixMed * 2, false);
        cheeseC[Medium][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseCU[LITTLE_DAMAGED],
                (int) CheeseParameter.Poison.RadixMed * 2, (int) CheeseParameter.Poison.RadixMed * 2, false);
        cheeseC[Medium][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseCU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Poison.RadixMed * 2, (int) CheeseParameter.Poison.RadixMed * 2, false);
        cheeseC[Medium][DEAD] = Bitmap.createScaledBitmap(cheeseCU[DEAD],
                (int) CheeseParameter.Poison.RadixMed * 2, (int) CheeseParameter.Poison.RadixMed * 2, false);

        cheeseC[Small][HEALTHY] = Bitmap.createScaledBitmap(cheeseCU[HEALTHY],
                (int) CheeseParameter.Poison.RadixSmall * 2, (int) CheeseParameter.Poison.RadixSmall * 2,
                false);
        cheeseC[Small][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseCU[LITTLE_DAMAGED],
                (int) CheeseParameter.Poison.RadixSmall * 2, (int) CheeseParameter.Poison.RadixSmall * 2,
                false);
        cheeseC[Small][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseCU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Poison.RadixSmall * 2, (int) CheeseParameter.Poison.RadixSmall * 2,
                false);
        cheeseC[Small][DEAD] = Bitmap.createScaledBitmap(cheeseCU[DEAD],
                (int) CheeseParameter.Poison.RadixSmall * 2, (int) CheeseParameter.Poison.RadixSmall * 2,
                false);

        cheeseC[Tiny][HEALTHY] = Bitmap
                .createScaledBitmap(cheeseCU[HEALTHY], (int) CheeseParameter.Poison.RadixTiny * 2,
                        (int) CheeseParameter.Poison.RadixTiny * 2, false);
        cheeseC[Tiny][LITTLE_DAMAGED] = Bitmap
                .createScaledBitmap(cheeseCU[LITTLE_DAMAGED], (int) CheeseParameter.Poison.RadixTiny * 2,
                        (int) CheeseParameter.Poison.RadixTiny * 2, false);
        cheeseC[Tiny][SERIOUS_DAMAGED] = Bitmap
                .createScaledBitmap(cheeseCU[SERIOUS_DAMAGED], (int) CheeseParameter.Poison.RadixTiny * 2,
                        (int) CheeseParameter.Poison.RadixTiny * 2, false);
        cheeseC[Tiny][DEAD] = Bitmap
                .createScaledBitmap(cheeseCU[DEAD], (int) CheeseParameter.Poison.RadixTiny * 2,
                        (int) CheeseParameter.Poison.RadixTiny * 2, false);

        Bitmap cheeseStmp = BitmapFactory.decodeResource(r, R.drawable.cheese_sweaty);
        cheeseSU = new Bitmap[4];
        cheeseSU[HEALTHY] = Bitmap.createBitmap(cheeseStmp, 0, 0, cheesePictureSize, cheesePictureSize);// ,matrix,true);
        cheeseSU[LITTLE_DAMAGED] = Bitmap.createBitmap(cheeseStmp, cheesePictureStep, 0, cheesePictureSize,
                cheesePictureSize);
        cheeseSU[SERIOUS_DAMAGED] = Bitmap.createBitmap(cheeseStmp, cheesePictureStep * 2, 0,
                cheesePictureSize, cheesePictureSize);
        cheeseSU[DEAD] = Bitmap.createBitmap(cheeseStmp, cheesePictureStep * 3, 0, cheesePictureSize,
                cheesePictureSize);

        cheeseS = new Bitmap[4][4];
        cheeseS[Large][HEALTHY] = Bitmap
                .createScaledBitmap(cheeseSU[HEALTHY], (int) CheeseParameter.Sweat.RadixLarge * 2,
                        (int) CheeseParameter.Sweat.RadixLarge * 2, false);
        cheeseS[Large][LITTLE_DAMAGED] = Bitmap
                .createScaledBitmap(cheeseSU[LITTLE_DAMAGED], (int) CheeseParameter.Sweat.RadixLarge * 2,
                        (int) CheeseParameter.Sweat.RadixLarge * 2, false);
        cheeseS[Large][SERIOUS_DAMAGED] = Bitmap
                .createScaledBitmap(cheeseSU[SERIOUS_DAMAGED], (int) CheeseParameter.Sweat.RadixLarge * 2,
                        (int) CheeseParameter.Sweat.RadixLarge * 2, false);
        cheeseS[Large][DEAD] = Bitmap
                .createScaledBitmap(cheeseSU[DEAD], (int) CheeseParameter.Sweat.RadixLarge * 2,
                        (int) CheeseParameter.Sweat.RadixLarge * 2, false);

        cheeseS[Medium][HEALTHY] = Bitmap.createScaledBitmap(cheeseSU[HEALTHY],
                (int) CheeseParameter.Sweat.RadixMed * 2, (int) CheeseParameter.Sweat.RadixMed * 2, false);
        cheeseS[Medium][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseSU[LITTLE_DAMAGED],
                (int) CheeseParameter.Sweat.RadixMed * 2, (int) CheeseParameter.Sweat.RadixMed * 2, false);
        cheeseS[Medium][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseSU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Sweat.RadixMed * 2, (int) CheeseParameter.Sweat.RadixMed * 2, false);
        cheeseS[Medium][DEAD] = Bitmap.createScaledBitmap(cheeseSU[DEAD],
                (int) CheeseParameter.Sweat.RadixMed * 2, (int) CheeseParameter.Sweat.RadixMed * 2, false);

        cheeseS[Small][HEALTHY] = Bitmap
                .createScaledBitmap(cheeseSU[HEALTHY], (int) CheeseParameter.Sweat.RadixSmall * 2,
                        (int) CheeseParameter.Sweat.RadixSmall * 2, false);
        cheeseS[Small][LITTLE_DAMAGED] = Bitmap
                .createScaledBitmap(cheeseSU[LITTLE_DAMAGED], (int) CheeseParameter.Sweat.RadixSmall * 2,
                        (int) CheeseParameter.Sweat.RadixSmall * 2, false);
        cheeseS[Small][SERIOUS_DAMAGED] = Bitmap
                .createScaledBitmap(cheeseSU[SERIOUS_DAMAGED], (int) CheeseParameter.Sweat.RadixSmall * 2,
                        (int) CheeseParameter.Sweat.RadixSmall * 2, false);
        cheeseS[Small][DEAD] = Bitmap
                .createScaledBitmap(cheeseSU[DEAD], (int) CheeseParameter.Sweat.RadixSmall * 2,
                        (int) CheeseParameter.Sweat.RadixSmall * 2, false);

        cheeseS[Tiny][HEALTHY] = Bitmap.createScaledBitmap(cheeseSU[HEALTHY],
                (int) CheeseParameter.Sweat.RadixTiny * 2, (int) CheeseParameter.Sweat.RadixTiny * 2, false);
        cheeseS[Tiny][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseSU[LITTLE_DAMAGED],
                (int) CheeseParameter.Sweat.RadixTiny * 2, (int) CheeseParameter.Sweat.RadixTiny * 2, false);
        cheeseS[Tiny][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseSU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Sweat.RadixTiny * 2, (int) CheeseParameter.Sweat.RadixTiny * 2, false);
        cheeseS[Tiny][DEAD] = Bitmap.createScaledBitmap(cheeseSU[DEAD],
                (int) CheeseParameter.Sweat.RadixTiny * 2, (int) CheeseParameter.Sweat.RadixTiny * 2, false);

        Bitmap cheeseFtmp = BitmapFactory.decodeResource(r, R.drawable.cheese_firing);
        cheeseFU = new Bitmap[4];
        cheeseFU[HEALTHY] = Bitmap.createBitmap(cheeseFtmp, 0, 0, cheesePictureSize, cheesePictureSize);// ,matrix,true);
        cheeseFU[LITTLE_DAMAGED] = Bitmap.createBitmap(cheeseFtmp, cheesePictureStep, 0, cheesePictureSize,
                cheesePictureSize);
        cheeseFU[SERIOUS_DAMAGED] = Bitmap.createBitmap(cheeseFtmp, cheesePictureStep * 2, 0,
                cheesePictureSize, cheesePictureSize);
        cheeseFU[DEAD] = Bitmap.createBitmap(cheeseFtmp, cheesePictureStep * 3, 0, cheesePictureSize,
                cheesePictureSize);

        cheeseF = new Bitmap[4][4];
        cheeseF[Large][HEALTHY] = Bitmap.createScaledBitmap(cheeseFU[HEALTHY],
                (int) CheeseParameter.Fire.RadixLarge * 2, (int) CheeseParameter.Fire.RadixLarge * 2, false);
        cheeseF[Large][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseFU[LITTLE_DAMAGED],
                (int) CheeseParameter.Fire.RadixLarge * 2, (int) CheeseParameter.Fire.RadixLarge * 2, false);
        cheeseF[Large][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseFU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Fire.RadixLarge * 2, (int) CheeseParameter.Fire.RadixLarge * 2, false);
        cheeseF[Large][DEAD] = Bitmap.createScaledBitmap(cheeseFU[DEAD],
                (int) CheeseParameter.Fire.RadixLarge * 2, (int) CheeseParameter.Fire.RadixLarge * 2, false);

        cheeseF[Medium][HEALTHY] = Bitmap.createScaledBitmap(cheeseFU[HEALTHY],
                (int) CheeseParameter.Fire.RadixMed * 2, (int) CheeseParameter.Fire.RadixMed * 2, false);
        cheeseF[Medium][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseFU[LITTLE_DAMAGED],
                (int) CheeseParameter.Fire.RadixMed * 2, (int) CheeseParameter.Fire.RadixMed * 2, false);
        cheeseF[Medium][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseFU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Fire.RadixMed * 2, (int) CheeseParameter.Fire.RadixMed * 2, false);
        cheeseF[Medium][DEAD] = Bitmap.createScaledBitmap(cheeseFU[DEAD],
                (int) CheeseParameter.Fire.RadixMed * 2, (int) CheeseParameter.Fire.RadixMed * 2, false);

        cheeseF[Small][HEALTHY] = Bitmap.createScaledBitmap(cheeseFU[HEALTHY],
                (int) CheeseParameter.Fire.RadixSmall * 2, (int) CheeseParameter.Fire.RadixSmall * 2, false);
        cheeseF[Small][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseFU[LITTLE_DAMAGED],
                (int) CheeseParameter.Fire.RadixSmall * 2, (int) CheeseParameter.Fire.RadixSmall * 2, false);
        cheeseF[Small][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseFU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Fire.RadixSmall * 2, (int) CheeseParameter.Fire.RadixSmall * 2, false);
        cheeseF[Small][DEAD] = Bitmap.createScaledBitmap(cheeseFU[DEAD],
                (int) CheeseParameter.Fire.RadixSmall * 2, (int) CheeseParameter.Fire.RadixSmall * 2, false);

        cheeseF[Tiny][HEALTHY] = Bitmap.createScaledBitmap(cheeseFU[HEALTHY],
                (int) CheeseParameter.Fire.RadixTiny * 2, (int) CheeseParameter.Fire.RadixTiny * 2, false);
        cheeseF[Tiny][LITTLE_DAMAGED] = Bitmap.createScaledBitmap(cheeseFU[LITTLE_DAMAGED],
                (int) CheeseParameter.Fire.RadixTiny * 2, (int) CheeseParameter.Fire.RadixTiny * 2, false);
        cheeseF[Tiny][SERIOUS_DAMAGED] = Bitmap.createScaledBitmap(cheeseFU[SERIOUS_DAMAGED],
                (int) CheeseParameter.Fire.RadixTiny * 2, (int) CheeseParameter.Fire.RadixTiny * 2, false);
        cheeseF[Tiny][DEAD] = Bitmap.createScaledBitmap(cheeseFU[DEAD],
                (int) CheeseParameter.Fire.RadixTiny * 2, (int) CheeseParameter.Fire.RadixTiny * 2, false);

        
        sweatyBitmap = BitmapFactory.decodeResource(r, R.drawable.sweaty);
        

        
    }

    
    
    public CheeseDisplay(CheeseMessage cm) {
        this.setCheeseMessage(cm);
        switch (getType()) {
            case Original: {
                switch (getSize()) {
                    case Large:
                        radix = CheeseParameter.Normal.RadixLarge;
                        break;
                    case Medium:
                        radix = CheeseParameter.Normal.RadixMed;
                        break;
                    case Small:
                        radix = CheeseParameter.Normal.RadixSmall;
                        break;
                    case Tiny:
                        radix = CheeseParameter.Normal.RadixTiny;
                        break;
                }
                break;
            }
            case Casumarzu: {
                switch (getSize()) {
                    case Large:
                        radix = CheeseParameter.Poison.RadixLarge;
                        break;
                    case Medium:
                        radix = CheeseParameter.Poison.RadixMed;
                        break;
                    case Small:
                        radix = CheeseParameter.Poison.RadixSmall;
                        break;
                    case Tiny:
                        radix = CheeseParameter.Poison.RadixTiny;
                        break;
                }
                break;
            }
            case Sweaty: {
                switch (getSize()) {
                    case Large:
                        radix = CheeseParameter.Sweat.RadixLarge;
                        break;
                    case Medium:
                        radix = CheeseParameter.Sweat.RadixMed;
                        break;
                    case Small:
                        radix = CheeseParameter.Sweat.RadixSmall;
                        break;
                    case Tiny:
                        radix = CheeseParameter.Sweat.RadixTiny;
                        break;
                }
                break;
            }
            case Firing: {
                switch (getSize()) {
                    case Large:
                        radix = CheeseParameter.Fire.RadixLarge;
                        break;
                    case Medium:
                        radix = CheeseParameter.Fire.RadixMed;
                        break;
                    case Small:
                        radix = CheeseParameter.Fire.RadixSmall;
                        break;
                    case Tiny:
                        radix = CheeseParameter.Fire.RadixTiny;
                        break;
                }
                break;
            }
        }
    }

    public void setCheeseMessage(CheeseMessage cheeseMessage) {
        this.cheeseMessage = cheeseMessage;
    }

    public CheeseMessage getCheeseMessage() {
        return cheeseMessage;
    }

    private CheeseMessage cheeseMessage;

    public byte getType() {// Normal, Casumarzu, Sweaty, Firing
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
        return GlobalParameter.MapHeight - cheeseMessage.getY();
    }

    short spinAngle;

    public short getSpinAngle() {
        //spinAngle += 3;
        return spinAngle;
        // return cheeseMessage.getSpinAngle();
    }

    public boolean removeDead() {
        return (deadCount == CheeseDisplay.MaxDeadCount);
    }

    private Bitmap getSweatyAni(){
        if(frame == sweatyRow*sweatyCol)frame =0;
        int row = frame/sweatyRow;
        int col = frame%5;
        return Bitmap.createBitmap(sweatyBitmap,row*sweatyWidth,col*sweatyHeight,(row+1)*sweatyWidth,(col+1)*sweatyHeight);
    }
    
    public void draw(boolean whichSide, Canvas canvas) {
        Matrix matrix = new Matrix();
        int status;
        if (getHPPercent() > 67)
            status = HEALTHY;
        else if (getHPPercent() > 34) {
            status = LITTLE_DAMAGED;
        } else if (getHPPercent() > 1) {
            status = SERIOUS_DAMAGED;
        } else {
            status = DEAD;
        }
        if (status == DEAD) {
            matrix.setTranslate(getX() - radix, getY() - radix);
            deadCount++;
        } else {
            matrix.preTranslate(-radix, -radix);
            if (whichSide == Cheese.Left)
                matrix.postRotate(getSpinAngle());
            else
                matrix.postRotate(-getSpinAngle());
            matrix.postTranslate(getX(), getY());
        }
        switch (getType()) {
            case Original: {
                canvas.drawBitmap(cheeseO[getSize()][status], matrix, null);
                break;
            }
            case Casumarzu: {
                canvas.drawBitmap(cheeseC[getSize()][status], matrix, null);
                break;
            }
            case Sweaty: {
                if(getHPPercent()!=0){
                    Bitmap s = getSweatyAni();
                    canvas.drawBitmap(s, getX()-50,getY()-85, null);
                }
                canvas.drawBitmap(cheeseS[getSize()][status], matrix, null);
                break;
            }
            case Firing: {
                canvas.drawBitmap(cheeseF[getSize()][status], matrix, null);
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
