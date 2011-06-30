package NMLab.team10.rollingthecheese.displayData;

import java.util.Iterator;
import java.util.LinkedList;

import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.gameSetting.GlobalParameter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

public class SkyDisplay {

    static Resources r = GameView.r;
    // static Bitmap morning;
    // static Bitmap noon;
    // static Bitmap dusk;
    // static Bitmap night;
    static float nightM[];
    static float mornM[];
    static float noonM[];
    static float duskM[];

    static final int nightUp = Color.argb(255, 0, 0, 0);
    static final int nightDown = Color.argb(255, 0, 0, 0);
    static final int morningUp = Color.argb(255, 45, 87, 137);
    static final int morningDown = Color.argb(255, 171, 198, 219);
    static final int noonUp = Color.argb(255, 135, 182, 255);
    static final int noonDown = Color.argb(255, 190, 222, 255);
    static final int duskUp = Color.argb(255, 115, 72, 20);
    static final int duskDown = Color.argb(255, 255, 164, 33);

    static int skyUpColor;
    static int skyDownColor;
    static Paint skyPaint;
    // static ColorMatrixColorFilter skyCMCF;
    static LinearGradient lg = null;
    static Paint starPaint;
    static ColorMatrixColorFilter starCMCF;

    static public final int SkyHigh = 250;

    // static Paint testPaint;

    // static boolean initNight = false;

    static public void initial() {
        r = GameView.r;
        // morning = BitmapFactory.decodeResource(r, R.drawable.sky_morning);
        // noon = BitmapFactory.decodeResource(r, R.drawable.sky_noon);
        // dusk = BitmapFactory.decodeResource(r, R.drawable.sky_dusk);
        // float dusk[] = { -6.5849F, 12.1696F, -4.6966F, 0, 0, -6.0332F,
        // 10.8845F, -4.3902F, 0, 0, -3.2949F,
        // 5.1701F, -1.9601F, 0, 0, 0, 0, 0, 1, 0 };

        lg = new LinearGradient(0, 0, 0, SkyHigh, 0, 0, Shader.TileMode.CLAMP);

        nightM = new float[20];
        for (int i = 0; i < 20; i++) {
            nightM[i] = 0;
        }
        nightM[18] = 1;

        mornM = new float[20];
        for (int i = 0; i < 20; i++) {
            mornM[i] = 0;
        }
        mornM[0] = 1;
        mornM[6] = 1;
        mornM[12] = 1;
        mornM[18] = 1;

        noonM = new float[20];
        for (int i = 0; i < 20; i++) {
            noonM[i] = 0;
        }
        noonM[0] = 0.44F;
        noonM[4] = 115.2F;
        noonM[6] = 0.3604F;
        noonM[9] = 151.6452F;
        noonM[12] = 0;
        noonM[14] = 255F;
        noonM[18] = 1;
        //
        // duskM = new float[20];
        // for (int i = 0; i < 20; i++) {
        // duskM[i] = 0;
        // }
        // duskM[0] = 2.56F;
        // duskM[6] = 0.83F;
        // duskM[12] = 0.15F;
        // duskM[18] = 1;

        skyPaint = new Paint();
        starPaint = new Paint();
        starPaint.setColorFilter(new ColorMatrixColorFilter(GameView.modifyAlphaColorMatrixByRatio(0)));

        // testPaint = new Paint();
        // LightingColorFilter testLCF = new LightingColorFilter(0x00888888,
        // 0x00884422);
        // testPaint.setColorFilter(testLCF);
        // skyCMCF = new ColorMatrixColorFilter(duskM);
        // skyPaint.setColorFilter(skyCMCF);
        // night = BitmapFactory.decodeResource(r, R.drawable.sky_night);
        // dusk = A=[119 194 255; 74 128 162; 33 50 31]=A=[R1 R2 R3; G1 G2 G3;
        // B1 B2 B3]
        // morning = B=[45 78 171; 87 128 198; 137 181 219]
        // C = [
        // -6.5849 12.1696 -4.6966;
        // -6.0332 10.8845 -4.3902;
        // -3.2949 5.1701 -1.9601
        // ]
    }

    static public void refreshPaint() {
        int time = GameView.displayData.getTime();
        if (time > GlobalParameter.Dusk) {
            if (time > GlobalParameter.Night) {
                skyPaint.setARGB(255, 0, 0, 0);
                starPaint = new Paint();
            } else {
                lg = new LinearGradient(0, 0, 0, SkyHigh, duskUp, duskDown, Shader.TileMode.CLAMP);
                skyPaint.setShader(lg);
                // // dusk
                // // skyCMCF = new
                // //
                // ColorMatrixColorFilter(GameView.closeColorMatrixByRatio(duskM,
                // // nightM,
                // // (time - GlobalParameter.Dusk) /
                // GlobalParameter.Dusk2Night));
                // // skyPaint.setColorFilter(skyCMCF);
                float ratio = (time - GlobalParameter.Dusk) / GlobalParameter.Dusk2Night;
                // skyUpColor = GameView.closeRGBByRatio(duskUp, nightUp,
                // ratio);
                // skyDownColor = GameView.closeRGBByRatio(duskDown, nightDown,
                // ratio);
                // lg = new LinearGradient(0, 0, 0, SkyHigh, skyUpColor,
                // skyDownColor, Shader.TileMode.CLAMP);
                // skyPaint.setShader(lg);
                starCMCF = new ColorMatrixColorFilter(GameView.modifyAlphaColorMatrixByRatio(ratio));
                starPaint.setColorFilter(starCMCF);
            }
        } else {
            if (time > GlobalParameter.Noon) {
                // skyCMCF = new
                // ColorMatrixColorFilter(GameView.closeColorMatrixByRatio(noonM,
                // duskM,
                // (time - GlobalParameter.Noon) / GlobalParameter.Noon2Dusk));
                // skyPaint.setColorFilter(skyCMCF);
                float ratio = (time - GlobalParameter.Noon) / GlobalParameter.Noon2Dusk;
                skyUpColor = GameView.closeRGBByRatio(noonUp, duskUp, ratio);
                skyDownColor = GameView.closeRGBByRatio(noonDown, duskDown, ratio);
                lg = new LinearGradient(0, 0, 0, SkyHigh, skyUpColor, skyDownColor, Shader.TileMode.CLAMP);
                skyPaint.setShader(lg);
            } else if (time > GlobalParameter.Morning) {
                // skyCMCF = new
                // ColorMatrixColorFilter(GameView.closeColorMatrixByRatio(mornM,
                // noonM,
                // (time - GlobalParameter.Morning) /
                // GlobalParameter.Morn2Noon));
                // skyPaint.setColorFilter(skyCMCF);
                float ratio = (time - GlobalParameter.Morning) / GlobalParameter.Morn2Noon;
                skyUpColor = GameView.closeRGBByRatio(morningUp, noonUp, ratio);
                skyDownColor = GameView.closeRGBByRatio(morningDown, noonDown, ratio);
                lg = new LinearGradient(0, 0, 0, SkyHigh, skyUpColor, skyDownColor, Shader.TileMode.CLAMP);
                skyPaint.setShader(lg);
            } else {
                // skyCMCF = new
                // ColorMatrixColorFilter(GameView.closeColorMatrixByRatio(nightM,
                // mornM, time
                // / GlobalParameter.Night2Morning));
                // skyPaint.setColorFilter(skyCMCF);
                lg = new LinearGradient(0, 0, 0, SkyHigh, morningUp, morningDown, Shader.TileMode.CLAMP);
                skyPaint.setShader(lg);
                starCMCF = new ColorMatrixColorFilter(
                        GameView.modifyAlphaColorMatrixByRatio(1 - (time / GlobalParameter.Night2Morning)));
                starPaint.setColorFilter(starCMCF);
            }
        }
    }

    static public void drawSky(Canvas canvas, float offset) {
        int time = GameView.displayData.getTime();
        if (time > GlobalParameter.Dusk) {
            if (time > GlobalParameter.Night) {
                // night
                canvas.drawRect(0, 0, 800, 480, skyPaint);
            } else {
                // dusk
                // canvas.drawBitmap(morning, 0, 0, skyPaint);
                canvas.drawRect(0, 0, 800, 480, skyPaint);
            }
        } else {
            if (time > GlobalParameter.Noon) {
                canvas.drawRect(0, 0, 800, 480, skyPaint);
            } else if (time > GlobalParameter.Morning) {
                canvas.drawRect(0, 0, 800, 480, skyPaint);
            } else {
                canvas.drawRect(0, 0, 800, 480, skyPaint);
            }
        }
    }

    static public void drawStar(Canvas canvas, float offset) {
        int time = GameView.displayData.getTime();
        if (time > GlobalParameter.Dusk) {
            if (time > GlobalParameter.Night) {
                drawStar(canvas, starPaint, offset);
            } else {
                drawStar(canvas, starPaint, offset);
            }
        } else {
            if (time < GlobalParameter.Morning) {
                drawStar(canvas, starPaint, offset);
            }
        }
    }

    static public LinkedList<Star> starList = new LinkedList<Star>();

    static private void drawStar(Canvas canvas, Paint paint, float offset) {
        for (Iterator<Star> iterator = starList.iterator(); iterator.hasNext();) {
            Star type = (Star) iterator.next();
            type.draw(canvas, paint, offset);
        }
    }

    public static class Star {
        public static final int Width = 18;
        public static final int Height = 18;

        public static Bitmap star1;
        public static Bitmap star2;
        public static Bitmap star3;
        public static Bitmap star4;
        public static Bitmap star5;

        public static void initial() {
            star1 = BitmapFactory.decodeResource(r, R.drawable.star1).copy(Config.ARGB_8888, true);
            star2 = BitmapFactory.decodeResource(r, R.drawable.star2).copy(Config.ARGB_8888, true);
            star3 = BitmapFactory.decodeResource(r, R.drawable.star3).copy(Config.ARGB_8888, true);
            star4 = BitmapFactory.decodeResource(r, R.drawable.star4).copy(Config.ARGB_8888, true);
            star5 = BitmapFactory.decodeResource(r, R.drawable.star5).copy(Config.ARGB_8888, true);
            GameView.copyRGBtoAlpha(star1, 0x00FFFFFF);
            GameView.copyRGBtoAlpha(star2, 0x00FFFFFF);
            GameView.copyRGBtoAlpha(star3, 0x00FFFFFF);
            GameView.copyRGBtoAlpha(star4, 0x00FFFFFF);
            GameView.copyRGBtoAlpha(star5, 0x00FFFFFF);
            starList = new LinkedList<Star>();
            int num = 20;
            int maxY_spread = 132;
            int interval = (int) (1360 / num);
            for (int i = 0; i < num; i++) {
                float x = (float) (i * interval + (interval - Width) * Math.random());
                float y = (float) ((maxY_spread - Height) * Math.random());
                Bitmap temp;
                int mapN = (int) (Math.random() * 4);
                switch (mapN) {
                    case 1: {
                        temp = star1;
                        break;
                    }
                    case 2: {
                        temp = star2;
                        break;
                    }
                    case 3: {
                        temp = star3;
                        break;
                    }
                    case 4: {
                        temp = star4;
                        break;
                    }
                        // case 5:{
                        // temp = star5;
                        // break;
                        // }
                    default: {
                        temp = star4;
                        break;
                    }
                }
                starList.add(new Star(x, y, temp));
            }
        }

        int frame;
        Rect src;
        RectF dest;
        Bitmap star;
        long time;
        float x;
        float y;

        public Star(float x, float y, Bitmap star) {
            this.x = x;
            this.y = y;
            frame = 0;
            src = new Rect(0, 0, Width, Height);
            dest = new RectF(x, y, x + Width, y + Height);
            this.star = star;
            time = System.currentTimeMillis();
        }

        public void draw(Canvas canvas, Paint paint, float offset) {
            dest = new RectF(x + offset, y, x + Width + offset, y + Height);
            canvas.drawBitmap(star, src, dest, paint);
            update();
        }

        public void update() {
            if ((System.currentTimeMillis() - time) > 200) {
                if (Math.random() > 0.9) {
                    if (++frame == 5)
                        frame = 0;
                    src = new Rect(frame * Width, 0, (frame + 1) * Width, Height);
                    time = System.currentTimeMillis();
                }
            }
        }
    }

}
