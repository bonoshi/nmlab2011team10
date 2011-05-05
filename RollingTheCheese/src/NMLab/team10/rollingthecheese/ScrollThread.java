package NMLab.team10.rollingthecheese;

import android.util.Log;

public class ScrollThread extends Thread {
    private static final int leftEdge = -800;
    private static final int rightEdge = 0;
    private static final int quantizeConst = 40;

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
                Log.e("V",String.format("%f", V));
                cur_time = System.nanoTime();
                delta_time = cur_time - prev_time;
                prev_time = cur_time;
                if (isPressing) {
                    if (prev_isPressing == false) { // run when finger down
                        prev_isPressing = true;
                        prev_fingerX = fingerX;
                        isRecovering = false;
                        recover_a = 0;
                        V = 0;
                    } else { // run when move
                        if ((posX > rightEdge && fingerX - prev_fingerX > 0)
                                || (posX < leftEdge && fingerX
                                        - prev_fingerX < 0)) {
                            posX += decadeDrag();
                        } else {
                            posX += (fingerX - prev_fingerX);
                        }
                       /* V = (double) (fingerX - prev_fingerX)
                                / (double) delta_time;
                        if (V > 0.0000002)
                            V = 0.0000002;*/
                        prev_fingerX = fingerX;
                    }
                } else { // run when finger up
                    prev_isPressing = false;
                    posX += V * delta_time;
                    if (!isRecovering) {
                        if (posX > rightEdge && V > 0) {
                            V = -0.000000004 * (double) (posX - rightEdge);
                            recover_a = 0.000000000000000008 * (double) (posX - rightEdge);
                            isRecovering = true;
                        } else if (posX < leftEdge && V < 0) {
                            V = -0.000000004 * (double) (posX - leftEdge);
                            recover_a = 0.000000000000000008 * (double) (posX - leftEdge);
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

                
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
