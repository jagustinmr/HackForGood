package h4g.iot4all;

/**
 * Created by JosePC on 09/03/2018.
 */

public class dispositivo {
    private int imagen;
    private String nombre;
    private String hora_inicio;
    private String hora_final;
    private int intensidad;

    public dispositivo(int imagen, String nombre, String hora_inicio, String hora_final, int intensidad) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.intensidad = intensidad;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }
}
