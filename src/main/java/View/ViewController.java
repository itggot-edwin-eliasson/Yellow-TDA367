package View;

import Model.YellowHandler;

public class ViewController {

    public YellowHandler yh;

    public void injectYellowHandler(YellowHandler yh){
        this.yh = yh;
    }
}
