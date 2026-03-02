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

    @Override
    public String toString() {
        return nombre;
    }

}
