module scheduler.littleschedulerapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens scheduler.appClasses to javafx.fxml;
    exports scheduler.appClasses;
    exports scheduler.time;
    opens scheduler.time to javafx.fxml;
}