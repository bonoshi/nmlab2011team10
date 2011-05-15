package NMLab.team10.rollingthecheese.displayData;

import java.util.LinkedList;
import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import NMLab.team10.rollingthecheese.GameView;
import NMLab.team10.rollingthecheese.R;
import NMLab.team10.rollingthecheese.gameSetting.Cow;
import NMLab.team10.rollingthecheese.gameSetting.CowMessage;

public class CowDisplay {
    static final int[][] normalPosY = { { 0, 0, 0, 0, 0, 0 }, { 200, 0, 0, 0, 0, 0 },
            { 170, 230, 0, 0, 0, 0 }, { 150, 200, 250, 0, 0, 0 }, { 140, 180, 220, 260, 0, 0 },
            { 133, 166, 200, 233, 266, 0 } };
    static final int[][] normalPosXLeft = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
    static final int[][] normalPosXRight = { { 0, 0, 0, 0, 0, 0 }, { 260, 0, 0, 0, 0, 0 },
            { 290, 250, 0, 0, 0, 0 }, { 330, 270, 225, 0, 0, 0 }, { 370, 300, 240, 180, 0, 0 },
            { 400, 340, 280, 220, 160, 0 } };
    static final int normalVelocityX = 5;
    static GameView gameView;
    static int numCow;
    static LinkedList<CowDisplay> leftCowList = new LinkedList<CowDisplay>();
    static LinkedList<CowDisplay> rightCowList = new LinkedList<CowDisplay>();
    static Bitmap ToRightCowBitmap;
    static Bitmap ToLeftCowBitmap;
    static Bitmap ToRightCowBitmap_p;
    static Bitmap ToLeftCowBitmap_p;

    /* TODO */
    static final float PosYAdjustConst = 0.03F;
    static final float ProbBack = 0;
    static final int COW_WIDTH = 180;
    static final int COW_HEIGHT = 150;

    /* end TODO */

    static public void initial() {
        /* TODO */
        Resources r = GameView.r;
        ToRightCowBitmap = BitmapFactory.decodeResource(r, R.drawable.cowright);
        ToLeftCowBitmap = BitmapFactory.decodeResource(r, R.drawable.cowleft);
        ToRightCowBitmap_p = BitmapFactory.decodeResource(r, R.drawable.cowright_poison);
        ToLeftCowBitmap_p = BitmapFactory.decodeResource(r, R.drawable.cowleft_poison);
    }

    static public void initialGameView(GameView gameView) {
        CowDisplay.gameView = gameView;
    }

    static public void sortCowListbyID() {
        for (int i = 0; i < leftCowList.size(); i++) {
            int maxID = -1;
            int maxIndex = -1;
            for (int j = 0; j < leftCowList.size(); j++) {
                if (leftCowList.get(j).getCowMessage().getID() > maxID) {
                    maxID = leftCowList.get(j).getCowMessage().getID();
                    maxIndex = j;
                }
            }
            CowDisplay tmp = leftCowList.remove(maxIndex);
            leftCowList.addFirst(tmp);
        }
        for (int i = 0; i < rightCowList.size(); i++) {
            int maxID = -1;
            int maxIndex = -1;
            for (int j = 0; j < rightCowList.size(); j++) {
                if (rightCowList.get(j).getCowMessage().getID() > maxID) {
                    maxID = rightCowList.get(j).getCowMessage().getID();
                    maxIndex = j;
                }
            }
            CowDisplay tmp = rightCowList.remove(maxIndex);
            rightCowList.addFirst(tmp);
        }
    }

    static public void sortCowListbyPosY() {
        for (int i = 0; i < leftCowList.size(); i++) {
            float minY = 999;
            int minIndex = -1;
            for (int j = 0; j < leftCowList.size(); j++) {
                if (leftCowList.get(j).PosY < minY) {
                    minY = leftCowList.get(j).PosY;
                    minIndex = j;
                }
            }
            CowDisplay tmp = leftCowList.remove(minIndex);
            leftCowList.addFirst(tmp);
        }
        for (int i = 0; i < rightCowList.size(); i++) {
            float minY = 999;
            int minIndex = -1;
            for (int j = 0; j < rightCowList.size(); j++) {
                if (rightCowList.get(j).PosY < minY) {
                    minY = rightCowList.get(j).PosY;
                    minIndex = j;
                }
            }
            CowDisplay tmp = rightCowList.remove(minIndex);
            rightCowList.addFirst(tmp);
        }
    }

    @SuppressWarnings("unchecked")
    static public void updateCowDisplay() {
        leftCowList = (LinkedList<CowDisplay>) GameView.displayData.leftCowList.clone();
        numCow = leftCowList.size();
        // Log.e("CowDisplay",String.format("numCow = %d",numCow));
        sortCowListbyID();
        for (int i = 0; i < leftCowList.size(); i++) {
            leftCowList.get(i).updateCow(numCow, i);
        }
        for (int i = 0; i < rightCowList.size(); i++) {
            rightCowList.get(i).updateCow(numCow, i);
        }
    }

    static public void draw(Canvas canvas, int offset) {
        updateCowDisplay();
        sortCowListbyPosY();
        for (int i = 0; i < leftCowList.size(); i++) {
            CowDisplay cow = leftCowList.get(i);
            if (cow.getCowMessage().getStatus() == Cow.Normal) {
                cow.drawCow(canvas, offset, false, false);
            } else {
                cow.drawCow(canvas, offset, false, true);
            }
        }
        for (int i = 0; i < rightCowList.size(); i++) {
            CowDisplay cow = rightCowList.get(i);
            if (cow.getCowMessage().getStatus() == Cow.Normal) {
                cow.drawCow(canvas, offset, true, false);
            } else {
                cow.drawCow(canvas, offset, true, true);
            }
        }
    }

    float PosY;
    float velocityX;
    float velocityY;
    float PosX;

    public void updateCow(int numCow, int order) {
        // adjust PosY
        float deltaY = normalPosY[numCow][order] - PosY;
        velocityY = deltaY * PosYAdjustConst;
        PosY = PosY + velocityY;

        // adjust PosX
        PosX = PosX + velocityX;
        if (PosX < normalPosXLeft[numCow][order])
            velocityX = Math.abs(velocityX);
        if (PosX > normalPosXRight[numCow][order])
            velocityX = -Math.abs(velocityX);
    }

    public void drawCow(Canvas canvas, int offset, boolean leftright, boolean isPoison) {
        if (leftright == false) {
            RectF dest = new RectF(PosX - COW_WIDTH / 2 + offset, PosY - COW_HEIGHT / 2, PosX + COW_WIDTH / 2
                    + offset, PosY + COW_HEIGHT / 2);
            if (velocityX < 0)
                if (isPoison) {
                    canvas.drawBitmap(ToLeftCowBitmap_p, null, dest, null);
                } else {
                    canvas.drawBitmap(ToLeftCowBitmap, null, dest, null);
                }
            else {
                if (isPoison) {
                    canvas.drawBitmap(ToRightCowBitmap_p, null, dest, null);
                } else {
                    canvas.drawBitmap(ToRightCowBitmap, null, dest, null);
                }
            }
        } else {
            // TODO
        }
    }

    public CowDisplay(CowMessage cm) {
        this.setCowMessage(cm);
        velocityX = normalVelocityX;
        PosX = 100;
        int ID = cm.getID();
        PosY = normalPosY[Math.max(5, numCow + 1)][ID];
        /* initialize Pos, velocity */
    }

    public void setCowMessage(CowMessage cowMessage) {
        this.cowMessage = cowMessage;
    }

    public CowMessage getCowMessage() {
        return cowMessage;
    }

    public CowMessage cowMessage;

    // //////////////////// bobuway /////////////////
    static Random random = new Random();

    static public void debug_addCow() {
        // SoundController.playSound(SoundController.EFF_COLLISION1,0);
        /*
         * boolean add = false; if(leftCowList.size() >= 5) return; while(!add){
         * short rr = (short)(random.nextInt(5)); boolean found = false; for(int
         * i = 0;i<leftCowList.size();i++)
         * if(leftCowList.get(i).cowMessage.getID() == rr) found = true;
         * if(!found){ add = true; leftCowList.addLast(new CowDisplay(new
         * CowMessage(rr))); } }
         */
    }

    static public void debug_deleteCow() {
        /*
         * if(leftCowList.size() > 0){ leftCowList.remove(0); }
         */
    }
    // ////////////////////bobuway ///////////////////
}
