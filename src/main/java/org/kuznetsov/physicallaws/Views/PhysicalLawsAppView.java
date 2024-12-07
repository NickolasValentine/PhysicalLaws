package org.kuznetsov.physicallaws.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.kuznetsov.physicallaws.Controllers.KinematicEquationController;
import org.kuznetsov.physicallaws.Controllers.KineticEnergyController;
import org.kuznetsov.physicallaws.Controllers.NewtonsSecondLawController;
import org.kuznetsov.physicallaws.Models.PhysicalModel;

public class PhysicalLawsAppView extends VBox {
    private PhysicalModel physicalModel;
    private TextFlow infoLabel;
    private Button newCalculator;

    public PhysicalLawsAppView() {
        initialize();
        physicalModel = new PhysicalModel();
    }
    private void initialize() {
        this.setPadding(new Insets(30)); // Общий отступ
        this.setSpacing(20); // Расстояние между элементами
        this.setAlignment(Pos.TOP_CENTER); // Выравнивание по центру

        // Заголовок
        Label mainInscription = new Label("Physical Laws Calculator");
        mainInscription.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        mainInscription.setStyle("-fx-fill: #2c3e50;"); // Цвет текста

        infoLabel = new TextFlow(
                createStyledText("1. Второй закон Ньютона (F = ma)\n", true),
                createStyledText("F = ma\n\n", false),
                createStyledText("Где:\n", true),
                createStyledText("    • F — сила (Ньютон, Н);\n", false),
                createStyledText("    • m — масса (кг);\n", false),
                createStyledText("    • a — ускорение (м/с²).\n\n", false),

                createStyledText("2. Кинематическое уравнение (v = v₀ + at)\n", true),
                createStyledText("v = v₀ + at\n\n", false),
                createStyledText("Где:\n", true),
                createStyledText("    • v — конечная скорость (м/с);\n", false),
                createStyledText("    • v₀ — начальная скорость (м/с);\n", false),
                createStyledText("    • a — ускорение (м/с²);\n", false),
                createStyledText("    • t — время (с).\n\n", false),

                createStyledText("3. Кинетическая энергия (Ek = mv² / 2)\n", true),
                createStyledText("Ek = mv² / 2\n\n", false),
                createStyledText("Где:\n", true),
                createStyledText("    • Ek — кинетическая энергия (Дж);\n", false),
                createStyledText("    • m — масса (кг);\n", false),
                createStyledText("    • v — скорость (м/с).\n\n", false),

                createStyledText("Связь переменных:\n", true),
                createStyledText("• m, a — Второй закон Ньютона.\n", false),
                createStyledText("• v₀, a, t — Кинематика.\n", false),
                createStyledText("• m, v — Кинетическая энергия.", false)
        );

        infoLabel.setPadding(new Insets(20));

        // Обернуть infoLabel в VBox
        VBox contentBox = new VBox(infoLabel);
        contentBox.setPadding(new Insets(15));
        contentBox.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #dcdcdc; -fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8;");

        // Создание ScrollPane
        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        // Убираем рамку и фон
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        //scrollPane.setPrefHeight(660); // Задаём желаемую высоту

        newCalculator = new Button("Open calculator");
        newCalculator.setFont(Font.font("Arial", FontWeight.MEDIUM, 24));
        newCalculator.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 5;");
        newCalculator.setPrefWidth(200);
        newCalculator.setOnAction(event -> {
            Stage stage = new Stage();

            PhysicalLawsView physicalLawsView = new PhysicalLawsView();

            NewtonsSecondLawController newtonsSecondLawController = new NewtonsSecondLawController(physicalLawsView, physicalModel);

            KinematicEquationController kinematicEquationController = new KinematicEquationController(physicalLawsView, physicalModel);

            KineticEnergyController kineticEnergyController = new KineticEnergyController(physicalLawsView, physicalModel);

            physicalModel.register(newtonsSecondLawController);
            physicalModel.register(kinematicEquationController);
            physicalModel.register(kineticEnergyController);

            Scene scene = new Scene(physicalLawsView, 600, 650);
            stage.setTitle("PhysicalLaws");
            stage.setMinWidth(600);
            stage.setMinHeight(670);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icons/calculator.png")));
            stage.setScene(scene);
            stage.show();
        });

        this.getChildren().addAll(mainInscription, scrollPane, newCalculator);
    }
    private Text createStyledText(String content, boolean isBold) {
        Text text = new Text(content);
        text.setFont(Font.font("Verdana", isBold ? FontWeight.BOLD : FontWeight.NORMAL, 16));
        return text;
    }

}
