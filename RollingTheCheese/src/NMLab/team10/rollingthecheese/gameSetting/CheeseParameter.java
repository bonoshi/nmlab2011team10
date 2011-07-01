package NMLab.team10.rollingthecheese.gameSetting;

import NMLab.team10.rollingthecheese.event.EventEnum;

public class CheeseParameter {// have display light type

    public static final float UnitSize = 55F;

    public static class Normal {// normal cheese

        public static final int Time = 2000;// ms
        public static final float Endurance = 100F * 1.5F;
        public static final float DistancePerSec = 100F;
        public static final float Speed = DistancePerSec * 0.001F * GlobalParameter.FramePeriod;// 1sec

        public static final float DamageTiny = 4.5F;
        public static final float DamageSmall = 5.0F;
        public static final float DamageMed = 5.5F;
        public static final float DamageLarge = 6.0F;

        // 100pixel
        // public static final float Radix = 36;// pixel
        public static final int Cost = 50;

        // static final float SizeLarge = 2.0F;
        // static final float SizeMed = 1.0F;
        // static final float SizeSmall = 0.6F;
        public static final float RadixLarge = 45F;
        public static final float RadixMed = 36F;
        public static final float RadixSmall = 27F;
        public static final float RadixTiny = 22F;

        public static final float CostLarge = 1.6F;
        public static final float CostMed = 1.0F;
        public static final float CostSmall = 0.7F;

        public static final float TimeLarge = 1.3F;
        public static final float TimeMed = 1.0F;
        public static final float TimeSmall = 0.7F;

        public static final float ENLarge = 2.5F;
        public static final float ENMed = 1.7F;
        public static final float ENSmall = 1.0F;
        public static final float ENTiny = 0.8F;

        // public static final float SizeCrisis = 0.7F;// ratio
        // public static final float ENCrisis = 0.6F;
        public static final float TimeCrisis = 0.6F;// increment

    }

    public static class Poison {

        public static final int Time = 3000;// ms
        public static final float Endurance = 60F * 1.5F;
        public static final float DistancePerSec = 85F;
        public static final float Speed = DistancePerSec * 0.001F * GlobalParameter.FramePeriod;// 1sec
                                                                                       // 100pixel
        public static final int Cost = 80;

        public static final float DamageTiny = 3.5F;
        public static final float DamageSmall = 4.0F;
        public static final float DamageMed = 4.5F;
        public static final float DamageLarge = 5.0F;

        public static final float RadixLarge = 36F;
        public static final float RadixMed = 28F;
        public static final float RadixSmall = 22F;
        public static final float RadixTiny = 18F;

        public static final float CostLarge = 1.8F;
        public static final float CostMed = 1.0F;
        public static final float CostSmall = 0.6F;

        public static final float TimeLarge = 1.4F;
        public static final float TimeMed = 1.0F;
        public static final float TimeSmall = 0.8F;

        public static final float ENLarge = 2.2F;
        public static final float ENMed = 1.5F;
        public static final float ENSmall = 1.0F;
        public static final float ENTiny = 0.7F;

        // public static final float SizeCrisis = 0.7F;// ratio
        // public static final float ENCrisis = 0.6F;
        public static final float TimeCrisis = 0.9F;// increment

        // for poison cheese and its contact infection
        public static final float PoisonDecreSmall = 0.1F;
        public static final float PoisonDecreMed = 0.2F;
        public static final float PoisonDecreLarge = 0.3F;
        public static final short PoisonSmallCount = (short)(2500 / GlobalParameter.FramePeriod);//
        public static final short PoisonMedCount = (short)(3500 / GlobalParameter.FramePeriod);//
        public static final short PoisonLargeCount = (short)(5000 / GlobalParameter.FramePeriod);//

        public static final short PoisonInfectDecay = (short)(1500 / GlobalParameter.FramePeriod);
        // public static final int PoisonDecayTime = 1000;// ms

    }

    public static class Sweat {
        public static final int Time = 2500;// ms
        public static final float Endurance = 75F * 1.5F;
        public static final float DistancePerSec = 75F;
        public static final float Speed = DistancePerSec * 0.001F * GlobalParameter.FramePeriod;// 1sec
                                                                                       // 100pixel
        public static final int Cost = 65;

        public static final float RadixLarge = 50F;
        public static final float RadixMed = 41F;
        public static final float RadixSmall = 34F;
        public static final float RadixTiny = 28F;

        public static final float CostLarge = 2.0F;
        public static final float CostMed = 1.0F;
        public static final float CostSmall = 0.7F;

        public static final float TimeLarge = 1.5F;
        public static final float TimeMed = 1.0F;
        public static final float TimeSmall = 0.7F;

        public static final float ENLarge = 1.9F;
        public static final float ENMed = 1.4F;
        public static final float ENSmall = 1.0F;
        public static final float ENTiny = 0.8F;

        public static final float TimeCrisis = 0.7F;

        // for poison cheese and its contact infection
        public static final float DamageTiny = 1.1F;
        public static final float DamageSmall = 1.4F;
        public static final float DamageMed = 1.7F;
        public static final float DamageLarge = 2.0F;
        public static final float RangeTiny = 200F;
        public static final float RangeSmall = 250F;
        public static final float RangeMed = 350F;
        public static final float RangeLarge = 450F;
        // public static final int PoisonDecayTime = 1000;// ms
        // for sweat cheese and its range and effect

    }

    public static class Fire {
        public static final int Time = 1200;// ms
        public static final float Endurance = 80F * 1.5F;
        public static final float DistancePerSec = 120F;
        public static final float Speed = DistancePerSec * 0.001F * GlobalParameter.FramePeriod;// 1sec
                                                                                       // 100pixel
        public static final int Cost = 95;

        public static final float RadixLarge = 40F;
        public static final float RadixMed = 33F;
        public static final float RadixSmall = 27F;
        public static final float RadixTiny = 22F;

        public static final float CostLarge = 2.0F;
        public static final float CostMed = 1.0F;
        public static final float CostSmall = 0.7F;

        public static final float TimeLarge = 1.4F;
        public static final float TimeMed = 1.0F;
        public static final float TimeSmall = 0.8F;

        public static final float ENLarge = 1.6F;
        public static final float ENMed = 1.3F;
        public static final float ENSmall = 1.0F;
        public static final float ENTiny = 0.8F;

        public static final float TimeCrisis = 0.8F;

        public static final float DamageTiny = 10F;
        public static final float DamageSmall = 12F;
        public static final float DamageMed = 15F;
        public static final float DamageLarge = 20F;

        // for firing cheese and its effect
        // public static final int recurTime = 100; //recur

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
            case EventEnum.CasuMarzuSmall:
                return Math.round(Poison.Cost * Poison.CostSmall);
            case EventEnum.CasuMarzuMed:
                return Math.round(Poison.Cost * Poison.CostMed);
            case EventEnum.CasuMarzuLarge:
                return Math.round(Poison.Cost * Poison.CostLarge);
            case EventEnum.SweatyCheeseSmall:
                return Math.round(Sweat.Cost * Sweat.CostSmall);
            case EventEnum.SweatyCheeseMed:
                return Math.round(Sweat.Cost * Sweat.CostMed);
            case EventEnum.SweatyCheeseLarge:
                return Math.round(Sweat.Cost * Sweat.CostLarge);
            case EventEnum.FiringCheeseSmall:
                return Math.round(Fire.Cost * Fire.CostSmall);
            case EventEnum.FiringCheeseMed:
                return Math.round(Fire.Cost * Fire.CostMed);
            case EventEnum.FiringCheeseLarge:
                return Math.round(Fire.Cost * Fire.CostLarge);
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
            case EventEnum.CasuMarzuSmall:
                return Math.round(Poison.Time * Poison.TimeSmall);
            case EventEnum.CasuMarzuMed:
                return Math.round(Poison.Time * Poison.TimeMed);
            case EventEnum.CasuMarzuLarge:
                return Math.round(Poison.Time * Poison.TimeLarge);
            case EventEnum.SweatyCheeseSmall:
                return Math.round(Sweat.Time * Sweat.TimeSmall);
            case EventEnum.SweatyCheeseMed:
                return Math.round(Sweat.Time * Sweat.TimeMed);
            case EventEnum.SweatyCheeseLarge:
                return Math.round(Sweat.Time * Sweat.TimeLarge);
            case EventEnum.FiringCheeseSmall:
                return Math.round(Fire.Time * Fire.TimeSmall);
            case EventEnum.FiringCheeseMed:
                return Math.round(Fire.Time * Fire.TimeMed);
            case EventEnum.FiringCheeseLarge:
                return Math.round(Fire.Time * Fire.TimeLarge);
        }
        return Integer.MAX_VALUE;
    }

    public static float getCrisisRatio(byte event) {
        switch (event) {
            case EventEnum.OriginalCheeseSmall:
            case EventEnum.OriginalCheeseMed:
            case EventEnum.OriginalCheeseLarge:
                return Normal.TimeCrisis;
            case EventEnum.CasuMarzuSmall:
            case EventEnum.CasuMarzuMed:
            case EventEnum.CasuMarzuLarge:
                return Poison.TimeCrisis;
            case EventEnum.SweatyCheeseSmall:
            case EventEnum.SweatyCheeseMed:
            case EventEnum.SweatyCheeseLarge:
                return Sweat.TimeCrisis;
            case EventEnum.FiringCheeseSmall:
            case EventEnum.FiringCheeseMed:
            case EventEnum.FiringCheeseLarge:
                return Fire.TimeCrisis;
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
