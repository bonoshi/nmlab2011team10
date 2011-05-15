package NMLab.team10.rollingthecheese;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import NMLab.team10.rollingthecheese.displayData.CheeseDisplay;
import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.AppMessage;
import NMLab.team10.rollingthecheese.gameSetting.Farm;
import NMLab.team10.rollingthecheese.gameSetting.House;
import NMLab.team10.rollingthecheese.gameSetting.ServerGameSetting;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class RollingCheeseActivity extends Activity {
    /** Called when the activity is first created. */
    static final int REQUEST_CONNECT_DEVICE = 0;
    static final int REQUEST_ENABLE_BT = 1;

    public String mConnectedDeviceName;
    

    // private boolean isLeft = false;// when two player
    private boolean isTwoPlayer = true;

    private static final int MENU_Exit = 0;
    private static final int MENU_InitClient = 1;
    private static final int MENU_Send = 2;

    public GameView gameView;
    public EntranceView entranceView;

    public House leftHouse, rightHouse;
    public Farm leftFarm, rightFarm;
    public ArrayList<CheeseDisplay> leftCheeseDisplays;
    public ArrayList<CheeseDisplay> rightCheeseDisplays;

    public GameCalThread gameCalThread = null;
    public DisplayData displayData = new DisplayData();
    public EventQueueCenter clientEventQueue = null;
    public RandomSoundGenerator randomSoundGenerator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        SoundController.initSoundController(this);
        SoundController.playBackground(SoundController.BACKGROUND1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        entranceView = new EntranceView(this);
        gameView = new GameView(this);
        setContentView(entranceView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        // if (mBluetoothService != null) {
        // if (mBluetoothService.getState() == BluetoothService.STATE_NONE) {
        // // Start the Blue-tooth chat services
        // mBluetoothService.start();
        // }
        // }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mBluetoothService != null)
//            mBluetoothService.stop();
    }

    /**
     * Invoked during init to give the Activity a chance to set up its Menu.
     *
     * @param menu
     *            the Menu to which entries may be added
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // menu.add(0, MENU_CLEAR, 0, R.string.menu_clear);
        menu.add(0, MENU_Exit, 0, "Exit");
        menu.add(0, MENU_InitClient, 0, "Init");
        menu.add(0, MENU_Send, 0, "Send");
        // menu.add(0, MENU_PAUSE, 0, R.string.menu_pause);
        // menu.add(0, MENU_RESUME, 0, R.string.menu_resume);

        return true;
    }

    private Socket clientSocket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    private void initClient() {
        try {
            this.clientSocket = new Socket("140.112.250.107", 5566);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            oos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        myHandler.sendEmptyMessage(InterThreadMsg.startGameView);
    }

    private void send() {
        Byte init = 20;
        try {
            oos.writeObject(init);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println("Cliemt Send = " + init);
        Log.e(TAG, "Client send = " + Byte.toString(init));
        Byte result = 0;
        try {
            result = (Byte) ois.readObject();
        } catch (OptionalDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.e(TAG, "Client receive = " + Byte.toString(result));
        // System.out.println("Cliemt Receive = " + result);
    }

    class SynSending {

    }

    SynSending synSending = new SynSending();

    public void sendEvent(Byte event) {
        synchronized (synSending) {
            try {
                oos.writeObject(event);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    RefreshDisplayThread refreshDisplayThread = new RefreshDisplayThread();

    class RefreshDisplayThread extends Thread {
        public RefreshDisplayThread() {
        }

        private boolean running = true;
        private boolean pause = false;

        public void setPause(boolean pause) {
            this.pause = pause;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            while (running) {
                while (pause) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                Object o = null;

                try {
                    Log.e("bonoshi", Integer.toString(ois.available()));
                    o = ois.readObject();
                } catch (OptionalDataException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (o != null) {
                    try {
                        AppMessage am = (AppMessage) o;
                        giveData(am);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }

            }
        }

        private void giveData(AppMessage am) {
            if (am.getType() == EventEnum.Data) {
                displayData.refresh(am.getSmd());
            }
        }
    }

    private static final String TAG = "ClientError";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // case MENU_CLEAR:
            // mAnimationThread.clearSprites();
            // return true;
            case MENU_Exit:
                finish();
                return true;
            case MENU_InitClient:
                initClient();
                return true;
            case MENU_Send:
                send();
                return true;
                // case MENU_PAUSE:
                // mAnimationThread.pause();
                // return true;
                // case MENU_RESUME:
                // mAnimationThread.unpause();
                // return true;
        }
        return false;
    }

    public Handler myHandler = new Handler() {
        RollingCheeseActivity rca = RollingCheeseActivity.this;

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case InterThreadMsg.serverStartGameView:
                    isTwoPlayer=false;
                    myHandler.sendEmptyMessage(InterThreadMsg.startGameView);
                    break;
                case InterThreadMsg.startGameView:
                    if (isTwoPlayer) {
                        clientEventQueue = new EventQueueCenter(rca);
                        gameView.initialTwoPlayer();
                        refreshDisplayThread.start();
                    } else {
                        if (gameCalThread != null) {
                            gameCalThread.stopGameCal();
                            gameCalThread = null;
                        }
                        ServerGameSetting sgs = new ServerGameSetting();
                        gameCalThread = new GameCalThread(rca, sgs);

                        displayData = gameCalThread.getDisplayData();
                        gameCalThread.start();
                        gameCalThread.resumeGameCal();
                        gameView.initialOnePlayer();
                    }
                    entranceView.setPause(true);
                    gameView.startDrawThread();
                    setContentView(gameView);
                    SoundController.playBackground(SoundController.BACKGROUND2);
                    randomSoundGenerator = new RandomSoundGenerator();
                    randomSoundGenerator.start();
                    break;
                case InterThreadMsg.endGame:
                    Toast.makeText(getApplicationContext(),
                            "Application is terminating ...",
                            Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                    break;
                case InterThreadMsg.connect:
                    break;
            }
        }

    };

    public void setTwoPlayer(boolean isTwoPlayer) {
        this.isTwoPlayer = isTwoPlayer;
    }

    public boolean isTwoPlayer() {
        return isTwoPlayer;
    }
}