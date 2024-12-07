package org.kuznetsov.physicallaws.Controllers;

import org.kuznetsov.physicallaws.Interfaces.IObserver;
import org.kuznetsov.physicallaws.Models.PhysicalModel;
import org.kuznetsov.physicallaws.Views.PhysicalLawsView;

public class KineticEnergyController extends Thread implements IObserver {
    private short subStatus;
    private PhysicalModel physicalModel;
    private PhysicalLawsView physicalLawsView;
    public KineticEnergyController(PhysicalLawsView physicalLawsView, PhysicalModel diseaseModel) {
        subStatus = 1;
        this.physicalLawsView = physicalLawsView;
        this.physicalModel = diseaseModel;
        physicalLawsView.unsubscribeKineticEnergyButton.setOnAction(event -> {
            if (subStatus == 1) {
                subStatus = 0;
                physicalLawsView.unsubscribeKineticEnergyButton.setText("Subscribe");
            } else {
                subStatus = 1;
                physicalLawsView.unsubscribeKineticEnergyButton.setText("Unsubscribe");
            }
        }); // Отписка
        // Регистрация слушателей на изменение текста в полях
        physicalLawsView.weightTextProperty().addListener((observable, oldValue, newValue) -> {
            if(isValidDouble(newValue)) { updateWeight(newValue); }
            else { updateWeight(String.valueOf(0));}
        });
        physicalLawsView.speedTextProperty().addListener((observable, oldValue, newValue) -> {
            if(isValidDouble(newValue)) { updateSpeed(newValue); }
            else { updateSpeed(String.valueOf(0));}
        });
    }

    private void updateWeight(String newValue) {
        double weight = Double.parseDouble(newValue);
        if (physicalModel.getWeight() != weight) {
            physicalModel.setWeight(weight);
            physicalModel.calculation();
        }
    }

    private void updateSpeed(String newValue) {
        double speed = Double.parseDouble(newValue);
        if (physicalModel.getSpeed() != speed) {
            physicalModel.setSpeed(speed);
            physicalModel.calculation();
        }
    }

    @Override
    public void update(double newtonsSecondLaw, double kinematicEquation, double kineticEnergy) {
        physicalLawsView.setKineticEnergyText(kineticEnergy);
    }
    @Override
    public short getSubStatus() {
        return subStatus;
    }
    private boolean isValidDouble(String text) {
        return text.matches("-?\\d+(\\.\\d+)?");
    }
}
