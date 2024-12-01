package org.kuznetsov.physicallaws.Controllers;

import org.kuznetsov.physicallaws.Interfaces.IObserver;
import org.kuznetsov.physicallaws.Models.PhysicalModel;
import org.kuznetsov.physicallaws.Views.PhysicalLawsView;

public class KineticEnergyController extends Thread implements IObserver {
    private short subStatus;
    private PhysicalModel physicalModel;
    private PhysicalLawsView physicalLawsView;
    private volatile boolean running = true;
    public KineticEnergyController(PhysicalLawsView physicalLawsView, PhysicalModel diseaseModel) {
        subStatus = 1;
        this.physicalLawsView = physicalLawsView;
        this.physicalModel = diseaseModel;
    }

    @Override
    public void run() {
        while (running) {
            updateData();
            physicalLawsView.unsubscribeKineticEnergyButton.setOnAction(event -> {
                if (subStatus == 1) {
                    subStatus = 0;
                    physicalLawsView.unsubscribeKineticEnergyButton.setText("Subscribe");
                } else {
                    subStatus = 1;
                    physicalLawsView.unsubscribeKineticEnergyButton.setText("Unsubscribe");
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
        physicalLawsView.setKineticEnergyText(kineticEnergy);
    }
    @Override
    public short getSubStatus() {
        return subStatus;
    }

    public void stopController() {
        running = false;
    }
}
