package NMLab.team10.rollingthecheese.event;

/****************************************************************************
八個動作按鍵：
Purchase Cow：單格按鍵
Original Cheese：第二排選單，分大中小
生蛆乳酪（Casu Marzu，見參考資料一）：會傳染到別的乳酪；第二排選單，分大中小
Sweaty Cheese：小範圍噴汁；第二排選單，分大中小
Firing Cheese：爆炸、（經過路線留下火痕？）；第二排選單，分大中小
Cancel Cheese：取消所有排程中的Cheese，單格按鍵
Construction：下拉，效果放下一段：第二排選單可以跳出，顯示升級金額。
Destruction：下拉，效果放下一段：第二排選單可以跳出，顯示升級金額。

Construction：
        Projector（升級斜坡）：木板（Board）、溜滑梯（Slide）、
                               火砲（Cannon）、火箭發射器（Rocket Launcher）
        Cheese Production：做好玩（For Fun）、業餘（After-Hours）、
                           烘焙坊（Bakery）、食品工廠（Food Factory）
        Production Quality：手工（Handmade）、模具生產（Cheese Mold）、
                            食品化學（Food Chemisty）、基因改造（GMO）
        Milk Production：放牧（Grazing）、畜牧（Husbandry）、
                         機械化（Mechanization）、生長激素（Growth Hormone）

Destruction（只能晚上做，每晚最多每種放一個）：
        破壞牛圈（Into the Wild）：晚上使用，立即生效，（動畫？）
        斷電（Black-Out）：晚上使用，立即生效一小段時間，（敵人畫面會看不到？）
        乳酪縮水（Mice Army）：晚上使用，立即生效較長一段時間
        乳酪產出（Lazy Weekend）：晚上使用，立即生效較長一段時間
        牛奶減產（Milk Leak）：晚上使用，立即生效較長一段時間
****************************************************************************/

public class EventEnum {
    //1 => Cow Queue
    public static final byte PurchaseCow = 0;
    //2 => Cheese Queue
    public static final byte CheeseStart = 1;
    public static final byte OriginalCheeseSmall = 1;
    public static final byte OriginalCheeseMed = 2;
    public static final byte OriginalCheeseLarge = 3;
    //3 => Cheese Queue
    public static final byte CasuMarzuSmall = 4;
    public static final byte CasuMarzuMed = 5;
    public static final byte CasuMarzuLarge = 6;
    //4 => Cheese Queue
    public static final byte SweatyCheeseSmall = 7;
    public static final byte SweatyCheeseMed = 8;
    public static final byte SweatyCheeseLarge = 9;
    //5 => Cheese Queue
    public static final byte FiringCheeseSmall = 10;
    public static final byte FiringCheeseMed = 11;
    public static final byte FiringCheeseLarge = 12;
    public static final byte CheeseEnd = 12;
    //6 => Cancel Cheese Thread
    public static final byte CancelCheese = 13;
    //7 => Construction Queue
    public static final byte ConsStart = 14;
    public static final byte Projector = 14;
    public static final byte CheeseProd = 15;
    public static final byte CheeseQual = 16;
    public static final byte MilkProd = 17;
    public static final byte ConsEnd = 17;
    //8 => Destruction Queue
    public static final byte DestStart = 18;
    public static final byte IntoTheWild = 18;
    public static final byte BlackOut = 19;
    public static final byte MiceArmy = 20;
    public static final byte LazyWeekend = 21;
    public static final byte MilkLeak = 22;
    public static final byte DestEnd = 22;
    //other bidirectional message => bonoshi and occqoo will discuss later!
    public static final byte Quit = 23;
    public static final byte Pause = 24;
    public static final byte Surrender = 25;
    public static final byte Start = 26;
    public static final byte Data = 27;
}
