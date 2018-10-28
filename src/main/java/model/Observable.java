package model;

import java.util.ArrayList;
import java.util.List;

public class Observable implements ObservableInterface{

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer){
        if(!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(){
        for(Observer o: observers){
            o.update();
        }
    }
}
