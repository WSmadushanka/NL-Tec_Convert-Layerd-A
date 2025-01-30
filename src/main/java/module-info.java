module lk.ijse.nltec.nltecconvertlayerda {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires com.jfoenix;
    requires com.gluonhq.maps;
    requires java.mail;
    requires net.sf.jasperreports.core;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires webcam.capture;
    requires java.management;


    opens lk.ijse.nltec.nltecconvertlayerda.view.tdm.tm to javafx.fxml;
    opens lk.ijse.nltec.nltecconvertlayerda.controller to javafx.fxml;
    exports lk.ijse.nltec.nltecconvertlayerda;
    opens report to net.sf.jasperreports.core;
}