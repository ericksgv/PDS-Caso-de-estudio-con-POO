module puj.pdscaso_de_estudio_con_poo {
    requires javafx.controls;
    requires javafx.fxml;


    opens puj.pdscaso_de_estudio_con_poo to javafx.fxml;
    exports puj.pdscaso_de_estudio_con_poo;
}