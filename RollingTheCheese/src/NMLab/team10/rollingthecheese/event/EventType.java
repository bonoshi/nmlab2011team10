package NMLab.team10.rollingthecheese.event;

/****************************************************************************
�K�Ӱʧ@����G
Purchase Cow�G������
Original Cheese�G�ĤG�ƿ��A���j���p
�ͳI�ŹT�]Casu Marzu�A���ѦҸ�Ƥ@�^�G�|�ǬV��O���ŹT�F�ĤG�ƿ��A���j���p
Sweaty Cheese�G�p�d��Q�ġF�ĤG�ƿ��A���j���p
Firing Cheese�G�z���B�]�g�L���u�d�U�����H�^�F�ĤG�ƿ��A���j���p
Cancel Cheese�G�����Ҧ��Ƶ{����Cheese�A������
Construction�G�U�ԡA�ĪG��U�@�q�G�ĤG�ƿ��i�H���X�A��ܤɯŪ��B�C
Destruction�G�U�ԡA�ĪG��U�@�q�G�ĤG�ƿ��i�H���X�A��ܤɯŪ��B�C

Construction�G
        Projector�]�ɯűשY�^�G��O�]Board�^�B�ȷƱ�]Slide�^�B
                               �����]Cannon�^�B���b�o�g���]Rocket Launcher�^
        Cheese Production�G���n���]For Fun�^�B�~�l�]After-Hours�^�B
                           �M�H�{�]Bakery�^�B���~�u�t�]Food Factory�^
        Production Quality�G��u�]Handmade�^�B�Ҩ�Ͳ��]Cheese Mold�^�B
                            ���~�ƾǡ]Food Chemisty�^�B��]��y�]GMO�^
        Milk Production�G�񪪡]Grazing�^�B�b���]Husbandry�^�B
                         ����ơ]Mechanization�^�B�ͪ��E���]Growth Hormone�^

Destruction�]�u��ߤW���A�C�̦߳h�C�ة�@�ӡ^�G
        �}�a����]Into the Wild�^�G�ߤW�ϥΡA�ߧY�ͮġA�]�ʵe�H�^
        �_�q�]Black-Out�^�G�ߤW�ϥΡA�ߧY�ͮĤ@�p�q�ɶ��A�]�ĤH�e���|�ݤ���H�^
        �ŹT�Y���]Mice Army�^�G�ߤW�ϥΡA�ߧY�ͮĸ����@�q�ɶ�
        �ŹT���X�]Lazy Weekend�^�G�ߤW�ϥΡA�ߧY�ͮĸ����@�q�ɶ�
        ������]Milk Leak�^�G�ߤW�ϥΡA�ߧY�ͮĸ����@�q�ɶ�
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
