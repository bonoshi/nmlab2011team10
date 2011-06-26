package NMLab.team10.rollingthecheese.gameSetting;

import java.util.Iterator;
import java.util.LinkedList;

import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import NMLab.team10.rollingthecheese.InterThreadMsg;
import NMLab.team10.rollingthecheese.RollingCheeseActivity;

public class ToastMessageThread extends Thread {

    RollingCheeseActivity father;

    private boolean isPause = false;
    private boolean isRunning = true;
    private Toast toast = null;
    LinkedList<ToastMessage> list = null;

    public ToastMessageThread(RollingCheeseActivity father, int cycle) {
        this.father = father;
        this.cycle = cycle;
        toast = Toast.makeText(father, "", Toast.LENGTH_SHORT);
        list = new LinkedList<ToastMessage>();
    }

    static public int DefaultTime = 1000;

    public void addMessage(String text) {
        synchronized (list) {
            list.add(new ToastMessage(DefaultTime, text));
        }
    }

    public void addMessage(int time, String text) {
        synchronized (list) {
            list.add(new ToastMessage(time, text));
        }
    }

    private void delete() {
        synchronized (list) {
            for (Iterator<ToastMessage> iterator = list.iterator(); iterator.hasNext();) {
                ToastMessage type = (ToastMessage) iterator.next();
                if ((type.time -= cycle) <= 0)
                    iterator.remove();
            }
        }
    }

    public void cancel(){
        toast.cancel();
    }

    public void display() {
//        father.myHandler.sendEmptyMessage(InterThreadMsg.ToastClose);
        String string = "";
        toast.cancel();
        synchronized (list) {
            int size = list.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    if (i != size - 1)
                        string += (list.get(i).text + "\n");
                    else
                        string += (list.get(i).text);
                }
                Message msg = new Message();
                msg.what = InterThreadMsg.ToastDisplay;
                msg.obj = string;
//                father.myHandler.sendMessage(msg);
                toast = Toast.makeText(father, string, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void run() {
        while(isRunning){
            //Looper.prepare();
//            display();
            delete();
            //Looper.loop();
            try {
                Thread.sleep(cycle);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (isPause) {
                try {
                    Thread.sleep(cycle);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    };

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    int cycle;// in ms

    class ToastMessage {
        public ToastMessage(int time, String text) {
            this.time = time;
            this.text = text;
        }

        String text;
        int time;
    }
}
