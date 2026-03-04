package proyectoLiga.competicion;

public class Premio {

    private String nombre;
    private String ganador;

    public Premio(String nombre, String ganador) {
        this.nombre = nombre;
        this.ganador = ganador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        return nombre + ": ·" + ganador;
    }
}
