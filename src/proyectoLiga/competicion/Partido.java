package proyectoLiga.competicion;

import proyectoLiga.personas.Arbitro;
import proyectoLiga.personas.Jugador;

import java.time.LocalDate;
import java.util.Random;

public class Partido {

    private static int contadorId = 1;
    private int idPartido;
    private LocalDate fecha;
    private Jugador mvp;
    private boolean jugado = false;
    private int golesLocal;
    private int golesVisitante;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int idLiga;
    private int numeroJornada;
    private Arbitro arbitro;


    public Partido(LocalDate fecha, Equipo equipoLocal, Equipo equipoVisitante, Arbitro arbitro, int idLiga, int numeroJornada ) {
        this.idPartido = contadorId++;
        this.fecha = fecha;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.arbitro = arbitro;
        this.idLiga = idLiga;
        this.numeroJornada = numeroJornada;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.jugado = false;
    }

    public boolean isJugado() {
        return jugado;
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

    public Jugador getMvp() {
        return mvp;
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

    public void simularPartido() {
        if (jugado) return;

        int mediaLocal = equipoLocal.calcularMediaEquipo();
        int mediaVisitante = equipoVisitante.calcularMediaEquipo();

        Random r = new Random();

        int fuerzaLocal = mediaLocal + r.nextInt(50);
        int fuerzaVisitante = mediaVisitante + r.nextInt(50);

        if (fuerzaLocal > fuerzaVisitante) {
            golesLocal = r.nextInt(3) + 1;
            golesVisitante = r.nextInt(2);
        } else if (fuerzaVisitante > fuerzaLocal) {
            golesVisitante = r.nextInt(3) + 1;
            golesLocal = r.nextInt(2);
        }else  {
            golesLocal = r.nextInt(3);
            golesVisitante = golesLocal;
        }
        calcularMVP();
        this.jugado = true;
        aplicarResultadoEnClasificacion();
        jugado = true;
    }

    private void calcularMVP() {
        Random r = new Random();

        Jugador mejor = null;
        double mejorNota = -1;

        for (Jugador j : equipoLocal.getJugadores()) {
            int goles = r.nextInt(3);
            int asistencias = r.nextInt(2);
            int faltas = r.nextInt(3);

            double nota = (goles * 10)
                    + (asistencias * 6)
                    - (faltas * 2)
                    + (j.getMedia() / 10.0);
            if (nota > mejorNota) {
                mejorNota = nota;
                mejor = j;
            }
        }
        for (Jugador j : equipoVisitante.getJugadores()) {

            int goles = r.nextInt(3);
            int asistencias = r.nextInt(2);
            int faltas = r.nextInt(3);

            double nota = (goles * 10)
                    + (asistencias * 6)
                    - (faltas * 2)
                    + (j.getMedia() / 10.0);

            if (nota > mejorNota) {
                mejorNota = nota;
                mejor = j;
            }
        }
        mvp = mejor;
    }

    public void aplicarResultadoEnClasificacion(){

        equipoLocal.setPartidosJugados(equipoLocal.getPartidosJugados()+1);
        equipoVisitante.setPartidosJugados(equipoVisitante.getPartidosJugados()+1);

        equipoLocal.setGolesAFavor(equipoLocal.getGolesAFavor() + golesLocal);
        equipoLocal.setGolesEnContra(equipoLocal.getGolesEnContra() + golesVisitante);

        equipoVisitante.setGolesAFavor(equipoVisitante.getGolesAFavor() + golesVisitante);
        equipoVisitante.setGolesEnContra(equipoVisitante.getGolesEnContra() + golesLocal);

        if (golesLocal > golesVisitante) {

            equipoLocal.setPartidosGanados(equipoLocal.getPartidosGanados() + 1);
            equipoVisitante.setPartidosPerdidos(equipoVisitante.getPartidosPerdidos() + 1);

            equipoLocal.setPuntos(equipoLocal.getPuntos() + 3);
        } else if (golesVisitante > golesLocal) {

            equipoVisitante.setPartidosGanados(equipoVisitante.getPartidosGanados() + 1);
            equipoLocal.setPartidosPerdidos(equipoLocal.getPartidosPerdidos() + 1);

            equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 3);

        } else {

            equipoLocal.setPartidosEmpatados(equipoLocal.getPartidosEmpatados() + 1);
            equipoVisitante.setPartidosEmpatados(equipoVisitante.getPartidosEmpatados() + 1);

            equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);
            equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);
        }
    }

    @Override
    public String toString() {
        return equipoLocal.getNombre() + " " + golesLocal + " - " + golesVisitante + " " + equipoVisitante.getNombre();
    }
}
