package View;

import Model.Observable;

public class View implements Observer {

    public View (Observable t) {
        t.addObserver((this));

    }







    @Override
    public void update() {

    }
}
