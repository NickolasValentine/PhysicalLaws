package org.kuznetsov.physicallaws.App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kuznetsov.physicallaws.Views.PhysicalLawsAppView;

import java.io.IOException;

public class PhysicalLawsApp extends Application {
    @Override
    public void start(Stage primsryStage) throws IOException {
        PhysicalLawsAppView physicalLawsAppView = new PhysicalLawsAppView();
        Scene scene = new Scene(physicalLawsAppView, 600, 810);
        primsryStage.setTitle("PhysicalLawsApp");
        primsryStage.setMinWidth(280);
        primsryStage.setMinHeight(810);
        primsryStage.getIcons().add(new Image(getClass().getResourceAsStream("icons/atom.png")));
        primsryStage.setScene(scene);

        primsryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}