package View;

import Model.YellowHandler;
import Model.YellowHandlerInterface;

public class ViewController {

    public YellowHandlerInterface yh;

    public void injectYellowHandler(YellowHandlerInterface yh){
        this.yh = yh;
    }
}
