package NMLab.team10.rollingthecheese.displayData;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.byteEnum.FireEnum;
import NMLab.team10.rollingthecheese.gameSetting.FireLineMessage;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;

public class FireLineDisplay {

    static final int FireHeight = 56;
    static final float FireDrawOffset = 10;
    static final float FireDrawTop = GlobalParameter.MapHeight - FireHeight - FireDrawOffset;
    static final float FireDrawBottom = GlobalParameter.MapHeight - FireDrawOffset;
    static final int FrameCountPlusOne = 8;
    static final int FrameNum = 3;

    public FireLineDisplay(FireLineMessage flm) {
        this.setFireLineMessage(flm);
    }

    public void setFireLineMessage(FireLineMessage fireLineMessage) {
        this.fireLineMessage = fireLineMessage;
    }

    public FireLineMessage getFireLineMessage() {
        return fireLineMessage;
    }

    private FireLineMessage fireLineMessage;

    static Resources r = GameView.r;
    private static Bitmap fireBitmap[];

    static public void initial() {
        r = GameView.r;
        fireBitmap = new Bitmap[5];
        fireBitmap[0] = BitmapFactory.decodeResource(r, R.drawable.fire1);
        fireBitmap[1] = BitmapFactory.decodeResource(r, R.drawable.fire2);
        fireBitmap[2] = BitmapFactory.decodeResource(r, R.drawable.fire3);
        fireBitmap[3] = BitmapFactory.decodeResource(r, R.drawable.fire4);
        fireBitmap[4] = BitmapFactory.decodeResource(r, R.drawable.fire5);
    }

    int frame = 0;
    int count = 0;

    public byte getType() {// Small, Medium, Large
        return fireLineMessage.getType();
    }

    public float getStartX() {
        return fireLineMessage.getStartX();
    }

    public float getEndX() {
        return fireLineMessage.getEndX();
    }

    public void draw(boolean isLeft, Canvas canvas) {
        Rect sRectangle;
        RectF dest;
        if (isLeft) {
            sRectangle = new Rect((int) getEndX(), 0, (int) getStartX(), FireHeight);
            dest = new RectF(getEndX(), FireDrawTop, getStartX(), FireDrawBottom);
        } else {
            sRectangle = new Rect((int) getStartX(), 0, (int) getEndX(), FireHeight);
            dest = new RectF(getStartX(), FireDrawTop, getEndX(), FireDrawBottom);
        }
        canvas.drawBitmap(fireBitmap[getType() + frame], sRectangle, dest, null);
        refreshFrame();
    }

    public void refreshFrame() {
        if (++count == FrameCountPlusOne) {
            count = 0;
            if (++frame == FrameNum) {
                frame = 0;
            }
        }
    }

    public static final byte Small = FireEnum.Small;
    public static final byte Medium = FireEnum.Medium;
    public static final byte Large = FireEnum.Large;

}
