package NMLab.team10.rollingthecheese.displayData;

import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class SmokeDisplay {
    static Resources r = GameView.r;
    static Bitmap smokeWL;
    // static Bitmap smokeWR;
    // static Bitmap smokeBL;
    // static Bitmap smokeBR;

    static Bitmap[][] smoke;

    public static final int SmokeWidth = 200;
    public static final int SmokeHeight = 200;
    public static final int SmokeInterval = 202;
    public static final int ChangeFrame = 15;

    public static final float WindOffset = 0.3F;
    public static final float WindRatio = 2.2F;

    static Paint smokePaint;
    static LightingColorFilter smokeCMCF;

    private static float[] mirrorY = { -1, 0, 0, 0, 1, 0, 0, 0, 1 };

    static private Matrix mirrorMatrixY = new Matrix();

    public static void initial() {
        r = GameView.r;
        smokeWL = BitmapFactory.decodeResource(r, R.drawable.house_smoke_w_l);
        // smokeWR = BitmapFactory.decodeResource(r,
        // R.drawable.house_smoke_w_r);
        // smokeBL = BitmapFactory.decodeResource(r,
        // R.drawable.house_smoke_w_l).copy(Config.ARGB_8888, true);
        // smokeBR = BitmapFactory.decodeResource(r,
        // R.drawable.house_smoke_w_r).copy(Config.ARGB_8888, true);

        smoke = new Bitmap[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                smoke[i][j] = Bitmap.createBitmap(smokeWL, (i * 5 + j) * SmokeInterval, 0, SmokeWidth,
                        SmokeHeight);
            }
        }

        smokePaint = new Paint();
        smokeCMCF = new LightingColorFilter(0x008805A8, 0);
//        smokeCMCF = new LightingColorFilter(0x00B70094, 0);
        smokePaint.setColorFilter(smokeCMCF);

        mirrorMatrixY.setValues(mirrorY);

        // smokeBL = BitmapFactory.decodeResource(r,
        // R.drawable.house_smoke_b_l).copy(Config.ARGB_8888, true);
        // smokeBR = BitmapFactory.decodeResource(r,
        // R.drawable.house_smoke_b_r).copy(Config.ARGB_8888, true);
        // for black at day
        // GameView.modifyRGBByRatio(smokeWL, 0.2F);
        // for black at night
        // GameView.modifyRedByRatio(smokeWR, 0.7F);
        // GameView.modifyGreenByRatio(smokeWR, 0.7F);
        // GameView.modifyBlueByRatio(smokeWR, 0.7F);
    }

    boolean isBlack;
    byte type;
    int startRSX;// start x from rectangles of bitmap
    float startX;// start x of smoke
    float startY;// start y of smoke

    int frame = 0;
    boolean isDead = false;

    int counter;

    public SmokeDisplay(boolean whichSide, byte type) {
        this.isBlack = GameView.displayData.getDSM(!whichSide).smallCheese;
        this.startX = GameView.displayData.getHouse(whichSide).getSmokeX(whichSide);
        startX -= SmokeWidth / 2;
        this.startY = GameView.displayData.getHouse(whichSide).getSmokeY(whichSide);
        startY -= SmokeHeight / 2;
        this.type = type;
        isDead = false;
        startRSX = SmokeInterval * (5 * type);
    }

    public void draw(Canvas canvas, boolean whichSide) {

        Rect sRectangle = new Rect(startRSX + frame * SmokeInterval, 0, startRSX + frame * SmokeInterval
                + SmokeWidth, SmokeHeight);
        RectF dest = new RectF(startX, startY, startX + SmokeWidth, startY + SmokeHeight);
        int time = GameView.displayData.getTime();

        Matrix matrix = new Matrix();
        matrix.preTranslate(-SmokeWidth / 2, -SmokeHeight / 2);
        matrix.postConcat(mirrorMatrixY);
        matrix.postTranslate(SmokeWidth / 2 + startX, SmokeHeight / 2 + startY);

//        if (time > GlobalParameter.Night) {// night
            if (isBlack) {
                if (whichSide) {
                    canvas.drawBitmap(smoke[type][frame], startX, startY, smokePaint);
                } else {
                    canvas.drawBitmap(smoke[type][frame], matrix, smokePaint);
                }
            } else {
                if (whichSide) {
                    canvas.drawBitmap(smoke[type][frame], startX, startY, null);
                } else {
                    // matrix.postConcat(mirrorMatrixY);
                    canvas.drawBitmap(smoke[type][frame], matrix, null);
                }
            }
//        } else {
//            if (isBlack) {
//                if (whichSide) {
//                    canvas.drawBitmap(smokeWL, sRectangle, dest, smokePaint);
//                } else {
////                    canvas.drawBitmap(smokeWR, sRectangle, dest, smokePaint);
//                    canvas.drawBitmap(smoke[type][frame], matrix, smokePaint);
//                }
//            } else {
//                if (whichSide) {
//                    canvas.drawBitmap(smokeWL, sRectangle, dest, null);
//                } else {
////                    canvas.drawBitmap(smokeWR, sRectangle, dest, null);
//                    canvas.drawBitmap(smoke[type][frame], matrix, null);
//                }
//            }
//        }

        if (++counter >= ChangeFrame) {
            if (Math.random() > 0.9) {
                counter = 0;
                ++frame;
            }
        }

        if (frame == 5)
            isDead = true;

        modifyXY(whichSide);
    }

    private void modifyXY(boolean whichSide) {
        startY -= 1;
        if (whichSide) {
            startX += Climate.getWind() * WindRatio + WindOffset;
        } else {
            startX += Climate.getWind() * WindRatio - WindOffset;
        }
    }

}
