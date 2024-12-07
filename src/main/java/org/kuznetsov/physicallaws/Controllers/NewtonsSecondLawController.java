package org.kuznetsov.physicallaws.Controllers;

import org.kuznetsov.physicallaws.Interfaces.IObserver;
import org.kuznetsov.physicallaws.Models.PhysicalModel;
import org.kuznetsov.physicallaws.Views.PhysicalLawsView;

public class NewtonsSecondLawController implements IObserver {

    private short subStatus;
    private PhysicalModel physicalModel;
    private PhysicalLawsView physicalLawsView;
    public NewtonsSecondLawController(PhysicalLawsView physicalLawsView, PhysicalModel diseaseModel) {
        subStatus = 1;
        this.physicalLawsView = physicalLawsView;
        this.physicalModel = diseaseModel;
        physicalLawsView.unsubscribeNewtonsSecondLawButton.setOnAction(event ->{
            if (subStatus == 1) {
                subStatus = 0;
                physicalLawsView.unsubscribeNewtonsSecondLawButton.setText("Subscribe");
            } else {
                subStatus = 1;
                physicalLawsView.unsubscribeNewtonsSecondLawButton.setText("Unsubscribe");
            }
        }); // Отписка
        // Регистрация слушателей на изменение текста в полях
        physicalLawsView.weightTextProperty().addListener((observable, oldValue, newValue) -> {
            if(isValidDouble(newValue)) { updateWeight(newValue); }
            else { updateWeight(String.valueOf(0));}
        });
        physicalLawsView.accelerationTextProperty().addListener((observable, oldValue, newValue) -> {
            if(isValidDouble(newValue)) { updateAcceleration(newValue); }
            else { updateAcceleration(String.valueOf(0));}
        });
    }

    private void updateWeight(String newValue) {
        double weight = Double.parseDouble(newValue);
        if (physicalModel.getWeight() != weight) {
            physicalModel.setWeight(weight);
            physicalModel.calculation();
        }
    }

    private void updateAcceleration(String newValue) {
        double acceleration = Double.parseDouble(newValue);
        if (physicalModel.getAcceleration() != acceleration) {
            physicalModel.setAcceleration(acceleration);
            physicalModel.calculation();
        }
    }

    @Override
    public void update(double newtonsSecondLaw, double kinematicEquation, double kineticEnergy) {
        physicalLawsView.setNewtonsSecondLawText(newtonsSecondLaw);
    }
    @Override
    public short getSubStatus() {
        return subStatus;
    }
    private boolean isValidDouble(String text) {
        return text.matches("-?\\d+(\\.\\d+)?");
    }
}
