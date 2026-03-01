package proyectoLiga.competicion;
import java.util.ArrayList;
import java.util.List;
public class Liga {
    private int idLiga;
    private String nombre;
    private String pais;
    private String temporada;

    private List<Equipo> equipos;
    private List<Jornada> jornadas;

    public Liga(int idLiga, String nombre, String pais, String temporada) {
        this.idLiga = idLiga;
        this.nombre = nombre;
        this.pais = pais;
        this.temporada = temporada;
        this.equipos = new ArrayList<>();
        this.jornadas = new ArrayList<>();
    }
    public int getIdLiga()
    { return idLiga; }

    public String getNombre()
    { return nombre; }

    public String getPais()
    { return pais; }

    public String getTemporada()
    { return temporada; }

    public List<Equipo> getEquipos()
    { return equipos; }

    public List<Jornada> getJornadas() { return jornadas; }


    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPais(String pais) { this.pais = pais; }
    public void setTemporada(String temporada) { this.temporada = temporada; }

    public void addEquipo(Equipo equipo) { equipos.add(equipo); }
    public void addJornada(Jornada jornada) { jornadas.add(jornada); }
}
