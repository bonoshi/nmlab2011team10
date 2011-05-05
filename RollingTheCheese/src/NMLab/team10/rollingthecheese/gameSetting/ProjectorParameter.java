package NMLab.team10.rollingthecheese.gameSetting;

class ProjectorParameter {

    static final int TimeInterval = 10000;//ms

    public static final float BoardSpeed = 1.0F;
    public static final float SlideSpeed = 1.3F;
    public static final float CannonSpeed = 1.8F;
    public static final float RocketSpeed = 2.4F;

    static final int SlideMilk = 1000;
    static final int CannonMilk = 1000;
    static final int RocketMilk = 1000;

    static final int SlideTime = 4000;
    static final int CannonTime = 9000;
    static final int RocketTime = 12000;

    static class Board {
        static final float offset;// pixel
        static final float drawX;
        static final float drawY;
        static final float startX;
        static final float startY;
        static final float endX;
        static final float endY;
        static final float angelX;
        static final float angelY;
        static final float length;
        // static final float point1X = 70;
        // static final float point1Y = 92;
        static {
            offset = 202;
            drawX = 110;
            drawY = 92;
            startX = 110;
            startY = 92;
            endX = 202;
            endY = 0;
            angelX = 0.3825F;
            angelY = 0.9239F;
            length = (1.414F * (endY - endX));
        }

        static float exceedAmount(float d, float radix) {
            float exceed = (float) (d - (length - radix / 1.414F));
            return exceed;
        }

        static float getCheeseX(float d, float radix) {
            float x = startX + d / 1.414F + radix / 1.414F;
            return x;
        }

        static float getCheeseY(float d, float radix) {
            float y = startY - d / 1.414F + radix / 1.414F;
            return y;
        }
    }
}
