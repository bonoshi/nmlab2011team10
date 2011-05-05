package NMLab.team10.rollingthecheese;


import java.util.ArrayList;

import NMLab.team10.rollingthecheese.gameSetting.CheeseDisplay;
import NMLab.team10.rollingthecheese.gameSetting.Farm;
import NMLab.team10.rollingthecheese.gameSetting.House;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

public class RollingCheeseActivity extends Activity {
    /** Called when the activity is first created. */
    public WelcomeView welcomeView;
    public GameView gameView;
    public GameThread gameThread;

    public House leftHouse,rightHouse;
    public Farm leftFarm, rightFarm;
    public ArrayList<CheeseDisplay> leftCheeseDisplays;
    public ArrayList<CheeseDisplay> rightCheeseDisplays;
    

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                );
        welcomeView = new WelcomeView(this);
        gameView = new GameView(this);
        gameThread = new GameThread(this);
        setContentView(welcomeView);
    }

    Handler myHandler = new Handler(){
        public void handleMessage(Message Msg){
            if(Msg.what == InterThreadMsg.startGameView){
                setContentView(gameView);

            }
        }

    };
}