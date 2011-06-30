package NMLab.team10.rollingthecheese;

import java.util.Random;

public class RandomSoundGenerator {
    static final int birdInterval = 20000;
    static final int cowInterval = 10000;
    static final int nightbirdInterval = 20000;
    static final int windInterval = 15000;
    static final float birdProb = 0.8F;
    static final float cowProb = 0.8F;
    static final float nightbirdProb = 0.7F;
    static final float windProb = 0.5F;

    public RandomSoundThread birdThread;
    public RandomSoundThread cowThread;
    public RandomSoundThread nightbirdThread;
    public RandomSoundThread windThread;

    public RandomSoundGenerator() {
        int[] birdEff = { SoundController.EFF_BIRD1, SoundController.EFF_BIRD2 };
        birdThread = new RandomSoundThread(birdInterval, birdProb, birdEff);
        int[] cowEff = { SoundController.EFF_COW1, SoundController.EFF_COW2, SoundController.EFF_COW3,
                SoundController.EFF_COW4 };
        cowThread = new RandomSoundThread(cowInterval, cowProb, cowEff);
        int[] nightbirdEff = { SoundController.EFF_NIGHTBIRD1 };
        nightbirdThread = new RandomSoundThread(nightbirdInterval, nightbirdProb, nightbirdEff);
        int[] windEff = { SoundController.EFF_WIND1 };
        windThread = new RandomSoundThread(windInterval, windProb, windEff);
    }

    public void start() {
        birdThread.start();
        cowThread.start();
        nightbirdThread.start();
        windThread.start();
    }

    public void pause() {
        birdThread.pause = true;
        cowThread.pause = true;
        nightbirdThread.pause = true;
        windThread.pause = true;
    }

    public void resume() {
        birdThread.pause = false;
        cowThread.pause = false;
        nightbirdThread.pause = false;
        windThread.pause = false;
    }

    public void stop() {
        birdThread.isRunning = false;
        cowThread.isRunning = false;
        nightbirdThread.isRunning = false;
        windThread.isRunning = false;
        birdThread.interrupt();
        cowThread.interrupt();
        nightbirdThread.interrupt();
        windThread.interrupt();
        resume();
    }


    class RandomSoundThread extends Thread {
        public int interval;
        public float prob;
        public int[] eff;
        public boolean enable = true;
        private Random random = new Random();
        public boolean isRunning;
        public boolean pause;

        public void setEnable(boolean e) {
            enable = e;
        }

        public RandomSoundThread(int interval, float prob, int[] eff) {
            this.interval = interval;
            this.prob = prob;
            this.eff = eff;
        }

        public void run() {
            isRunning = true;
            pause = false;
            while (isRunning) {
                float r = random.nextFloat();
                if (r < prob) {
                    int rr = random.nextInt(eff.length);
                    SoundController.playSound(eff[rr]);
                }
                try {
                    sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (pause) {
                    try {
                        sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
