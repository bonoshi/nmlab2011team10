package NMLab.team10.rollingthecheese.gameSetting;

public class CowDisplay{
    public CowDisplay(CowMessage cm) {
        this.setCowMessage(cm);
    }
    public void setCowMessage(CowMessage cowMessage) {
        this.cowMessage = cowMessage;
    }
    public CowMessage getCowMessage() {
        return cowMessage;
    }
    private CowMessage cowMessage;
}
