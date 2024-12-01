module org.kuznetsov.physicallaws {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.kuznetsov.physicallaws.App  to javafx.fxml;
    exports org.kuznetsov.physicallaws.App;
    opens org.kuznetsov.physicallaws.Controllers  to javafx.fxml;
    exports org.kuznetsov.physicallaws.Controllers;
    opens org.kuznetsov.physicallaws.Interfaces  to javafx.fxml;
    exports org.kuznetsov.physicallaws.Interfaces;
    opens org.kuznetsov.physicallaws.Models  to javafx.fxml;
    exports org.kuznetsov.physicallaws.Models;
    opens org.kuznetsov.physicallaws.Views  to javafx.fxml;
    exports org.kuznetsov.physicallaws.Views;
}