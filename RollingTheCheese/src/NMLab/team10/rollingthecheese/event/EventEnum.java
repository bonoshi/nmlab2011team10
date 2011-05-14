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
