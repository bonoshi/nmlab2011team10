package NMLab.team10.rollingthecheese.gameSetting;

import NMLab.team10.rollingthecheese.byteEnum.CheeseProdEnum;

public class HouseParameter {
    static final int TimeInterval = 10000;// ms

    static final int AfterHoursMilk = 700;
    static final int BakeryMilk = 1000;
    static final int FoodFactoryMilk = 1300;

    static final int AfterHoursTime = 4000;
    static final int BakeryTime = 9000;
    static final int FoodFactoryTime = 12000;

    static final int CheeseMoldMilk = 700;
    static final int FoodChemistyMilk = 1000;
    static final int GMOMilk = 1300;

    static final int CheeseMoldTime = 4000;
    static final int FoodChemistyTime = 9000;
    static final int GMOTime = 12000;

    //for cheese setting
    public static final float ForFun = 0.8F;
    public static final float ForFunHP = 5000;
    public static final float ForFunBorder = 127 + 100;
    public static final float ForFunSmokeX = 113 + 100;
    //public static final float ForFunSmokeX = 100;
    public static final float ForFunSmokeY = GlobalParameter.MapHeight - 265;


    public static final float AfterHours = 1.2F;
    public static final float AfterHoursHP = 6500;
    public static final float AfterHoursBorder = 127 + 100;
    public static final float AfterHoursSmokeX = 113 + 100;
    public static final float AfterHoursSmokeY = GlobalParameter.MapHeight - 265;

    public static final float Bakery = 1.9F;
    public static final float BakeryHP = 10000;
    public static final float BakeryBorder = 200;
    public static final float BakerySmokeX = 113;
    public static final float BakerySmokeY = GlobalParameter.MapHeight - 265;

    public static final float FoodFactory = 3.0F;
    public static final float FoodFactoryHP = 16000;
    public static final float FoodFactoryBorder = 200;
    public static final float FoodFactorySmokeX = 113;
    public static final float FoodFactorySmokeY = GlobalParameter.MapHeight - 265;

    public static final float Handmade = 1.0F;
    public static final float CheeseMold = 1.2F;
    public static final float FoodChemisty = 1.5F;
    public static final float GMO = 2.0F;
}
