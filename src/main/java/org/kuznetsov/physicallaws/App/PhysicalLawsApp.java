package org.kuznetsov.physicallaws.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kuznetsov.physicallaws.Controllers.KinematicEquationController;
import org.kuznetsov.physicallaws.Controllers.KineticEnergyController;
import org.kuznetsov.physicallaws.Controllers.NewtonsSecondLawController;
import org.kuznetsov.physicallaws.Models.PhysicalModel;
import org.kuznetsov.physicallaws.Views.PhysicalLawsView;

import java.io.IOException;

public class PhysicalLawsApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PhysicalModel physicalModel = new PhysicalModel();
        PhysicalLawsView physicalLawsView = new PhysicalLawsView();

        NewtonsSecondLawController newtonsSecondLawController = new NewtonsSecondLawController(physicalLawsView, physicalModel);
        newtonsSecondLawController.start();

        KinematicEquationController kinematicEquationController = new KinematicEquationController(physicalLawsView, physicalModel);
        kinematicEquationController.start();

        KineticEnergyController kineticEnergyController = new KineticEnergyController(physicalLawsView, physicalModel);
        kineticEnergyController.start();

        physicalModel.register(newtonsSecondLawController);
        physicalModel.register(kinematicEquationController);
        physicalModel.register(kineticEnergyController);

        stage.setOnCloseRequest(event -> {
            newtonsSecondLawController.stopController();
            kinematicEquationController.stopController();
            kineticEnergyController.stopController();
        });

        Scene scene = new Scene(physicalLawsView, 600, 650);
        stage.setTitle("PhysicalLawsApp");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icons/atom.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}