package NMLab.team10.rollingthecheese;



public class WelcomeThread extends Thread {
    WelcomeView father;
    boolean isRunning;
    
    public WelcomeThread(WelcomeView father){
        this.father = father;
        isRunning = true;
    }
    public void run(){
        while(isRunning){
            
        }
    }
}
