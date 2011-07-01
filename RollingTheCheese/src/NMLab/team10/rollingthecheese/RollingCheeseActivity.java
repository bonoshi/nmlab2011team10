package NMLab.team10.rollingthecheese;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import NMLab.team10.rollingthecheese.displayData.CheeseDisplay;
import NMLab.team10.rollingthecheese.displayData.CloudDisplay;
import NMLab.team10.rollingthecheese.displayData.CowDisplay;
import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.displayData.FireLineDisplay;
import NMLab.team10.rollingthecheese.displayData.HouseDisplay;
import NMLab.team10.rollingthecheese.displayData.SkyDisplay;
import NMLab.team10.rollingthecheese.displayData.SmokeDisplay;
import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.gameSetting.AppMessage;
import NMLab.team10.rollingthecheese.gameSetting.Farm;
import NMLab.team10.rollingthecheese.gameSetting.House;
import NMLab.team10.rollingthecheese.gameSetting.Projector;
import NMLab.team10.rollingthecheese.gameSetting.ServerGameSetting;
import NMLab.team10.rollingthecheese.gameSetting.SynMessageData;
import NMLab.team10.rollingthecheese.gameSetting.ToastMessageThread;
import NMLab.team10.rollingthecheese.gameSetting.cZipFactory;
import NMLab.team10.rollingthecheese.gameSetting.cZipObject;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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

    static final int ENTRANCE_VIEW = 0;
    static final int GAME_VIEW = 1;

    public String mConnectedDeviceName;

    public int viewState = ENTRANCE_VIEW;// indicate the current view
                                         // to respond to onBackPressed()

    // private boolean isLeft = false;// when two player
    static private boolean isTwoPlayer = true;// be careful to maintain
    static private boolean isClient = true;// be careful to maintain

    private static final int MENU_Exit = 0;
    private static final int MENU_InitClient = 1;
    private static final int MENU_Send = 2;

    public GameView gameView = null;
    public EntranceView entranceView;

    public static Resources r;
    public static Context context;

    ServerSocket server = null;

    public House leftHouse, rightHouse;
    public Farm leftFarm, rightFarm;
    public ArrayList<CheeseDisplay> leftCheeseDisplays;
    public ArrayList<CheeseDisplay> rightCheeseDisplays;

    public GameCalThread gameCalThread = null;
    static private DisplayData displayData = null;
    public RandomSoundGenerator randomSoundGenerator = null;
    public ToastMessageThread toastMessage = null;

    private ServerThread serverThread = null;
    private ReceiveThread receiveThread = null;

    // private static final String TAG = "ClientError";
    private static final String TAG = "TestLeft";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewState = ENTRANCE_VIEW;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // SoundController.initSoundController(this);
        // SoundController.playBackground(SoundController.BACKGROUND1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        entranceView = new EntranceView(this);
        setContentView(entranceView);
        toastMessage = new ToastMessageThread(this, 200);
        toastMessage.start();

        GameView.initBitmap(this);
    }

    public void initialGameView() {
        if (gameView != null) {
            gameView.stop();
        }
        gameView = new GameView(this);
    }

    public void resumeGameView() {
        if (gameView != null) {
            gameView.resume();
        }
    }

    public void interruptGameView() {
        if (gameView != null) {
            gameView.interrupt();
        }
    }

    public void pauseGameView() {
        if (gameView != null) {
            gameView.pause();
        }
    }

    public void stopGameView() {
        if (gameView != null) {
            gameView.stop();
        }
    }

    public void destroyGameView() {
        if (gameView != null) {
            gameView = null;
        }
    }

    public void closeServer() {
        if (serverThread != null) {
            serverThread.closeServer();
            serverThread = null;
        }
    }

    public void resumeNetwork() {
        if (receiveThread != null) {
            receiveThread.setPause(false);
        }
    }

    public void interruptNetwork() {
        if (receiveThread != null) {
            receiveThread.interrupt();
        }
    }

    public void pauseNetwork() {
        if (receiveThread != null) {
            receiveThread.setPause(true);
        }
    }

    public void destroyNetwork() {
        receiveThread = null;
    }

    public void stopNetwork() {
        if (receiveThread != null) {
            receiveThread.setRunning(false);
        }
    }

    public void resumeRandomSound() {
        if (randomSoundGenerator != null) {
            randomSoundGenerator.resume();
        }

    }

    public void interruptRandomSound() {
        if (randomSoundGenerator != null) {
            randomSoundGenerator.interrupt();
        }
    }

    public void pauseRandomSound() {
        if (randomSoundGenerator != null) {
            randomSoundGenerator.pause();
        }
    }

    public void stopRandomSound() {
        if (randomSoundGenerator != null) {
            randomSoundGenerator.stop();
        }
    }

    public void destroyRandomSound() {
        if (randomSoundGenerator != null) {
            randomSoundGenerator = null;
        }
    }

    @Override
    public void onBackPressed() {
        switch (viewState) {
            case ENTRANCE_VIEW: {
                entranceView.back();
                break;
            }
            case GAME_VIEW: {
                break;
            }
        }
        return;
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
        SoundController.initSoundController(this);

        entranceView.setPause(false);
        entranceView.interrupt();
        resumeGameView();
        interruptGameView();
        resumeGameCalThread();
        interruptGameCalThread();

        resumeRandomSound();
        interruptRandomSound();
        toastMessage.setPause(false);
        toastMessage.interrupt();
        resumeNetwork();
        interruptNetwork();
        if (viewState == ENTRANCE_VIEW) {
            SoundController.playBackground(SoundController.BACKGROUND1);
            setContentView(entranceView);
        } else if (viewState == GAME_VIEW) {
            SoundController.playBackground(SoundController.BACKGROUND2);
            setContentView(gameView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
        Log.e(TAG, "onDestroy");
        // SoundController.cancelAllMusic();
        // if (mBluetoothService != null)
        // mBluetoothService.stop();

    }

    private void destroy() {
        entranceView.setRunning(false);
        entranceView.setPause(false);
        entranceView.interrupt();

        stopGameView();
        resumeGameView();
        interruptGameView();
        destroyGameView();

        stopGameCalThread();
        resumeGameCalThread();
        interruptGameCalThread();
        destroyGameCalThread();

        stopRandomSound();
        resumeRandomSound();
        interruptRandomSound();
        destroyRandomSound();

        closeServer();

        stopNetwork();
        resumeNetwork();
        interruptNetwork();
        destroyNetwork();

        toastMessage.setRunning(false);
        toastMessage.setPause(false);
        finish();
    }

    public void onPause() {
        super.onPause();
        SoundController.cancelAllMusic();

        entranceView.setPause(true);
        pauseGameView();
        pauseGameCalThread();
        pauseRandomSound();
        toastMessage.setPause(true);
        pauseNetwork();
        // closeServer();
        Log.e(TAG, "onPause");
    }

    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
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
        return super.onCreateOptionsMenu(menu);
    }

    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    private void closeSocket() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    class SynSending {

    }

    cZipFactory ZipFactory = new cZipFactory(java.util.zip.Deflater.BEST_COMPRESSION);

    SynSending synSending = new SynSending();

    public void sendMessage(AppMessage message) throws IOException {
        synchronized (synSending) {
            oos.reset();
            synchronized (ZipFactory) {
                cZipObject czo = ZipFactory.Compress(message);
                oos.writeObject(czo);
            }
            oos.flush();
        }
    }

    class ReceiveThread extends Thread {
        public ReceiveThread() {
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
                        sleep(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

                Object o = null;

                try {
                    // Log.e("bonoshi", Integer.toString(ois.available()));
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
                        AppMessage am = (AppMessage) ZipFactory.Decompress((cZipObject) o);
                        byte type = am.getType();
                        if (type <= EventEnum.DestEnd) {
                            gameCalThread.sendEvent(type);
                        } else {
                            switch (type) {
                                case EventEnum.Jump: {
                                    break;
                                }
                                case EventEnum.Pause: {
                                    break;
                                }
                                case EventEnum.Surrender: {
                                    break;
                                }
                                case EventEnum.Restart: {
                                    break;
                                }
                                case EventEnum.Data: {
                                    refreshDisplayData(am.getSmd());
                                    GameView.refreshPPS(false);
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }

            }
        }
    }

    public void refreshDisplayData(SynMessageData smd) {
        displayData.refresh(smd);
    }

    static public DisplayData getDisplayData() {
        return displayData;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // case MENU_CLEAR:
            // mAnimationThread.clearSprites();
            // return true;
            case MENU_Exit:
                destroy();
                return true;
            case MENU_InitClient:
                initClient();
                return true;
            case MENU_Send:
                // send();
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

    public void stopGameCalThread() {
        if (gameCalThread != null) {
            gameCalThread.stopGameCal();
        }
    }

    public void pauseGameCalThread() {
        if (gameCalThread != null) {
            gameCalThread.pauseGameCal();
        }
    }

    public void resumeGameCalThread() {
        if (gameCalThread != null) {
            gameCalThread.resumeGameCal();
        }
    }

    public void destroyGameCalThread() {
        if (gameCalThread != null) {
            gameCalThread = null;
        }
    }

    public void interruptGameCalThread() {
        if (gameCalThread != null) {
            gameCalThread.interrupt();
        }
    }

    ServerGameSetting sgs = null;

    public Handler myHandler = new Handler() {
        RollingCheeseActivity rca = RollingCheeseActivity.this;

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case InterThreadMsg.startCrazyGame:

                    isTwoPlayer = false;

                    stopGameCalThread();
                    sgs = new ServerGameSetting();

                    // will only draw after initialization
                    displayData = new DisplayData();
                    gameCalThread = new GameCalThread(rca, sgs);// need
                                                                // displayData
                    initialGameView();
                    gameView.initialOnePlayer();// need gameCalThread

                    gameCalThread.start();
                    resumeGameCalThread();

                    entranceView.setPause(true);
                    gameView.start();

                    setContentView(gameView);
                    viewState = GAME_VIEW;
                    SoundController.playBackground(SoundController.BACKGROUND2);
                    randomSoundGenerator = new RandomSoundGenerator();
                    randomSoundGenerator.start();
                    break;
                case InterThreadMsg.clientConnect: {
                    Toast toast = Toast.makeText(rca, "Client Connect", Toast.LENGTH_SHORT);
                    toast.show();
                    closeSocket();
                    initClient();
                    break;
                }
                case InterThreadMsg.connectSuccess: {// a waiting view
                    Toast toast = Toast.makeText(rca, "Client Connect Success", Toast.LENGTH_SHORT);
                    toast.show();
                    isTwoPlayer = true;
                    isClient = true;

                    displayData = new DisplayData();
                    initialGameView();
                    gameView.initialClient();// need to send message about the
                                             // clicked events

                    receiveThread = new ReceiveThread();
                    receiveThread.start();

                    entranceView.setPause(true);
                    gameView.start();

                    viewState = GAME_VIEW;
                    setContentView(gameView);
                    SoundController.playBackground(SoundController.BACKGROUND3);
                    randomSoundGenerator = new RandomSoundGenerator();
                    randomSoundGenerator.start();
                    break;
                }
                case InterThreadMsg.connectFail: {
                    break;
                }
                case InterThreadMsg.serverWait: {
                    String ip = "";
                    try {
                        ip = InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    Toast toast = Toast.makeText(rca, "Server Wait at " + ip, Toast.LENGTH_SHORT);
                    toast.show();
                    ServerSocket ss = null;
                    try {
                        ss = new ServerSocket(5566);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    serverThread = new ServerThread(ss);
                    serverThread.start();
                    break;
                }
                case InterThreadMsg.waitSuccess: {
                    Toast toast = Toast.makeText(rca, "Server Wait Success", Toast.LENGTH_SHORT);
                    toast.show();
                    isTwoPlayer = true;
                    isClient = false;

                    sgs = new ServerGameSetting();

                    // will only draw after initialization
                    displayData = new DisplayData();
                    // before receiveThread to make sure eventqueue is ready to
                    // accept data
                    gameCalThread = new GameCalThread(RollingCheeseActivity.this, sgs);
                    initialGameView();
                    gameView.initialServer();

                    // start to accept event from client
                    receiveThread = new ReceiveThread();
                    receiveThread.start();

                    gameCalThread.start();
                    gameCalThread.resumeGameCal();

                    entranceView.setPause(true);
                    gameView.start();

                    viewState = GAME_VIEW;
                    setContentView(gameView);
                    SoundController.playBackground(SoundController.BACKGROUND2);
                    randomSoundGenerator = new RandomSoundGenerator();
                    randomSoundGenerator.start();
                    break;
                }
                case InterThreadMsg.waitExceed: {
                    break;
                }
                case InterThreadMsg.endGame:
                    Toast.makeText(getApplicationContext(), "Application is terminating ...",
                            Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    destroy();
                    break;
                case InterThreadMsg.ToastDisplay: {
                    // gameToastMessage =
                    // Toast.makeText(RollingCheeseActivity.this, (String)
                    // msg.obj,
                    // Toast.LENGTH_SHORT);
                    // gameToastMessage.show();
                    break;
                }
                case InterThreadMsg.ToastClose: {
                    // gameToastMessage.cancel();
                    break;
                }
                case InterThreadMsg.ServerFailToSend: {
                    Toast toast = Toast.makeText(rca, "Fail to send", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }
                case InterThreadMsg.OptionalDataException: {
                    Toast toast = Toast.makeText(RollingCheeseActivity.this, "OptionalDataException",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }
                case InterThreadMsg.ClassNotFoundException: {
                    Toast toast = Toast.makeText(RollingCheeseActivity.this, "ClassNotFoundException",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }
                case InterThreadMsg.IOException: {
                    Toast toast = Toast.makeText(RollingCheeseActivity.this, "IOException",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }
            }
        }

    };

    static public void setTwoPlayer(boolean isTwoPlayer) {
        RollingCheeseActivity.isTwoPlayer = isTwoPlayer;
    }

    static public boolean isTwoPlayer() {
        return RollingCheeseActivity.isTwoPlayer;
    }

    static public void setClient(boolean isClient) {
        RollingCheeseActivity.isClient = isClient;
    }

    static public boolean isClient() {
        return RollingCheeseActivity.isClient;
    }

    class ServerThread extends Thread {

        ServerSocket server = null;
        public boolean isConnected = false;
        public boolean isClosed = false;

        public ServerThread(ServerSocket serverSocket) {
            this.server = serverSocket;
        }

        public void closeServer() {
            isClosed = true;
            if (server != null && !server.isClosed()) {
                try {
                    server.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {

            try {
                socket = server.accept();
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.flush();
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                if (isClosed) {
                    // bonoshi: notify you close
                } else {
                    // bonoshi: notify accidental close
                }
                return;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                if (isClosed) {
                    // bonoshi: notify you close
                } else {
                    // bonoshi: notify accidental close
                }
                return;
            }

            myHandler.sendEmptyMessage(InterThreadMsg.waitSuccess);

        }
    }

    private void initClient() {
        try {
            this.socket = new Socket("192.168.1.1", 5566);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            // bonoshi: notify accidental failure
            e.printStackTrace();
            return;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // bonoshi: notify accidental failure
            e.printStackTrace();
            return;
        }

        isTwoPlayer = true;
        myHandler.sendEmptyMessage(InterThreadMsg.connectSuccess);
    }

}