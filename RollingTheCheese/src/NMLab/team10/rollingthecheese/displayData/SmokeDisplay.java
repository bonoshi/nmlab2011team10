package NMLab.team10.rollingthecheese.displayData;

import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class SmokeDisplay {
    static Resources r = GameView.r;
    static Bitmap smokeWL;
    static Bitmap smokeWR;

    public static void initial() {
        r = GameView.r;
        smokeWL = BitmapFactory.decodeResource(r, R.drawable.house_smoke_w_l).copy(Config.ARGB_8888, true);
        smokeWR = BitmapFactory.decodeResource(r, R.drawable.house_smoke_w_r).copy(Config.ARGB_8888, true);
        //for black at day
        //GameView.modifyRGBByRatio(smokeWL, 0.2F);
        //for black at night
//        GameView.modifyRedByRatio(smokeWR, 0.7F);
//        GameView.modifyGreenByRatio(smokeWR, 0.7F);
//        GameView.modifyBlueByRatio(smokeWR, 0.7F);
    }

    public static final int SmokeWidth = 200;
    public static final int SmokeHeight = 200;
    public static final int SmokeInterval = 202;
    public static final int ChangeFrame = 15;

    public static final float WindOffset = 0.3F;
    public static final float WindRatio = 2.2F;

    boolean isBlack;
    byte type;
    int startRSX;// start x from rectangles of bitmap
    float startX;// start x of smoke
    float startY;// start y of smoke

    int frame = 0;
    boolean isDead = false;

    int counter;

    public SmokeDisplay(boolean whichSide, byte type) {
        this.isBlack = (whichSide) ? GameView.displayData.getRightDSM().smallCheese : GameView.displayData
                .getLeftDSM().smallCheese;
        this.startX = (whichSide) ? GameView.displayData.getLeftHouse().getSmokeX(whichSide)
                : GameView.displayData.getRightHouse().getSmokeX(whichSide);
        startX -= SmokeWidth / 2;
        this.startY = (whichSide) ? GameView.displayData.getLeftHouse().getSmokeY(whichSide)
                : GameView.displayData.getRightHouse().getSmokeY(whichSide);
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
        if (time > 35000) {// night
            if (isBlack) {
                if (whichSide) {
                    canvas.drawBitmap(smokeWL, sRectangle, dest, null);
                } else {
                    canvas.drawBitmap(smokeWR, sRectangle, dest, null);
                }
            } else {
                if (whichSide) {
                    canvas.drawBitmap(smokeWL, sRectangle, dest, null);
                } else {
                    canvas.drawBitmap(smokeWR, sRectangle, dest, null);
                }
            }
        } else {
            if (isBlack) {
                if (whichSide) {
                    canvas.drawBitmap(smokeWL, sRectangle, dest, null);
                } else {
                    canvas.drawBitmap(smokeWR, sRectangle, dest, null);
                }
            } else {
                if (whichSide) {
                    canvas.drawBitmap(smokeWL, sRectangle, dest, null);
                } else {
                    canvas.drawBitmap(smokeWR, sRectangle, dest, null);
                }
            }
        }

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
