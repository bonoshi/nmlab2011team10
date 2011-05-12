package NMLab.team10.rollingthecheese;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

public class ButtomListControl {

    private static final byte CLOSE = 0;
    private static final byte CLOSE_TO_OPEN = 1;
    private static final byte OPEN = 2;
    private static final byte OPEN_TO_CLOSE = 3;

    private byte status;
    private byte statusQueue;
    private int frame;
    private Bitmap buttomListBitmap;
    private Rect dest;
    private int startX;

    private static final int buttomListW = 130;
    private static final int buttomListH = 300;
    private static final int buttomRowN = 15;
    private static final int animationEnd = 10;


    public ButtomListControl(int startX, Bitmap listBitmap){
        status = CLOSE;
        statusQueue = CLOSE;
        frame = 0;
        this .startX = startX;
        this .dest =  new Rect(startX, 0, startX + buttomListW, buttomListH);
        this.buttomListBitmap = listBitmap;

    }

    public void buttomPress(){
        switch(status){
            case CLOSE:
                frame = 0;
                status = CLOSE_TO_OPEN;
                break;
            case CLOSE_TO_OPEN:
                break;
            case OPEN_TO_CLOSE:
                statusQueue = CLOSE_TO_OPEN;
                break;
            case OPEN:

                break;
        }
    }

    public void draw(Canvas canvas){
        Rect src = new Rect(0,0,1,1);

        if(status == CLOSE){
            if(statusQueue == CLOSE)return;
            if(statusQueue == CLOSE_TO_OPEN){
                frame=0;
                src = new Rect(frame*buttomListW,0,(frame+1)*buttomListW,buttomListH);
                status = CLOSE_TO_OPEN;
                statusQueue = CLOSE;
            }
        }
        if(status == OPEN){
            src = new Rect(animationEnd*buttomListW,0,(animationEnd+1)*buttomListW,buttomListH);
        }
        if(status == CLOSE_TO_OPEN){
            src = new Rect(frame*buttomListW,0,(frame+1)*buttomListW,buttomListH);
            if(frame==animationEnd){
                if(statusQueue == OPEN_TO_CLOSE){
                    status = OPEN_TO_CLOSE;
                    statusQueue = CLOSE;
                    frame = 0;
                }else{
                    status = OPEN;
                }
            }else{
                frame++;
            }
        }
        if(status == OPEN_TO_CLOSE){
            src = new Rect(frame*buttomListW,buttomListH,(frame+1)*buttomListW,buttomListH*2);
            if(frame==animationEnd){
                if(statusQueue == CLOSE_TO_OPEN){
                    status = CLOSE_TO_OPEN;
                    statusQueue = CLOSE;
                    frame = 0;
                }else{
                    status = CLOSE;
                }
            }else{
                frame++;
            }
        }
        canvas.drawBitmap(buttomListBitmap,src,dest,null);
    }


    public boolean onTouch(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        Rect touchArea = new Rect(startX,70,startX + buttomListW, buttomListH);
        Rect buttomArea = new Rect(startX,0,startX + buttomListW,70);
        if(touchArea.contains(x,y))return true;
        //if(event.getAction()!=MotionEvent.ACTION_UP)return false;
        if(touchArea.contains(x, y)){
            if(status == OPEN){
                status = OPEN_TO_CLOSE;
                frame = 0;
            }
            if(status == OPEN_TO_CLOSE){
                statusQueue = CLOSE_TO_OPEN;
            }
        }else{
            if(status == CLOSE_TO_OPEN){
                statusQueue = OPEN_TO_CLOSE;
            }
            if(status == OPEN){
                status = OPEN_TO_CLOSE;
                frame = 0;

            }
        }


        return true;
    }
}
