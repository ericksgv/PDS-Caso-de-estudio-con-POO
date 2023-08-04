module puj.pdscaso_de_estudio_con_poo {
    requires javafx.controls;
    requires javafx.fxml;


    opens puj.pdscaso_de_estudio_con_poo to javafx.fxml;
    exports puj.pdscaso_de_estudio_con_poo;
    exports puj.pdscaso_de_estudio_con_poo.controlador;
    opens puj.pdscaso_de_estudio_con_poo.controlador to javafx.fxml;
}