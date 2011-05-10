package NMLab.team10.rollingthecheese.displayData;

import java.util.Iterator;
import java.util.LinkedList;

import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.gameSetting.ClimateEnum;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class CloudDisplay {

    public static final int CloudWidth = 200;
    public static final int CloudHeight = 100;
    public static final int CloudInterval = 201;

    static LinkedList<Cloud> list;

    static {
        list = new LinkedList<Cloud>();
        list.add(new Cloud(true));
        list.add(new Cloud(true));
        list.add(new Cloud(true));
    }

    static private byte climate = ClimateEnum.Sunny;
    static long lastAddCloudTime = System.currentTimeMillis();

    public static void updateCloud() {
        if (CloudDisplay.climate != Climate.getClimate()) {
            CloudDisplay.climate = Climate.getClimate();
            switch (CloudDisplay.climate) {
                case ClimateEnum.Sunny: {
                    break;
                }
                case ClimateEnum.Cloudy: {
                    break;
                }
                case ClimateEnum.Rainy: {
                    break;
                }
                case ClimateEnum.Snowy: {
                    break;
                }
                case ClimateEnum.Foggy: {
                    break;
                }
            }
        }

        addCloud();

        for (Iterator<Cloud> iterator = list.iterator(); iterator.hasNext();) {
            Cloud type = (Cloud) iterator.next();
            type.updateCloud();
            if (type.isDead)
                iterator.remove();
        }
    }

    private static void addCloud() {
        if ((System.currentTimeMillis() - lastAddCloudTime) < 3000) {
            return;
        }
        double rand = Math.random();
        switch (CloudDisplay.climate) {
            case ClimateEnum.Sunny: {
                if (rand > 0.99 && list.size() < 11) {
                    list.add(new Cloud(false));
                    lastAddCloudTime = System.currentTimeMillis();
                }
                break;
            }
            case ClimateEnum.Cloudy: {
                if (rand > 0.97 && list.size() < 11) {
                    list.add(new Cloud(false));
                    lastAddCloudTime = System.currentTimeMillis();
                }
                break;
            }
            case ClimateEnum.Rainy: {
                break;
            }
            case ClimateEnum.Snowy: {
                break;
            }
            case ClimateEnum.Foggy: {
                break;
            }
        }
    }

    static class Cloud {

        static Resources r = GameView.r;
        static Bitmap cloud = BitmapFactory.decodeResource(r, R.drawable.cloud_white2).copy(Config.ARGB_8888, true);

        public Cloud(boolean init) {
            cloud_x = (float) (1560 * Math.random()) - 200;
            cloud_y = (float) (150 * Math.random()) - 50;
            fixCloud();
            if (init) {
                double initF = Math.random();
                frame = 1 + ((int) (initF * 4));
            } else {
                frame = 0;
            }
            sRectangle = new Rect(CloudInterval * frame, 0, CloudInterval * frame + CloudWidth, CloudHeight);
        }

        boolean isDead = false;
        float cloud_x;
        float cloud_y;

        long lastChangeTime = System.currentTimeMillis();

        int frame;
        private Rect sRectangle;

        public void updateCloud() {
            cloud_x += Climate.getWind();
            double rand = Math.random();
            if (rand > 0.98) {
                if (rand > 0.99) {
                    cloud_y += 0.5;
                } else {
                    cloud_y -= 0.5;
                }
            }
            if (cloud_x > 1360 && cloud_x < -200) {
                isDead = true;
            }
            fixCloud();
            updateFrame();
        }

        private void fixCloud() {
            if (cloud_y > 100) {
                cloud_y -= 1;
            } else if (cloud_y < -50) {
                cloud_y += 1;
            }
        }

        private void updateFrame() {
            double rand = Math.random();
            switch (CloudDisplay.climate) {
                case ClimateEnum.Sunny: {
                    if (rand > 0.998) {
                        if ((System.currentTimeMillis() - lastChangeTime) < 5000) {
                            return;
                        }
                        lastChangeTime = System.currentTimeMillis();
                        if (rand > 0.999 && frame < 11) {
                            frame++;
                        } else if (rand < 0.999 && frame > -1) {
                            frame--;
                            if (frame == -1)
                                isDead = true;
                        }
                        sRectangle = new Rect(CloudInterval * frame, 0, CloudInterval * frame + CloudWidth,
                                CloudHeight);
                    }
                    break;
                }
                case ClimateEnum.Cloudy: {
                    if (rand > 0.998) {
                        if ((System.currentTimeMillis() - lastChangeTime) < 5000) {
                            return;
                        }
                        lastChangeTime = System.currentTimeMillis();
                        if (rand > 0.9985 && frame < 11) {
                            frame++;
                        } else if (rand < 0.9985 && frame > -1) {
                            frame--;
                            if (frame == 0)
                                isDead = true;
                        }
                        sRectangle = new Rect(CloudWidth * frame, 0, CloudWidth * frame + CloudWidth,
                                CloudHeight);
                    }
                    break;
                }
                case ClimateEnum.Rainy: {
                    break;
                }
                case ClimateEnum.Snowy: {
                    break;
                }
                case ClimateEnum.Foggy: {
                    break;
                }
            }
        }

        public void draw(Canvas canvas, float offSet) {
            RectF dest = new RectF(cloud_x + offSet, cloud_y, cloud_x + CloudWidth + offSet, cloud_y
                    + CloudHeight);
            canvas.drawBitmap(cloud, sRectangle, dest, null);
        }

        public static void changeCloud(Bitmap b) {
            cloud = b;
        }
    }

    public static void draw(Canvas canvas, float offSet) {
        for (Iterator<Cloud> iterator = list.iterator(); iterator.hasNext();) {
            Cloud cloud = (Cloud) iterator.next();
            cloud.draw(canvas, offSet);
        }
    }
}
