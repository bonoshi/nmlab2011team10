package NMLab.team10.rollingthecheese.gameSetting;

public class FireLineDisplay {
    public FireLineDisplay(FireLineMessage flm) {
        this.setFireLineMessage(flm);
    }
    public void setFireLineMessage(FireLineMessage fireLineMessage) {
        this.fireLineMessage = fireLineMessage;
    }
    public FireLineMessage getFireLineMessage() {
        return fireLineMessage;
    }
    private FireLineMessage fireLineMessage;

}
