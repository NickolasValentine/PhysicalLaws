package org.kuznetsov.physicallaws.Views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PhysicalLawsView extends VBox {
    private TextField weightField;
    private TextField accelerationField;
    private TextField initialSpeedField;
    private TextField timeField;
    private TextField speedField;
    private Text newtonsSecondLawText;
    private Text kinematicEquationText;
    private Text kineticEnergyText;
    public Button unsubscribeNewtonsSecondLawButton;
    public Button unsubscribeKinematicEquationButton;
    public Button unsubscribeKineticEnergyButton;

    public PhysicalLawsView() {
        initialize();
    }

    public void initialize() {
        // Настройки основного VBox
        this.setPadding(new Insets(30)); // Общий отступ
        this.setSpacing(20); // Расстояние между элементами
        this.setAlignment(Pos.TOP_CENTER); // Выравнивание по центру

        // Заголовок
        Label mainInscription = new Label("Physical Laws Calculator");
        mainInscription.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        mainInscription.setStyle("-fx-fill: #2c3e50;"); // Цвет текста

        // Поля ввода
        VBox inputFields = createInputFields();
        inputFields.setSpacing(15);
        inputFields.setPadding(new Insets(20));
        inputFields.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Вывод результатов
        VBox outputFields = createOutputFields();
        outputFields.setSpacing(15);
        outputFields.setPadding(new Insets(20));
        outputFields.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #bdc3c7; -fx-border-radius: 10; -fx-background-radius: 10;");

        this.getChildren().addAll(mainInscription, inputFields, outputFields);
    }

    private VBox createInputFields() {
        // Создание элементов для ввода
        VBox weight = createInputField("Weight (kg): ", weightField = new TextField());
        VBox acceleration = createInputField("Acceleration (m/s²): ", accelerationField = new TextField());
        VBox initialSpeed = createInputField("Initial speed (m/s): ", initialSpeedField = new TextField());
        VBox time = createInputField("Time (s): ", timeField = new TextField());
        VBox speed = createInputField("Speed (m/s): ", speedField = new TextField());

        VBox inputFields = new VBox(weight, acceleration, initialSpeed, time, speed);
        inputFields.setSpacing(10);
        inputFields.setAlignment(Pos.CENTER_LEFT);

        return inputFields;
    }

    private VBox createInputField(String labelText, TextField textField) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
        textField.setPrefWidth(200);
        textField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-padding: 5;");
        return new VBox(5, label, textField);
    }

    private VBox createOutputFields() {
        Label outputLabel = new Label("Results:");
        outputLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        HBox newtonsSecondLaw = createOutputField("Newton's Second Law: ", newtonsSecondLawText = new Text(), unsubscribeNewtonsSecondLawButton = new Button("Unsubscribe"));
        HBox kinematicEquation = createOutputField("Kinematic Equation: ", kinematicEquationText = new Text(), unsubscribeKinematicEquationButton = new Button("Unsubscribe"));
        HBox kineticEnergy = createOutputField("Kinetic Energy: ", kineticEnergyText = new Text(), unsubscribeKineticEnergyButton = new Button("Unsubscribe"));

        VBox outputFields = new VBox(outputLabel, newtonsSecondLaw, kinematicEquation, kineticEnergy);
        outputFields.setSpacing(15);
        outputFields.setAlignment(Pos.CENTER_LEFT);

        return outputFields;
    }

    private HBox createOutputField(String labelText, Text outputText, Button button) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));

        outputText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        outputText.setStyle("-fx-fill: #2c3e50;");

        button.setFont(Font.font("Arial", FontWeight.MEDIUM, 12));
        button.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 5;");
        button.setPrefWidth(100);

        HBox outputField = new HBox(10, label, outputText, button);
        outputField.setAlignment(Pos.CENTER_LEFT);
        return outputField;
    }

    // Методы для получения данных

    public ObservableValue<String> weightTextProperty() { return weightField.textProperty(); }
    public ObservableValue<String> accelerationTextProperty() { return accelerationField.textProperty(); }
    public ObservableValue<String> initialSpeedTextProperty() { return initialSpeedField.textProperty(); }
    public ObservableValue<String> timeTextProperty() { return timeField.textProperty(); }
    public ObservableValue<String> speedTextProperty() { return speedField.textProperty(); }

    // Вспомогательные методы
    private boolean isValidDouble(String text) {
        return text.matches("-?\\d+(\\.\\d+)?");
    }

    public void setNewtonsSecondLawText(double newtonsSecondLaw) {
        newtonsSecondLawText.setText(String.valueOf(newtonsSecondLaw));
    }
    public void setKinematicEquationText(double kinematicEquation) {
        kinematicEquationText.setText(String.valueOf(kinematicEquation));
    }
    public void setKineticEnergyText(double kineticEnergy) {
        kineticEnergyText.setText(String.valueOf(kineticEnergy));
    }
}
