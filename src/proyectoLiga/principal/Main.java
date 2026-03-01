package proyectoLiga.principal;

import proyectoLiga.competicion.Equipo;
import proyectoLiga.competicion.Posicion;
import proyectoLiga.personas.Entrenador;
import proyectoLiga.personas.Jugador;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        historiaIntro(sc);

        menuPrincipal(sc);

        System.out.println("Muchas gracias por ver la DELUX CHAMPIONS LEAGUE. ¡Hasta el año que viene!");
        sc.close();
    }

    private static void historiaIntro(Scanner sc) throws InterruptedException {
        System.out.println("========================================");
        System.out.println("         DELUX CHAMPIONS LEAGUE");
        System.out.println("========================================");

        escribirConPausa("El fútbol nunca ha sido solo un juego", 700);
        escribirConPausa("Es constancia , sacrificio y sobretodo momentos que son inolvidables", 700);
        escribirConPausa("Cada año, los mejores equipos luchan por demostrar quien merece ganar la mayor competición , la DELUX CHAMPIONS LEAGUE", 700);

        escribirConPausa("16 equipos.", 700);
        escribirConPausa("Cuatro grupos", 700);
        escribirConPausa("Todos con un mismo objetivo", 700);

        escribirConPausa("Bienvenido a la Delux Champions League", 700);
        escribirConPausa("Aquí no es suficiente jugar bien", 700);
        escribirConPausa("Hay que demostrarlo en cada partido", 700);


        System.out.println("Pulsa ENTER para empezar");
        sc.nextLine();
    }

    private static void escribirConPausa(String texto, int tiempoEspera) throws InterruptedException {
        System.out.println(texto);
        Thread.sleep(tiempoEspera);
    }

    private static void menuPrincipal(Scanner sc) {
        List < Equipo > equipos = crearEquipos();
        int opcion;
        do{
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1. Equipos");
            System.out.println("2. Partidos");
            System.out.println("3. Resultados");
            System.out.println("4. Clasificación");
            System.out.println("5. Salir");
            opcion = leerOpcion(sc, 1, 5);

            switch(opcion){
                case 1: System.out.println("Equipos");
                menuEquipos(sc,equipos);
                    break;
                case 2: System.out.println("Partidos (aun no esta hecho)");
                break;
                case 3: System.out.println("Resultados (aun no esta hecho)");
                break;
                case 4: System.out.println("Clasificación (aun no esta hecho)");
                break;
                case 5: System.out.println("Saliendo...");
                break;
            }
        }while(opcion!=5);
    }

    private static void menuEquipos(Scanner sc, List < Equipo > equipos){
        int opcion;
        do{
            System.out.println("EQUIPOS");
            System.out.println("1. Todos los equipos de la liga");
            System.out.println("2. Ver detalles de los equipos");
            System.out.println("3. Salir");
            opcion = leerOpcion(sc, 1, 3);

            switch(opcion){
                case 1: listarEquipos(equipos);
                pausar(sc);
                break;
                case 2: verDetallesEquipo(sc, equipos);
                break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
            }
        }while (opcion !=3);
    }

    private static void listarEquipos(List < Equipo > equipos){
        System.out.println(" EQUIPOS DE LA DELUX LEAGUE ");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i));
        }
    }

    private static void verDetallesEquipo(Scanner sc, List < Equipo > equipos){
        listarEquipos(equipos);

        System.out.println("ELIGE EL NUMERO DEL EQUIPO");
        int num = leerOpcion(sc, 1, equipos.size());
        Equipo e = equipos.get(num-1);

        System.out.println("DETALLES DEL EQUIPO");
        System.out.println("Nombre del equipo: " + e.getNombre());
        System.out.println("Ciudad del equipo: " + e.getCiudad());
        System.out.println("Estadio del  equipo: " + e.getEstadio());
        System.out.println("Entrenador: " + e.getEntrenador());


        System.out.println(" 11 INICIAL DEL EQUIPO");
        for (int i = 0; i<11;  i++){
            System.out.println((i + 1) + " - " + e.getJugadores().get(i));
        }
        pausar(sc);
    }


        private static int leerOpcion(Scanner sc, int minimo, int maximo){
        int opcion;

        while (true) {
            System.out.println("Elige una opción: ");

            while (!sc.hasNextInt()) {
                System.out.println("Error , escribe un número valido");
                sc.next();;
                System.out.println("Elige una opción: ");
                }
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion < minimo || opcion > maximo) {
                System.out.println("Opcion fuera de rango , el rango esta entre " + minimo + " y " + maximo);
            }else {
                return opcion;
            }
            }
        }

    private static List<Equipo> crearEquipos() {
        List<Equipo> equipos = new ArrayList<>();

        Equipo madrid = new Equipo(1, "Real Madrid" , "Madrid" , "Santiago Bernabeu" , 1);
        Entrenador ancelotti = new Entrenador("Carlo" , "Ancelotti" , "Italia" , 1);
        madrid.setEntrenador(ancelotti);

        madrid.getJugadores().add(new Jugador("Thibaut", "Courtois" , "Belga", 1, LocalDate.of(1992, 5, 11), 1, "28001", null , 1, Posicion.PORTERO));
        madrid.getJugadores().add(new Jugador("Daniel", "Carvajal" , "Española", 2, LocalDate.of(1992, 1, 11), 2, "28001", null , 1, Posicion.DEFENSA));
        madrid.getJugadores().add(new Jugador("Antonio","Rüdiger","Alemana",3,LocalDate.of(1993,3,3),22,"28001",null,1,Posicion.DEFENSA));
        madrid.getJugadores().add(new Jugador("David","Alaba","Austríaca",4,LocalDate.of(1992,6,24),4,"28001",null,1,Posicion.DEFENSA));
        madrid.getJugadores().add(new Jugador("Ferland","Mendy","Francesa",5,LocalDate.of(1995,6,8),23,"28001",null,1,Posicion.DEFENSA));
        madrid.getJugadores().add(new Jugador("Jude","Bellingham","Inglesa",6,LocalDate.of(2003,6,29),5,"28001",null,1,Posicion.CENTROCAMPISTA));
        madrid.getJugadores().add(new Jugador("Federico","Valverde","Uruguaya",7,LocalDate.of(1998,7,22),15,"28001",null,1,Posicion.CENTROCAMPISTA));
        madrid.getJugadores().add(new Jugador("Eduardo","Camavinga","Francesa",8,LocalDate.of(2002,11,10),12,"28001",null,1,Posicion.CENTROCAMPISTA));
        madrid.getJugadores().add(new Jugador("Vinicius","Junior","Brasileña",9,LocalDate.of(2000,7,12),7,"28001",null,1,Posicion.DELANTERO));
        madrid.getJugadores().add(new Jugador("Rodrygo","Goes","Brasileña",10,LocalDate.of(2001,1,9),11,"28001",null,1,Posicion.DELANTERO));
        madrid.getJugadores().add(new Jugador("Kylian","Mbappé","Francesa",11,LocalDate.of(1998,12,20),9,"28001",null,1,Posicion.DELANTERO));

        equipos.add(madrid);
        return equipos;
    }

    private static void pausar(Scanner sc) {
        System.out.println("Pulsa ENTER para continuar...");
        sc.nextLine();
    }
}



