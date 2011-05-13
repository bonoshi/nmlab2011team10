package NMLab.team10.rollingthecheese;

import java.util.ArrayList;
import java.util.HashMap;

import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class ListContFourButtom {
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
    private HashMap<Rect,Byte> buttom_function;

    private static final int buttomListW = 300;
    private static final int buttomListH = 300;
    private static final int buttom1y1 = 105;
    private static final int buttom1y2 = 175;
    private static final int buttom2y1 = 200;
    private static final int buttom2y2 = 275;
    private static final int buttomH = 85;
    private EventQueueCenter eqc;
    
    public ListContFourButtom(int startX, Bitmap listBitmap,EventQueueCenter eqc,Byte event1,Byte event2,Byte event3, Byte event4){
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
        Rect rect = new Rect(startX+10,buttom1y1,startX+150,buttom1y2);
        buttoms.add(rect);
        buttom_function.put(rect, event1);
        rect = new Rect(startX+151,buttom1y1,startX+290,buttom1y2);
        buttoms.add(rect);
        buttom_function.put(rect, event2);
        rect = new Rect(startX+10, buttom2y1,startX+150,buttom2y2);
        buttoms.add(rect);
        buttom_function.put(rect, event3);
        rect = new Rect(startX+151, buttom2y1,startX+290,buttom2y2);
        buttoms.add(rect);
        buttom_function.put(rect, event4);
    }
    
    public void draw(Canvas canvas){
        Rect src = new Rect(0,0,1,1);//dummy

        if(status == CLOSE){
            if(statusQueue == CLOSE)return;
            if(statusQueue == CLOSE_TO_OPEN){
                frame=0;
                src = new Rect(0,0,buttomListW,buttomListH);
                status = CLOSE_TO_OPEN;
                statusQueue = CLOSE;
            }
        }
        if(status == OPEN){
            src = new Rect(0,buttomListH*2,buttomListW,buttomListH*3);
        }
        if(status == CLOSE_TO_OPEN){
            if(frame<5){
                src = new Rect(frame*buttomListW,0,(frame+1)*buttomListW,buttomListH);
                frame++;
            }else if(frame<10){
                src = new Rect((frame-5)*buttomListW,buttomListH,(frame-5+1)*buttomListW,buttomListH*2);
                frame++;
            }else if(frame==10){
                src = new Rect(0,buttomListH*2,buttomListW,buttomListH*3);
                if(statusQueue == OPEN_TO_CLOSE){
                    status = OPEN_TO_CLOSE;
                    statusQueue = CLOSE;
                    frame = 0;
                }else{
                    status = OPEN;
                }
            }
        }
        if(status == OPEN_TO_CLOSE){
            if(frame<4){
                src = new Rect(frame+1*buttomListW,buttomListH*2,(frame+2)*buttomListW,buttomListH*3);
                frame++;
            }else if(frame <9){
                src = new Rect((frame-4)*buttomListW,buttomListH*3,(frame-4+1)*buttomListW,buttomListH*4);
                frame++;
            }else if(frame == 9){
                src = new Rect(0,buttomListH*4,buttomListW,buttomListH*5);
                frame++;
            }else if(frame ==10){
                src = new Rect(buttomListW,buttomListH*4,2*buttomListW,buttomListH*5);
                if(statusQueue == CLOSE_TO_OPEN){
                    status = CLOSE_TO_OPEN;
                    statusQueue = CLOSE;
                    frame = 0;
                }else{
                    status = CLOSE;
                }
            }
            
        }
        canvas.drawBitmap(buttomListBitmap,src,dest,null);
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
    
    public boolean onTouch(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        Rect touchArea = new Rect(startX+10,buttomH,startX+290, buttomListH);
               
               
        if(touchArea.contains(x, y)){
            if(status == OPEN){
                for (Rect r : buttoms) {
                    if(r.contains(x,y)){
                        eqc.addEvent(buttom_function.get(r));
                        return true;
                    }
                }
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

