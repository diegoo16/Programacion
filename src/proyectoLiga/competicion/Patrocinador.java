package proyectoLiga.competicion;

public class Patrocinador {

    private int idPatrocinador;
    private String nombre;
    private double dinero;
    private String pais;

    public Patrocinador(int idPatrocinador, String nombre, double dinero) {
        this.idPatrocinador = idPatrocinador;
        this.nombre = nombre;
        this.dinero = dinero;
        this.pais = pais;
    }

    public int getIdPatrocinador() {
        return idPatrocinador;
    }

    public void setIdPatrocinador(int idPatrocinador) {
        this.idPatrocinador = idPatrocinador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return nombre + " aporta " + String.format("%,.0f" , dinero ) + " € ";
    }
}
