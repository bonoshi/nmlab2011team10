package NMLab.team10.rollingthecheese.displayData;

import java.util.Iterator;
import java.util.LinkedList;

import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class SkyDisplay {

    static Resources r = GameView.r;
    static Bitmap morning;
    static Bitmap noon;
    static Bitmap dusk;
    static Bitmap night;

    // static boolean initNight = false;

    static public void initial() {
        r = GameView.r;
        morning = BitmapFactory.decodeResource(r, R.drawable.sky_morning);
        noon = BitmapFactory.decodeResource(r, R.drawable.sky_noon);
        dusk = BitmapFactory.decodeResource(r, R.drawable.sky_dusk);
        night = BitmapFactory.decodeResource(r, R.drawable.sky_night);
    }

    static public void draw(Canvas canvas, float offset) {
        int time = GameView.displayData.getTime();
        if (time > 30000) {
            if (time > 35000) {
                canvas.drawBitmap(night, 0, 0, null);
                drawStar(canvas, offset);
            } else {
                canvas.drawBitmap(dusk, 0, 0, null);
            }
        } else {
            if (time > 20000) {
                canvas.drawBitmap(morning, 0, 0, null);
            } else if (time > 10000) {
                canvas.drawBitmap(noon, 0, 0, null);
            } else {
                canvas.drawBitmap(morning, 0, 0, null);
            }
        }
    }

    static public LinkedList<Star> starList = new LinkedList<Star>();

    static private void drawStar(Canvas canvas, float offset) {
        for (Iterator<Star> iterator = starList.iterator(); iterator.hasNext();) {
            Star type = (Star) iterator.next();
            type.draw(canvas, offset);
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
            star1 = BitmapFactory.decodeResource(r, R.drawable.star1);
            star2 = BitmapFactory.decodeResource(r, R.drawable.star2);
            star3 = BitmapFactory.decodeResource(r, R.drawable.star3);
            star4 = BitmapFactory.decodeResource(r, R.drawable.star4);
            star5 = BitmapFactory.decodeResource(r, R.drawable.star5);
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

        public void draw(Canvas canvas, float offset) {
            dest = new RectF(x + offset, y, x + Width + offset, y + Height);
            canvas.drawBitmap(star, src, dest, null);
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
