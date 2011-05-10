package NMLab.team10.rollingthecheese.displayData;

import NMLab.team10.rollingthecheese.gameSetting.ClimateEnum;

public class Climate {

    static private float wind = 0.0F;
    static private int windCount = 0;

    static private byte climate = ClimateEnum.Sunny;

    //static private long lastChangeTime = System.currentTimeMillis();

    public static void modifyWind(byte climate) {
        if (Climate.climate != climate) {
            Climate.climate = climate;
        }
        windCount++;
        windCount %= 100;
        double rand = Math.random();
        if (windCount > 98) {
        }
        switch (Climate.climate) {
            case ClimateEnum.Sunny: {
                if (rand > 0.99) {
                    wind += 0.02;
                } else if (rand < 0.05) {
                    wind -= 0.02;
                }
                if (wind > 0.6) {
                    wind -= 0.25;
                } else if (wind < -0.6) {
                    wind += 0.25;
                }
                break;
            }
            case ClimateEnum.Cloudy: {
                break;
            }
            case ClimateEnum.Rainy: {
                break;
            }
            case ClimateEnum.Snowy: {
                break;
            }
            case ClimateEnum.Foggy: {
                break;
            }
        }
    }

    public static float getWind() {
        return wind;
    }

    public static byte getClimate() {
        return climate;
    }

}
