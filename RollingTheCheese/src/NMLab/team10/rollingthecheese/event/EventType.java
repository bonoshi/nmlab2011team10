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

public enum EventType {
    //1 => Cow Queue
    PurchaseCow,
    //2 => Cheese Queue
    OriginalCheeseSmall,
    OriginalCheeseMid,
    OriginalCheeseLarge,
    //3 => Cheese Queue
    CasuMarzuSmall,
    CasuMarzuMid,
    CasuMarzuLarge,
    //4 => Cheese Queue
    SweatyCheeseSmall,
    SweatyCheeseMid,
    SweatyCheeseLarge,
    //5 => Cheese Queue
    FiringCheeseSmall,
    FiringCheeseMid,
    FiringCheeseLarge,
    //6 => Cancel Cheese Thread
    CancelCheese,
    //7 => Construction Queue
    Projector,
    CheeseProd,
    CheeseQual,
    MilkProd,
    //8 => Destruction Queue
    IntoTheWild,
    BlackOut,
    MiceArmy,
    LazyWeekend,
    MilkLeak,
    //other bidirectional message => bonoshi and occqoo will discuss later!
    Quit,
    Pause,
    Surrender,
}
