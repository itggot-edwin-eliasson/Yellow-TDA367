package model;

public interface ObservableInterface {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
