package NMLab.team10.rollingthecheese.gameSetting;

public class FireLine {// have display light type

    public FireLine(byte type, float s, int length) {
        this.type = type;
        this.setStartX(s);
        this.setLength(length);
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

    public void setType(byte type) {
        this.type = type;
    }

    public byte getType() {
        return type;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartX() {
        return startX;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isOwnerLeft() {
        return owner;
    }

    byte type;
    private float startX;
    private int length;

    private int strength;

    private boolean owner;

    public static final byte Small = FireEnum.Small;
    public static final byte Medium = FireEnum.Medium;
    public static final byte Large = FireEnum.Large;
}

class FireEnum {
    public static final byte Small = 0;
    public static final byte Medium = 1;
    public static final byte Large = 2;
}
