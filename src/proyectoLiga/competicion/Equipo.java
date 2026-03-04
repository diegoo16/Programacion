package proyectoLiga.competicion;

import proyectoLiga.personas.Entrenador;
import proyectoLiga.personas.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private int idEquipo;
    private String nombre;
    private String ciudad;
    private String estadio;
    private int idEntrenador;
    private Entrenador entrenador;
    private List<Jugador> jugadores;
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;
    private Patrocinador patrocinador;
    private int nivel;


    public Equipo(int idEquipo, String nombre, String ciudad, String estadio, int idEntrenador) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.idEntrenador = idEntrenador;
        this.jugadores = new ArrayList<>();
    }



    public int getIdEquipo() {
        return idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public int getPuntos() {
        return puntos;
    }

    public int  getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public void addJugador(Jugador jugador){
        if(jugador != null){
            jugadores.add(jugador);
        }
    }

    public int calcularMediaEquipo() {
        int suma=0;
        for(Jugador j : jugadores){
            suma+=j.getMedia();
        }
        return suma;
    }


    public void resetEstadisticas() {
        puntos = 0;
        partidosJugados = 0;
        partidosGanados = 0;
        partidosEmpatados = 0;
        partidosPerdidos = 0;
        golesAFavor = 0;
        golesEnContra = 0;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
