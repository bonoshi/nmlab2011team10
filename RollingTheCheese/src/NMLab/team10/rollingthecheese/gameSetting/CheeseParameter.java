package NMLab.team10.rollingthecheese.gameSetting;

import NMLab.team10.rollingthecheese.event.EventEnum;

public class CheeseParameter {// have display light type

    public class Normal {// normal cheese

        public static final int Time = 2000;// ms
        public static final float Endurance = 100F;
        public static final float DistancePerSec = 100F;
        public static final float Speed = DistancePerSec / GlobalParameter.FramePeriod;// 1sec
                                                                                       // 100pixel
        public static final float Radix = 36;// pixel
        public static final int Cost = 50;

        // static final float SizeLarge = 2.0F;
        // static final float SizeMed = 1.0F;
        // static final float SizeSmall = 0.6F;
        public static final float RadixLarge = 36F;
        public static final float RadixMed = 36F;
        public static final float RadixSmall = 36F;
        public static final float RadixTiny = 36F;

        public static final float CostLarge = 1.6F;
        public static final float CostMed = 1.0F;
        public static final float CostSmall = 0.7F;

        public static final float TimeLarge = 1.3F;
        public static final float TimeMed = 1.0F;
        public static final float TimeSmall = 0.8F;

        public static final float ENLarge = 2.5F;
        public static final float ENMed = 1.7F;
        public static final float ENSmall = 1.0F;
        public static final float ENTiny = 0.7F;

        // public static final float SizeCrisis = 0.7F;// ratio
        // public static final float ENCrisis = 0.6F;
        public static final float TimeCrisis = 0.6F;// increment

    }

    public class Poison {
        // for poison cheese and its contact infection
        public static final float PoisonDecreSmall = 4;
        public static final float PoisonDecreMed = 10;
        public static final float PoisonDecreLarge = 20;
        public static final int PoisonSmallCount = 2;// 1~2
        public static final int PoisonMedCount = 4;// 3~4
        public static final int PoisonLargeCount = 6;// 5~6
        public static final int PoisonDecayTime = 1000;// ms
    }

    public class Sweat {
        // for sweaty cheese and its range and effect
    }

    public class Fire {
        // for firing cheese and its effect
    }

    EventEnum E;

    public static int getPrice(byte event) {
        switch (event) {
            case EventEnum.OriginalCheeseSmall:
                return Math.round(Normal.Cost * Normal.CostSmall);
            case EventEnum.OriginalCheeseMed:
                return Math.round(Normal.Cost * Normal.CostMed);
            case EventEnum.OriginalCheeseLarge:
                return Math.round(Normal.Cost * Normal.CostLarge);
                // case EventEnum.CasuMarzuSmall:
                // return CasuMarzuSmall;
                // case EventEnum.CasuMarzuMed:
                // return CasuMarzuMed;
                // case EventEnum.CasuMarzuLarge:
                // return CasuMarzuLarge;
                // case EventEnum.SweatyCheeseSmall:
                // return SweatyCheeseSmall;
                // case EventEnum.SweatyCheeseMed:
                // return SweatyCheeseMed;
                // case EventEnum.SweatyCheeseLarge:
                // return SweatyCheeseLarge;
                // case EventEnum.FiringCheeseSmall:
                // return FiringCheeseSmall;
                // case EventEnum.FiringCheeseMed:
                // return FiringCheeseMed;
                // case EventEnum.FiringCheeseLarge:
                // return FiringCheeseLarge;
        }
        return Integer.MAX_VALUE;
    }

    public static int getTime(byte event) {
        switch (event) {
            case EventEnum.OriginalCheeseSmall:
                return Math.round(Normal.Time * Normal.TimeSmall);
            case EventEnum.OriginalCheeseMed:
                return Math.round(Normal.Time * Normal.TimeMed);
            case EventEnum.OriginalCheeseLarge:
                return Math.round(Normal.Time * Normal.TimeLarge);
                // case EventEnum.CasuMarzuSmall:
                // return CasuMarzuSmall;
                // case EventEnum.CasuMarzuMed:
                // return CasuMarzuMed;
                // case EventEnum.CasuMarzuLarge:
                // return CasuMarzuLarge;
                // case EventEnum.SweatyCheeseSmall:
                // return SweatyCheeseSmall;
                // case EventEnum.SweatyCheeseMed:
                // return SweatyCheeseMed;
                // case EventEnum.SweatyCheeseLarge:
                // return SweatyCheeseLarge;
                // case EventEnum.FiringCheeseSmall:
                // return FiringCheeseSmall;
                // case EventEnum.FiringCheeseMed:
                // return FiringCheeseMed;
                // case EventEnum.FiringCheeseLarge:
                // return FiringCheeseLarge;
        }
        return Integer.MAX_VALUE;
    }

    public static Cheese createCheese(byte event, boolean isSmallCrisis) {
        Cheese cheese = null;
        switch (event) {
            case EventEnum.OriginalCheeseSmall:
                cheese = (isSmallCrisis) ? new Cheese(Original, Tiny) : new Cheese(Original, Small);
                break;
            case EventEnum.OriginalCheeseMed:
                cheese = (isSmallCrisis) ? new Cheese(Original, Small) : new Cheese(Original, Medium);
                break;
            case EventEnum.OriginalCheeseLarge:
                cheese = (isSmallCrisis) ? new Cheese(Original, Medium) : new Cheese(Original, Large);
                break;
            case EventEnum.CasuMarzuSmall:
                cheese = (isSmallCrisis) ? new Cheese(CasuMarzu, Tiny) : new Cheese(CasuMarzu, Small);
                break;
            case EventEnum.CasuMarzuMed:
                cheese = (isSmallCrisis) ? new Cheese(CasuMarzu, Small) : new Cheese(CasuMarzu, Medium);
                break;
            case EventEnum.CasuMarzuLarge:
                cheese = (isSmallCrisis) ? new Cheese(CasuMarzu, Medium) : new Cheese(CasuMarzu, Large);
                break;
            case EventEnum.SweatyCheeseSmall:
                cheese = (isSmallCrisis) ? new Cheese(Sweaty, Tiny) : new Cheese(Sweaty, Small);
                break;
            case EventEnum.SweatyCheeseMed:
                cheese = (isSmallCrisis) ? new Cheese(Sweaty, Small) : new Cheese(Sweaty, Medium);
                break;
            case EventEnum.SweatyCheeseLarge:
                cheese = (isSmallCrisis) ? new Cheese(Sweaty, Medium) : new Cheese(Sweaty, Large);
                break;
            case EventEnum.FiringCheeseSmall:
                cheese = (isSmallCrisis) ? new Cheese(Firing, Tiny) : new Cheese(Firing, Small);
                break;
            case EventEnum.FiringCheeseMed:
                cheese = (isSmallCrisis) ? new Cheese(Firing, Small) : new Cheese(Firing, Medium);
                break;
            case EventEnum.FiringCheeseLarge:
                cheese = (isSmallCrisis) ? new Cheese(Firing, Medium) : new Cheese(Firing, Large);
                break;
        }
        return cheese;
    }

    public static final byte Original = CheeseEnum.Original;
    public static final byte CasuMarzu = CheeseEnum.Poison;
    public static final byte Sweaty = CheeseEnum.Sweaty;
    public static final byte Firing = CheeseEnum.Firing;

    public static final byte Large = CheeseSizeEnum.Large;
    public static final byte Medium = CheeseSizeEnum.Medium;
    public static final byte Small = CheeseSizeEnum.Small;
    public static final byte Tiny = CheeseSizeEnum.Tiny;
}
