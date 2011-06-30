package NMLab.team10.rollingthecheese.gameSetting;

class ProjectorParameter {

    static final int TimeInterval = 10000;// ms

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
        static final float startX;
        static final float startY;
        static final float endX;
        static final float endY;
//        static final float angelX;
//        static final float angelY;
        static final float length;
        // static final float point1X = 70;
        // static final float point1Y = 92;
        static {
            startX = 0+100;
            startY = 126;
            endX = 126+100;
            endY = 0;
//            angelX = 0.3825F;
//            angelY = 0.9239F;
            length = (1.414F * (endX - startX));
        }

        static float exceedAmount(float d, float radix) {
            float exceed = (float) (d - (length - radix * 0.414F));
            return exceed;
        }

        static float getCheeseX(float d, float radix, boolean whichSide) {
            float x = startX + d / 1.414F + radix / 1.414F;
            if (!whichSide) {// right
                x = GlobalParameter.MapWidth - x;
            }
            return x;
        }

        static float getCheeseY(float d, float radix, boolean whichSide) {
            float y = startY - d / 1.414F + radix / 1.414F;
            return y;
        }

        // static float getBattleCheeseX(float d, float radix, boolean
        // whichSide) {
        // float maxD = (length - radix * 0.414F);
        // float exceed = (float) (d - maxD);
        // float x = getCheeseX(maxD, radix, whichSide);
        // x += exceed;
        // if (!whichSide) {// right
        // x = GlobalParameter.MapWidth - x;
        // }
        // return x;
        // }

        static float getBattleCheeseX(float exceed, float radix, boolean whichSide) {
            float x = getCheeseX(getMaxPrepareD(radix), radix, whichSide);
            if (whichSide) {
                x += exceed;
            } else {
                x -= exceed;
            }
            return x;
        }

        static float getMaxPrepareD(float radix) {
            return (length - radix * 0.414F);
        }

        static float getBattleBorderX(float radix, boolean whichSide) {
            float x = getCheeseX(getMaxPrepareD(radix), radix, whichSide);
            if (!whichSide) {// right
                x = GlobalParameter.MapWidth - x;
            }
            return x;
        }

        // no need since it is equal to radix
        // static float getBattleCheeseY(float d, float radix, boolean
        // whichSide) {
        // float y = startY - d / 1.414F + radix / 1.414F;
        // return y;
        // }
    }

    static class Slide{
        static final float startX;
        static final float startY;
        static final float endX;
        static final float endY;
//        static final float angelX;
//        static final float angelY;
        static final float length;
        // static final float point1X = 70;
        // static final float point1Y = 92;
        static {
            startX = 0-28+100;//28 is offset relative to wood
            startY = 126;
            endX = 126-28+100;
            endY = 0;
//            angelX = 0.3825F;
//            angelY = 0.9239F;
            length = (1.414F * (endX - startX));
        }

        static float exceedAmount(float d, float radix) {
            float exceed = (float) (d - (length - radix * 0.414F));
            return exceed;
        }

        static float getCheeseX(float d, float radix, boolean whichSide) {
            float x = startX + d / 1.414F + radix / 1.414F;
            if (!whichSide) {// right
                x = GlobalParameter.MapWidth - x;
            }
            return x;
        }

        static float getCheeseY(float d, float radix, boolean whichSide) {
            float y = startY - d / 1.414F + radix / 1.414F;
            return y;
        }

        // static float getBattleCheeseX(float d, float radix, boolean
        // whichSide) {
        // float maxD = (length - radix * 0.414F);
        // float exceed = (float) (d - maxD);
        // float x = getCheeseX(maxD, radix, whichSide);
        // x += exceed;
        // if (!whichSide) {// right
        // x = GlobalParameter.MapWidth - x;
        // }
        // return x;
        // }

        static float getBattleCheeseX(float exceed, float radix, boolean whichSide) {
            float x = getCheeseX(getMaxPrepareD(radix), radix, whichSide);
            if (whichSide) {
                x += exceed;
            } else {
                x -= exceed;
            }
            return x;
        }

        static float getMaxPrepareD(float radix) {
            return (length - radix * 0.414F);
        }

        static float getBattleBorderX(float radix, boolean whichSide) {
            float x = getCheeseX(getMaxPrepareD(radix), radix, whichSide);
            if (!whichSide) {// right
                x = GlobalParameter.MapWidth - x;
            }
            return x;
        }

        // no need since it is equal to radix
        // static float getBattleCheeseY(float d, float radix, boolean
        // whichSide) {
        // float y = startY - d / 1.414F + radix / 1.414F;
        // return y;
        // }
    }
}
