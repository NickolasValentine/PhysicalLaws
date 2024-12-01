package org.kuznetsov.physicallaws.Models;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.kuznetsov.physicallaws.Interfaces.IObserver;
import org.kuznetsov.physicallaws.Interfaces.ISubscription;

import java.util.ArrayList;
import java.util.List;

public class PhysicalModel implements ISubscription {
    public List<IObserver> observers;
    private double weight;
    private double acceleration;
    private double initialSpeed;
    private int time;
    private double speed;
    /////////////////////////////////////////////////////////////////
    private double newtonsSecondLaw;
    private double kinematicEquation;
    private double kineticEnergy;

    private Timeline updateOut;

    public PhysicalModel() {
        observers = new ArrayList<>();
        calculation();
    }

    public void setWeight(double weight) { this.weight = weight; }
    public void setAcceleration(double acceleration) { this.acceleration = acceleration; }
    public void setInitialSpeed(double initialSpeed) { this.initialSpeed = initialSpeed; }
    public void setTime(int time) { this.time = time; }
    public void setSpeed(double speed) { this.speed = speed; }
    public double getNewtonsSecondLaw() { return newtonsSecondLaw; }
    public double getKinematicEquation() { return kinematicEquation; }
    public double getKineticEnergy() { return kineticEnergy; }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setNewtonsSecondLaw(double newtonsSecondLaw) { this.newtonsSecondLaw = newtonsSecondLaw; }
    public void setKinematicEquation(double kinematicEquation) { this.kinematicEquation = kinematicEquation; }
    public void setKineticEnergy(double kineticEnergy) { this.kineticEnergy = kineticEnergy; }

    @Override
    public void register(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        List<Integer> deleteMass = new ArrayList<>();
        for (int i = 0; i < observers.size(); i++) {
            if(observers.get(i).getSubStatus() == 1) { observers.get(i).update(getNewtonsSecondLaw(), getKinematicEquation(), getKineticEnergy()); }
            else if (observers.get(i).getSubStatus() == 2) { deleteMass.add(i);}
        }
        for (Integer mass : deleteMass) { unregister(observers.get(mass)); }
    }

    public void calculation() {
        updateOut = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            setNewtonsSecondLaw(weight * acceleration);
            setKinematicEquation(initialSpeed + acceleration * time);
            setKineticEnergy((weight * Math.pow(speed, 2)) / 2);
            notifyObserver();
        }));
        updateOut.setCycleCount(Timeline.INDEFINITE);
        updateOut.play();
    }
}
