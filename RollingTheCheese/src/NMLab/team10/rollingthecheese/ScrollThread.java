package NMLab.team10.rollingthecheese;

import android.util.Log;

public class ScrollThread extends Thread {
    private static final int leftEdge = -800;
    private static final int rightEdge = 0;
    private static final int quantizeConst = 20;

    public boolean isPressing;
    public boolean prev_isPressing;
    public boolean isRecovering;
    public double V;
    public double recover_a;
    public int posX;
    public int fingerX;
    public int prev_fingerX;
    public long prev_time;

    public ScrollThread() {
        prev_isPressing = false;
        isPressing = false;
        isRecovering = false;
        posX = 0;
        recover_a = 0;
        V = 0;
        fingerX = 0;
        prev_fingerX = 0;
        prev_time = 0;
    }

    private int decadeDrag() {
        if (posX > rightEdge + 3 * quantizeConst
                || posX < leftEdge - 3 * quantizeConst) {
            return 0;
        } else {
            return (fingerX - prev_fingerX) / 10;
        }
    }

    public void run() {
        
        long cur_time;
        long delta_time;
        while (true) {
                //Log.e("V",String.format("%f", V));
                cur_time = System.nanoTime();
                delta_time = cur_time - prev_time;
                prev_time = cur_time;
                delta_time/=1000000;
                

                if (isPressing) {                   
                        if ((posX > rightEdge && fingerX - prev_fingerX > 0)
                                || (posX < leftEdge && fingerX
                                        - prev_fingerX < 0)) {
                            posX += decadeDrag();
                            if (posX > rightEdge + 3 * quantizeConst){
                                posX = rightEdge + 3* quantizeConst;
                            }
                            if( posX < leftEdge - 3 * quantizeConst) {
                                posX = leftEdge - 3* quantizeConst;
                            } 
                        } else {
                            posX += (fingerX - prev_fingerX);
                        }

                        prev_fingerX = fingerX;
                } else { 
                    if (!isRecovering) {  
                        if (posX > rightEdge && V >= 0) {
                            V = 0.004 * (double) (posX - rightEdge);
                            recover_a = -0.0008 * (double) (posX - rightEdge);
                            isRecovering = true;
                        } else if (posX < leftEdge && V <= 0) {
                            V = 0.004 * (double) (posX - leftEdge);
                            recover_a = -0.0008 * (double) (posX - leftEdge);
                            isRecovering = true;
                        }
                    }
                    posX += (V * delta_time) + 0.5 * recover_a * delta_time
                    * delta_time;
                    if (V * (V + recover_a * delta_time) < 0) {
                        isRecovering = false;
                        recover_a = 0;
                        V = 0;
                    } else {
                         V += recover_a * delta_time;
                    }
                    if (posX > rightEdge + 3 * quantizeConst){
                        posX = rightEdge + 3* quantizeConst;
                    }
                    if( posX < leftEdge - 3 * quantizeConst) {
                        posX = leftEdge - 3* quantizeConst;
                    }

                }
            
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }

    }
}
