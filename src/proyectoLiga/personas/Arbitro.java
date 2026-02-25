package proyectoLiga.personas;

public class Arbitro extends Persona {

    private int idArbitro;


    public Arbitro(String nombre, String apellido, String nacionalidad, int idArbitro) {
        super(nombre, apellido, nacionalidad);
        this.idArbitro = idArbitro;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    @Override
    public String toString() {
        return "Arbitro: " + nombre + " " + apellido + " y ha nacido en " + nacionalidad;
    }
}

