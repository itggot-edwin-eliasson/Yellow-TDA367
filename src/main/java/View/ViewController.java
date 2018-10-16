package View;

import Model.YellowHandlerInterface;

public class ViewController {

    private YellowHandlerInterface yh;

    public void injectYellowHandler(YellowHandlerInterface yh){
        this.yh = yh;
    }
}
