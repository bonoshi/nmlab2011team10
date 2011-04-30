package NMLab.team10.rollingthecheese.event;

import java.util.LinkedList;
import java.util.Queue;

public class EventQueue {

    public EventQueue() {
        queue = new LinkedList<Object>();
        this.waitingTime = 0;
    }

    public synchronized void push(Object o){
        try {
            queue.add(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized Object pop(){
        return queue.poll();
    }

    public synchronized int getWaitingTime() {
        return waitingTime;
    }

    public synchronized void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public synchronized int getSize(){
        return queue.size();
    }

    public synchronized void clear(){
        queue.clear();
    }

    Queue<Object> queue = null;
    int waitingTime;

}
