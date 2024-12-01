package org.kuznetsov.physicallaws.Interfaces;

public interface IObserver {
    void update(double newtonsSecondLaw, double kinematicEquation, double kineticEnergy);
    short getSubStatus();
}
