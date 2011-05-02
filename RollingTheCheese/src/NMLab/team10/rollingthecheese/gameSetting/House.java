package NMLab.team10.rollingthecheese.gameSetting;

public class House {
    public House() {
        this.setProd(CheeseProdEnum.ForFun);
        this.setQual(CheeseQualityEnum.Handmade);
    }

    public void upgradeProd(){
        switch (prod) {
            case ForFun:
                this.prod = AfterHours;
                break;
            case AfterHours:
               this.prod = Bakery;
               break;
            case Bakery:
               this.prod = FoodFactory;
               break;
            case FoodFactory:
                break;
            default:// no use
                break;
        }
    }

    public synchronized boolean canUpgradeProd(){
        return !(prod==FoodFactory);
    }

    public int getUpProdMilk(){
        switch (prod) {
            case ForFun:
                return HouseParameter.AfterHoursMilk;
            case AfterHours:
                return HouseParameter.BakeryMilk;
            case Bakery:
                return HouseParameter.FoodFactoryMilk;
            case FoodFactory:
                return Integer.MAX_VALUE;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public String getUpProdMilkText(){
        switch (prod) {
            case ForFun:
                return Integer.toString(HouseParameter.AfterHoursMilk);
            case AfterHours:
                return Integer.toString(HouseParameter.BakeryMilk);
            case Bakery:
                return Integer.toString(HouseParameter.FoodFactoryMilk);
            case FoodFactory:
                return "MAX";
            default:// no use
                return "NOUSE";
        }
    }

    public int getUpProdTime(){
        switch (prod) {
            case ForFun:
                return HouseParameter.AfterHoursMilk;
            case AfterHours:
                return HouseParameter.BakeryMilk;
            case Bakery:
                return HouseParameter.FoodFactoryMilk;
            case FoodFactory:
                return Integer.MAX_VALUE;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public byte getProd() {
        return prod;
    }

    public void setProd(byte prod) {
        this.prod = prod;
    }

    public void upgradeQual(){
        switch (qual) {
            case Handmade:
                this.qual = CheeseMold;
                break;
            case CheeseMold:
               this.qual = FoodChemisty;
               break;
            case FoodChemisty:
               this.qual = GMO;
               break;
            case GMO:
                break;
            default:// no use
                break;
        }
    }

    public synchronized boolean canUpgradeQual(){
        return !(qual==GMO);
    }

    public int getUpQualMilk(){
        switch (qual) {
            case Handmade:
                return HouseParameter.CheeseMoldMilk;
            case CheeseMold:
                return HouseParameter.FoodChemistyMilk;
            case FoodChemisty:
                return HouseParameter.GMOMilk;
            case GMO:
                return Integer.MAX_VALUE;
            default:// no use
                return Integer.MAX_VALUE;
        }
    }

    public String getUpQualMilkText(){
        switch (qual) {
            case Handmade:
                return Integer.toString(HouseParameter.CheeseMoldMilk);
            case CheeseMold:
                return Integer.toString(HouseParameter.FoodFactoryMilk);
            case FoodChemisty:
                return Integer.toString(HouseParameter.GMOMilk);
            case GMO:
                return "MAX";
            default:// no use
                return "NOUSE";
        }
    }
    
    public int getUpQualTime(){
        switch (qual) {
            case Handmade:
                return HouseParameter.CheeseMoldTime;
            case CheeseMold:
                return HouseParameter.FoodChemistyTime;
            case FoodChemisty:
                return HouseParameter.GMOTime;
            default:// no use
                return 0;
        }
    }

    public byte getQual() {
        return qual;
    }

    public void setQual(byte qual) {
        this.qual = qual;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isOwnerLeft() {// for occqoo
        return owner;
    }

    public House clone(){
        House h = new House();
        h.setProd(prod);
        h.setQual(qual);
        h.setOwner(owner);
        return h;
    }

    byte prod;
    byte qual;
    private boolean owner;

    public static final byte ForFun = CheeseProdEnum.ForFun;
    public static final byte AfterHours = CheeseProdEnum.AfterHours;
    public static final byte Bakery = CheeseProdEnum.Bakery;
    public static final byte FoodFactory = CheeseProdEnum.FoodFactory;

    public static final byte Handmade = CheeseQualityEnum.Handmade;
    public static final byte CheeseMold = CheeseQualityEnum.CheeseMold;
    public static final byte FoodChemisty = CheeseQualityEnum.FoodChemisty;
    public static final byte GMO = CheeseQualityEnum.GMO;

    public static HouseParameter P;
}
