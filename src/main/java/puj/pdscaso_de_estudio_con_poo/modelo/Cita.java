package puj.pdscaso_de_estudio_con_poo.modelo;

public class Cita {
    Usuario usuario;
    String fecha;
    String hora;

    public Cita() {}

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

}
