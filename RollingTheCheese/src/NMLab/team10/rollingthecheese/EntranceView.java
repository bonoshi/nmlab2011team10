package NMLab.team10.rollingthecheese;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class EntranceView extends View {
    RollingCheeseActivity father;
    EntranceDrawThread entranceDrawThread;

    public void setPause(boolean pause) {
        entranceDrawThread.setPause(pause);
    }

    public void interrupt(){
        entranceDrawThread.interrupt();
    }

    public void setRunning(boolean running) {
        entranceDrawThread.setRunning(running);
    }

    /*
     * 畫(1) 靜態背景 (2) wait 按鈕 (3) conncet 按鈕 (4) quit 按鈕 (5) enter 按鈕 (6)
     * 某區域顯示目前是否已連線/連線機器名稱、address (7) disconnect 按鈕 (8) 動畫??
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
    static final int BUT_WAIT = 8;
    static final int BUT_CONNECT = 9;
    static final int BUT_BACK = 10;
    static final int BUT_EMPTY = 11;
    static final int BUT_DISCONN = 12;
    static final int BUT_QUIT = 13;

    Bitmap staticBackgroundBitmap;
    /*
     * Bitmap singlePlayerBitmap; Bitmap twoPlayerBitmap; Bitmap groupBitmap;
     * Bitmap backBitmap; Bitmap easyBitmap; Bitmap normalBitmap; Bitmap
     * hardBitmap; Bitmap crazyBitmap; Bitmap connectBitmap; Bitmap startBitmap;
     */
    // TODO
    static final int firstX = 125;
    static final int firstY = 238;
    static final int secondX = 100;
    static final int secondY = 288;
    static final int thirdX = 140;
    static final int thirdY = 337;
    static final int fourthX = 145;
    static final int fourthY = 385;
    static final int backX = 365;
    static final int backY = 424;
    // end TODO
    static final Rect firstRect = new Rect(firstX, firstY, firstX + 200, firstY + 50);
    static final Rect secondRect = new Rect(secondX, secondY, secondX + 200, secondY + 50);
    static final Rect thirdRect = new Rect(thirdX, thirdY, thirdX + 200, thirdY + 50);
    static final Rect fourthRect = new Rect(fourthX, fourthY, fourthX + 200, fourthY + 50);
    static final Rect backRect = new Rect(backX, backY, backX + 80, backY + 30);

    Paint paint1;
    Paint paint2;
    Paint paint3;
    Paint paint4;

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

    public EntranceView(RollingCheeseActivity father) {
        super(father);
        this.father = father;
        initBitmap(father);
        hasLoadedButton = false;
        initRect();
        state = S_12;
        firstPressing = false;
        secondPressing = false;
        thirdPressing = false;
        fourthPressing = false;
        connected = false;
        paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setTextSize(27);
        paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setTextSize(27);
        paint3 = new Paint();
        paint3.setColor(Color.BLACK);
        paint3.setTextSize(17);
        paint4 = new Paint();
        paint4.setColor(Color.RED);
        paint4.setTextSize(17);
        entranceDrawThread = new EntranceDrawThread(this);
        entranceDrawThread.start();
        gotoState(S_12);

    }

    public void initBitmap(Context context) {
        Resources r = context.getResources();
        staticBackgroundBitmap = BitmapFactory.decodeResource(r, R.drawable.new_back);
        /* TODO */
    }

    public void initRect() {
        // TODO
    }

    public boolean hasLoadedButton;

    public void onDraw(Canvas canvas) {
        if(!hasLoadedButton){
            ButtomBar.warmupButton(canvas);
            hasLoadedButton = true;
        }

        canvas.drawBitmap(staticBackgroundBitmap, 0, 0, null);

        if (state == S_12) {
            if (!firstPressing)
                canvas.drawText(firstString, firstX, firstY, paint1);
            else
                canvas.drawText(firstString, firstX, firstY, paint2);
        } else {
            if (!firstPressing)
                canvas.drawText(firstString, firstX + 20, firstY, paint1);
            else
                canvas.drawText(firstString, firstX + 20, firstY, paint2);
        }

        if (state == S_12) {
            if (!secondPressing)
                canvas.drawText(secondString, secondX, secondY, paint1);
            else
                canvas.drawText(secondString, secondX, secondY, paint2);
        } else {
            if (!secondPressing)
                canvas.drawText(secondString, secondX + 35, secondY, paint1);
            else
                canvas.drawText(secondString, secondX + 35, secondY, paint2);
        }
        if (!thirdPressing)
            canvas.drawText(thirdString, thirdX, thirdY, paint1);
        else
            canvas.drawText(thirdString, thirdX, thirdY, paint2);

        if (!fourthPressing)
            canvas.drawText(fourthString, fourthX, fourthY, paint1);
        else
            canvas.drawText(fourthString, fourthX, fourthY, paint2);

        if (!backPressing)
            canvas.drawText(backString, backX, backY, paint3);
        else
            canvas.drawText(backString, backX, backY, paint4);

        entranceDrawThread.cancelDrawflag();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (firstRect.contains(x, y))
                firstPressing = true;
            else
                firstPressing = false;
            if (secondRect.contains(x, y))
                secondPressing = true;
            else
                secondPressing = false;
            if (thirdRect.contains(x, y))
                thirdPressing = true;
            else
                thirdPressing = false;
            if (fourthRect.contains(x, y))
                fourthPressing = true;
            else
                fourthPressing = false;
            if (backRect.contains(x, y))
                backPressing = true;
            else
                backPressing = false;
            entranceDrawThread.setDrawflag();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.e("EntranceView", "onTouchEvent: ACTION_UP");
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (firstRect.contains(x, y)) {
                Log.e("EntranceView", "Single Player selected");
                firstPressing = false;
                this.handleButton(firstButton);
            } else if (secondRect.contains(x, y)) {
                secondPressing = false;
                this.handleButton(secondButton);
            } else if (thirdRect.contains(x, y)) {
                thirdPressing = false;
                this.handleButton(thirdButton);
            } else if (fourthRect.contains(x, y)) {
                fourthPressing = false;
                this.handleButton(fourthButton);
            } else if (backRect.contains(x, y)) {
                backPressing = false;
                this.handleButton(backButton);
            }
            entranceDrawThread.setDrawflag();
        }
        return true;
    }

    public void back() {
        this.handleButton(backButton);
    }

    public void handleButton(int b) {
        switch (b) {
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
            case BUT_EASY: {
                father.toastMessage.addMessage("Easy");
                father.toastMessage.display();
                // Toast test = Toast.makeText(father, "Test\nTest", 0);
                // test.setGravity(Gravity.CENTER, 0, 0);
                // test.show();
                break;
            }
            case BUT_NORMAL: {
                // father.ToastMessage.addMessage("Normal");
                // father.ToastMessage.display();
                father.toastMessage.cancel();
                // Toast test = Toast.makeText(father, "Test\nTest", 0);
                // test.setGravity(Gravity.CENTER, 100, 100);
                // test.show();
                break;
            }
            case BUT_HARD: {
                father.toastMessage.addMessage("Hard");
                father.toastMessage.display();
                // Toast test = Toast.makeText(father, "Test\nTest", 0);
                // test.setGravity(Gravity.CENTER, 200, 200);
                // test.show();
                break;
            }
            case BUT_CRAZY:
                father.myHandler.sendEmptyMessage(InterThreadMsg.startCrazyGame);
                break;
            case BUT_WAIT:
                father.myHandler.sendEmptyMessage(InterThreadMsg.serverWait);
                break;
            case BUT_CONNECT:
                father.myHandler.sendEmptyMessage(InterThreadMsg.clientConnect);
                break;
            /*
             * case BUT_START:
             * father.myHandler.sendEmptyMessage(InterThreadMsg.startGameView);
             * break;
             */
            case BUT_BACK:
                if (state == S_1 || state == S_2) {
                    gotoState(S_12);
                } else if (state == S_12) {
                    new AlertDialog.Builder(father).setMessage("Are you sure to leave?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Log.e("entranceView", "user LEAVE game");
                                    father.myHandler.sendEmptyMessage(InterThreadMsg.endGame);
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing
                                }
                            }).show();
                }
                break;
            case BUT_EMPTY:
                break;
        }
        entranceDrawThread.setDrawflag();
    }

    public void gotoState(int s) {
        switch (s) {
            case S_12:
                firstButton = BUT_SINGLE;
                secondButton = BUT_TWO;
                thirdButton = BUT_SET;
                fourthButton = BUT_GROUP;
                backButton = BUT_BACK;
                firstString = "Single Player";
                secondString = "Two Player";
                thirdString = "Settings";
                fourthString = "Credits";
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
                firstButton = BUT_WAIT;
                secondButton = BUT_CONNECT;
                thirdButton = BUT_EMPTY;
                fourthButton = BUT_EMPTY;
                backButton = BUT_BACK;
                firstString = "Wait";
                secondString = "Connect";
                thirdString = "";
                fourthString = "";
                backString = "BACK";
                state = S_2;
                break;
        }

        entranceDrawThread.setDrawflag();
    }

    public void becomeConnected() {
        connected = true;
        gotoState(S_2);
    }

    public void becomeDisconnected() {
        connected = false;
        gotoState(S_2);
    }
}
