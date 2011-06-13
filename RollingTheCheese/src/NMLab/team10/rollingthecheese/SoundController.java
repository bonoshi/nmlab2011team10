package NMLab.team10.rollingthecheese;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

public class SoundController {
    // public SoundThread getSoundThread(SoundEffectType, boolean repeat)
    // SoundThread: public start(), pause(), stop()
    static RollingCheeseActivity father;
    static int numChannel = 8;
    
    /////////////////////// add needed sound ///////////////////////////////
    static public final int BACKGROUND1 = -1;  
    static public final int BACKGROUND2 = -2;
    static public final int EFF_COLLISION1 = 1;    static public final int PRIO_EFF_COLLISION1 = 0;
    static public final int EFF_BIRD1 = 2;         static public final int PRIO_EFF_BIRD1 = 0;
    static public final int EFF_BIRD2 = 3;         static public final int PRIO_EFF_BIRD2 = 0;
    static public final int EFF_BUTTON1 = 4;       static public final int PRIO_EFF_BUTTON1 = 0;
    static public final int EFF_BUTTON2 = 5;       static public final int PRIO_EFF_BUTTON2 = 0;
    static public final int EFF_COW1 = 6;          static public final int PRIO_EFF_COW1 = 0;
    static public final int EFF_COW2 = 7;          static public final int PRIO_EFF_COW2 = 0;
    static public final int EFF_COW3 = 8;          static public final int PRIO_EFF_COW3 = 0;
    static public final int EFF_COW4 = 9;          static public final int PRIO_EFF_COW4 = 0;
    static public final int EFF_LAUGH1 = 10;       static public final int PRIO_EFF_LAUGH1 =0;
    static public final int EFF_LAUGH2 = 11;       static public final int PRIO_EFF_LAUGH2 = 0;
    static public final int EFF_LAUGH3 = 12;       static public final int PRIO_EFF_LAUGH3 = 0;
    static public final int EFF_NIGHTBIRD1 = 13;   static public final int PRIO_EFF_NIGHTBIRD1 = 0;
    static public final int EFF_WATER1 = 14;       static public final int PRIO_EFF_WATER1 = 0;
    static public final int EFF_WATER2 = 15;       static public final int PRIO_EFF_WATER2 = 0;
    static public final int EFF_WIND1 = 17;        static public final int PRIO_EFF_WIND1 = 0;
    /////////////////////////// add more ... ////////////////////////////
    
    static SoundPool soundPool;
    static SoundController soundController;
    static HashMap<Integer, Integer> soundPoolMap;
    static HashMap<Integer, Integer> soundPrioMap;
    static float streamVolumeCurrent;
    static float streamVolumeMax;
    static AudioManager mgr;
    static int currentBackground;
    static MediaPlayer mediaPlayer;
    
    static public void cancelAllMusic(){
        mediaPlayer.stop();
        soundPool.release();
    }
    
    
    static public void initSoundController(RollingCheeseActivity father){
        SoundController.father = father;
        currentBackground = BACKGROUND1;
        mediaPlayer = MediaPlayer.create(father, R.raw.background1);
        mediaPlayer.setLooping(true);
        
        soundPool = new SoundPool(numChannel, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        ///////////////////////// add needed sound //////////////////////////////
        soundPoolMap.put(EFF_COLLISION1, soundPool.load(father,R.raw.collision1, PRIO_EFF_COLLISION1));
        soundPoolMap.put(EFF_BIRD1 , soundPool.load(father,R.raw.bird1, PRIO_EFF_BIRD1));
        soundPoolMap.put(EFF_BIRD2 , soundPool.load(father,R.raw.bird2, PRIO_EFF_BIRD2));
        soundPoolMap.put(EFF_BUTTON1 , soundPool.load(father,R.raw.button1, PRIO_EFF_BUTTON1));
        soundPoolMap.put(EFF_BUTTON2 , soundPool.load(father,R.raw.button2, PRIO_EFF_BUTTON2));
        soundPoolMap.put(EFF_COW1 , soundPool.load(father,R.raw.cow1, PRIO_EFF_COW1));
        soundPoolMap.put(EFF_COW2 , soundPool.load(father,R.raw.cow2, PRIO_EFF_COW2));
        soundPoolMap.put(EFF_COW3 , soundPool.load(father,R.raw.cow3, PRIO_EFF_COW3));
        soundPoolMap.put(EFF_COW4 , soundPool.load(father,R.raw.cow4, PRIO_EFF_COW4));
        soundPoolMap.put(EFF_LAUGH1 , soundPool.load(father,R.raw.laugh1, PRIO_EFF_LAUGH1));
        soundPoolMap.put(EFF_LAUGH2 , soundPool.load(father,R.raw.laugh2, PRIO_EFF_LAUGH2));
        soundPoolMap.put(EFF_LAUGH3 , soundPool.load(father,R.raw.laugh3, PRIO_EFF_LAUGH3));
        soundPoolMap.put(EFF_NIGHTBIRD1, soundPool.load(father,R.raw.nightbird1, PRIO_EFF_NIGHTBIRD1));
        soundPoolMap.put(EFF_WATER1 , soundPool.load(father,R.raw.water1, PRIO_EFF_WATER1));
        soundPoolMap.put(EFF_WATER2, soundPool.load(father,R.raw.water2, PRIO_EFF_WATER2));
        soundPoolMap.put(EFF_WIND1, soundPool.load(father,R.raw.wind1, PRIO_EFF_WIND1));
        ///////////////////////// add more ///////////////////////////////
        mgr = (AudioManager)father.getSystemService(Context.AUDIO_SERVICE);
        soundPrioMap = new HashMap<Integer, Integer>();
        soundPrioMap.put(EFF_COLLISION1, PRIO_EFF_COLLISION1);
        soundPrioMap.put(EFF_BIRD1, PRIO_EFF_BIRD1);
        soundPrioMap.put(EFF_BIRD2, PRIO_EFF_BIRD2);
        soundPrioMap.put(EFF_BUTTON1, PRIO_EFF_BUTTON1);
        soundPrioMap.put(EFF_BUTTON2, PRIO_EFF_BUTTON2);
        soundPrioMap.put(EFF_COW1, PRIO_EFF_COW1);
        soundPrioMap.put(EFF_COW2, PRIO_EFF_COW2);
        soundPrioMap.put(EFF_COW3, PRIO_EFF_COW3);
        soundPrioMap.put(EFF_COW4, PRIO_EFF_COW4);
        soundPrioMap.put(EFF_LAUGH1, PRIO_EFF_LAUGH1);
        soundPrioMap.put(EFF_LAUGH2, PRIO_EFF_LAUGH2);
        soundPrioMap.put(EFF_LAUGH3, PRIO_EFF_LAUGH3);
        soundPrioMap.put(EFF_NIGHTBIRD1, PRIO_EFF_NIGHTBIRD1);
        soundPrioMap.put(EFF_WATER1, PRIO_EFF_WATER1);
        soundPrioMap.put(EFF_WATER2, PRIO_EFF_WATER2);
        soundPrioMap.put(EFF_WIND1, PRIO_EFF_WIND1);
    }
    
    //////////// return streamID (0 if failed) , repeattime: -1 if forever loop 
    static public int playSound(int soundeff){
        Log.e("SoundController","playereff once");
        streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent/streamVolumeMax;
        return soundPool.play(soundPoolMap.get(soundeff), volume, volume, 
                soundPrioMap.get(soundeff),0, 1f);
    }
    
    static public void stopSound(int streamID){
        soundPool.stop(streamID);
    }
    static public void pauseSound(int streamID){
        soundPool.pause(streamID);
    }
    static public void resumeSound(int streamID){
        soundPool.resume(streamID);
    }
    
    static public void playBackground(int background){
        if(background != currentBackground){
            stopBackground();
            currentBackground = background;
            switch(background){
                case BACKGROUND1:
                    mediaPlayer = MediaPlayer.create(father, R.raw.background1);
                    break;
                case BACKGROUND2:
                    mediaPlayer = MediaPlayer.create(father, R.raw.background2);
                    break;
                
            }
            mediaPlayer.setLooping(true);
        }
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }
    static public void stopBackground(){
        mediaPlayer.stop();
    }
    static public void pauseBackground(){
        mediaPlayer.pause();
    }
    
    
}
