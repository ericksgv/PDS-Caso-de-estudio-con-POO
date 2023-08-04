package puj.pdscaso_de_estudio_con_poo.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import puj.pdscaso_de_estudio_con_poo.main;
import puj.pdscaso_de_estudio_con_poo.modelo.Cita;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Buscar implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnRegresar;

    @FXML
    private ComboBox<Cita> cbFechasCitas;

    @FXML
    private TableView<?> tbCitas;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    @FXML
    void buscar(MouseEvent event) {

    }

    @FXML
    void regresar(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("agendamiento-citas.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Agendamiento de Citas");
        stage.setScene(scene);
        stage.show();
        this.btnRegresar.getScene().getWindow().hide();
    }

}
