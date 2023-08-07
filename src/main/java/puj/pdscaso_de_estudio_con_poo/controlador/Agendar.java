package puj.pdscaso_de_estudio_con_poo.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import puj.pdscaso_de_estudio_con_poo.main;
import puj.pdscaso_de_estudio_con_poo.modelo.Cita;
import puj.pdscaso_de_estudio_con_poo.modelo.Usuario;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class Agendar implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(Agendar.class.getName());

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

        initDatePicker();
        initHourSpinner();

        txtCedula.setTextFormatter(verificarCampoNumerico());
        txtNombres.setTextFormatter(verificarCampoAlfabetico());
        txtApellidos.setTextFormatter(verificarCampoAlfabetico());
        txtEdad.setTextFormatter(verificarCampoNumerico());
    }

    private void initDatePicker() {
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
    }

    private void initHourSpinner() {
        // Configurar el Spinner para trabajar con LocalTime y formato AM/PM
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<>() {
            {
                setConverter(new StringConverter<LocalTime>() {
                    @Override
                    public String toString(LocalTime time) {
                        if (time == null) {
                            return "";
                        }
                        return timeFormatter.format(time);
                    }

                    @Override
                    public LocalTime fromString(String string) {
                        try {
                            return LocalTime.parse(string, timeFormatter);
                        } catch (DateTimeParseException e) {
                            return getValue(); // Restaurar el valor anterior si el formato es inválido
                        }
                    }
                });
                setWrapAround(true); // Permite que el Spinner vuelva a comenzar después de llegar al final
            }

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
        };
        spnHora.setValueFactory(valueFactory);
        spnHora.setEditable(true);
    }

    private TextFormatter<String> verificarCampoNumerico(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                // Si el cambio contiene caracteres que no son números, rechazar el cambio
                return null;
            }
            return change; // Aceptar el cambio
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        return textFormatter;
    }

    private TextFormatter<String> verificarCampoAlfabetico(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (!change.getControlNewText().matches("[a-zA-Z ]*")) {
                // Si el cambio contiene caracteres que no son letras, rechazar el cambio
                return null;
            }
            return change; // Aceptar el cambio
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        return textFormatter;
    }

    private boolean verificarCampos() {
        if (txtCedula.getText().isEmpty() || txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty() || txtEdad.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean verificarCamposFechaHora() {
        String fechaText = dpFecha.getEditor().getText();
        String horaText = spnHora.getEditor().getText().trim(); // Eliminar espacios al principio y al final

        // Verificar la hora con regex y parse
        if (!horaText.matches("\\d{1,2}:\\d{2}\\s?(?i)(a\\.?m?\\.?|p\\.?m?\\.?)?")) {
            // El formato de hora no es válido (debe ser hh:mm a.m. o hh:mm p.m. o hh:mm am o hh:mm pm)
            return false;
        }

        // Si pasa todas las validaciones, los campos de fecha y hora son válidos
        return true;
    }

    private void alerta(Alert.AlertType tipoAlerta, String mensaje) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        // Mostrar el diálogo y esperar a que el usuario interactúe con él
        Optional<ButtonType> resultado = alert.showAndWait();

        // Verificar el resultado dependiendo del tipo de alerta
         if (tipoAlerta == Alert.AlertType.CONFIRMATION) {
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // El usuario seleccionó "Aceptar"
                limpiarCampos();
            }
        }
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("agendamiento-citas.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Agendamiento de Citas");
        stage.setScene(scene);
        stage.show();
        this.btnRegresar.getScene().getWindow().hide();
    }

    public void limpiarCampos() {
        txtCedula.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        dpFecha.setValue(null);
        spnHora.setValueFactory(null);
    }

    @FXML
    private void registrarCita(MouseEvent event) {

        if(!verificarCampos()){
            alerta(Alert.AlertType.ERROR, "Por favor, ingrese todos los datos");
        }
        else if(!verificarCamposFechaHora()){
            alerta(Alert.AlertType.ERROR, "Por favor, ingrese una fecha y hora válidas");
        }
        else{
            String cedula = txtCedula.getText();
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String edadStr = txtEdad.getText();
            int edad = Integer.parseInt(edadStr);


            // Crear el objeto Usuario
            Usuario usuario = new Usuario(cedula, nombres, apellidos, edad);
            System.out.println(usuario);

            // Obtener la fecha y hora de la cita
            String fechaCita = String.valueOf(dpFecha.getValue());
            String horaCita = String.valueOf(spnHora.getValue());

            // Crear el objeto Cita
            Cita cita = new Cita(usuario, fechaCita, horaCita);

            // Registrar la cita
            cita.registrarCita();

            alerta(Alert.AlertType.CONFIRMATION, "Cita registrada con éxito");
        }
    }
}