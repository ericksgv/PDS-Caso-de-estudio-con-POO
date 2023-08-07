package puj.pdscaso_de_estudio_con_poo.modelo;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;


public class Cita {
    Usuario usuario;
    String fecha;
    String hora;

    public Cita() {
    }

    public Cita(Usuario usuario, String fecha, String hora) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void registrarCita() {

        // Obtener la cedula del usuario de la cita
        String nombreJSON = getUsuario().getCedula();

        // Obtener la fecha de la cita
        String fechaCita = this.getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaCitaParsed = LocalDate.parse(fechaCita);
        String fechaFormateada = fechaCitaParsed.format(formatter);

        // Crear el directorio con la fecha actual si no existe
        String rutaDirectorio = "C:\\Registro Citas Medicas" + File.separator + fechaFormateada;
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Serializar la cita a JSON usando Gson
        Gson gson = new Gson();
        String citaJson = gson.toJson(this);

        try {
            File archivo = new File(directorio, nombreJSON + ".json");
            FileUtils.writeStringToFile(archivo, citaJson, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}