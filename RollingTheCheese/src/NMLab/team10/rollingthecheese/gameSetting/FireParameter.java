package NMLab.team10.rollingthecheese.gameSetting;

public class FireParameter {

    class Small {
        public static final int Strength = 5000/GlobalParameter.FramePeriod;//ms
        public static final float Damage = 1.0F;
    }

    class Medium {
        public static final int Strength = 10000/GlobalParameter.FramePeriod;
        public static final float Damage = 1.5F;
    }

    class Large {
        public static final int Strength = 15000/GlobalParameter.FramePeriod;
        public static final float Damage = 2.2F;
    }
}
