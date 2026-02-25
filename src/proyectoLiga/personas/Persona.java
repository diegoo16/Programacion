package proyectoLiga.personas;

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String nacionalidad;

    public Persona(String nombre, String apellido, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " y ha nacido en " + nacionalidad ;
    }
}
