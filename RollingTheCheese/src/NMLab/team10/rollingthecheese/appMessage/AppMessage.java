package NMLab.team10.rollingthecheese.appMessage;

public class AppMessage {

    AppMessageEnum type;
    Object data;

    public AppMessage(AppMessageEnum type) {
        this.type = type;
    }

    public void setData(Object o){
        this.data = o;
    }

    public AppMessageEnum getType() {
        return type;
    }

    public void setType(AppMessageEnum type) {
        this.type = type;
    }
}
