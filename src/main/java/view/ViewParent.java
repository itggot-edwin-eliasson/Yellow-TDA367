package view;

import model.YellowHandlerInterface;

public class ViewParent {

    public YellowHandlerInterface yh;

    public void injectYellowHandler(YellowHandlerInterface yh){
        this.yh = yh;
    }
}
