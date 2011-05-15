package NMLab.team10.rollingthecheese.displayData;

import java.util.ArrayList;
import java.util.LinkedList;

import android.R.drawable;
import android.R.integer;
import android.app.PendingIntent.CanceledException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.gameSetting.CowMessage;

public class CowDisplay{
    static final int[][] normalPosY = {{0,0,0,0,0,0},
                                 {160,0,0,0,0,0},
                                 {110,210,0,0,0,0},
                                 {90,160,230,0,0,0},
                                 {70,130,200,260,0,0},
                                 {60,110,160,210,260,0}};
    static final int[][] normalPosXLeft = {{0,0,0,0,0,0},
                                           {0,0,0,0,0,0},
                                           {0,0,0,0,0,0},
                                           {0,0,0,0,0,0},
                                           {0,0,0,0,0,0},
                                           {0,0,0,0,0,0}};
    static final int[][] normalPosXRight = {{0,0,0,0,0,0},
                                            {350,0,0,0,0,0},
                                            {440,270,0,0,0,0},
                                            {475,350,225,0,0,0},
                                            {500,400,300,200,0,0},
                                            {525,440,355,270,185,0}};
    static GameView gameView;
    static int numCow;
    static LinkedList<CowDisplay> leftCowList;
    static Bitmap ToRightCowBitmap;
    static Bitmap ToLeftCowBitmap;
    
    /*TODO*/
    static final float PosYAdjustConst = 0.1F;
    static final float ProbBack = 0;
    static final int COW_WIDTH = 60;
    static final int COW_HEIGHT = 30;
    /*end TODO*/
    
    static public void initial(){
        /*TODO*/
        Resources r = GameView.r;
        ToRightCowBitmap = BitmapFactory.decodeResource(r, R.drawable.cowright);
        ToLeftCowBitmap = BitmapFactory.decodeResource(r, R.drawable.cowleft);
    }
    static public void initialGameView(GameView gameView){
        CowDisplay.gameView = gameView;
    }
    
    static public void sortCowListbyID(LinkedList<CowDisplay> leftCowList){
        for(int i =0;i<leftCowList.size();i++){
            int maxID = -1;
            int maxIndex = -1;
            for(int j=0;j<leftCowList.size();j++){
                if(leftCowList.get(j).getCowMessage().getID() > maxID){
                    maxID = leftCowList.get(j).getCowMessage().getID();
                    maxIndex = j;
                }
            }
            CowDisplay tmp = leftCowList.remove(maxIndex);
            leftCowList.addFirst(tmp);
        }
    }
    
    static public void updateCowDisplay(){
        leftCowList = (LinkedList<CowDisplay>)gameView.displayData.leftCowList.clone();
        numCow = leftCowList.size();
        sortCowListbyID(leftCowList);
        for(int i = 0;i<leftCowList.size();i++){
           leftCowList.get(i).updateCow(numCow,i);
        }
    }
    static public void draw(Canvas canvas, int offset){
        updateCowDisplay();
        for(int i =0; i< leftCowList.size();i++){
            leftCowList.get(i).drawCow(canvas, offset);
        }
    }
    
    float PosY;
    float velocityX;
    float velocityY;
    float PosX;
    
    public void updateCow(int numCow, int order){
        // adjust PosY
        float deltaY = normalPosY[numCow][order] - PosY;
        velocityY = deltaY*PosYAdjustConst;
        PosY = PosY + velocityY;
        
        // adjust PosX
        PosX = PosX + velocityX;
        if(PosX < normalPosXLeft[numCow][order])
            velocityX = Math.abs(velocityX);
        if(PosX > normalPosXRight[numCow][order])
            velocityX = -Math.abs(velocityX);
    }
    
    public void drawCow(Canvas canvas, int offset){
        RectF dest = new RectF(PosX-COW_WIDTH/2+offset,PosY-COW_HEIGHT/2,
                PosX+COW_WIDTH/2 + offset, PosY + COW_HEIGHT/2);
        if(velocityX < 0)
           canvas.drawBitmap(ToLeftCowBitmap, null, dest, null);
        else canvas.drawBitmap(ToRightCowBitmap, null, dest, null);
    }
    
    public CowDisplay(CowMessage cm) {
        this.setCowMessage(cm);
        /*initialize Pos, velocity*/
    }
    public void setCowMessage(CowMessage cowMessage) {
        this.cowMessage = cowMessage;
    }
    public CowMessage getCowMessage() {
        return cowMessage;
    }
    private CowMessage cowMessage;
}
