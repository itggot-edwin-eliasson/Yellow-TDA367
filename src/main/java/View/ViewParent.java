package View;

import Model.YellowHandler;
import Model.YellowHandlerInterface;

public class ViewParent {

    public YellowHandlerInterface yh;

    public void injectYellowHandler(YellowHandlerInterface yh){
        this.yh = yh;
    }
}
