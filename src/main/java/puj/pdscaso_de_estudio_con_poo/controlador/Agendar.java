package puj.pdscaso_de_estudio_con_poo.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import puj.pdscaso_de_estudio_con_poo.main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Agendar implements Initializable {

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnRegresar;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private Spinner<LocalTime> spnHora;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombres;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Establecer la fecha actual como valor mínimo del DatePicker
        dpFecha.setValue(fechaActual);

        dpFecha.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(fechaActual));
            }
        });


        // Configurar el Spinner para trabajar con LocalTime y formato AM/PM
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<>() {
            @Override
            public void decrement(int steps) {
                if (getValue() == null) {
                    setValue(LocalTime.now());
                } else {
                    LocalTime time = getValue().minusMinutes(steps);
                    setValue(time);
                }
            }

            @Override
            public void increment(int steps) {
                if (getValue() == null) {
                    setValue(LocalTime.now());
                } else {
                    LocalTime time = getValue().plusMinutes(steps);
                    setValue(time);
                }
            }


            {
                setConverter(new LocalTimeStringConverter(timeFormatter, null));
                setWrapAround(true); // Permite que el Spinner vuelva a comenzar después de llegar al final
            }
        };
        spnHora.setValueFactory(valueFactory);
        spnHora.setEditable(true);

    }

    @FXML
    void registrarCita(MouseEvent event) {

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