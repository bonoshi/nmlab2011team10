package NMLab.team10.rollingthecheese.displayData;

import java.util.Iterator;
import NMLab.team10.rollingthecheese.byteEnum.ClimateEnum;
import java.util.LinkedList;

import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
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
                if (rand > 0.99 && list.size() < 7) {
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

    public static class Cloud {

        static Resources r = GameView.r;
        static Bitmap dusk;
        static Bitmap noon;
        static Bitmap morning;
        static Bitmap night;

        static public void initial() {
            r = GameView.r;
            dusk = BitmapFactory.decodeResource(r, R.drawable.cloud);
            noon = BitmapFactory.decodeResource(r, R.drawable.cloud);
            morning = BitmapFactory.decodeResource(r, R.drawable.cloud);
            night = BitmapFactory.decodeResource(r, R.drawable.cloud);
            //GameView.modifyGreenByRatio(dusk, 0.65F);
            //GameView.modifyBlueByRatio(dusk, 0.2F);
            //GameView.modifyRGBByRatio(morning, 0.85F);
            //GameView.modifyRGBByRatio(night, 0.2F);
        }

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
            if (rand > 0.975) {
                if (rand > 0.9875) {
                    cloud_y += 0.8;
                } else {
                    cloud_y -= 0.8;
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
                cloud_y -= 0.8;
            } else if (cloud_y < -50) {
                cloud_y += 0.8;
            }
        }

        private void updateFrame() {
            double rand = Math.random();
            switch (CloudDisplay.climate) {
                case ClimateEnum.Sunny: {
                    if (rand > 0.99) {
                        if ((System.currentTimeMillis() - lastChangeTime) < 5000) {
                            return;
                        }
                        lastChangeTime = System.currentTimeMillis();
                        if (rand > 0.997 && frame < 9) {
                            frame++;
                        } else if (rand < 0.997 && frame > -1) {
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
                    if (rand > 0.99) {
                        if ((System.currentTimeMillis() - lastChangeTime) < 5000) {
                            return;
                        }
                        lastChangeTime = System.currentTimeMillis();
                        if (frame >= 0 && frame <= 5) {
                            if (rand > 0.993) {
                                frame++;
                            } else if (rand < 0.993) {
                                if (--frame == -1)
                                    isDead = true;
                            }
                        } else if (frame <= 9) {
                            if (rand > 0.997) {
                                frame++;
                            } else if (rand < 0.997) {
                                frame--;
                            }
                        }
//                        } else if (frame == 8) {
//                            if (rand > 0.996) {
//                                frame++;
//                            } else if (rand < 0.996) {
//                                frame--;
//                            }
//                        } else if (frame == 9) {
//                            if (rand < 0.996) {
//                                frame--;
//                            }
//                        }

                        sRectangle = new Rect(CloudInterval * frame, 0, CloudInterval * frame + CloudWidth,
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
            int time = GameView.displayData.getTime();
            if (time > 30000) {
                if (time > 35000) {
                    canvas.drawBitmap(night, sRectangle, dest, null);
                } else {
                    canvas.drawBitmap(dusk, sRectangle, dest, null);
                }
            } else {
                if (time > 20000) {
                    canvas.drawBitmap(morning, sRectangle, dest, null);
                } else if (time > 10000) {
                    canvas.drawBitmap(noon, sRectangle, dest, null);
                } else {
                    canvas.drawBitmap(morning, sRectangle, dest, null);
                }
            }
        }

        public static void changeCloud(Bitmap b) {
            dusk = b;
        }
    }

    public static void draw(Canvas canvas, float offSet) {
        for (Iterator<Cloud> iterator = list.iterator(); iterator.hasNext();) {
            Cloud cloud = (Cloud) iterator.next();
            cloud.draw(canvas, offSet);
        }
    }
}
