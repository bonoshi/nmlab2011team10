package NMLab.team10.rollingthecheese;

import java.util.ArrayList;
import java.util.HashMap;


import NMLab.team10.rollingthecheese.event.EventQueueCenter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class ListContTwoButtom {

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
    private ArrayList<Rect> buttoms;
    private HashMap<Rect, Byte> buttom_function;

    private static final int buttomListW = 130;
    private static final int buttomListH = 300;
    private static final int buttomH = 85;
    private static final int animationEnd = 10;
    private static final int buttom1y1 = 100;
    private static final int buttom1y2 = 160;
    private static final int buttom2y1 = 190;
    private static final int buttom2y2 = 290;
    private EventQueueCenter eqc;


    public ListContTwoButtom(int startX, Bitmap listBitmap,EventQueueCenter eqc,Byte event1,Byte event2){
        //initial state

        status = CLOSE;
        statusQueue = CLOSE;
        frame = 0;


        //initial data members
        this.startX = startX;
        this.dest =  new Rect(startX, 0, startX + buttomListW, buttomListH);
        this.buttomListBitmap = listBitmap;
        this.eqc = eqc;

        //initial buttoms
        buttoms = new ArrayList<Rect>();
        buttom_function = new HashMap<Rect, Byte>();

        Rect rect = new Rect(startX,buttom1y1,startX+buttomListW,buttom1y2);
        buttoms.add(rect);
        buttom_function.put(rect, event1);
        rect = new Rect(startX, buttom2y1,startX+buttomListW,buttom2y2);

        buttoms.add(rect);
        buttom_function.put(rect, event2);
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
                frame=0;
                status = OPEN_TO_CLOSE;
                break;
        }
    }

    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, 1, 1);

        if (status == CLOSE) {
            if (statusQueue == CLOSE)
                return;
            if (statusQueue == CLOSE_TO_OPEN) {
                frame = 0;
                src = new Rect(frame * buttomListW, 0, (frame + 1) * buttomListW, buttomListH);
                status = CLOSE_TO_OPEN;
                statusQueue = CLOSE;
            }
        }
        if (status == OPEN) {
            src = new Rect(animationEnd * buttomListW, 0, (animationEnd + 1) * buttomListW, buttomListH);
        }
        if (status == CLOSE_TO_OPEN) {
            src = new Rect(frame * buttomListW, 0, (frame + 1) * buttomListW, buttomListH);
            if (frame == animationEnd) {
                if (statusQueue == OPEN_TO_CLOSE) {
                    status = OPEN_TO_CLOSE;
                    statusQueue = CLOSE;
                    frame = 0;
                } else {
                    status = OPEN;
                }
            } else {
                frame++;
            }
        }
        if (status == OPEN_TO_CLOSE) {
            src = new Rect(frame * buttomListW, buttomListH, (frame + 1) * buttomListW, buttomListH * 2);
            if (frame == animationEnd) {
                if (statusQueue == CLOSE_TO_OPEN) {
                    status = CLOSE_TO_OPEN;
                    statusQueue = CLOSE;
                    frame = 0;
                } else {
                    status = CLOSE;
                }
            } else {
                frame++;
            }
        }
        canvas.drawBitmap(buttomListBitmap, src, dest, null);
        /*if (status == OPEN) {
            src = new Rect(animationEnd * buttomListW, 0, (animationEnd + 1) * buttomListW, buttomListH);
        }*/
    }



    public boolean onTouch(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        Rect touchArea = new Rect(startX,buttomH,startX + buttomListW, buttomListH);
        Rect buttomArea = new Rect(startX,0,startX+buttomListW,buttomH);
        if(buttomArea.contains(x,y))return false;
        if(touchArea.contains(x, y)){
            if(status == OPEN){

                for (Rect r : buttoms) {
                    if (r.contains(x, y)) {
                        eqc.addEvent(buttom_function.get(r));
                        return true;
                    }
                }
            }
        } else {
            if (status == CLOSE_TO_OPEN) {
                statusQueue = OPEN_TO_CLOSE;
            }
            if (status == OPEN) {
                status = OPEN_TO_CLOSE;
                frame = 0;
            }
        }
        return true;
    }
}
