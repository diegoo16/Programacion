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


    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre + " " +
                apellido + " y ha nacido en " +
                nacionalidad + " - El jugador juega como " +
                posicion + " y viste el dorsal " + dorsal;

    }
}
