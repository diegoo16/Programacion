package proyectoLiga.principal;

import proyectoLiga.competicion.Equipo;
import proyectoLiga.competicion.Posicion;
import proyectoLiga.personas.Entrenador;
import proyectoLiga.personas.Jugador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc;
    private final List<Equipo> equipos;

    public Menu() {
        this.sc = new Scanner(System.in);
        this.equipos = crearEquipos();
    }

    public void iniciar() throws InterruptedException {
        historiaIntro();
        menuPrincipal();
        System.out.println("Muchas gracias por ver la DELUX CHAMPIONS LEAGUE. ¡Hasta el año que viene!");
        sc.close();
    }

    private void historiaIntro() throws InterruptedException {
        System.out.println("========================================");
        System.out.println("         DELUX LEAGUE");
        System.out.println("========================================");

        escribirConPausa("El fútbol nunca ha sido solo un juego", 700);
        escribirConPausa("Es constancia, sacrificio y momentos inolvidables", 700);
        escribirConPausa("Los mejores equipos compiten por la gloria", 700);

        System.out.println("Pulsa ENTER para empezar");
        sc.nextLine();
    }

    private void escribirConPausa(String texto, int tiempoEspera) throws InterruptedException {
        System.out.println(texto);
        Thread.sleep(tiempoEspera);
    }

    private void menuPrincipal() {
        int opcion;
        do {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1. Equipos");
            System.out.println("2. Partidos");
            System.out.println("3. Resultados");
            System.out.println("4. Clasificación");
            System.out.println("5. Salir");

            opcion = leerOpcion(1, 5);

            switch (opcion) {
                case 1:
                    menuEquipos();
                    break;
                case 2:
                    System.out.println("Partidos (aun no esta hecho)");
                    pausar();
                    break;
                case 3:
                    System.out.println("Resultados (aun no esta hecho)");
                    pausar();
                    break;
                case 4:
                    System.out.println("Clasificación (aun no esta hecho)");
                    pausar();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 5);
    }

    private void menuEquipos() {
        int opcion;
        do {
            System.out.println("=== EQUIPOS ===");
            System.out.println("1. Listar equipos");
            System.out.println("2. Ver detalle de un equipo");
            System.out.println("3. Volver");

            opcion = leerOpcion(1, 3);

            switch (opcion) {
                case 1:
                    listarEquipos();
                    pausar();
                    break;
                case 2:
                    verDetalleEquipo();
                    break;
                case 3:
                    System.out.println("Volviendo...");
                    break;
            }
        } while (opcion != 3);
    }

    private void listarEquipos() {
        System.out.println("--- EQUIPOS DE LA LIGA ---");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }
    }

    private void verDetalleEquipo() {
        listarEquipos();
        System.out.println("Elige el número del equipo:");
        int num = leerOpcion(1, equipos.size());
        Equipo e = equipos.get(num - 1);


        System.out.println("DETALLES DEL EQUIPO");
        System.out.println("Nombre  : " + e.getNombre());
        System.out.println("Ciudad  : " + e.getCiudad());
        System.out.println("Estadio : " + e.getEstadio());
        System.out.println("Entrenador: " + e.getEntrenador());

        System.out.println("--- 11 INICIAL ---");
        for (int i = 0; i < 11; i++) {
            System.out.println((i + 1) + ". " + e.getJugadores().get(i));
        }

        pausar();
    }

    private int leerOpcion(int minimo, int maximo) {
        int opcion;
        while (true) {
            System.out.print("Elige una opción: ");
            while (!sc.hasNextInt()) {
                System.out.println("Error: escribe un número válido.");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion >= minimo && opcion <= maximo) {
                return opcion;
            } else {
                System.out.println("Opción fuera de rango.");
            }
        }
    }

    private void pausar() {
        System.out.println("Pulsa ENTER para continuar...");
        sc.nextLine();
    }

    private List<Equipo> crearEquipos() {
        List<Equipo> lista = new ArrayList<>();

        Equipo madrid = new Equipo(1,"Real Madrid","Madrid","Santiago Bernabéu",1);
        madrid.setEntrenador(new Entrenador("Carlo","Ancelotti","Italia",1));

        madrid.getJugadores().add(new Jugador("Thibaut","Courtois","Belgica",1,null,1,"28001",null,1,Posicion.PORTERO));
        madrid.getJugadores().add(new Jugador("Dani","Carvajal","España",2,null,2,"28001",null,1,Posicion.DEFENSA));
        madrid.getJugadores().add(new Jugador("Antonio","Rüdiger","Alemania",3,null,22,"28001",null,1,Posicion.DEFENSA));
        madrid.getJugadores().add(new Jugador("David","Alaba","Austria",4,null,4,"28001",null,1,Posicion.DEFENSA));
        madrid.getJugadores().add(new Jugador("Ferland","Mendy","Francia",5,null,23,"28001",null,1,Posicion.DEFENSA));

        madrid.getJugadores().add(new Jugador("Jude","Bellingham","Inglaterra",6,null,5,"28001",null,1,Posicion.CENTROCAMPISTA));
        madrid.getJugadores().add(new Jugador("Federico","Valverde","Uruguay",7,null,15,"28001",null,1,Posicion.CENTROCAMPISTA));
        madrid.getJugadores().add(new Jugador("Eduardo","Camavinga","Francia",8,null,12,"28001",null,1,Posicion.CENTROCAMPISTA));

        madrid.getJugadores().add(new Jugador("Vinicius","Junior","Brasil",9,null,7,"28001",null,1,Posicion.DELANTERO));
        madrid.getJugadores().add(new Jugador("Rodrygo","Goes","Brasil",10,null,11,"28001",null,1,Posicion.DELANTERO));
        madrid.getJugadores().add(new Jugador("Kylian","Mbappé","Francia",11,null,9,"28001",null,1,Posicion.DELANTERO));

        lista.add(madrid);

        Equipo barca = new Equipo(2,"FC Barcelona","Barcelona","Camp Nou",2);
        barca.setEntrenador(new Entrenador("Hansi","Flick","Alemania",2));

        barca.getJugadores().add(new Jugador("Marc-André","ter Stegen","Alemania",12,null,1,"08028",null,2,Posicion.PORTERO));
        barca.getJugadores().add(new Jugador("Jules","Koundé","Francia",13,null,23,"08028",null,2,Posicion.DEFENSA));
        barca.getJugadores().add(new Jugador("Ronald","Araújo","Uruguay",14,null,4,"08028",null,2,Posicion.DEFENSA));
        barca.getJugadores().add(new Jugador("Pau","Cubarsí","España",15,null,2,"08028",null,2,Posicion.DEFENSA));
        barca.getJugadores().add(new Jugador("Alejandro","Balde","España",16,null,3,"08028",null,2,Posicion.DEFENSA));

        barca.getJugadores().add(new Jugador("Frenkie","de Jong","Paises Bajos",17,null,21,"08028",null,2,Posicion.CENTROCAMPISTA));
        barca.getJugadores().add(new Jugador("Pedri","González","España",18,null,8,"08028",null,2,Posicion.CENTROCAMPISTA));
        barca.getJugadores().add(new Jugador("Gavi","Páez","España",19,null,6,"08028",null,2,Posicion.CENTROCAMPISTA));

        barca.getJugadores().add(new Jugador("Lamine","Yamal","España",20,null,27,"08028",null,2,Posicion.DELANTERO));
        barca.getJugadores().add(new Jugador("Robert","Lewandowski","Polonia",21,null,9,"08028",null,2,Posicion.DELANTERO));
        barca.getJugadores().add(new Jugador("Raphinha","Dias","Brasil",22,null,11,"08028",null,2,Posicion.DELANTERO));

        lista.add(barca);

        Equipo leganes = new Equipo(3,"CD Leganés","Leganés","Estadio Butarque",3);
        leganes.setEntrenador(new Entrenador("Igor","Oca","España",3));

        leganes.getJugadores().add(new Jugador("Juan","Soriano","España",23,null,1,"28918",null,3,Posicion.PORTERO));
        leganes.getJugadores().add(new Jugador("Enric","Franquesa","España",24,null,15,"28918",null,3,Posicion.DEFENSA));
        leganes.getJugadores().add(new Jugador("Ignasi","Miquel","España",25,null,5,"28918",null,3,Posicion.DEFENSA));
        leganes.getJugadores().add(new Jugador("Gonzalo","Aguilar","España",26,null,6,"28918",null,3,Posicion.DEFENSA));
        leganes.getJugadores().add(new Jugador("Sebastián","Figueredo","Uruguay",27,null,22,"28918",null,3,Posicion.DEFENSA));

        leganes.getJugadores().add(new Jugador("Óscar","Plano","España",28,null,20,"28918",null,3,Posicion.CENTROCAMPISTA));
        leganes.getJugadores().add(new Jugador("Dani","Rodríguez","España",29,null,9,"28918",null,3,Posicion.CENTROCAMPISTA));
        leganes.getJugadores().add(new Jugador("Amadou","Diawara","Guinea",30,null,24,"28918",null,3,Posicion.CENTROCAMPISTA));

        leganes.getJugadores().add(new Jugador("Naím","García","España",31,null,17,"28918",null,3,Posicion.DELANTERO));
        leganes.getJugadores().add(new Jugador("Luis","Asué","Guinea",32,null,12,"28918",null,3,Posicion.DELANTERO));
        leganes.getJugadores().add(new Jugador("Juan","Cruz","España",33,null,10,"28918",null,3,Posicion.DELANTERO));

        lista.add(leganes);


        Equipo betis = new Equipo(4, "Real Betis", "Sevilla", "Benito Villamarín", 4);
        betis.setEntrenador(new Entrenador("Manuel", "Pellegrini", "Chile", 4));

        betis.getJugadores().add(new Jugador("Álvaro", "Valles", "España", 34, null, 1, "41012", null, 4, Posicion.PORTERO));
        betis.getJugadores().add(new Jugador("Ricardo", "Rodríguez", "Chile", 35, null, 12, "41012", null, 4, Posicion.DEFENSA));
        betis.getJugadores().add(new Jugador("Natan Bernardo", "de Souza", "Brasil", 36, null, 4, "41012", null, 4, Posicion.DEFENSA));
        betis.getJugadores().add(new Jugador("Diego", "Llorente", "España", 37, null, 3, "41012", null, 4, Posicion.DEFENSA));
        betis.getJugadores().add(new Jugador("Aitor", "Ruibal", "España", 38, null, 24, "41012", null, 4, Posicion.DEFENSA));

        betis.getJugadores().add(new Jugador("Álvaro", "Fidalgo", "Mexico", 39, null, 15, "41012", null, 4, Posicion.CENTROCAMPISTA));
        betis.getJugadores().add(new Jugador("Marc", "Roca", "España", 40, null, 21, "41012", null, 4, Posicion.CENTROCAMPISTA));
        betis.getJugadores().add(new Jugador("Pablo", "Fornals", "España", 41, null, 8, "41012", null, 4, Posicion.CENTROCAMPISTA));

        betis.getJugadores().add(new Jugador("Abde", "Ezzalzouli", "Marruecos", 42, null, 10, "41012", null, 4, Posicion.DELANTERO));
        betis.getJugadores().add(new Jugador("Cucho", "Hernández", "Colombia", 43, null, 19, "41012", null, 4, Posicion.DELANTERO));
        betis.getJugadores().add(new Jugador("Antony", "", "Brasil", 44, null, 7, "41012", null, 4, Posicion.DELANTERO));

        lista.add(betis);

        Equipo realSociedad = new Equipo(5, "Real Sociedad", "San Sebastián", "Reale Arena", 5);
        realSociedad.setEntrenador(new Entrenador("Pellegrino", "Matarazzo", "Estados Unidos", 5));

        realSociedad.getJugadores().add(new Jugador("Álex", "Remiro", "España", 45, null, 1, "20014", null, 5, Posicion.PORTERO));
        realSociedad.getJugadores().add(new Jugador("Jon", "Aramburu", "Venezuela", 46, null, 2, "20014", null, 5, Posicion.DEFENSA));
        realSociedad.getJugadores().add(new Jugador("Igor", "Zubeldia", "España", 47, null, 5, "20014", null, 5, Posicion.DEFENSA));
        realSociedad.getJugadores().add(new Jugador("Duje", "Caleta-Car", "Francia", 48, null, 16, "20014", null, 5, Posicion.DEFENSA));
        realSociedad.getJugadores().add(new Jugador("Sergio", "Gómez", "España", 49, null, 17, "20014", null, 5, Posicion.DEFENSA));

        realSociedad.getJugadores().add(new Jugador("Jon", "Gorrotxategi", "España", 50, null, 4, "20014", null, 5, Posicion.CENTROCAMPISTA));
        realSociedad.getJugadores().add(new Jugador("Beñat", "Turrientes", "España", 51, null, 8, "20014", null, 5, Posicion.CENTROCAMPISTA));
        realSociedad.getJugadores().add(new Jugador("Carlos", "Soler", "España", 52, null, 18, "20014", null, 5, Posicion.CENTROCAMPISTA));
        realSociedad.getJugadores().add(new Jugador("Arsen", "Zakharyan", "Rusia", 53, null, 21, "20014", null, 5, Posicion.CENTROCAMPISTA));

        realSociedad.getJugadores().add(new Jugador("Gonçalo", "Guedes", "Portugal", 54, null, 11, "20014", null, 5, Posicion.DELANTERO));
        realSociedad.getJugadores().add(new Jugador("Mikel", "Oyarzabal", "España", 55, null, 10, "20014", null, 5, Posicion.DELANTERO));

        lista.add(realSociedad);

        Equipo atletico = new Equipo(6, "Atlético de Madrid", "Madrid", "Metropolitano", 6);
        atletico.setEntrenador(new Entrenador("Diego","Simeone","Argentina",6));

        atletico.getJugadores().add(new Jugador("Jan","Oblak","Eslovenia",56,null,1,"28029",null,6,Posicion.PORTERO));
        atletico.getJugadores().add(new Jugador("Nahuel","Molina","Argentina",57,null,16,"28029",null,6,Posicion.DEFENSA));
        atletico.getJugadores().add(new Jugador("José","Giménez","Uruguay",58,null,2,"28029",null,6,Posicion.DEFENSA));
        atletico.getJugadores().add(new Jugador("Stefan","Savić","Montenegro",59,null,15,"28029",null,6,Posicion.DEFENSA));
        atletico.getJugadores().add(new Jugador("Renan","Lodi","Brasil",60,null,12,"28029",null,6,Posicion.DEFENSA));
        atletico.getJugadores().add(new Jugador("Koke","Resurrección","España",61,null,6,"28029",null,6,Posicion.CENTROCAMPISTA));
        atletico.getJugadores().add(new Jugador("Rodrigo","De Paul","Argentina",62,null,5,"28029",null,6,Posicion.CENTROCAMPISTA));
        atletico.getJugadores().add(new Jugador("Marcos","Llorente","España",63,null,14,"28029",null,6,Posicion.CENTROCAMPISTA));
        atletico.getJugadores().add(new Jugador("Antoine","Griezmann","Francia",64,null,7,"28029",null,6,Posicion.DELANTERO));
        atletico.getJugadores().add(new Jugador("Álvaro","Morata","España",65,null,9,"28029",null,6,Posicion.DELANTERO));
        atletico.getJugadores().add(new Jugador("Ángel","Correa","Argentina",66,null,10,"28029",null,6,Posicion.DELANTERO));

        lista.add(atletico);

        Equipo villarreal = new Equipo(7, "Villarreal CF", "Villarreal", "Estadio de la Cerámica", 7);
        villarreal.setEntrenador(new Entrenador("Pepe","Mel","España",7));

        villarreal.getJugadores().add(new Jugador("Gerónimo","Rulli","Argentina",67,null,1,"12540",null,7,Posicion.PORTERO));
        villarreal.getJugadores().add(new Jugador("Rubén","Peybernes","España",68,null,22,"12540",null,7,Posicion.DEFENSA));
        villarreal.getJugadores().add(new Jugador("Raúl","Albiol","España",69,null,4,"12540",null,7,Posicion.DEFENSA));
        villarreal.getJugadores().add(new Jugador("Pervis","Estupiñán","Ecuador",70,null,3,"12540",null,7,Posicion.DEFENSA));
        villarreal.getJugadores().add(new Jugador("Alfonso","Pedraza","España",71,null,19,"12540",null,7,Posicion.DEFENSA));

        villarreal.getJugadores().add(new Jugador("Santi","Cazorla","España",72,null,10,"12540",null,7,Posicion.CENTROCAMPISTA));
        villarreal.getJugadores().add(new Jugador("Étienne","Capoue","Francia",73,null,17,"12540",null,7,Posicion.CENTROCAMPISTA));
        villarreal.getJugadores().add(new Jugador("Manu","Trigueros","España",74,null,21,"12540",null,7,Posicion.CENTROCAMPISTA));

        villarreal.getJugadores().add(new Jugador("Borja","Mayoral","España",75,null,9,"12540",null,7,Posicion.DELANTERO));
        villarreal.getJugadores().add(new Jugador("Samuel","Chukwueze","Nigeria",76,null,11,"12540",null,7,Posicion.DELANTERO));
        villarreal.getJugadores().add(new Jugador("Álvaro","Moreno","España",77,null,7,"12540",null,7,Posicion.DELANTERO));

        lista.add(villarreal);

        Equipo valencia = new Equipo(8, "Valencia CF", "Valencia", "Mestalla", 8);
        valencia.setEntrenador(new Entrenador("Rubén","Baraja","España",8));

        valencia.getJugadores().add(new Jugador("Cristian","Rivero","España",78,null,1,"46001",null,8,Posicion.PORTERO));
        valencia.getJugadores().add(new Jugador("Héctor","Bellerín","España",79,null,2,"46001",null,8,Posicion.DEFENSA));
        valencia.getJugadores().add(new Jugador("Gabriel","Pires","Brasil",80,null,3,"46001",null,8,Posicion.DEFENSA));
        valencia.getJugadores().add(new Jugador("Gustavo","Gómez","Paraguay",81,null,15,"46001",null,8,Posicion.DEFENSA));
        valencia.getJugadores().add(new Jugador("José","Gayà","España",82,null,14,"46001",null,8,Posicion.DEFENSA));

        valencia.getJugadores().add(new Jugador("Rubén","Sobrino","España",83,null,8,"46001",null,8,Posicion.CENTROCAMPISTA));
        valencia.getJugadores().add(new Jugador("Carlos","Solér","España",84,null,10,"46001",null,8,Posicion.CENTROCAMPISTA));
        valencia.getJugadores().add(new Jugador("Yunus","Musah","Estados Unidos",85,null,6,"46001",null,8,Posicion.CENTROCAMPISTA));

        valencia.getJugadores().add(new Jugador("Mickaël","Cicy","Francia",86,null,11,"46001",null,8,Posicion.DELANTERO));
        valencia.getJugadores().add(new Jugador("Álvaro","Negredo","España",87,null,9,"46001",null,8,Posicion.DELANTERO));
        valencia.getJugadores().add(new Jugador("Lee","Kang-in","Corea del Sur",88,null,7,"46001",null,8,Posicion.DELANTERO));

        lista.add(valencia);

        Equipo athletic = new Equipo(9, "Athletic Club", "Bilbao", "San Mamés", 9);
        athletic.setEntrenador(new Entrenador("Ernesto","Valverde","España",9));

        athletic.getJugadores().add(new Jugador("Unai","Simón","España",89,null,1,"48001",null,9,Posicion.PORTERO));
        athletic.getJugadores().add(new Jugador("Yeray","Alvarez","España",90,null,5,"48001",null,9,Posicion.DEFENSA));
        athletic.getJugadores().add(new Jugador("Iñigo","Martínez","España",91,null,4,"48001",null,9,Posicion.DEFENSA));
        athletic.getJugadores().add(new Jugador("Unai","Núñez","España",92,null,3,"48001",null,9,Posicion.DEFENSA));
        athletic.getJugadores().add(new Jugador("Óscar","De Marcos","España",93,null,2,"48001",null,9,Posicion.DEFENSA));

        athletic.getJugadores().add(new Jugador("Mikel","Vesga","España",94,null,8,"48001",null,9,Posicion.CENTROCAMPISTA));
        athletic.getJugadores().add(new Jugador("Unai","López","España",95,null,16,"48001",null,9,Posicion.CENTROCAMPISTA));
        athletic.getJugadores().add(new Jugador("Óscar","Gil","España",96,null,22,"48001",null,9,Posicion.CENTROCAMPISTA));

        athletic.getJugadores().add(new Jugador("Mikel","Oyarzabal","España",97,null,10,"48001",null,9,Posicion.DELANTERO));
        athletic.getJugadores().add(new Jugador("Iker","Muniain","España",98,null,7,"48001",null,9,Posicion.DELANTERO));
        athletic.getJugadores().add(new Jugador("Raúl","García","España",99,null,19,"48001",null,9,Posicion.DELANTERO));

        lista.add(athletic);
        return lista;
    }
}