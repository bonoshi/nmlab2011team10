package NMLab.team10.rollingthecheese.gameSetting;

public class FireLine {

    public FireLine(byte type, int s, int e) {
        this.type = type;
        this.startX = s;
        this.endX = e;
        switch (type) {
            case Small:
                strength = FireParameter.Small.Strength;
                break;
            case Medium:
                strength = FireParameter.Medium.Strength;
                break;
            case Large:
                strength = FireParameter.Large.Strength;
                break;
            default:
                break;
        }
    }

    public void refresh() {
        switch (type) {
            case Small:
                break;
            case Medium:
                if (strength <= FireParameter.Small.Strength)
                    type = Medium;
                break;
            case Large:
                if (strength <= FireParameter.Medium.Strength)
                    type = Medium;
                break;
            default:
                break;
        }
    }

    public void decrement() {
        strength--;
    }

    public boolean isDead() {
        return (strength <= 0);
    }

    byte type;
    private float startX;
    private float endX;

    private int strength;

    public static final byte Small = FireEnum.Small;
    public static final byte Medium = FireEnum.Medium;
    public static final byte Large = FireEnum.Large;
}

class FireParameter {
    public static final int Length = 20;// minimum pixels in length for a fire segment

    class Small {
        public static final int Strength = 20;
        public static final float Damage = 1.0F;
    }

    class Medium {
        public static final int Strength = 30;
        public static final float Damage = 1.5F;
    }

    class Large {
        public static final int Strength = 40;
        public static final float Damage = 2.2F;
    }
}

class FireEnum {
    public static final byte Small = 0;
    public static final byte Medium = 1;
    public static final byte Large = 2;
}
