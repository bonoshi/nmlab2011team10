package NMLab.team10.rollingthecheese;


import android.R.integer;
import android.R.string;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class EntranceView extends View{
    RollingCheeseActivity father;
    EntranceDrawThread entranceDrawThread;
    EntranceThread entranceThread;
    
    /* 畫(1) 靜態背景
     * (2) wait 按鈕
     * (3) conncet 按鈕
     * (4) quit 按鈕
     * (5) enter 按鈕
     * (6) 某區域顯示目前是否已連線/連線機器名稱、address
     * (7) disconnect 按鈕
     * (8) 動畫??
     */
    
    static final int S_12 = 0;
    static final int S_1 = 1;
    static final int S_2 = 2;
    static final int BUT_SINGLE = 0;
    static final int BUT_TWO = 1;
    static final int BUT_SET = 2;
    static final int BUT_GROUP = 3;
    static final int BUT_EASY = 4;
    static final int BUT_NORMAL = 5;
    static final int BUT_HARD = 6;
    static final int BUT_CRAZY = 7;
    static final int BUT_CONN = 8;
    //static final int BUT_START = 9;
    static final int BUT_BACK = 10;
    static final int BUT_EMPTY = 11;
    static final int BUT_DISCONN = 12;
    static final int BUT_QUIT = 13;
    
    Bitmap staticBackgroundBitmap;
    /*Bitmap singlePlayerBitmap;
    Bitmap twoPlayerBitmap;
    Bitmap groupBitmap;
    Bitmap backBitmap;
    Bitmap easyBitmap;
    Bitmap normalBitmap;
    Bitmap hardBitmap;
    Bitmap crazyBitmap;
    Bitmap connectBitmap;
    Bitmap startBitmap;*/
 // TODO
    static final int firstX = 200;
    static final int firstY = 230;
    static final int secondX = 200;
    static final int secondY = 285;
    static final int thirdX = 200;
    static final int thirdY = 340;
    static final int fourthX = 200;
    static final int fourthY = 395;
    static final int backX = 380;
    static final int backY = 435;
    // end TODO
    static final Rect firstRect = new Rect(firstX, firstY, firstX+100,firstY+50);
    static final Rect secondRect = new Rect(secondX, secondY, secondX+100,secondY+50);
    static final Rect thirdRect = new Rect(thirdX, thirdY, thirdX+100,thirdY+50);
    static final Rect fourthRect = new Rect(fourthX, fourthY, fourthX+100,fourthY+50);
    static final Rect backRect = new Rect(backX, backY, backX+50,backY+30);
    
    Paint paint1;
    Paint paint2;
 
    
    int state = S_12;
    boolean firstPressing;
    boolean secondPressing;
    boolean thirdPressing;
    boolean fourthPressing;
    boolean backPressing;
    String firstString;
    String secondString;
    String thirdString;
    String fourthString;
    String backString;
    int firstButton;
    int secondButton;
    int thirdButton;
    int fourthButton;
    int backButton;
    
    boolean connected;
     
    public EntranceView(RollingCheeseActivity father){
        super(father);
        this.father = father;
        initBitmap(father);
        initRect();
        state = S_12;
        firstPressing = false;
        secondPressing = false;
        thirdPressing = false;
        fourthPressing = false;
        connected = false;
        paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setTextSize(35);
        paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setTextSize(35);
        entranceDrawThread = new EntranceDrawThread(this);
        entranceDrawThread.start();
        gotoState(S_12);
        
    }
    public void initBitmap(Context context){
        Resources r = context.getResources();
        staticBackgroundBitmap = BitmapFactory.decodeResource(r, R.drawable.new_back);
        /* TODO */
    }
    public void initRect(){
        // TODO
    }
    
    public void onDraw(Canvas canvas){
        canvas.drawBitmap(staticBackgroundBitmap , 0, 0, null);
        if(!firstPressing) 
            canvas.drawText(firstString, firstX, firstY, paint1);
        else canvas.drawText(firstString, firstX, firstY, paint2);
        
        if(!secondPressing) 
            canvas.drawText(secondString, secondX, secondY, paint1);
        else canvas.drawText(secondString, secondX, secondY, paint2);
        
        if(!thirdPressing) 
            canvas.drawText(thirdString, thirdX, thirdY, paint1);
        else canvas.drawText(thirdString, thirdX, thirdY, paint2);
        
        if(!fourthPressing) 
            canvas.drawText(fourthString, fourthX, fourthY, paint1);
        else canvas.drawText(fourthString, fourthX, fourthY, paint2);
        
        if(!backPressing)
            canvas.drawText(backString, backX, backY, paint1);
        else canvas.drawText(backString, backX, backY, paint2);
        
        entranceDrawThread.cancelDrawflag();
    }
    
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
            int x = (int)event.getX();
            int y = (int)event.getY();
            if(firstRect.contains(x, y)) firstPressing = true;
            else firstPressing = false;
            if(secondRect.contains(x, y)) secondPressing = true;
            else secondPressing = false;
            if(thirdRect.contains(x, y)) thirdPressing = true;
            else thirdPressing = false;
            if(fourthRect.contains(x, y)) fourthPressing = true;
            else fourthPressing = false;
            if(backRect.contains(x, y)) backPressing = true;
            else backPressing = false;
            entranceDrawThread.setDrawflag();
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            Log.e("EntranceView","onTouchEvent: ACTION_UP");
            int x = (int)event.getX();
            int y = (int)event.getY();
            if(firstRect.contains(x, y)){
                Log.e("EntranceView","Single Player selected");
                firstPressing = false;
                this.handleButton(firstButton);
            }else if(secondRect.contains(x, y)){
                secondPressing = false;
                this.handleButton(secondButton);
            }else if(thirdRect.contains(x,y)){
                thirdPressing = false;
                this.handleButton(thirdButton);
            }else if(fourthRect.contains(x,y)){
                fourthPressing = false;
                this.handleButton(fourthButton);
            }else if(backRect.contains(x,y)){
                backPressing = false;
                this.handleButton(backButton);
            }
            entranceDrawThread.setDrawflag();
        }
        return true;
    }
    
    public void handleButton(int b){
        switch(b){
            case BUT_SINGLE:
                gotoState(S_1);
                break;
            case BUT_TWO:
                gotoState(S_2);
                break;
            case BUT_SET:
                break;
            case BUT_GROUP:
                break;
            case BUT_EASY:
            case BUT_NORMAL:
            case BUT_HARD:
            case BUT_CRAZY:
                father.myHandler.sendEmptyMessage(InterThreadMsg.serverStartGameView);
                break;
            case BUT_CONN:
                father.myHandler.sendEmptyMessage(InterThreadMsg.connect);
                break;
            /*case BUT_START:
                father.myHandler.sendEmptyMessage(InterThreadMsg.startGameView);
                break;*/
            case BUT_BACK:
                if(state == S_1 || state == S_2){
                    gotoState(S_12);
                }
                else if(state == S_12){
                    Log.e("entranceView","user LEAVE game");
                    father.myHandler.sendEmptyMessage(InterThreadMsg.endGame);
                }
                break;
            case BUT_EMPTY:
                break;
        }
        entranceDrawThread.setDrawflag();
    }
    
    public void gotoState(int s){
        switch(s){
            case S_12:
                firstButton = BUT_SINGLE;
                secondButton = BUT_TWO;
                thirdButton = BUT_SET;
                fourthButton = BUT_GROUP;
                backButton = BUT_BACK;
                firstString = "Single Player";
                secondString = "Two Player";
                thirdString = "Settings";
                fourthString = "Group";
                backString = "QUIT";
                state = S_12;
                break;
            case S_1:
                firstButton = BUT_EASY;
                secondButton = BUT_NORMAL;
                thirdButton = BUT_HARD;
                fourthButton = BUT_CRAZY;
                backButton = BUT_BACK;
                firstString = "Easy";
                secondString = "Normal";
                thirdString = "Hard";
                fourthString = "Crazy";
                backString = "BACK";
                state = S_1;
                
                break;
            case S_2:
                if(!connected){
                   firstButton = BUT_CONN;
                   firstString = "Connect";
                }else {
                   firstButton = BUT_DISCONN;
                   firstString = "Disconnect";
                }
                secondButton = BUT_EMPTY;
                thirdButton = BUT_EMPTY;
                fourthButton = BUT_EMPTY;
                backButton = BUT_BACK;
                secondString = "Start";
                thirdString = "";
                fourthString = "";
                backString = "BACK";
                state = S_2;
                break;
        }
        
        entranceDrawThread.setDrawflag();
    }
    
    public void becomeConnected(){
        connected = true;
        gotoState(S_2);
    }
    public void becomeDisconnected(){
        connected = false;
        gotoState(S_2);
    }
}
