package Model;


import View.View;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObservableTest {
    @Test
    public void addObserver() {
        Observable observable = new Observable();
        View view = new View(observable);
        assertEquals(1, observable.getObservers().size());

    }

    @Test
    public void removeObserver() {
        Observable observable = new Observable();
        View view = new View(observable);
        System.out.println("Adding. Size of list is " + observable.getObservers().size());
        observable.removeObserver(view);
        System.out.println("Removing. Size of list is " + observable.getObservers().size());
        assertEquals(0, observable.getObservers().size());

    }



}