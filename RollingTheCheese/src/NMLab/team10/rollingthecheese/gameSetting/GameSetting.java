package NMLab.team10.rollingthecheese.gameSetting;

public class GameSetting {

    public GameSetting() {
        // TODO Auto-generated constructor stub
    }

    // setting relating to global display
    int leftStartBorder = 0;
    int rightStartBorder = 1600;
    String leftName = "LeftName";
    String RightName = "RightName";
    ClimateType climate = ClimateType.Sunny;
    BackGroundType background = BackGroundType.SpringFarm;

    // setting relating to farm properties => Construction
    ProjectorType leftProjector = ProjectorType.Board;
    ProjectorType rightProjector = ProjectorType.Board;

    // setting relating to destruction done to farm
    DestructState leftDestruct = new DestructState();
    DestructState rightDestruct = new DestructState();

    class DestructState {
        public DestructState() {
            // TODO Auto-generated constructor stub
        }
        boolean fense = false;
        boolean power = false;
        boolean smallCheese = false;
        boolean slowCheese = false;// produce slower
        boolean milk = false;
    };
}

enum BackGroundType {
    SpringFarm
};

enum ClimateType {
    Sunny, Cloudy, Rainy, Snowy
};

enum ProjectorType {// according to projector
    Board, Slide, Cannon, Rocket
};

enum CheeseProdType {//according to house
    ForFun, AfterHours, Bakery, FoodFactory
};

enum CheeseQualityType {//according to house
    Handmade, CheeseMold, FoodChemisty, GMO
};

enum MilkProdType {//according to farm
    Grazing, Husbandry, Mechanization, Hormone
};
