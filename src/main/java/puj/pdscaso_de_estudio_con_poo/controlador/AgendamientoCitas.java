package puj.pdscaso_de_estudio_con_poo.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import puj.pdscaso_de_estudio_con_poo.main;

import java.io.IOException;

public class AgendamientoCitas {

    @FXML
    private Button btnAgendar;

    @FXML
    void agendar(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("agendar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Agendamiento de Citas");
        stage.setScene(scene);
        stage.show();
        this.btnAgendar.getScene().getWindow().hide();
    }

    @FXML
    void buscar(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("buscar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Agendamiento de Citas");
        stage.setScene(scene);
        stage.show();
        this.btnAgendar.getScene().getWindow().hide();
    }

}
