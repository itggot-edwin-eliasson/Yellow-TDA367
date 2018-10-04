package Model;


import Controller.Controller;
import View.View;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObservableTest {

   @Test
    public void addObserver() {
       Observable observable = new Observable();
       Controller controller = new Controller();
       YellowHandler yellowHandler = new YellowHandler();
       View view = new View(observable, controller, yellowHandler);
        assertEquals(1, observable.getObservers().size());

    }

    @Test
    public void removeObserver() {
        Observable observable = new Observable();
        Controller controller = new Controller();
        YellowHandler yellowHandler = new YellowHandler();
        View view = new View(observable, controller, yellowHandler);
        System.out.println("Adding. Size of list is " + observable.getObservers().size());
        observable.removeObserver(view);
        System.out.println("Removing. Size of list is " + observable.getObservers().size());
        assertEquals(0, observable.getObservers().size());

    }



}