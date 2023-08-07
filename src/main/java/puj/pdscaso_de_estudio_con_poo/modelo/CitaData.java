package puj.pdscaso_de_estudio_con_poo.modelo;


import javafx.scene.control.ComboBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitaData {

    private String cedula;
    private String nombre;
    private String hora;
    private List<CitaData> citasEncontradas;  // Lista para almacenar las citas encontradas


    public CitaData() {
        citasEncontradas = new ArrayList<>();
    }

    public CitaData(String cedula, String nombre, String hora) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.hora = hora;
        citasEncontradas = new ArrayList<>();
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHora() {
        return hora;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void colocarFechaComboBox(ComboBox<String> comboBox) {
        File directory = new File("D:\\Registro Citas Medicas");
        if (directory.exists() && directory.isDirectory()) {
            File[] subdirectories = directory.listFiles(File::isDirectory);
            if (subdirectories != null) {
                List<String> nombresSubdirectorios = new ArrayList<>();
                for (File subdirectory : subdirectories) {
                    nombresSubdirectorios.add(subdirectory.getName());
                }
                comboBox.getItems().addAll(nombresSubdirectorios);
            }
        } else {
            // Manejar el caso si el directorio no existe o no es válido
            System.out.println("El directorio no existe o no es válido.");
        }
    }

    public List<CitaData> cargarCitasDesdeDirectorio(String subdirectorio) {
        List<CitaData> citas = new ArrayList<>();

        File directorio = new File("D:\\Registro Citas Medicas\\" + subdirectorio);
        if (directorio.exists() && directorio.isDirectory()) {
            File[] archivosJson = directorio.listFiles((dir, name) -> name.endsWith(".json"));
            if (archivosJson != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                for (File archivoJson : archivosJson) {
                    try {
                        CitaData cita = objectMapper.readValue(archivoJson, CitaData.class);
                        citas.add(cita);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return citas;
    }
}