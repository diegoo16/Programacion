package proyectoLiga.personas;

public class Entrenador extends Persona {

    private int idEntrenador;

    public Entrenador(String nombre, String apellido, String nacionalidad, int idEntrenador) {
        super(nombre, apellido, nacionalidad);
        this.idEntrenador = idEntrenador;
    }

    @Override
    public String toString() {
        return "Entrenador: " + nombre + " " + apellido + " y ha nacido en " + nacionalidad;
    }
}
