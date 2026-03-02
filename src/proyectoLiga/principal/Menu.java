package proyectoLiga.principal;

import proyectoLiga.competicion.Equipo;
import proyectoLiga.competicion.Liga;
import proyectoLiga.competicion.Posicion;
import proyectoLiga.personas.Entrenador;
import proyectoLiga.personas.Jugador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc;
    private final Liga deluxLeague;
    private final Liga premierleague;
    private Liga ligaActual;

    public Menu() {
        this.sc = new Scanner(System.in);
        this.deluxLeague = crearDeluxLeague();
        this.premierleague = crearPremierLeague();
    }

    public void iniciar() throws InterruptedException {
        historiaIntro();
        elegirLiga();
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

    private void elegirLiga() {
        System.out.println(" ELIGE LA LIGA ");
        System.out.println("1. " + deluxLeague.getNombre());
        System.out.println("2. " + premierleague.getNombre());

        int op = leerOpcion(1,2);

        if (op == 1) ligaActual = deluxLeague;
        else ligaActual = premierleague;

        System.out.println(" Perfecto , has elegido: " + ligaActual.getNombre());
        pausar();
    }

    private void menuPrincipal() {
        if (ligaActual == null) {
            elegirLiga();
        }
        int opcion;
        do {
            System.out.println("=== MENÚ PRINCIPAL (" + ligaActual.getNombre() + ") ===");
            System.out.println("1. Equipos");
            System.out.println("2. Partidos");
            System.out.println("3. Resultados");
            System.out.println("4. Clasificación");
            System.out.println("5. Cambiar liga");
            System.out.println("6. Salir");

            opcion = leerOpcion(1, 6);

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
                    elegirLiga();
                    break;
                    case 6:
                        System.out.println("Saliendo...");
                        break;
            }
        } while (opcion != 6);
    }

    private void menuEquipos() {
        int opcion;
        do {
            System.out.println("=== EQUIPOS === (" + ligaActual.getNombre() + ") ===");
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
        List <Equipo> equipos = ligaActual.getEquipos();

        System.out.println("--- EQUIPOS DE LA LIGA ---");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }
    }

    private void verDetalleEquipo() {
        List <Equipo> equipos = ligaActual.getEquipos();

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
                System.out.println("Elige una opción: ");
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

    private Liga crearDeluxLeague() {
        Liga liga = new Liga(1, "Delux League", "España" , "2025/26");

        List <Equipo> equipos = crearEquiposDelux();
        for (Equipo e : equipos) {
            liga.addEquipo(e);
        }
        return liga;
    }

    private Liga crearPremierLeague() {
        Liga liga = new Liga (2, "Premier League (aún no esta hecho" , "Inglaterra" , "2025/26");

        List <Equipo> equipos = crearEquiposDelux();
        for (Equipo e : equipos) {
            liga.addEquipo(e);
        }
        return liga;
    }
    private List<Equipo> crearEquiposDelux() {
        List<Equipo> lista = new ArrayList<>();

        Equipo madrid = new Equipo(1,"Real Madrid","Madrid","Santiago Bernabéu",1);
        madrid.setEntrenador(new Entrenador("Carlo","Ancelotti","Italia",1));

        madrid.getJugadores().add(new Jugador("Thibaut","Courtois","Belgica",1,null,1,"28001",null,1,Posicion.PORTERO , 90));
        madrid.getJugadores().add(new Jugador("Dani","Carvajal","España",2,null,2,"28001",null,1,Posicion.DEFENSA , 83));
        madrid.getJugadores().add(new Jugador("Antonio","Rüdiger","Alemania",3,null,22,"28001",null,1,Posicion.DEFENSA , 86));
        madrid.getJugadores().add(new Jugador("David","Alaba","Austria",4,null,4,"28001",null,1,Posicion.DEFENSA , 84));
        madrid.getJugadores().add(new Jugador("Ferland","Mendy","Francia",5,null,23,"28001",null,1,Posicion.DEFENSA , 82));

        madrid.getJugadores().add(new Jugador("Jude","Bellingham","Inglaterra",6,null,5,"28001",null,1,Posicion.CENTROCAMPISTA , 91));
        madrid.getJugadores().add(new Jugador("Federico","Valverde","Uruguay",7,null,15,"28001",null,1,Posicion.CENTROCAMPISTA , 88));
        madrid.getJugadores().add(new Jugador("Eduardo","Camavinga","Francia",8,null,12,"28001",null,1,Posicion.CENTROCAMPISTA , 85));

        madrid.getJugadores().add(new Jugador("Vinicius","Junior","Brasil",9,null,7,"28001",null,1,Posicion.DELANTERO , 92));
        madrid.getJugadores().add(new Jugador("Rodrygo","Goes","Brasil",10,null,11,"28001",null,1,Posicion.DELANTERO , 86));
        madrid.getJugadores().add(new Jugador("Kylian","Mbappé","Francia",11,null,9,"28001",null,1,Posicion.DELANTERO , 93));

        lista.add(madrid);

        Equipo barca = new Equipo(2,"FC Barcelona","Barcelona","Camp Nou",2);
        barca.setEntrenador(new Entrenador("Hansi","Flick","Alemania",2));

        barca.getJugadores().add(new Jugador("Marc-André","ter Stegen","Alemania",12,null,1,"08028",null,2,Posicion.PORTERO , 88));
        barca.getJugadores().add(new Jugador("Jules","Koundé","Francia",13,null,23,"08028",null,2,Posicion.DEFENSA , 84));
        barca.getJugadores().add(new Jugador("Ronald","Araújo","Uruguay",14,null,4,"08028",null,2,Posicion.DEFENSA , 87));
        barca.getJugadores().add(new Jugador("Pau","Cubarsí","España",15,null,2,"08028",null,2,Posicion.DEFENSA , 80));
        barca.getJugadores().add(new Jugador("Alejandro","Balde","España",16,null,3,"08028",null,2,Posicion.DEFENSA, 83));

        barca.getJugadores().add(new Jugador("Frenkie","de Jong","Paises Bajos",17,null,21,"08028",null,2,Posicion.CENTROCAMPISTA , 87));
        barca.getJugadores().add(new Jugador("Pedri","González","España",18,null,8,"08028",null,2,Posicion.CENTROCAMPISTA , 88));
        barca.getJugadores().add(new Jugador("Gavi","Páez","España",19,null,6,"08028",null,2,Posicion.CENTROCAMPISTA , 84));

        barca.getJugadores().add(new Jugador("Lamine","Yamal","España",20,null,27,"08028",null,2,Posicion.DELANTERO , 85));
        barca.getJugadores().add(new Jugador("Robert","Lewandowski","Polonia",21,null,9,"08028",null,2,Posicion.DELANTERO , 89));
        barca.getJugadores().add(new Jugador("Raphinha","Dias","Brasil",22,null,11,"08028",null,2,Posicion.DELANTERO , 88));

        lista.add(barca);

        Equipo leganes = new Equipo(3,"CD Leganés","Leganés","Estadio Butarque",3);
        leganes.setEntrenador(new Entrenador("Igor","Oca","España",3));

        leganes.getJugadores().add(new Jugador("Juan","Soriano","España",23,null,1,"28918",null,3,Posicion.PORTERO , 77));
        leganes.getJugadores().add(new Jugador("Enric","Franquesa","España",24,null,15,"28918",null,3,Posicion.DEFENSA, 75));
        leganes.getJugadores().add(new Jugador("Ignasi","Miquel","España",25,null,5,"28918",null,3,Posicion.DEFENSA , 76));
        leganes.getJugadores().add(new Jugador("Gonzalo","Aguilar","España",26,null,6,"28918",null,3,Posicion.DEFENSA , 74));
        leganes.getJugadores().add(new Jugador("Sebastián","Figueredo","Uruguay",27,null,22,"28918",null,3,Posicion.DEFENSA , 74));

        leganes.getJugadores().add(new Jugador("Óscar","Plano","España",28,null,20,"28918",null,3,Posicion.CENTROCAMPISTA , 76));
        leganes.getJugadores().add(new Jugador("Dani","Rodríguez","España",29,null,9,"28918",null,3,Posicion.CENTROCAMPISTA , 75));
        leganes.getJugadores().add(new Jugador("Amadou","Diawara","Guinea",30,null,24,"28918",null,3,Posicion.CENTROCAMPISTA , 78));

        leganes.getJugadores().add(new Jugador("Naím","García","España",31,null,17,"28918",null,3,Posicion.DELANTERO , 74));
        leganes.getJugadores().add(new Jugador("Luis","Asué","Guinea",32,null,12,"28918",null,3,Posicion.DELANTERO , 73));
        leganes.getJugadores().add(new Jugador("Juan","Cruz","España",33,null,10,"28918",null,3,Posicion.DELANTERO , 77));

        lista.add(leganes);


        Equipo betis = new Equipo(4, "Real Betis", "Sevilla", "Benito Villamarín", 4);
        betis.setEntrenador(new Entrenador("Manuel", "Pellegrini", "Chile", 4));

        betis.getJugadores().add(new Jugador("Álvaro", "Valles", "España", 34, null, 1, "41012", null, 4, Posicion.PORTERO , 83));
        betis.getJugadores().add(new Jugador("Ricardo", "Rodríguez", "Chile", 35, null, 12, "41012", null, 4, Posicion.DEFENSA , 79));
        betis.getJugadores().add(new Jugador("Natan Bernardo", "de Souza", "Brasil", 36, null, 4, "41012", null, 4, Posicion.DEFENSA , 84));
        betis.getJugadores().add(new Jugador("Diego", "Llorente", "España", 37, null, 3, "41012", null, 4, Posicion.DEFENSA , 82));
        betis.getJugadores().add(new Jugador("Aitor", "Ruibal", "España", 38, null, 24, "41012", null, 4, Posicion.DEFENSA , 78));

        betis.getJugadores().add(new Jugador("Álvaro", "Fidalgo", "Mexico", 39, null, 15, "41012", null, 4, Posicion.CENTROCAMPISTA , 85));
        betis.getJugadores().add(new Jugador("Marc", "Roca", "España", 40, null, 21, "41012", null, 4, Posicion.CENTROCAMPISTA , 82));
        betis.getJugadores().add(new Jugador("Isco", "Alarcón", "España", 41, null, 20, "41012", null, 4, Posicion.CENTROCAMPISTA, 87));

        betis.getJugadores().add(new Jugador("Abde", "Ezzalzouli", "Marruecos", 42, null, 10, "41012", null, 4, Posicion.DELANTERO , 84));
        betis.getJugadores().add(new Jugador("Cucho", "Hernández", "Colombia", 43, null, 19, "41012", null, 4, Posicion.DELANTERO , 79));
        betis.getJugadores().add(new Jugador("Antony", "", "Brasil", 44, null, 7, "41012", null, 4, Posicion.DELANTERO , 86));

        lista.add(betis);

        Equipo realSociedad = new Equipo(5, "Real Sociedad", "San Sebastián", "Reale Arena", 5);
        realSociedad.setEntrenador(new Entrenador("Pellegrino", "Matarazzo", "Estados Unidos", 5));

        realSociedad.getJugadores().add(new Jugador("Álex", "Remiro", "España", 45, null, 1, "20014", null, 5, Posicion.PORTERO , 85));
        realSociedad.getJugadores().add(new Jugador("Jon", "Aramburu", "Venezuela", 46, null, 2, "20014", null, 5, Posicion.DEFENSA , 81));
        realSociedad.getJugadores().add(new Jugador("Igor", "Zubeldia", "España", 47, null, 5, "20014", null, 5, Posicion.DEFENSA , 86));
        realSociedad.getJugadores().add(new Jugador("Duje", "Caleta-Car", "Francia", 48, null, 16, "20014", null, 5, Posicion.DEFENSA , 83));
        realSociedad.getJugadores().add(new Jugador("Sergio", "Gómez", "España", 49, null, 17, "20014", null, 5, Posicion.DEFENSA , 80));

        realSociedad.getJugadores().add(new Jugador("Jon", "Gorrotxategi", "España", 50, null, 4, "20014", null, 5, Posicion.CENTROCAMPISTA , 77));
        realSociedad.getJugadores().add(new Jugador("Beñat", "Turrientes", "España", 51, null, 8, "20014", null, 5, Posicion.CENTROCAMPISTA, 79));
        realSociedad.getJugadores().add(new Jugador("Carlos", "Soler", "España", 52, null, 18, "20014", null, 5, Posicion.CENTROCAMPISTA, 79));
        realSociedad.getJugadores().add(new Jugador("Arsen", "Zakharyan", "Rusia", 53, null, 21, "20014", null, 5, Posicion.CENTROCAMPISTA , 80));

        realSociedad.getJugadores().add(new Jugador("Gonçalo", "Guedes", "Portugal", 54, null, 11, "20014", null, 5, Posicion.DELANTERO , 85));
        realSociedad.getJugadores().add(new Jugador("Mikel", "Oyarzabal", "España", 55, null, 10, "20014", null, 5, Posicion.DELANTERO , 80));

        lista.add(realSociedad);

        Equipo atletico = new Equipo(6, "Atlético de Madrid", "Madrid", "Metropolitano", 6);
        atletico.setEntrenador(new Entrenador("Diego","Simeone","Argentina",6));

        atletico.getJugadores().add(new Jugador("Jan","Oblak","Eslovenia",56,null,1,"28029",null,6,Posicion.PORTERO , 89));
        atletico.getJugadores().add(new Jugador("Nahuel","Molina","Argentina",57,null,16,"28029",null,6,Posicion.DEFENSA , 82));
        atletico.getJugadores().add(new Jugador("José","Giménez","Uruguay",58,null,2,"28029",null,6,Posicion.DEFENSA , 85));
        atletico.getJugadores().add(new Jugador("Stefan","Savić","Montenegro",59,null,15,"28029",null,6,Posicion.DEFENSA , 82));
        atletico.getJugadores().add(new Jugador("Renan","Lodi","Brasil",60,null,12,"28029",null,6,Posicion.DEFENSA , 81));
        atletico.getJugadores().add(new Jugador("Koke","Resurrección","España",61,null,6,"28029",null,6,Posicion.CENTROCAMPISTA , 84));
        atletico.getJugadores().add(new Jugador("Rodrigo","De Paul","Argentina",62,null,5,"28029",null,6,Posicion.CENTROCAMPISTA , 84));
        atletico.getJugadores().add(new Jugador("Marcos","Llorente","España",63,null,14,"28029",null,6,Posicion.CENTROCAMPISTA , 86));
        atletico.getJugadores().add(new Jugador("Antoine","Griezmann","Francia",64,null,7,"28029",null,6,Posicion.DELANTERO , 90));
        atletico.getJugadores().add(new Jugador("Álvaro","Morata","España",65,null,9,"28029",null,6,Posicion.DELANTERO , 84));
        atletico.getJugadores().add(new Jugador("Ángel","Correa","Argentina",66,null,10,"28029",null,6,Posicion.DELANTERO , 83));

        lista.add(atletico);

        Equipo villarreal = new Equipo(7, "Villarreal CF", "Villarreal", "Estadio de la Cerámica", 7);
        villarreal.setEntrenador(new Entrenador("Pepe","Mel","España",7));

        villarreal.getJugadores().add(new Jugador("Gerónimo","Rulli","Argentina",67,null,1,"12540",null,7,Posicion.PORTERO , 83));
        villarreal.getJugadores().add(new Jugador("Rubén","Peybernes","España",68,null,22,"12540",null,7,Posicion.DEFENSA , 74));
        villarreal.getJugadores().add(new Jugador("Raúl","Albiol","España",69,null,4,"12540",null,7,Posicion.DEFENSA , 82));
        villarreal.getJugadores().add(new Jugador("Pervis","Estupiñán","Ecuador",70,null,3,"12540",null,7,Posicion.DEFENSA , 83));
        villarreal.getJugadores().add(new Jugador("Alfonso","Pedraza","España",71,null,19,"12540",null,7,Posicion.DEFENSA , 80));

        villarreal.getJugadores().add(new Jugador("Santi","Cazorla","España",72,null,10,"12540",null,7,Posicion.CENTROCAMPISTA , 80));
        villarreal.getJugadores().add(new Jugador("Étienne","Capoue","Francia",73,null,17,"12540",null,7,Posicion.CENTROCAMPISTA , 82));
        villarreal.getJugadores().add(new Jugador("Manu","Trigueros","España",74,null,21,"12540",null,7,Posicion.CENTROCAMPISTA , 79));

        villarreal.getJugadores().add(new Jugador("Borja","Mayoral","España",75,null,9,"12540",null,7,Posicion.DELANTERO , 82));
        villarreal.getJugadores().add(new Jugador("Samuel","Chukwueze","Nigeria",76,null,11,"12540",null,7,Posicion.DELANTERO , 84));
        villarreal.getJugadores().add(new Jugador("Álvaro","Moreno","España",77,null,7,"12540",null,7,Posicion.DELANTERO , 75));

        lista.add(villarreal);

        Equipo valencia = new Equipo(8, "Valencia CF", "Valencia", "Mestalla", 8);
        valencia.setEntrenador(new Entrenador("Rubén","Baraja","España",8));

        valencia.getJugadores().add(new Jugador("Cristian","Rivero","España",78,null,1,"46001",null,8,Posicion.PORTERO , 75));
        valencia.getJugadores().add(new Jugador("Héctor","Bellerín","España",79,null,2,"46001",null,8,Posicion.DEFENSA , 79));
        valencia.getJugadores().add(new Jugador("Gabriel","Pires","Brasil",80,null,3,"46001",null,8,Posicion.DEFENSA , 80));
        valencia.getJugadores().add(new Jugador("Gustavo","Gómez","Paraguay",81,null,15,"46001",null,8,Posicion.DEFENSA , 83));
        valencia.getJugadores().add(new Jugador("José","Gayà","España",82,null,14,"46001",null,8,Posicion.DEFENSA , 85));

        valencia.getJugadores().add(new Jugador("Rubén","Sobrino","España",83,null,8,"46001",null,8,Posicion.CENTROCAMPISTA , 76));
        valencia.getJugadores().add(new Jugador("Carlos","Solér","España",84,null,10,"46001",null,8,Posicion.CENTROCAMPISTA , 83));
        valencia.getJugadores().add(new Jugador("Yunus","Musah","Estados Unidos",85,null,6,"46001",null,8,Posicion.CENTROCAMPISTA , 81));

        valencia.getJugadores().add(new Jugador("Mickaël","Cicy","Francia",86,null,11,"46001",null,8,Posicion.DELANTERO , 74));
        valencia.getJugadores().add(new Jugador("Álvaro","Negredo","España",87,null,9,"46001",null,8,Posicion.DELANTERO , 77));
        valencia.getJugadores().add(new Jugador("Lee","Kang-in","Corea del Sur",88,null,7,"46001",null,8,Posicion.DELANTERO , 84));

        lista.add(valencia);

        Equipo athletic = new Equipo(9, "Athletic Club", "Bilbao", "San Mamés", 9);
        athletic.setEntrenador(new Entrenador("Ernesto","Valverde","España",9));

        athletic.getJugadores().add(new Jugador("Unai","Simón","España",89,null,1,"48001",null,9,Posicion.PORTERO , 86));
        athletic.getJugadores().add(new Jugador("Yeray","Alvarez","España",90,null,5,"48001",null,9,Posicion.DEFENSA , 82));
        athletic.getJugadores().add(new Jugador("Iñigo","Martínez","España",91,null,4,"48001",null,9,Posicion.DEFENSA , 83));
        athletic.getJugadores().add(new Jugador("Unai","Núñez","España",92,null,3,"48001",null,9,Posicion.DEFENSA , 80));
        athletic.getJugadores().add(new Jugador("Óscar","De Marcos","España",93,null,2,"48001",null,9,Posicion.DEFENSA , 79));

        athletic.getJugadores().add(new Jugador("Mikel","Vesga","España",94,null,8,"48001",null,9,Posicion.CENTROCAMPISTA , 78));
        athletic.getJugadores().add(new Jugador("Unai","López","España",95,null,16,"48001",null,9,Posicion.CENTROCAMPISTA, 77));
        athletic.getJugadores().add(new Jugador("Óscar","Gil","España",96,null,22,"48001",null,9,Posicion.CENTROCAMPISTA, 75));

        athletic.getJugadores().add(new Jugador("Mikel","Oyarzabal","España",97,null,10,"48001",null,9,Posicion.DELANTERO , 86));
        athletic.getJugadores().add(new Jugador("Iker","Muniain","España",98,null,7,"48001",null,9,Posicion.DELANTERO , 82));
        athletic.getJugadores().add(new Jugador("Raúl","García","España",99,null,19,"48001",null,9,Posicion.DELANTERO , 79));

        lista.add(athletic);

        Equipo osasuna = new Equipo(10, "CA Osasuna", "Pamplona", "El Sadar", 10);
        osasuna.setEntrenador(new Entrenador("Alessio", "Lisci", "Italia", 10));

        osasuna.getJugadores().add(new Jugador("Sergio","Herrera","España",100,null,1,"31001",null,10,Posicion.PORTERO , 79));
        osasuna.getJugadores().add(new Jugador("Enzo","Boyomo","Camerún",101,null,22,"31001",null,10,Posicion.DEFENSA , 77));
        osasuna.getJugadores().add(new Jugador("Jorge","Herrando","España",102,null,5,"31001",null,10,Posicion.DEFENSA , 75));
        osasuna.getJugadores().add(new Jugador("Juan","Cruz","España",103,null,3,"31001",null,10,Posicion.DEFENSA , 76));
        osasuna.getJugadores().add(new Jugador("David","García","España",104,null,4,"31001",null,10,Posicion.DEFENSA , 83));

        osasuna.getJugadores().add(new Jugador("Lucas","Torró","España",105,null,6,"31001",null,10,Posicion.CENTROCAMPISTA , 80));
        osasuna.getJugadores().add(new Jugador("Jon","Moncayola","España",106,null,7,"31001",null,10,Posicion.CENTROCAMPISTA , 81));
        osasuna.getJugadores().add(new Jugador("Iker","Muñoz","España",107,null,8,"31001",null,10,Posicion.CENTROCAMPISTA , 80));

        osasuna.getJugadores().add(new Jugador("Moi","Gómez","España",108,null,16,"31001",null,10,Posicion.CENTROCAMPISTA , 77));
        osasuna.getJugadores().add(new Jugador("Raúl","García","España",109,null,9,"31001",null,10,Posicion.DELANTERO , 83));
        osasuna.getJugadores().add(new Jugador("Kike","Barja","España",110,null,11,"31001",null,10,Posicion.DELANTERO , 78));

        lista.add(osasuna);



        Equipo celta = new Equipo(11, "RC Celta", "Vigo", "ABANCA Balaídos", 11);
        celta.setEntrenador(new Entrenador("Claudio", "Giráldez", "España", 11));


        celta.getJugadores().add(new Jugador("Andrei","Radu","Rumanía",111,null,13,"36201",null,11,Posicion.PORTERO , 80));
        celta.getJugadores().add(new Jugador("Óscar","Mingueza","España",112,null,3,"36201",null,11,Posicion.DEFENSA , 77));
        celta.getJugadores().add(new Jugador("Javi","Rodríguez","España",113,null,32,"36201",null,11,Posicion.DEFENSA , 80));
        celta.getJugadores().add(new Jugador("Joseph","Aidoo","Ghana",114,null,15,"36201",null,11,Posicion.DEFENSA , 76));
        celta.getJugadores().add(new Jugador("Carlos","Domínguez","España",115,null,24,"36201",null,11,Posicion.DEFENSA , 80));

        celta.getJugadores().add(new Jugador("Sergio","Carreira","España",116,null,2,"36201",null,11,Posicion.CENTROCAMPISTA , 82));
        celta.getJugadores().add(new Jugador("Ilaix","Moriba","Guinea",117,null,6,"36201",null,11,Posicion.CENTROCAMPISTA , 81));
        celta.getJugadores().add(new Jugador("Miguel","Román","España",118,null,8,"36201",null,11,Posicion.CENTROCAMPISTA , 76));

        celta.getJugadores().add(new Jugador("Fer","López","España",119,null,11,"36201",null,11,Posicion.DELANTERO , 85));
        celta.getJugadores().add(new Jugador("Hugo","Álvarez","España",120,null,17,"36201",null,11,Posicion.DELANTERO , 80));
        celta.getJugadores().add(new Jugador("Ferran","Jutglà","España",121,null,9,"36201",null,11,Posicion.DELANTERO , 82));

        lista.add(celta);


        Equipo rayo = new Equipo(12, "Rayo Vallecano", "Madrid", "Vallecas", 12);
        rayo.setEntrenador(new Entrenador("Íñigo", "Pérez", "España", 12));


        rayo.getJugadores().add(new Jugador("Dani","Cárdenas","España",122,null,1,"28001",null,12,Posicion.PORTERO, 77));
        rayo.getJugadores().add(new Jugador("Andrei","Rațiu","Rumanía",123,null,2,"28001",null,12,Posicion.DEFENSA , 76));
        rayo.getJugadores().add(new Jugador("Pep","Chavarría","España",124,null,3,"28001",null,12,Posicion.DEFENSA , 77));
        rayo.getJugadores().add(new Jugador("Florian","Lejeune","Francia",125,null,24,"28001",null,12,Posicion.DEFENSA , 82));
        rayo.getJugadores().add(new Jugador("Abdul","Mumin","Ghana",126,null,16,"28001",null,12,Posicion.DEFENSA , 80));

        rayo.getJugadores().add(new Jugador("Abdul","Mumin","Ghana",127,null,16,"28001",null,12,Posicion.DEFENSA , 79));
        rayo.getJugadores().add(new Jugador("Pathé","Ciss","Senegal",128,null,6,"28001",null,12,Posicion.CENTROCAMPISTA , 78));
        rayo.getJugadores().add(new Jugador("Pedro","Díaz","España",129,null,4,"28001",null,12,Posicion.CENTROCAMPISTA , 80));

        rayo.getJugadores().add(new Jugador("Isi","Palazón","España",130,null,7,"28001",null,12,Posicion.DELANTERO , 84));
        rayo.getJugadores().add(new Jugador("Óscar","Trejo","Argentina",131,null,8,"28001",null,12,Posicion.CENTROCAMPISTA , 82));
        rayo.getJugadores().add(new Jugador("Alemão","da Silva","Brasil",132,null,9,"28001",null,12,Posicion.DELANTERO , 78));

        lista.add(rayo);

        Equipo mallorca = new Equipo(13, "RCD Mallorca", "Palma", "Son Moix", 13);
        mallorca.setEntrenador(new Entrenador("Martín", "Demichelis", "Argentina", 13));

        mallorca.getJugadores().add(new Jugador("Leo","Román","España",133,null,1,"07001",null,13,Posicion.PORTERO , 77));
        mallorca.getJugadores().add(new Jugador("Mateu","Jaume","España",134,null,2,"07001",null,13,Posicion.DEFENSA , 76));
        mallorca.getJugadores().add(new Jugador("Toni","Lato","España",135,null,3,"07001",null,13,Posicion.DEFENSA , 77));
        mallorca.getJugadores().add(new Jugador("Antonio","Raíllo","España",136,null,21,"07001",null,13,Posicion.DEFENSA , 82));
        mallorca.getJugadores().add(new Jugador("Johan","Mojica","Colombia",137,null,22,"07001",null,13,Posicion.DEFENSA , 80));

        mallorca.getJugadores().add(new Jugador("Omar","Mascarell","España",138,null,5,"07001",null,13,Posicion.CENTROCAMPISTA , 79));
        mallorca.getJugadores().add(new Jugador("Antonio","Sánchez","España",139,null,6,"07001",null,13,Posicion.CENTROCAMPISTA , 78));
        mallorca.getJugadores().add(new Jugador("Dani","Rodríguez","España",140,null,14,"07001",null,13,Posicion.CENTROCAMPISTA, 80));

        mallorca.getJugadores().add(new Jugador("Vedat","Muriqi","Kosovo",141,null,7,"07001",null,13,Posicion.DELANTERO , 84));
        mallorca.getJugadores().add(new Jugador("Cyle","Larin","Canadá",142,null,17,"07001",null,13,Posicion.DELANTERO, 82));
        mallorca.getJugadores().add(new Jugador("Abdón","Prats","España",143,null,9,"07001",null,13,Posicion.DELANTERO, 78));

        lista.add(mallorca);

        Equipo alaves = new Equipo(16, "Deportivo Alavés", "Vitoria-Gasteiz", "Mendizorroza", 16);
        alaves.setEntrenador(new Entrenador("Eduardo", "Coudet", "Argentina", 16));

        alaves.getJugadores().add(new Jugador("Antonio","Sivera","España",144,null,1,"01001",null,16,Posicion.PORTERO , 78));
        alaves.getJugadores().add(new Jugador("Nahuel","Tenaglia","Argentina",145,null,14,"01001",null,16,Posicion.DEFENSA , 77));
        alaves.getJugadores().add(new Jugador("Abdel","Abqar","Marruecos",146,null,5,"01001",null,16,Posicion.DEFENSA , 79));
        alaves.getJugadores().add(new Jugador("Rubén","Duarte","España",147,null,3,"01001",null,16,Posicion.DEFENSA , 80));
        alaves.getJugadores().add(new Jugador("Andoni","Gorosabel","España",148,null,2,"01001",null,16,Posicion.DEFENSA , 78));

        alaves.getJugadores().add(new Jugador("Antonio","Blanco","España",149,null,8,"01001",null,16,Posicion.CENTROCAMPISTA , 81));
        alaves.getJugadores().add(new Jugador("Jon","Guridi","España",150,null,18,"01001",null,16,Posicion.CENTROCAMPISTA , 80));
        alaves.getJugadores().add(new Jugador("Carlos","Benavídez","Uruguay",151,null,23,"01001",null,16,Posicion.CENTROCAMPISTA , 79));

        alaves.getJugadores().add(new Jugador("Luis","Rioja","España",152,null,11,"01001",null,16,Posicion.DELANTERO , 83));
        alaves.getJugadores().add(new Jugador("Kike","García","España",153,null,9,"01001",null,16,Posicion.DELANTERO , 82));
        alaves.getJugadores().add(new Jugador("Samu","Omorodion","España",154,null,32,"01001",null,16,Posicion.DELANTERO , 84));

        lista.add(alaves);

        Equipo girona = new Equipo(15, "Girona FC", "Girona", "Montilivi", 15);
        girona.setEntrenador(new Entrenador("Míchel", "Sánchez", "España", 15));

        girona.getJugadores().add(new Jugador("Paulo","Gazzaniga","Argentina",155,null,13,"17001",null,15,Posicion.PORTERO , 82));
        girona.getJugadores().add(new Jugador("Hugo","Rincón","España",156,null,2,"17001",null,15,Posicion.DEFENSA , 81));
        girona.getJugadores().add(new Jugador("Vitor","Reis","Brasil",157,null,4,"17001",null,15,Posicion.DEFENSA , 82));
        girona.getJugadores().add(new Jugador("Daley","Blind","Países Bajos",158,null,17,"17001",null,15,Posicion.DEFENSA , 80));
        girona.getJugadores().add(new Jugador("Arnau","Martínez","España",159,null,16,"17001",null,15,Posicion.DEFENSA , 79));

        girona.getJugadores().add(new Jugador("Axel","Witsel","Bélgica",160,null,6,"17001",null,15,Posicion.CENTROCAMPISTA , 79));
        girona.getJugadores().add(new Jugador("Fran","Beltrán","España",161,null,8,"17001",null,15,Posicion.CENTROCAMPISTA , 80));
        girona.getJugadores().add(new Jugador("Viktor","Tsygankov","Ucrania",162,null,11,"17001",null,15,Posicion.DELANTERO , 80));

        girona.getJugadores().add(new Jugador("Thomas","Lemar","Francia",163,null,7,"17001",null,15,Posicion.CENTROCAMPISTA , 81));
        girona.getJugadores().add(new Jugador("Bryan","Gil","España",164,null,10,"17001",null,15,Posicion.DELANTERO , 79));
        girona.getJugadores().add(new Jugador("Vladyslav","Vanat","Ucrania",165,null,9,"17001",null,15,Posicion.DELANTERO , 81));

        lista.add(girona);

        Equipo getafe = new Equipo(14, "Getafe CF", "Getafe", "Coliseum", 14);
        getafe.setEntrenador(new Entrenador("José", "Bordalás", "España", 14));


        getafe.getJugadores().add(new Jugador("David","Soria","España",166, null, 6,"28901",null,14,Posicion.PORTERO , 78));
        getafe.getJugadores().add(new Jugador("Kiko","Femenía","España",167,null,2,"28901",null,14,Posicion.DEFENSA , 77));
        getafe.getJugadores().add(new Jugador("Domingos","Duarte","Cabo Verde",168,null,22,"28901",null,14,Posicion.DEFENSA , 75));
        getafe.getJugadores().add(new Jugador("Abdel","Abqar","Marruecos",169,null,4,"28901",null,14,Posicion.DEFENSA , 77));
        getafe.getJugadores().add(new Jugador("Djené","Dakonam","Togo",170,null,12,"28901",null,14,Posicion.DEFENSA , 78));

        getafe.getJugadores().add(new Jugador("Juan","Iglesias","España",171,null,21,"28901",null,14,Posicion.DEFENSA , 79));
        getafe.getJugadores().add(new Jugador("Luis","Milla","España",172,null,5,"28901",null,14,Posicion.CENTROCAMPISTA , 76));
        getafe.getJugadores().add(new Jugador("Mauro","Arambarri","Uruguay",173,null,8,"28901",null,14,Posicion.CENTROCAMPISTA , 76));

        getafe.getJugadores().add(new Jugador("Mario","Martín","España",174,null,6,"28901",null,14,Posicion.CENTROCAMPISTA , 78));
        getafe.getJugadores().add(new Jugador("Luis","Vázquez","Argentina",175,null,9,"28901",null,14,Posicion.DELANTERO , 78));
        getafe.getJugadores().add(new Jugador("Martín","Satriano","Uruguay",176,null,19,"28901",null,14,Posicion.DELANTERO , 79));

        lista.add(getafe);


        return lista;
    }
}