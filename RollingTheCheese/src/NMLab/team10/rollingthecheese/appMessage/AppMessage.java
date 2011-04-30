package NMLab.team10.rollingthecheese.appMessage;

public class AppMessage {

    AppMessageType type;
    Object data;

    public AppMessage(AppMessageType type) {
        this.type = type;
    }

    public void setData(Object o){
        this.data = o;
    }

    public AppMessageType getType() {
        return type;
    }

    public void setType(AppMessageType type) {
        this.type = type;
    }
}
