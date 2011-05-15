package NMLab.team10.rollingthecheese;

import java.util.HashMap;

import android.R.integer;
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
    
    static public void initSoundController(RollingCheeseActivity father){
        SoundController.father = father;
        currentBackground = BACKGROUND1;
        mediaPlayer = MediaPlayer.create(father, R.raw.background1);
        
        soundPool = new SoundPool(numChannel, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        ///////////////////////// add needed sound //////////////////////////////
        soundPoolMap.put(EFF_COLLISION1, soundPool.load(father,R.raw.collision1, PRIO_EFF_COLLISION1));
        ///////////////////////// add more ///////////////////////////////
        mgr = (AudioManager)father.getSystemService(Context.AUDIO_SERVICE);
        soundPrioMap = new HashMap<Integer, Integer>();
        soundPrioMap.put(EFF_COLLISION1, PRIO_EFF_COLLISION1);
    }
    
    //////////// return streamID (0 if failed) , repeattime: -1 if forever loop 
    static public int playSound(int soundeff, int repeattime){
        Log.e("SoundController","playereff once");
        streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent/streamVolumeMax;
        return soundPool.play(soundPoolMap.get(soundeff), volume, volume, 
                soundPrioMap.get(soundeff),repeattime, 1f);
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
