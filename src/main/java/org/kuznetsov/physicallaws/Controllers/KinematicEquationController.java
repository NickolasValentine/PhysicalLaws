package org.kuznetsov.physicallaws.Controllers;

import org.kuznetsov.physicallaws.Interfaces.IObserver;
import org.kuznetsov.physicallaws.Models.PhysicalModel;
import org.kuznetsov.physicallaws.Views.PhysicalLawsView;

public class KinematicEquationController implements IObserver {

    private short subStatus;
    private PhysicalModel physicalModel;
    private PhysicalLawsView physicalLawsView;
    public KinematicEquationController(PhysicalLawsView physicalLawsView, PhysicalModel diseaseModel) {
        subStatus = 1;
        this.physicalLawsView = physicalLawsView;
        this.physicalModel = diseaseModel;
        physicalLawsView.unsubscribeKinematicEquationButton.setOnAction(event -> {
            if (subStatus == 1) {
                subStatus = 0;
                physicalLawsView.unsubscribeKinematicEquationButton.setText("Subscribe");
            } else {
                subStatus = 1;
                physicalLawsView.unsubscribeKinematicEquationButton.setText("Unsubscribe");
            }
        }); // Отписка
        physicalLawsView.accelerationTextProperty().addListener((observable, oldValue, newValue) -> {
            if(isValidDouble(newValue)) { updateAcceleration(newValue); }
            else { updateAcceleration(String.valueOf(0));}
        });
        physicalLawsView.initialSpeedTextProperty().addListener((observable, oldValue, newValue) -> {
            if(isValidDouble(newValue)) { updateInitialSpeed(newValue); }
            else { updateInitialSpeed(String.valueOf(0));}
        });
        physicalLawsView.timeTextProperty().addListener((observable, oldValue, newValue) -> {
            if(isValidDouble(newValue)) { updateTime(newValue); }
            else { updateTime(String.valueOf(0));}
        });

    }

    private void updateAcceleration(String newValue) {
        double acceleration = Double.parseDouble(newValue);
        if (physicalModel.getAcceleration() != acceleration) {
            physicalModel.setAcceleration(acceleration);
            physicalModel.calculation();
        }
    }
    private void updateInitialSpeed(String newValue) {
        double initialSpeed = Double.parseDouble(newValue);
        if (physicalModel.getInitialSpeed() != initialSpeed) {
            physicalModel.setInitialSpeed(initialSpeed);
            physicalModel.calculation();
        }
    }
    private void updateTime(String newValue) {
        int time = Integer.parseInt(newValue);
        if (physicalModel.getTime() != time) {
            physicalModel.setTime(time);
            physicalModel.calculation();
        }
    }

    @Override
    public void update(double newtonsSecondLaw, double kinematicEquation, double kineticEnergy) {
        physicalLawsView.setKinematicEquationText(kinematicEquation);
    }
    @Override
    public short getSubStatus() { return subStatus; }
    private boolean isValidDouble(String text) { return text.matches("-?\\d+(\\.\\d+)?"); }
}
