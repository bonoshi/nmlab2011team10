package NMLab.team10.rollingthecheese;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class ButtomControl {
    private boolean isMoving;
    private int frame;
    private Bitmap buttomBitmap;
    private Rect dest;
    private int startX;

    private static final int buttomW = 110;
    private static final int buttomH = 85;
    private static final int buttomAniRowN = 15;
    private static final int aniNum = 30;



    public ButtomControl(int startX, Bitmap buttomBitmap){
        isMoving = false;
        frame = 0;
        this .startX = startX;
        this .dest =  new Rect(startX, 0, startX + buttomW, buttomH);
        this.buttomBitmap = buttomBitmap;

    }

    public void draw(Canvas canvas){
        Rect src = new Rect();
        if(isMoving){
            if(frame<aniNum){
                if(frame<buttomAniRowN){
                    src = new Rect(frame*buttomW,0,(frame+1)*buttomW,buttomH);
                }else{
                    int frame2 = frame - buttomAniRowN;
                    src = new Rect(frame2*buttomW,buttomH,(frame2+1)*buttomW,buttomH*2);
                }
                frame++;
            }else{
                isMoving = false;
                frame=0;
                src = new Rect(0,0,buttomW,buttomH);
            }
        }else{
            src = new Rect(0,0,buttomW,buttomH);
        }
        canvas.drawBitmap(buttomBitmap,src,dest,null);
    }


    public boolean onTouch(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();

        if(dest.contains(x, y)){
            frame =0;
            isMoving = true;
            return true;
        }
        return false;
    }
}
