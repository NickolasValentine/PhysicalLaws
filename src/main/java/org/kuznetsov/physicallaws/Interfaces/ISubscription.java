package org.kuznetsov.physicallaws.Interfaces;

public interface ISubscription {
    void register(IObserver observer);
    void unregister(IObserver observer);
    void notifyObserver();
}
