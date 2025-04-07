module com.daangit.la8kierulf {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.daangit.la8kierulf to javafx.fxml;
    exports com.daangit.la8kierulf;
}