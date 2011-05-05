package NMLab.team10.rollingthecheese.event;

import java.util.LinkedList;
import java.util.Queue;

public class EventQueue {

    public EventQueue() {
        queue = new LinkedList<Byte>();
        this.waitingTime = 0;
    }

    public synchronized void push(byte o) {
        try {
            queue.add(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized byte pop() {
        return queue.poll();
    }

    public synchronized byte peak() {
        return queue.peek();
    }

    public synchronized int getWaitingTime() {
        return waitingTime;
    }

    public synchronized void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public synchronized int getSize() {
        return queue.size();
    }

    public synchronized void clear() {
        queue.clear();
    }

    public synchronized boolean findEvent(byte event){
        return queue.contains(event);
    }

    public synchronized boolean isHead(byte event){
        return queue.peek().equals(event);
    }

    public synchronized void removeEvent(byte event){
        queue.remove(event);
    }

    Queue<Byte> queue = null;
    int waitingTime;
    int waitingTimeMax;

    public byte getPercent(){
        byte percent = (byte) Math.ceil(100.0 * waitingTime / waitingTimeMax);
        return percent;
    }
}
