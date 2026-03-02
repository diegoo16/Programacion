package proyectoLiga.competicion;

import proyectoLiga.personas.Arbitro;
import java.time.LocalDate;

public class Partido {

    private static int contadorId = 1;
    private int idPartido;
    private LocalDate fecha;
    private int golesLocal;
    private int golesVisitante;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int idLiga;
    private int numeroJornada;
    private Arbitro arbitro;


    public Partido(LocalDate fecha, Equipo equipoLocal, Equipo equipoVisitante, Arbitro arbitro, int idLiga, int numeroJornada) {
        this.idPartido = contadorId++;
        this.fecha = fecha;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.arbitro = arbitro;
        this.idLiga = idLiga;
        this.numeroJornada = numeroJornada;
        this.golesLocal = 0;
        this.golesVisitante = 0;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public int getNumeroJornada() {
        return numeroJornada;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public static int getSiguienteId() {
        return contadorId;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public void setResultado(int golesLocal, int golesVisitante) {
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }


    @Override
    public String toString() {
        return equipoLocal.getNombre() + " " + golesLocal + " - " + golesVisitante + " " + equipoVisitante.getNombre();
    }
}
