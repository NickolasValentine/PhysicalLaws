package org.kuznetsov.physicallaws.Controllers;

import org.kuznetsov.physicallaws.Interfaces.IObserver;
import org.kuznetsov.physicallaws.Interfaces.ISubscription;
import org.kuznetsov.physicallaws.Models.PhysicalModel;
import org.kuznetsov.physicallaws.Views.PhysicalLawsView;

import java.util.ArrayList;
import java.util.List;

public class NewtonsSecondLawController extends Thread implements IObserver {

    private short subStatus;
    private PhysicalModel physicalModel;
    private PhysicalLawsView physicalLawsView;
    private volatile boolean running = true;
    public NewtonsSecondLawController(PhysicalLawsView physicalLawsView, PhysicalModel diseaseModel) {
        subStatus = 1;
        this.physicalLawsView = physicalLawsView;
        this.physicalModel = diseaseModel;
    }

    @Override
    public void run() {
        while (running) {
            updateData();
            physicalLawsView.unsubscribeNewtonsSecondLawButton.setOnAction(event ->{
                if (subStatus == 1) {
                    subStatus = 0;
                    physicalLawsView.unsubscribeNewtonsSecondLawButton.setText("Subscribe");
                } else {
                    subStatus = 1;
                    physicalLawsView.unsubscribeNewtonsSecondLawButton.setText("Unsubscribe");
                }
            }); // Отписка
        }
    }

    public void updateData() {
        physicalModel.setWeight(physicalLawsView.getWeight());
        physicalModel.setAcceleration(physicalLawsView.getAcceleration());
        physicalModel.setInitialSpeed(physicalLawsView.getInitialSpeed());
        physicalModel.setTime(physicalLawsView.getTime());
        physicalModel.setSpeed(physicalLawsView.getSpeed());
    }

    @Override
    public void update(double newtonsSecondLaw, double kinematicEquation, double kineticEnergy) {
        physicalLawsView.setNewtonsSecondLawText(newtonsSecondLaw);
    }
    @Override
    public short getSubStatus() {
        return subStatus;
    }

    public void stopController() {
        running = false;
    }
}
