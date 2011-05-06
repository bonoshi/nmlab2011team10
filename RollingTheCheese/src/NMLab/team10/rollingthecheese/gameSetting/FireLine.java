package NMLab.team10.rollingthecheese.gameSetting;

import java.util.LinkedList;

public class FireLine {// have display light type

    public FireLine(Cheese c, byte type, float x) {
        ID = GlobalID++;
        this.fireCheese = c;
        this.type = type;
        this.startX = x;
        this.endX = x;
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

    private static short GlobalID = 0;
    private short ID;

    public short getID() {
        return ID;
    }

    public void refresh() {
        switch (type) {
            case Small:
                break;
            case Medium:
                if (strength <= FireParameter.Small.Strength)
                    type = Small;
                break;
            case Large:
                if (strength <= FireParameter.Medium.Strength)
                    type = Medium;
                break;
            default:
                break;
        }
    }

    public void move(boolean whichSide) {
        startX = fireCheese.x;
        if (strength > 0) {
            strength--;
        } else {
            if (whichSide) {
                endX += fireCheese.getSpeed();
                if (endX > startX)
                    endX = startX;
            } else {
                endX -= fireCheese.getSpeed();
                if (endX < startX)
                    endX = startX;
            }
        }
    }

    public void fireDamage(LinkedList<Cheese> list, boolean whichSide) {
        for (int i = 0; i < list.size(); i++) {
            Cheese cheese = list.get(i);
            if (cheese.getType() == Cheese.Firing)
                continue;
            if (whichSide) {
                if(startX>=cheese.x && endX<=cheese.x){
                    cheese.setEndurance(cheese.getEndurance() - this.getDamage());
                    cheese.setOnFire(this.type);
                }
            }
            else{
                if(startX<=cheese.x && endX>=cheese.x){
                    cheese.setEndurance(cheese.getEndurance() - this.getDamage());
                    cheese.setOnFire(this.type);
                }
            }
        }
    }

    public boolean isDead() {
        return (strength <= 0 && endX == startX);
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

    //
    // public void setOwner(boolean owner) {
    // this.owner = owner;
    // }
    //
    // public boolean isOwnerLeft() {
    // return owner;
    // }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndX() {
        return endX;
    }

    public void setFireCheese(Cheese fireCheese) {
        this.fireCheese = fireCheese;
    }

    public Cheese getFireCheese() {
        return fireCheese;
    }

    public float getDamage(){
        switch (type) {
            case Small:
                return FireParameter.Small.Damage;
            case Medium:
                return FireParameter.Medium.Damage;
            case Large:
                return FireParameter.Large.Damage;
            default:
                return 0.0F;
        }
    }

    private byte type;
    private float startX;
    private float endX;

    private int strength;

    // private boolean owner;

    // link to Firing Cheese
    private Cheese fireCheese;

    public static final byte Small = FireEnum.Small;
    public static final byte Medium = FireEnum.Medium;
    public static final byte Large = FireEnum.Large;

    public static final boolean Right = false;
    public static final boolean Left = true;

    public static byte getFireSFromCheese(Cheese c){
        switch (c.getSize()) {
            case CheeseSizeEnum.Large:
                return Large;
            case CheeseSizeEnum.Medium:
                return Medium;
            case CheeseSizeEnum.Small:
                return Small;
            case CheeseSizeEnum.Tiny:
                return Small;
            default:
                return Small;

        }
    }
}

class FireEnum {
    public static final byte Small = 1;
    public static final byte Medium = 2;
    public static final byte Large = 3;
}
