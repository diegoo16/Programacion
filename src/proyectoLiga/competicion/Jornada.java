package proyectoLiga.competicion;

import java.util.ArrayList;
import java.util.List;

public class Jornada {

    private int idLiga;
    private int numero;
    private List<Partido> partidos;

    public Jornada(int idLiga, int numero) {
        this.idLiga = idLiga;
        this.numero = numero;
        this.partidos = new ArrayList<>();
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void addPartido(Partido partido) {
        this.partidos.add(partido);
    }
}