package proyectoLiga.personas;

import proyectoLiga.competicion.Posicion;

import java.time.LocalDate;

public class Jugador extends Persona {

    private int idJugador;
    private LocalDate fechaNacimiento;
    private int dorsal;
    private String codigoPostal;
    private String telefono;
    private int idEquipo;
    private Posicion posicion;


    public Jugador(String nombre, String apellido, String nacionalidad, int idJugador, LocalDate fechaNacimiento, int dorsal, String codigoPostal, String telefono, int idEquipo, Posicion posicion) {
        super(nombre, apellido, nacionalidad);
        this.idJugador = idJugador;
        this.fechaNacimiento = fechaNacimiento;
        this.dorsal = dorsal;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.idEquipo = idEquipo;
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre + " " +
                apellido + " y ha nacido en " +
                nacionalidad + " - El jugador juega como" +
                posicion + " y viste el dorsal " + dorsal;

    }
}
