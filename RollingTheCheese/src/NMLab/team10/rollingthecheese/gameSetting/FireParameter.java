package NMLab.team10.rollingthecheese.gameSetting;

public class FireParameter {

    class Small {
        public static final int Strength = 1000/GlobalParameter.FramePeriod;//ms
        public static final float Damage = 1.0F;
    }

    class Medium {
        public static final int Strength = 2500/GlobalParameter.FramePeriod;
        public static final float Damage = 1.5F;
    }

    class Large {
        public static final int Strength = 4500/GlobalParameter.FramePeriod;
        public static final float Damage = 2.2F;
    }
}
