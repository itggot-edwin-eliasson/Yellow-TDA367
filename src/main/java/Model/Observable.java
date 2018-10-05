package Model;
import java.util.ArrayList;

/**
 * @author Joakim Agnemyr
 * @date 02-10-2018
 *
 */
public class Observable {

    private ArrayList<Observer> observers;

    public Observable () {
        observers = new ArrayList<>();
    }

    /**
     * Adds an observer to the observer list
     * @param newObserver the new Observer
     */

    public void addObserver(Observer newObserver){
        observers.add(newObserver);

    }

    /**
     * Removes observer from the observer list
     * @param deleteObserver the observer that is going to be removed
     */
    public void removeObserver(Observer deleteObserver){
        int observerIndex = observers.indexOf(deleteObserver);
        observers.remove(observerIndex);

    }

    /**
     * Tells all observers that something has changed in the model
     */
    public void notifyObserver (){

        for (Observer observer : observers) {
            observer.update();
        }

    }

    public ArrayList <Observer> getObservers () {
        return this.observers;
    }


}
