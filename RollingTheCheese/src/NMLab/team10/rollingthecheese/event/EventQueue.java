package NMLab.team10.rollingthecheese.event;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class EventQueue {

    public EventQueue() {
        queue = new LinkedList<Byte>();
        this.waitingTime = 0;
        this.waitingTimeMax = 100;
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

    public synchronized void decreWaitingTime(int time) {
        this.waitingTime -= time;
        if (this.waitingTime < 0)
            this.waitingTime = 0;
    }

    public synchronized int getWaitingTime() {
        return waitingTime;
    }

    public synchronized void initialWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
        this.waitingTimeMax = waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public synchronized int getSize() {
        return queue.size();
    }

    public synchronized void clear() {
        queue.clear();
    }

    public synchronized boolean findEvent(byte event) {
        return queue.contains(event);
    }

    public synchronized byte countEvent(byte event) {
        int n = 0;
        for (Iterator<Byte> iterator = queue.iterator(); iterator.hasNext();) {
            byte type = (byte) iterator.next();
            if (type == event)
                n++;
        }
        if (n > Byte.MAX_VALUE) {
            return Byte.MAX_VALUE;
        }
        return (byte) n;
    }

    public synchronized boolean isHead(byte event) {
        return queue.peek().equals(event);
    }

    public synchronized void removeEvent(byte event) {
        queue.remove(event);
    }

    private Queue<Byte> queue = null;
    private int waitingTime = 0;
    private int waitingTimeMax = 100;

    public byte getPercent() {
        byte percent = (byte) Math.floor(100.0 * (1 - ((float) waitingTime) / waitingTimeMax));
        if(percent==100)
            percent = 99;
        return percent;
    }
}
