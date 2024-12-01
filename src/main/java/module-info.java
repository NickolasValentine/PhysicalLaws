module org.kuznetsov.physicallaws {
    // Экспортируем пакеты, чтобы они были доступны JavaFX и другим модулям
    exports org.kuznetsov.physicallaws.App;
    exports org.kuznetsov.physicallaws.Controllers;
    exports org.kuznetsov.physicallaws.Models;
    exports org.kuznetsov.physicallaws.Views;
    exports org.kuznetsov.physicallaws.Interfaces;

    // Требуемые JavaFX модули
    requires javafx.controls;
    requires javafx.fxml;

    // Открываем для рефлексии (нужно для FXML)
    opens org.kuznetsov.physicallaws.App to javafx.fxml;
    opens org.kuznetsov.physicallaws.Controllers to javafx.fxml;
}