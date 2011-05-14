package NMLab.team10.rollingthecheese;

import java.io.IOException;
import java.util.ArrayList;

import NMLab.team10.rollingthecheese.displayData.CheeseDisplay;
import NMLab.team10.rollingthecheese.displayData.DisplayData;
import NMLab.team10.rollingthecheese.event.EventEnum;
import NMLab.team10.rollingthecheese.event.EventQueueCenter;
import NMLab.team10.rollingthecheese.gameSetting.Cheese;
import NMLab.team10.rollingthecheese.gameSetting.Farm;
import NMLab.team10.rollingthecheese.gameSetting.House;
import NMLab.team10.rollingthecheese.gameSetting.ServerGameSetting;
import NMLab.team10.rollingthecheese.gameSetting.SynMessageData;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class RollingCheeseActivity extends Activity {
    /** Called when the activity is first created. */
    static final int REQUEST_CONNECT_DEVICE = 0;
    static final int REQUEST_ENABLE_BT = 1;
    boolean bluetoothSetupFinished = false;
    private BluetoothAdapter mBluetoothAdapter = null;
    public BluetoothService mBluetoothService = null;
    public String mConnectedDeviceName;

    private boolean isClient = false;
    private boolean isTwoPlayer = false;
    private boolean hasBeenServer = false;

    private static final int MENU_STOP = 7;

    public WelcomeView welcomeView;
    public GameView gameView;

    public House leftHouse, rightHouse;
    public Farm leftFarm, rightFarm;
    public ArrayList<CheeseDisplay> leftCheeseDisplays;
    public ArrayList<CheeseDisplay> rightCheeseDisplays;

    public GameCalThread gameCalThread = null;
    public DisplayData displayData = new DisplayData();
    public EventQueueCenter clientEventQueue = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        welcomeView = new WelcomeView(this);
        gameView = new GameView(this);
        setContentView(welcomeView);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the chat session
        } else {
            if (mBluetoothService == null)
                setupService();
        }
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        if (mBluetoothService != null) {
            if (mBluetoothService.getState() == BluetoothService.STATE_NONE) {
                // Start the Blue-tooth chat services
                mBluetoothService.start();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBluetoothService != null)
            mBluetoothService.stop();
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
        menu.add(0, MENU_STOP, 0, "Exit");
        // menu.add(0, MENU_PAUSE, 0, R.string.menu_pause);
        // menu.add(0, MENU_RESUME, 0, R.string.menu_resume);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // case MENU_CLEAR:
            // mAnimationThread.clearSprites();
            // return true;
            case MENU_STOP:
                finish();
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

    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    private void setupService() {
        mBluetoothService = new BluetoothService(this, myHandler);
    }

    public Handler myHandler = new Handler() {
        RollingCheeseActivity rca = RollingCheeseActivity.this;

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case InterThreadMsg.startGameView:
                    if (gameCalThread != null) {
                        gameCalThread.stopGameCal();
                        gameCalThread = null;
                    }
                    if (!isClient) {
                        //myHandler.sendEmptyMessage(InterThreadMsg.serverStartGameView);
                    } else {
                        clientEventQueue = new EventQueueCenter(rca);
                        gameView.initialClientGameView();
                        clientEventQueue.addEvent(EventEnum.Start);
                        setContentView(gameView);
                    }
                    break;
                case InterThreadMsg.serverStartGameView:
                    ServerGameSetting sgs = new ServerGameSetting();
                    gameCalThread = new GameCalThread(rca, sgs, true);

                    displayData = gameCalThread.getDisplayData();
                    gameCalThread.start();
                    gameCalThread.resumeGameCal();
                    gameView.initialServerGameView();

                    setContentView(gameView);
                    break;
                case InterThreadMsg.scan:
                    startScan();
                    break;
                case InterThreadMsg.discoverable:
                    ensureDiscoverable();
                    break;
                case InterThreadMsg.MESSAGE_STATE_CHANGE:
                    break;
                case InterThreadMsg.MESSAGE_WRITE:
                    break;
                case InterThreadMsg.MESSAGE_READ:
                    /* bobuway: receive a object */
                    Object o = msg.obj;
                    if (!isClient) {
                        Byte eventByte = (Byte) o;
                        if (eventByte == EventEnum.Start) {
                            myHandler.sendEmptyMessage(InterThreadMsg.serverStartGameView);
                        } else {
                            gameCalThread.getEventCenter().addEvent(eventByte, Cheese.Right);
                        }
                    } else {
                        SynMessageData smd = (SynMessageData) o;
                        displayData.refresh(smd);
                        GameView.refreshPPS(false);
                    }
                    break;
                case InterThreadMsg.MESSAGE_DEVICE_NAME:
                    mConnectedDeviceName = msg.getData().getString(BundleKey.DEVICE_NAME);
                    Toast.makeText(getApplicationContext(), "Connected to " + mConnectedDeviceName,
                            Toast.LENGTH_SHORT).show();
                    switch (msg.arg1) {
                        case BluetoothService.IAMSERVER:
                            isClient = false;
                            break;
                        case BluetoothService.IAMCLIENT:
                            isClient = true;
                            break;
                    }
                    break;
                case InterThreadMsg.ConnectionLost:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(BundleKey.TOAST),
                            Toast.LENGTH_SHORT).show();
                    // To do...back to two player menu
                    // If server then pause GameCalThread
                    break;
                case InterThreadMsg.ConnectionFail:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(BundleKey.TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
                case InterThreadMsg.LinkingErrorInGame:
                    // To do...
                    break;
            }
        }

    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                    mBluetoothService.connect(device);
                }
                break;
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    setupService();
                } else {
                    Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    public void sendObject(Object o) throws IOException { // bobuway: send
                                                          // Object
        if (mBluetoothService.getState() != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }
        mBluetoothService.write(o);
    }

    private void startScan() {
        Intent serverIntent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    }

    public boolean isClient() {
        return isClient;
    }

    public void setTwoPlayer(boolean isTwoPlayer) {
        this.isTwoPlayer = isTwoPlayer;
    }

    public boolean isTwoPlayer() {
        return isTwoPlayer;
    }

    public void setHasBeenServer(boolean hasBeenServer) {
        this.hasBeenServer = hasBeenServer;
    }

    public boolean isHasBeenServer() {
        return hasBeenServer;
    }
}