package NMLab.team10.rollingthecheese.gameSetting;

public class HouseDisplay {
    public HouseDisplay(HouseMessage h) {
        this.setHouse(h);
    }

    public void setHouse(HouseMessage house) {
        this.house = house;
    }

    public HouseMessage getHouse() {
        return house;
    }

    public void setAnimation(byte animation) {
        this.animation = animation;
    }

    public int getAnimation() {
        animation++;
        animation%=4;
        return animation;
    }

    private HouseMessage house;
    
    private int animation = 0;
}
