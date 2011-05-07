package NMLab.team10.rollingthecheese.gameSetting;

public class CheeseDisplay {
    public CheeseDisplay(CheeseMessage cm) {
        this.setCheeseMessage(cm);
    }
    public void setCheeseMessage(CheeseMessage cheeseMessage) {
        this.cheeseMessage = cheeseMessage;
    }
    public CheeseMessage getCheeseMessage() {
        return cheeseMessage;
    }
    private CheeseMessage cheeseMessage;
}
