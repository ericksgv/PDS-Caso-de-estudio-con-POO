module puj.pdscaso_de_estudio_con_poo {

    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.logging;
    requires com.google.gson;
    requires org.apache.commons.io;

    opens puj.pdscaso_de_estudio_con_poo to javafx.fxml;
    opens puj.pdscaso_de_estudio_con_poo.modelo;
    opens puj.pdscaso_de_estudio_con_poo.controlador to javafx.fxml;

    exports puj.pdscaso_de_estudio_con_poo;
    exports puj.pdscaso_de_estudio_con_poo.controlador;
    exports puj.pdscaso_de_estudio_con_poo.modelo;
}