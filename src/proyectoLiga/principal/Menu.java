package proyectoLiga.principal;

import proyectoLiga.competicion.*;
import proyectoLiga.personas.Entrenador;
import proyectoLiga.personas.Jugador;

import java.time.LocalDate;
import java.util.*;

public class Menu {

    private final Scanner sc;
    private final Liga deluxLeague;
    private final Liga premierleague;
    private Liga ligaActual;
    private final Liga ligueOne;


    public Menu() {
        this.sc = new Scanner(System.in);
        this.deluxLeague = crearDeluxLeague();
        this.premierleague = crearPremierLeague();
        this.ligueOne = crearLigueOne();
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
        System.out.println("3. " + ligueOne.getNombre());

        int op = leerOpcion(1,3);

        if (op == 1) ligaActual = deluxLeague;
        else if (op == 2) ligaActual = premierleague;
        else ligaActual = ligueOne;

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
                    menuPartidos();
                    break;
                case 3:
                    menuPremios();
                    break;
                case 4:
                    menuClasificacion();
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
        System.out.println("Entrenador : " + e.getEntrenador());
        System.out.println("Patrocinador : " + e.getPatrocinador());

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

        asignarPatrocinadores(liga);
        return liga;
    }

    private Liga crearPremierLeague() {
        Liga liga = new Liga (2, "Premier League " , "Inglaterra" , "2025/26");

        List <Equipo> equipos = crearEquiposPremierLeague();
        for (Equipo e : equipos) {
            liga.addEquipo(e);
        }

        asignarPatrocinadores(liga);
        return liga;
    }

    private Liga crearLigueOne() {
        Liga liga = new Liga (3, "LigueOne " , "Francia" , "2025/26");

        List <Equipo> equipos = crearEquiposLigueOne();
        for (Equipo e : equipos) {
            liga.addEquipo(e);
        }

        asignarPatrocinadores(liga);
        return liga;
    }

    private List<Equipo> crearEquiposDelux() {
        List<Equipo> lista = new ArrayList<>();

        Equipo madrid = new Equipo(1,"Real Madrid","Madrid","Santiago Bernabéu",1);
        madrid.setEntrenador(new Entrenador("Alvaro","Arbeloa","España",1));

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

        leganes.getJugadores().add(new Jugador("Dani","Rodríguez","España",31,null,9,"28918",null,3,Posicion.DELANTERO , 80));
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

        Equipo alaves = new Equipo(14, "Deportivo Alavés", "Vitoria-Gasteiz", "Mendizorroza", 14);
        alaves.setEntrenador(new Entrenador("Eduardo", "Coudet", "Argentina", 14));

        alaves.getJugadores().add(new Jugador("Antonio","Sivera","España",144,null,1,"01001",null,14,Posicion.PORTERO , 78));
        alaves.getJugadores().add(new Jugador("Nahuel","Tenaglia","Argentina",145,null,14,"01001",null,14,Posicion.DEFENSA , 77));
        alaves.getJugadores().add(new Jugador("Abdel","Abqar","Marruecos",146,null,5,"01001",null,14,Posicion.DEFENSA , 79));
        alaves.getJugadores().add(new Jugador("Rubén","Duarte","España",147,null,3,"01001",null,14,Posicion.DEFENSA , 80));
        alaves.getJugadores().add(new Jugador("Andoni","Gorosabel","España",148,null,2,"01001",null,14,Posicion.DEFENSA , 78));

        alaves.getJugadores().add(new Jugador("Antonio","Blanco","España",149,null,8,"01001",null,14,Posicion.CENTROCAMPISTA , 81));
        alaves.getJugadores().add(new Jugador("Jon","Guridi","España",150,null,18,"01001",null,14,Posicion.CENTROCAMPISTA , 80));
        alaves.getJugadores().add(new Jugador("Carlos","Benavídez","Uruguay",151,null,23,"01001",null,14,Posicion.CENTROCAMPISTA , 79));

        alaves.getJugadores().add(new Jugador("Luis","Rioja","España",152,null,11,"01001",null,14,Posicion.DELANTERO , 83));
        alaves.getJugadores().add(new Jugador("Kike","García","España",153,null,9,"01001",null,14,Posicion.DELANTERO , 82));
        alaves.getJugadores().add(new Jugador("Samu","Omorodion","España",154,null,32,"01001",null,14,Posicion.DELANTERO , 84));

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

        Equipo getafe = new Equipo(16, "Getafe CF", "Getafe", "Coliseum", 16);
        getafe.setEntrenador(new Entrenador("José", "Bordalás", "España", 16));


        getafe.getJugadores().add(new Jugador("David","Soria","España",166, null, 6,"28901",null,16,Posicion.PORTERO , 78));
        getafe.getJugadores().add(new Jugador("Kiko","Femenía","España",167,null,2,"28901",null,16,Posicion.DEFENSA , 77));
        getafe.getJugadores().add(new Jugador("Domingos","Duarte","Cabo Verde",168,null,22,"28901",null,16,Posicion.DEFENSA , 75));
        getafe.getJugadores().add(new Jugador("Abdel","Abqar","Marruecos",169,null,4,"28901",null,16,Posicion.DEFENSA , 77));
        getafe.getJugadores().add(new Jugador("Djené","Dakonam","Togo",170,null,12,"28901",null,16,Posicion.DEFENSA , 78));

        getafe.getJugadores().add(new Jugador("Juan","Iglesias","España",171,null,21,"28901",null,16,Posicion.DEFENSA , 79));
        getafe.getJugadores().add(new Jugador("Luis","Milla","España",172,null,5,"28901",null,16,Posicion.CENTROCAMPISTA , 76));
        getafe.getJugadores().add(new Jugador("Mauro","Arambarri","Uruguay",173,null,8,"28901",null,16,Posicion.CENTROCAMPISTA , 76));

        getafe.getJugadores().add(new Jugador("Mario","Martín","España",174,null,6,"28901",null,16,Posicion.CENTROCAMPISTA , 78));
        getafe.getJugadores().add(new Jugador("Luis","Vázquez","Argentina",175,null,9,"28901",null,16,Posicion.DELANTERO , 78));
        getafe.getJugadores().add(new Jugador("Martín","Satriano","Uruguay",176,null,19,"28901",null,16,Posicion.DELANTERO , 79));

        lista.add(getafe);

        return lista;
    }

    private List<Equipo> crearEquiposPremierLeague() {
        List<Equipo> lista = new ArrayList<>();
        Equipo Liverpool = new Equipo(1,"Liverpool","Liverpool","Anfield",1);
        Liverpool.setEntrenador(new Entrenador("Arne","Slot","Países Bajos",1));

        Liverpool.getJugadores().add(new Jugador("Alisson","Becker",        "Brasil",            1,null, 1,"L4",null,1,Posicion.PORTERO,       87));
        Liverpool.getJugadores().add(new Jugador("Conor","Bradley",         "Irlanda del Norte", 2,null,84,"L4",null,1,Posicion.DEFENSA,      82));
        Liverpool.getJugadores().add(new Jugador("Ibrahima","Konaté",       "Francia",           3,null, 5,"L4",null,1,Posicion.DEFENSA,      85));
        Liverpool.getJugadores().add(new Jugador("Virgil","van Dijk",       "Países Bajos",      4,null, 4,"L4",null,1,Posicion.DEFENSA,      89));
        Liverpool.getJugadores().add(new Jugador("Andrew","Robertson",      "Escocia",           5,null,26,"L4",null,1,Posicion.DEFENSA,      85));

        Liverpool.getJugadores().add(new Jugador("Alexis","Mac Allister",   "Argentina",         6,null,10,"L4",null,1,Posicion.CENTROCAMPISTA,86));
        Liverpool.getJugadores().add(new Jugador("Dominik","Szoboszlai",    "Hungría",           7,null, 8,"L4",null,1,Posicion.CENTROCAMPISTA,85));
        Liverpool.getJugadores().add(new Jugador("Ryan","Gravenberch",      "Países Bajos",      8,null,38,"L4",null,1,Posicion.CENTROCAMPISTA,84));

        Liverpool.getJugadores().add(new Jugador("Mohamed","Salah",         "Egipto",            9,null,11,"L4",null,1,Posicion.DELANTERO,     89));
        Liverpool.getJugadores().add(new Jugador("Darwin","Núñez",          "Uruguay",          10,null, 9,"L4",null,1,Posicion.DELANTERO,     85));
        Liverpool.getJugadores().add(new Jugador("Cody","Gakpo",            "Países Bajos",     11,null,18,"L4",null,1,Posicion.DELANTERO,     85));

        lista.add(Liverpool);

        Equipo ManchesterCity = new Equipo(2,"Manchester City","Manchester","Etihad Stadium",2);
        ManchesterCity.setEntrenador(new Entrenador("Pep","Guardiola","España",2));

        ManchesterCity.getJugadores().add(new Jugador("Ederson","Moraes",      "Brasil",        12,null,31,"M1",null,2,Posicion.PORTERO,       88));
        ManchesterCity.getJugadores().add(new Jugador("Kyle","Walker",         "Inglaterra",    13,null, 2,"M1",null,2,Posicion.DEFENSA,      84));
        ManchesterCity.getJugadores().add(new Jugador("Ruben","Dias",          "Portugal",      14,null, 3,"M1",null,2,Posicion.DEFENSA,      89));
        ManchesterCity.getJugadores().add(new Jugador("John","Stones",         "Inglaterra",    15,null, 5,"M1",null,2,Posicion.DEFENSA,      86));
        ManchesterCity.getJugadores().add(new Jugador("Josko","Gvardiol",      "Croacia",       16,null,24,"M1",null,2,Posicion.DEFENSA,      86));

        ManchesterCity.getJugadores().add(new Jugador("Rodri","Hernandez",     "España",        17,null,16,"M1",null,2,Posicion.CENTROCAMPISTA,89));
        ManchesterCity.getJugadores().add(new Jugador("Kevin","De Bruyne",     "Bélgica",       18,null,17,"M1",null,2,Posicion.CENTROCAMPISTA,91));
        ManchesterCity.getJugadores().add(new Jugador("Bernardo","Silva",      "Portugal",      19,null,20,"M1",null,2,Posicion.CENTROCAMPISTA,88));

        ManchesterCity.getJugadores().add(new Jugador("Phil","Foden",          "Inglaterra",    20,null,47,"M1",null,2,Posicion.DELANTERO,     89));
        ManchesterCity.getJugadores().add(new Jugador("Erling","Haaland",      "Noruega",       21,null, 9,"M1",null,2,Posicion.DELANTERO,     92));
        ManchesterCity.getJugadores().add(new Jugador("Jeremy","Doku",         "Bélgica",       22,null,11,"M1",null,2,Posicion.DELANTERO,     85));

        lista.add(ManchesterCity);

        Equipo Arsenal = new Equipo(3,"Arsenal","Londres","Emirates Stadium",3);
        Arsenal.setEntrenador(new Entrenador("Mikel","Arteta","España",3));

        Arsenal.getJugadores().add(new Jugador("David","Raya",          "España",      23,null,22,"N7",null,3,Posicion.PORTERO,       85));
        Arsenal.getJugadores().add(new Jugador("Ben","White",           "Inglaterra",  24,null, 4,"N7",null,3,Posicion.DEFENSA,      84));
        Arsenal.getJugadores().add(new Jugador("William","Saliba",      "Francia",     25,null, 2,"N7",null,3,Posicion.DEFENSA,      87));
        Arsenal.getJugadores().add(new Jugador("Gabriel","Magalhaes",   "Brasil",      26,null, 6,"N7",null,3,Posicion.DEFENSA,      86));
        Arsenal.getJugadores().add(new Jugador("Oleksandr","Zinchenko", "Ucrania",     27,null,35,"N7",null,3,Posicion.DEFENSA,      83));

        Arsenal.getJugadores().add(new Jugador("Declan","Rice",         "Inglaterra",  28,null,41,"N7",null,3,Posicion.CENTROCAMPISTA,89));
        Arsenal.getJugadores().add(new Jugador("Martin","Odegaard",     "Noruega",     29,null, 8,"N7",null,3,Posicion.CENTROCAMPISTA,88));
        Arsenal.getJugadores().add(new Jugador("Kai","Havertz",         "Alemania",    30,null,29,"N7",null,3,Posicion.CENTROCAMPISTA,85));

        Arsenal.getJugadores().add(new Jugador("Bukayo","Saka",         "Inglaterra",  32,null, 7,"N7",null,3,Posicion.DELANTERO,     89));
        Arsenal.getJugadores().add(new Jugador("Gabriel","Martinelli",  "Brasil",     32,null,11,"N7",null,3,Posicion.DELANTERO,     86));
        Arsenal.getJugadores().add(new Jugador("Gabriel","Jesus",       "Brasil",     33,null, 9,"N7",null,3,Posicion.DELANTERO,     84));

        lista.add(Arsenal);

        Equipo ManchesterUnited = new Equipo(4,"Manchester United","Manchester","Old Trafford",4);
        ManchesterUnited.setEntrenador(new Entrenador("Erik","Ten Hag","Países Bajos",4));

        ManchesterUnited.getJugadores().add(new Jugador("David",      "De Gea",         "España",        34, null, 1,  "M4", null, 4, Posicion.PORTERO,       86));
        ManchesterUnited.getJugadores().add(new Jugador("Lisandro",   "Martínez",       "Argentina",     35, null, 6,  "M4", null, 4, Posicion.DEFENSA,       85));
        ManchesterUnited.getJugadores().add(new Jugador("Raphael",    "Varane",         "Francia",       36, null, 5,  "M4", null, 4, Posicion.DEFENSA,       87));
        ManchesterUnited.getJugadores().add(new Jugador("Diogo",      "Dalot",          "Portugal",      37, null, 20, "M4", null, 4, Posicion.DEFENSA,       84));
        ManchesterUnited.getJugadores().add(new Jugador("Tyrell",     "Malinga",        "Países Bajos",  38, null, 23, "M4", null, 4, Posicion.DEFENSA,       83));

        ManchesterUnited.getJugadores().add(new Jugador("Bruno",      "Fernandes",      "Portugal",      39, null, 8,  "M4", null, 4, Posicion.CENTROCAMPISTA,88));
        ManchesterUnited.getJugadores().add(new Jugador("Casemiro",   "Casemiro",       "Brasil",        40, null, 18, "M4", null, 4, Posicion.CENTROCAMPISTA,87));
        ManchesterUnited.getJugadores().add(new Jugador("Christian",  "Eriksen",        "Dinamarca",     41, null, 21, "M4", null, 4, Posicion.CENTROCAMPISTA,85));

        ManchesterUnited.getJugadores().add(new Jugador("Marcus",     "Rashford",       "Inglaterra",    42, null, 10, "M4", null, 4, Posicion.DELANTERO,      88));
        ManchesterUnited.getJugadores().add(new Jugador("Anthony",    "Martial",        "Francia",       43, null, 9,  "M4", null, 4, Posicion.DELANTERO,      84));
        ManchesterUnited.getJugadores().add(new Jugador("Jadon",      "Sancho",         "Inglaterra",    44, null, 7,  "M4", null, 4, Posicion.DELANTERO,      86));

        lista.add(ManchesterUnited);

        Equipo Chelsea = new Equipo(5,"Chelsea","Londres","Stamford Bridge",5);
        Chelsea.setEntrenador(new Entrenador("Graham","Potter","Inglaterra",5));

        Chelsea.getJugadores().add(new Jugador("Kepa",        "Arrizabalaga",   "España",        45, null, 1,  "C5", null, 5, Posicion.PORTERO,       85));
        Chelsea.getJugadores().add(new Jugador("Reece",       "James",          "Inglaterra",    46, null, 24, "C5", null, 5, Posicion.DEFENSA,       86));
        Chelsea.getJugadores().add(new Jugador("Thiago",      "Silva",          "Brasil",        47, null, 6,  "C5", null, 5, Posicion.DEFENSA,       87));
        Chelsea.getJugadores().add(new Jugador("Kalidou",     "Koulibaly",      "Senegal",       48, null, 26, "C5", null, 5, Posicion.DEFENSA,       88));
        Chelsea.getJugadores().add(new Jugador("Wes",         "Fofana",         "Francia",       49, null, 33, "C5", null, 5, Posicion.DEFENSA,       85));

        Chelsea.getJugadores().add(new Jugador("Enzo",        "Fernandez",      "Argentina",     50, null, 8,  "C5", null, 5, Posicion.CENTROCAMPISTA,87));
        Chelsea.getJugadores().add(new Jugador("Mason",       "Mount",          "Inglaterra",    51, null, 19, "C5", null, 5, Posicion.CENTROCAMPISTA,86));
        Chelsea.getJugadores().add(new Jugador("N’Golo",      "Kante",          "Francia",       52, null, 7,  "C5", null, 5, Posicion.CENTROCAMPISTA,87));

        Chelsea.getJugadores().add(new Jugador("Raheem",      "Sterling",       "Inglaterra",    53, null, 7,  "C5", null, 5, Posicion.DELANTERO,      86));
        Chelsea.getJugadores().add(new Jugador("Kai",         "Havertz",        "Alemania",      54, null, 29, "C5", null, 5, Posicion.DELANTERO,      85));
        Chelsea.getJugadores().add(new Jugador("Mykhailo",    "Mudryk",         "Ucrania",       55, null, 10, "C5", null, 5, Posicion.DELANTERO,      84));

        lista.add(Chelsea);

        Equipo Tottenham = new Equipo(6,"Tottenham Hotspur","Londres","Tottenham Hotspur Stadium",6);
        Tottenham.setEntrenador(new Entrenador("Antonio","Conte","Italia",6));

        Tottenham.getJugadores().add(new Jugador("Hugo",       "Lloris",       "Francia",       56, null, 1,  "T6", null, 6, Posicion.PORTERO,       86));
        Tottenham.getJugadores().add(new Jugador("Cristian",   "Romero",       "Argentina",     57, null, 3,  "T6", null, 6, Posicion.DEFENSA,       85));
        Tottenham.getJugadores().add(new Jugador("Eric",       "Dier",         "Inglaterra",    58, null, 15, "T6", null, 6, Posicion.DEFENSA,       84));
        Tottenham.getJugadores().add(new Jugador("Ben",        "Davies",       "Gales",         59, null, 33, "T6", null, 6, Posicion.DEFENSA,       84));
        Tottenham.getJugadores().add(new Jugador("Ivan",       "Perisic",      "Croacia",       60, null, 14, "T6", null, 6, Posicion.DEFENSA,       83));

        Tottenham.getJugadores().add(new Jugador("Pierre-Emile","Højbjerg",    "Dinamarca",     61, null, 5,  "T6", null, 6, Posicion.CENTROCAMPISTA,86));
        Tottenham.getJugadores().add(new Jugador("Rodrigo",    "Bentancur",    "Uruguay",       62, null, 30, "T6", null, 6, Posicion.CENTROCAMPISTA,85));
        Tottenham.getJugadores().add(new Jugador("Dejan",      "Kulusevski",   "Suecia",        63, null, 7,  "T6", null, 6, Posicion.CENTROCAMPISTA,85));

        Tottenham.getJugadores().add(new Jugador("Harry",      "Kane",         "Inglaterra",    64, null, 10, "T6", null, 6, Posicion.DELANTERO,      91));
        Tottenham.getJugadores().add(new Jugador("Son",        "Heung-min",    "Corea del Sur", 65, null, 7,  "T6", null, 6, Posicion.DELANTERO,      90));
        Tottenham.getJugadores().add(new Jugador("Richarlison","de Andrade",   "Brasil",        66, null, 9,  "T6", null, 6, Posicion.DELANTERO,      86));

        lista.add(Tottenham);


        Equipo Newcastle = new Equipo(7,"Newcastle United","Newcastle","St. James' Park",7);
        Newcastle.setEntrenador(new Entrenador("Eddie","Howe","Inglaterra",7));

        Newcastle.getJugadores().add(new Jugador("Nick","Pope",             "Inglaterra",   67, null, 1,  "N7", null, 7, Posicion.PORTERO,       85));
        Newcastle.getJugadores().add(new Jugador("Kieran","Trippier",       "Inglaterra",   68, null, 2,  "N7", null, 7, Posicion.DEFENSA,       86));
        Newcastle.getJugadores().add(new Jugador("Sven","Botman",          "Países Bajos", 69, null, 5,  "N7", null, 7, Posicion.DEFENSA,       86));
        Newcastle.getJugadores().add(new Jugador("Fabian","Schar",         "Suiza",        70, null, 3,  "N7", null, 7, Posicion.DEFENSA,       84));
        Newcastle.getJugadores().add(new Jugador("Dan","Burn",             "Inglaterra",   71, null, 24, "N7", null, 7, Posicion.DEFENSA,       83));

        Newcastle.getJugadores().add(new Jugador("Bruno","Guimaraes",      "Brasil",       72, null, 8,  "N7", null, 7, Posicion.CENTROCAMPISTA,87));
        Newcastle.getJugadores().add(new Jugador("Joelinton","Silva",        "Brasil",       73, null, 7,  "N7", null, 7, Posicion.CENTROCAMPISTA,85));
        Newcastle.getJugadores().add(new Jugador("Miguel","Almiron",        "Paraguay",     74, null, 10, "N7", null, 7, Posicion.CENTROCAMPISTA,84));

        Newcastle.getJugadores().add(new Jugador("Callum","Wilson",         "Inglaterra",   75, null, 9,  "N7", null, 7, Posicion.DELANTERO,      86));
        Newcastle.getJugadores().add(new Jugador("Alexander","Isak",         "Suecia",       76, null, 11, "N7", null, 7, Posicion.DELANTERO,      87));
        Newcastle.getJugadores().add(new Jugador("Allan","Saint-Maximin",   "Francia",      77, null, 7,  "N7", null, 7, Posicion.DELANTERO,      85));

        lista.add(Newcastle);


        Equipo AstonVilla = new Equipo(8,"Aston Villa","Birmingham","Villa Park",8);
        AstonVilla.setEntrenador(new Entrenador("Unai","Emery","España",8));

        AstonVilla.getJugadores().add(new Jugador("Emiliano","Martinez",      "Argentina",    78, null, 1,  "A8", null, 8, Posicion.PORTERO,       86));
        AstonVilla.getJugadores().add(new Jugador("Matty","Cash",            "Inglaterra",   79, null, 2,  "A8", null, 8, Posicion.DEFENSA,       84));
        AstonVilla.getJugadores().add(new Jugador("Diego","Carlos",          "Brasil",       80, null, 3,  "A8", null, 8, Posicion.DEFENSA,       85));
        AstonVilla.getJugadores().add(new Jugador("Ezri","Konsa",            "Inglaterra",   81, null, 5,  "A8", null, 8, Posicion.DEFENSA,       84));
        AstonVilla.getJugadores().add(new Jugador("Tyrone","Mings",          "Inglaterra",   82, null, 6,  "A8", null, 8, Posicion.DEFENSA,       83));

        AstonVilla.getJugadores().add(new Jugador("John","McGinn",          "Escocia",      83, null, 8,  "A8", null, 8, Posicion.CENTROCAMPISTA,85));
        AstonVilla.getJugadores().add(new Jugador("Douglas","Luiz",         "Brasil",       84, null, 21, "A8", null, 8, Posicion.CENTROCAMPISTA,86));
        AstonVilla.getJugadores().add(new Jugador("Jacob","Ramsey",         "Inglaterra",   85, null, 16, "A8", null, 8, Posicion.CENTROCAMPISTA,84));

        AstonVilla.getJugadores().add(new Jugador("Ollie","Watkins",        "Inglaterra",   86, null, 9,  "A8", null, 8, Posicion.DELANTERO,      87));
        AstonVilla.getJugadores().add(new Jugador("Leon","Bailey",          "Jamaica",      87, null, 7,  "A8", null, 8, Posicion.DELANTERO,      85));
        AstonVilla.getJugadores().add(new Jugador("Ashley","Young",         "Inglaterra",   88, null, 11, "A8", null, 8, Posicion.DELANTERO,      84));

        lista.add(AstonVilla);


        Equipo Brighton = new Equipo(9,"Brighton & Hove Albion","Brighton","Amex Stadium",9);
        Brighton.setEntrenador(new Entrenador("Roberto","De Zerbi","Italia",9));

        Brighton.getJugadores().add(new Jugador("Robert","Sánchez",       "España",       89, null, 1,  "B9", null, 9, Posicion.PORTERO,       85));
        Brighton.getJugadores().add(new Jugador("Leandro","Trossard",      "Bélgica",      90, null, 2,  "B9", null, 9, Posicion.DEFENSA,       84));
        Brighton.getJugadores().add(new Jugador("Adam","Webster",         "Inglaterra",   91, null, 3,  "B9", null, 9, Posicion.DEFENSA,       83));
        Brighton.getJugadores().add(new Jugador("Lewis","Dunk",           "Inglaterra",   92, null, 5,  "B9", null, 9, Posicion.DEFENSA,       84));
        Brighton.getJugadores().add(new Jugador("Marc","Cucurella",      "España",       93, null, 23, "B9", null, 9, Posicion.DEFENSA,       85));

        Brighton.getJugadores().add(new Jugador("Pascal","Gross",         "Alemania",     94, null, 8,  "B9", null, 9, Posicion.CENTROCAMPISTA,86));
        Brighton.getJugadores().add(new Jugador("Moises","Caicedo",       "Ecuador",      95, null, 10, "B9", null, 9, Posicion.CENTROCAMPISTA,85));
        Brighton.getJugadores().add(new Jugador("Solly","March",          "Inglaterra",   96, null, 7,  "B9", null, 9, Posicion.CENTROCAMPISTA,84));

        Brighton.getJugadores().add(new Jugador("Leandro","Trossard",      "Bélgica",      97, null, 9,  "B9", null, 9, Posicion.DELANTERO,      86));
        Brighton.getJugadores().add(new Jugador("Danny","Welbeck",        "Inglaterra",   98, null, 11, "B9", null, 9, Posicion.DELANTERO,      84));
        Brighton.getJugadores().add(new Jugador("Kaoru","Mitoma",          "Japón",        99, null, 7,  "B9", null, 9, Posicion.DELANTERO,      85));

        lista.add(Brighton);


        Equipo Wolves = new Equipo(10,"Wolverhampton Wanderers","Wolverhampton","Molineux Stadium",10);
        Wolves.setEntrenador(new Entrenador("Julen","Lopetegui","España",10));

        Wolves.getJugadores().add(new Jugador("José","Sa",              "Portugal",     100, null, 1, "W10", null, 10, Posicion.PORTERO,      84));
        Wolves.getJugadores().add(new Jugador("Maximilian","Kilman",     "Inglaterra",   101, null, 2, "W10", null, 10, Posicion.DEFENSA,      83));
        Wolves.getJugadores().add(new Jugador("Conor","Coady",           "Inglaterra",   102, null, 4, "W10", null, 10, Posicion.DEFENSA,      84));
        Wolves.getJugadores().add(new Jugador("Marçal","",              "Brasil",       103, null, 6, "W10", null, 10, Posicion.DEFENSA,      83));

        Wolves.getJugadores().add(new Jugador("Rayan","Aït-Nouri",        "Francia",      104, null, 33, "W10", null, 10, Posicion.DEFENSA,      84));
        Wolves.getJugadores().add(new Jugador("João","Moutinho",         "Portugal",     105, null, 8, "W10", null, 10, Posicion.CENTROCAMPISTA,85));
        Wolves.getJugadores().add(new Jugador("Matheus","Nunes",         "Portugal",     106, null, 10, "W10", null, 10, Posicion.CENTROCAMPISTA,86));
        Wolves.getJugadores().add(new Jugador("Daniel","Podence",        "Portugal",     107, null, 7, "W10", null, 10, Posicion.CENTROCAMPISTA,84));

        Wolves.getJugadores().add(new Jugador("Raúl","Jiménez",          "México",       108, null, 9, "W10", null, 10, Posicion.DELANTERO,     86));
        Wolves.getJugadores().add(new Jugador("Hwang","Hee-chan",        "Corea del Sur",109, null, 11, "W10", null, 10, Posicion.DELANTERO,     84));
        Wolves.getJugadores().add(new Jugador("Pedro"," Neto",            "Brasil",       110, null, 7, "W10", null, 10, Posicion.DELANTERO,     85));

        lista.add(Wolves);

        Equipo Leicester = new Equipo(11,"Leicester City","Leicester","King Power Stadium",11);
        Leicester.setEntrenador(new Entrenador("Enzo","Maresca","Italia",11));

        Leicester.getJugadores().add(new Jugador("Danny",       "Ward",       "Gales",       111, null, 1,  "L11", null, 11, Posicion.PORTERO,       84));
        Leicester.getJugadores().add(new Jugador("Wesley",      "Fofana",     "Francia",     112, null, 2,  "L11", null, 11, Posicion.DEFENSA,       85));
        Leicester.getJugadores().add(new Jugador("Ryan",        "Bennett",    "Gales",       113, null, 3,  "L11", null, 11, Posicion.DEFENSA,       83));
        Leicester.getJugadores().add(new Jugador("James",       "Justin",     "Inglaterra",  114, null, 4,  "L11", null, 11, Posicion.DEFENSA,       84));

        Leicester.getJugadores().add(new Jugador("Daniel",      "Amartey",    "Ghana",       115, null, 5,  "L11", null, 11, Posicion.DEFENSA,       83));
        Leicester.getJugadores().add(new Jugador("Youri",       "Tielemans",  "Bélgica",     116, null, 6,  "L11", null, 11, Posicion.CENTROCAMPISTA,86));
        Leicester.getJugadores().add(new Jugador("James",       "Maddison",   "Inglaterra",  117, null, 7,  "L11", null, 11, Posicion.CENTROCAMPISTA,85));
        Leicester.getJugadores().add(new Jugador("Boubakary",   "Soumaré",    "Francia",     118, null, 8,  "L11", null, 11, Posicion.CENTROCAMPISTA,84));
        Leicester.getJugadores().add(new Jugador("Kelechi",     "Iheanacho",  "Nigeria",     119, null, 9,  "L11", null, 11, Posicion.DELANTERO,      86));
        Leicester.getJugadores().add(new Jugador("Patson",      "Daka",       "Zambia",      120, null, 10, "L11", null, 11, Posicion.DELANTERO,      85));
        Leicester.getJugadores().add(new Jugador("Harvey",      "Barnes",     "Inglaterra",  121, null, 11, "L11", null, 11, Posicion.DELANTERO,      84));

        lista.add(Leicester);


        Equipo CrystalPalace = new Equipo(12,"Crystal Palace","Londres","Selhurst Park",12);
        CrystalPalace.setEntrenador(new Entrenador("Roy","Hodgson","Inglaterra",12));

        CrystalPalace.getJugadores().add(new Jugador("Vicente",    "Guaita",       "España",       122, null, 1,  "C12", null, 12, Posicion.PORTERO,       84));
        CrystalPalace.getJugadores().add(new Jugador("Joel",       "Ward",         "Inglaterra",   123, null, 2,  "C12", null, 12, Posicion.DEFENSA,       83));
        CrystalPalace.getJugadores().add(new Jugador("Tyrick",     "Mitchell",     "Inglaterra",   124, null, 3,  "C12", null, 12, Posicion.DEFENSA,       84));
        CrystalPalace.getJugadores().add(new Jugador("Marc",       "Guéhi",        "Costa de Marfil",125, null, 5,  "C12", null, 12, Posicion.DEFENSA,      85));
        CrystalPalace.getJugadores().add(new Jugador("James",      "Tomkins",      "Inglaterra",   126, null, 6,  "C12", null, 12, Posicion.DEFENSA,       83));

        CrystalPalace.getJugadores().add(new Jugador("Eberechi",   "Eze",          "Inglaterra",   127, null, 7,  "C12", null, 12, Posicion.CENTROCAMPISTA,86));
        CrystalPalace.getJugadores().add(new Jugador("Conor",      "Gallagher",    "Inglaterra",   128, null, 8,  "C12", null, 12, Posicion.CENTROCAMPISTA,85));
        CrystalPalace.getJugadores().add(new Jugador("Cheick",     "Doucouré",     "Malí",         129, null, 10, "C12", null, 12, Posicion.CENTROCAMPISTA,84));

        CrystalPalace.getJugadores().add(new Jugador("Odsonne",    "Edouard",      "Francia",      130, null, 9,  "C12", null, 12, Posicion.DELANTERO,      85));
        CrystalPalace.getJugadores().add(new Jugador("Jean-Philippe","Mateta",     "Francia",      131, null, 11, "C12", null, 12, Posicion.DELANTERO,      84));
        CrystalPalace.getJugadores().add(new Jugador("Wilfried",   "Zaha",         "Costa de Marfil",132, null, 7,  "C12", null, 12, Posicion.DELANTERO,      86));

        lista.add(CrystalPalace);


        Equipo Fulham = new Equipo(13,"Fulham","Londres","Craven Cottage",13);
        Fulham.setEntrenador(new Entrenador("Marco","Silva","Portugal",13));

        Fulham.getJugadores().add(new Jugador("Bernd",       "Leno",         "Alemania",     133, null, 1, "F13", null, 13, Posicion.PORTERO,       85));
        Fulham.getJugadores().add(new Jugador("Kenny",       "Tete",         "Países Bajos", 134, null, 2, "F13", null, 13, Posicion.DEFENSA,       83));
        Fulham.getJugadores().add(new Jugador("Joachim",     "Andersson",    "Suecia",       135, null, 3, "F13", null, 13, Posicion.DEFENSA,       84));
        Fulham.getJugadores().add(new Jugador("Tosin",       "Adarabioyo",   "Inglaterra",  136, null, 5, "F13", null, 13, Posicion.DEFENSA,       83));
        Fulham.getJugadores().add(new Jugador("Antonee",     "Robinson",     "Inglaterra",  137, null, 6, "F13", null, 13, Posicion.DEFENSA,       84));

        Fulham.getJugadores().add(new Jugador("Harrison",    "Reed",         "Inglaterra",  138, null, 8, "F13", null, 13, Posicion.CENTROCAMPISTA,85));
        Fulham.getJugadores().add(new Jugador("Andreas",     "Pereira",      "Brasil",       139, null, 10, "F13", null, 13, Posicion.CENTROCAMPISTA,84));
        Fulham.getJugadores().add(new Jugador("Joao",        "Palhinha",     "Portugal",     140, null, 7, "F13", null, 13, Posicion.CENTROCAMPISTA,85));

        Fulham.getJugadores().add(new Jugador("Aleksandar",  "Mitrovic",     "Serbia",       141, null, 9, "F13", null, 13, Posicion.DELANTERO,      88));
        Fulham.getJugadores().add(new Jugador("Bobby",       "Decordova-Reid","Inglaterra", 142, null, 11, "F13", null, 13, Posicion.DELANTERO,      85));
        Fulham.getJugadores().add(new Jugador("Harry",       "Wilson",       "Gales",        143, null, 7, "F13", null, 13, Posicion.DELANTERO,      84));

        lista.add(Fulham);


        Equipo Bournemouth = new Equipo(14,"Bournemouth","Bournemouth","Vitality Stadium",14);
        Bournemouth.setEntrenador(new Entrenador("Andoni","Etxeberria","España",14));

        Bournemouth.getJugadores().add(new Jugador("Mark",       "Travers",      "Inglaterra",   144, null, 1, "B14", null, 14, Posicion.PORTERO,       84));
        Bournemouth.getJugadores().add(new Jugador("Nathan",     "Ake",          "Países Bajos", 145, null, 2, "B14", null, 14, Posicion.DEFENSA,       84));
        Bournemouth.getJugadores().add(new Jugador("Chris",      "Mepham",       "Gales",        146, null, 3, "B14", null, 14, Posicion.DEFENSA,       83));
        Bournemouth.getJugadores().add(new Jugador("Jordan",     "Zagadou",      "Francia",      147, null, 5, "B14", null, 14, Posicion.DEFENSA,       84));
        Bournemouth.getJugadores().add(new Jugador("Lloyd",      "Kelly",        "Inglaterra",   148, null, 6, "B14", null, 14, Posicion.DEFENSA,       83));

        Bournemouth.getJugadores().add(new Jugador("Philip",     "Billing",      "Inglaterra",   149, null, 8, "B14", null, 14, Posicion.CENTROCAMPISTA,85));
        Bournemouth.getJugadores().add(new Jugador("Ben",        "Brereton",     "Chile",        150, null, 10, "B14", null, 14, Posicion.CENTROCAMPISTA,84));
        Bournemouth.getJugadores().add(new Jugador("Junior",     "Stanislas",    "Inglaterra",   151, null, 7, "B14", null, 14, Posicion.CENTROCAMPISTA,85));

        Bournemouth.getJugadores().add(new Jugador("Dominic",    "Solanke",      "Inglaterra",   152, null, 9, "B14", null, 14, Posicion.DELANTERO,      86));
        Bournemouth.getJugadores().add(new Jugador("Jaidon",     "Anthony",      "Inglaterra",   153, null, 11, "B14", null, 14, Posicion.DELANTERO,      84));
        Bournemouth.getJugadores().add(new Jugador("Arnaut",     "Danjuma",      "Países Bajos", 154, null, 7, "B14", null, 14, Posicion.DELANTERO,      85));

        lista.add(Bournemouth);


        Equipo Nottingham = new Equipo(15,"Nottingham Forest","Nottingham","City Ground",15);
        Nottingham.setEntrenador(new Entrenador("Steve","Cooper","Gales",15));

        Nottingham.getJugadores().add(new Jugador("Brice",      "Samba",        "Francia",      155, null, 1, "N15", null, 15, Posicion.PORTERO,       84));
        Nottingham.getJugadores().add(new Jugador("Joe",        "Worrall",      "Inglaterra",   156, null, 2, "N15", null, 15, Posicion.DEFENSA,       83));
        Nottingham.getJugadores().add(new Jugador("Brennan",    "Johnson",      "Inglaterra",   157, null, 3, "N15", null, 15, Posicion.DEFENSA,       84));
        Nottingham.getJugadores().add(new Jugador("Morgan",     "Gibbs-White",  "Inglaterra",   158, null, 5, "N15", null, 15, Posicion.DEFENSA,       84));
        Nottingham.getJugadores().add(new Jugador("Johan",      "Bakasetas",    "Grecia",       159, null, 6, "N15", null, 15, Posicion.DEFENSA,       83));

        Nottingham.getJugadores().add(new Jugador("Ryan",       "Yates",        "Inglaterra",   160, null, 8, "N15", null, 15, Posicion.CENTROCAMPISTA,85));
        Nottingham.getJugadores().add(new Jugador("James",      "Brunt",        "Inglaterra",   161, null, 10, "N15", null, 15, Posicion.CENTROCAMPISTA,84));
        Nottingham.getJugadores().add(new Jugador("Lyle",       "Taylor",       "Inglaterra",   162, null, 7, "N15", null, 15, Posicion.CENTROCAMPISTA,85));

        Nottingham.getJugadores().add(new Jugador("Brennan",    "Johnson",      "Inglaterra",   163, null, 9, "N15", null, 15, Posicion.DELANTERO,      86));
        Nottingham.getJugadores().add(new Jugador("Taiwo",      "Awoniyi",      "Nigeria",      164, null, 11, "N15", null, 15, Posicion.DELANTERO,      84));
        Nottingham.getJugadores().add(new Jugador("Keinan",     "Davis",        "Inglaterra",   165, null, 7, "N15", null, 15, Posicion.DELANTERO,      85));

        lista.add(Nottingham);

        Equipo Southampton = new Equipo(16,"Southampton","Southampton","St Mary's Stadium",16);
        Southampton.setEntrenador(new Entrenador("Russell","Martin","Escocia",16));

        Southampton.getJugadores().add(new Jugador("Fraser",     "Forster",      "Inglaterra",   166, null, 1, "S16", null, 16, Posicion.PORTERO,       84));
        Southampton.getJugadores().add(new Jugador("Ryan",       "Bertrand",     "Inglaterra",   167, null, 2, "S16", null, 16, Posicion.DEFENSA,       83));
        Southampton.getJugadores().add(new Jugador("Jan",        "Bednarek",     "Polonia",      168, null, 3, "S16", null, 16, Posicion.DEFENSA,       84));
        Southampton.getJugadores().add(new Jugador("Mohammed",   "Salisu",       "Ghana",        169, null, 5, "S16", null, 16, Posicion.DEFENSA,       83));
        Southampton.getJugadores().add(new Jugador("Lyanco",     "",             "Brasil",       170, null, 6, "S16", null, 16, Posicion.DEFENSA,       84));

        Southampton.getJugadores().add(new Jugador("James",      "Ward-Prowse",  "Inglaterra",   171, null, 8, "S16", null, 16, Posicion.CENTROCAMPISTA,85));
        Southampton.getJugadores().add(new Jugador("Roméo",      "Lavia",        "Bélgica",      172, null, 10,"S16", null, 16, Posicion.CENTROCAMPISTA,84));
        Southampton.getJugadores().add(new Jugador("Nathan",     "Redmond",      "Inglaterra",   173, null, 7, "S16", null, 16, Posicion.CENTROCAMPISTA,85));

        Southampton.getJugadores().add(new Jugador("Che",        "Adams",        "Inglaterra",   174, null, 9, "S16", null, 16, Posicion.DELANTERO,      86));
        Southampton.getJugadores().add(new Jugador("Lyanco",     "Fernando",     "Brasil",       175, null, 11,"S16", null, 16, Posicion.DELANTERO,      84));
        Southampton.getJugadores().add(new Jugador("Adam",       "Armstrong",    "Inglaterra",   176, null, 7, "S16", null, 16, Posicion.DELANTERO,      85));

        lista.add(Southampton);

        return lista;


    }
    private List<Equipo> crearEquiposLigueOne() {
        List<Equipo> lista = new ArrayList<>();

        Equipo PSG = new Equipo(1, "Paris Saint-Germain", "Paris", "Parc des Princes", 1);
        PSG.setEntrenador(new Entrenador("Luis", "Enrique", "España", 1));

        PSG.getJugadores().add(new Jugador("Gianluigi", "Donnarumma", "Italia",        177, null,  1, "75016", null, 1, Posicion.PORTERO,        89));

        PSG.getJugadores().add(new Jugador("Achraf",    "Hakimi",     "Marruecos",     178, null,  2, "75016", null, 1, Posicion.DEFENSA,        86));
        PSG.getJugadores().add(new Jugador("Milan",     "Skriniar",   "Eslovaquia",    179, null, 37, "75016", null, 1, Posicion.DEFENSA,        86));
        PSG.getJugadores().add(new Jugador("Marquinhos","Correa",     "Brasil",        180, null,  5, "75016", null, 1, Posicion.DEFENSA,        87));
        PSG.getJugadores().add(new Jugador("Lucas",     "Hernandez",  "Francia",       181, null, 21, "75016", null, 1, Posicion.DEFENSA,        85));

        PSG.getJugadores().add(new Jugador("Vitinha",   "Ferreira",   "Portugal",      182, null, 17, "75016", null, 1, Posicion.CENTROCAMPISTA, 85));
        PSG.getJugadores().add(new Jugador("Manuel",    "Ugarte",     "Uruguay",       183, null,  4, "75016", null, 1, Posicion.CENTROCAMPISTA, 84));
        PSG.getJugadores().add(new Jugador("Warren",    "Zaire-Emery","Francia",       184, null, 33, "75016", null, 1, Posicion.CENTROCAMPISTA, 84));

        PSG.getJugadores().add(new Jugador("Ousmane",   "Dembele",    "Francia",       185, null, 10, "75016", null, 1, Posicion.DELANTERO,      86));
        PSG.getJugadores().add(new Jugador("Goncalo",   "Ramos",      "Portugal",      186, null,  9, "75016", null, 1, Posicion.DELANTERO,      84));
        PSG.getJugadores().add(new Jugador("Desire",   "Doué",    "Francia",       187, null, 14, "75016", null, 1, Posicion.DELANTERO,      84));


        lista.add(PSG);

        Equipo Monaco = new Equipo(2, "AS Monaco", "Monaco", "Stade Louis II", 2);
        Monaco.setEntrenador(new Entrenador("Adi", "Hutter", "Austria", 2));

        Monaco.getJugadores().add(new Jugador("Philipp", "Kohn",       "Suiza",      188, null, 16, "98000", null, 2, Posicion.PORTERO,        83));

        Monaco.getJugadores().add(new Jugador("Vanderson","Oliveira",  "Brasil",     189, null,  2, "98000", null, 2, Posicion.DEFENSA,        83));
        Monaco.getJugadores().add(new Jugador("Guillermo","Maripan",   "Chile",      190, null,  3, "98000", null, 2, Posicion.DEFENSA,        84));
        Monaco.getJugadores().add(new Jugador("Thilo",    "Kehrer",    "Alemania",   191, null,  5, "98000", null, 2, Posicion.DEFENSA,        83));
        Monaco.getJugadores().add(new Jugador("Caio",     "Henrique",  "Brasil",     192, null, 12, "98000", null, 2, Posicion.DEFENSA,        84));

        Monaco.getJugadores().add(new Jugador("Youssouf", "Fofana",    "Francia",    193, null, 19, "98000", null, 2, Posicion.CENTROCAMPISTA, 85));
        Monaco.getJugadores().add(new Jugador("Denis",    "Zakaria",   "Suiza",      194, null,  6, "98000", null, 2, Posicion.CENTROCAMPISTA, 84));
        Monaco.getJugadores().add(new Jugador("Aleksandr","Golovin",   "Rusia",      195, null, 17, "98000", null, 2, Posicion.CENTROCAMPISTA, 84));

        Monaco.getJugadores().add(new Jugador("Takumi",   "Minamino",  "Japon",      196, null, 18, "98000", null, 2, Posicion.DELANTERO,      83));
        Monaco.getJugadores().add(new Jugador("Wissam",   "Ben Yedder","Francia",    197, null, 10, "98000", null, 2, Posicion.DELANTERO,      85));
        Monaco.getJugadores().add(new Jugador("Folarin",  "Balogun",   "EEUU",       198, null, 29, "98000", null, 2, Posicion.DELANTERO,      84));

        lista.add(Monaco);

        Equipo Marseille = new Equipo(3, "Olympique Marseille", "Marseille", "Orange Velodrome", 3);
        Marseille.setEntrenador(new Entrenador("Jean-Louis", "Gasset", "Francia", 3));

        Marseille.getJugadores().add(new Jugador("Pau",      "Lopez",      "España",    199, null, 16, "13008", null, 3, Posicion.PORTERO,        84));

        Marseille.getJugadores().add(new Jugador("Jonathan", "Clauss",     "Francia",   200, null,  7, "13008", null, 3, Posicion.DEFENSA,        84));
        Marseille.getJugadores().add(new Jugador("Chancel",  "Mbemba",     "RD Congo",  201, null, 99, "13008", null, 3, Posicion.DEFENSA,        84));
        Marseille.getJugadores().add(new Jugador("Leonardo", "Balerdi",    "Argentina", 202, null,  5, "13008", null, 3, Posicion.DEFENSA,        83));
        Marseille.getJugadores().add(new Jugador("Quentin",  "Merlin",     "Francia",   203, null,  3, "13008", null, 3, Posicion.DEFENSA,        83));

        Marseille.getJugadores().add(new Jugador("Jordan",   "Veretout",   "Francia",   204, null, 27, "13008", null, 3, Posicion.CENTROCAMPISTA, 84));
        Marseille.getJugadores().add(new Jugador("Geoffrey", "Kondogbia",  "R. Centroafricana",205,null,19,"13008",null,3,Posicion.CENTROCAMPISTA,84));
        Marseille.getJugadores().add(new Jugador("Amine",    "Harit",      "Marruecos", 206, null, 11, "13008", null, 3, Posicion.CENTROCAMPISTA, 83));

        Marseille.getJugadores().add(new Jugador("Ismaila",  "Sarr",       "Senegal",   207, null, 23, "13008", null, 3, Posicion.DELANTERO,      84));
        Marseille.getJugadores().add(new Jugador("Pierre",   "Aubameyang", "Gabon",     208, null, 10, "13008", null, 3, Posicion.DELANTERO,      85));
        Marseille.getJugadores().add(new Jugador("Iliman",   "Ndiaye",     "Senegal",   209, null, 29, "13008", null, 3, Posicion.DELANTERO,      83));

        lista.add(Marseille);

        Equipo Lille = new Equipo(4, "LOSC Lille", "Lille", "Stade Pierre-Mauroy", 4);
        Lille.setEntrenador(new Entrenador("Paulo", "Fonseca", "Portugal", 4));

        Lille.getJugadores().add(new Jugador("Lucas",   "Chevalier", "Francia", 210, null, 30, "59000", null, 4, Posicion.PORTERO,        83));

        Lille.getJugadores().add(new Jugador("Tiago",   "Santos",    "Portugal",211, null, 22, "59000", null, 4, Posicion.DEFENSA,        82));
        Lille.getJugadores().add(new Jugador("Leny",    "Yoro",      "Francia", 212, null, 15, "59000", null, 4, Posicion.DEFENSA,        84));
        Lille.getJugadores().add(new Jugador("Alexsandro","Ribeiro", "Brasil",  213, null,  4, "59000", null, 4, Posicion.DEFENSA,        83));
        Lille.getJugadores().add(new Jugador("Ismaily", "Goncalves", "Brasil",  214, null, 31, "59000", null, 4, Posicion.DEFENSA,        83));

        Lille.getJugadores().add(new Jugador("Benjamin","Andre",     "Francia", 215, null, 21, "59000", null, 4, Posicion.CENTROCAMPISTA, 84));
        Lille.getJugadores().add(new Jugador("Angel",   "Gomes",     "Inglaterra",216,null, 8,"59000",null,4,Posicion.CENTROCAMPISTA,83));
        Lille.getJugadores().add(new Jugador("Remy",    "Cabella",   "Francia", 217, null,10,"59000",null,4,Posicion.CENTROCAMPISTA,83));

        Lille.getJugadores().add(new Jugador("Edon",    "Zhegrova",  "Kosovo",  218, null,23,"59000",null,4,Posicion.DELANTERO,84));
        Lille.getJugadores().add(new Jugador("Jonathan","David",     "Canada",  219, null, 9,"59000",null,4,Posicion.DELANTERO,85));
        Lille.getJugadores().add(new Jugador("Ivan",    "Cavaleiro", "Portugal",220,null,17,"59000",null,4,Posicion.DELANTERO,82));

        lista.add(Lille);

        Equipo Lyon = new Equipo(5, "Olympique Lyonnais", "Lyon", "Groupama Stadium", 5);
        Lyon.setEntrenador(new Entrenador("Pierre", "Sage", "Francia", 5));

        Lyon.getJugadores().add(new Jugador("Anthony","Lopes","Portugal",221,null,1,"69150",null,5,Posicion.PORTERO,83));

        Lyon.getJugadores().add(new Jugador("Clinton","Mata","Angola",222,null,22,"69150",null,5,Posicion.DEFENSA,82));
        Lyon.getJugadores().add(new Jugador("Jake",   "OBrien","Irlanda",223,null,12,"69150",null,5,Posicion.DEFENSA,83));
        Lyon.getJugadores().add(new Jugador("Duje",   "Caleta-Car","Croacia",224,null,55,"69150",null,5,Posicion.DEFENSA,83));
        Lyon.getJugadores().add(new Jugador("Nicolas","Tagliafico","Argentina",225,null,3,"69150",null,5,Posicion.DEFENSA,84));

        Lyon.getJugadores().add(new Jugador("Maxence","Caqueret","Francia",226,null,6,"69150",null,5,Posicion.CENTROCAMPISTA,84));
        Lyon.getJugadores().add(new Jugador("Corentin","Tolisso","Francia",227,null,8,"69150",null,5,Posicion.CENTROCAMPISTA,84));
        Lyon.getJugadores().add(new Jugador("Rayan","Cherki","Francia",228,null,18,"69150",null,5,Posicion.CENTROCAMPISTA,85));

        Lyon.getJugadores().add(new Jugador("Ernest","Nuamah","Ghana",229,null,37,"69150",null,5,Posicion.DELANTERO,83));
        Lyon.getJugadores().add(new Jugador("Alexandre","Lacazette","Francia",230,null,10,"69150",null,5,Posicion.DELANTERO,85));
        Lyon.getJugadores().add(new Jugador("Malick","Fofana","Belgica",231,null,11,"69150",null,5,Posicion.DELANTERO,82));

        lista.add(Lyon);

        Equipo Nice = new Equipo(6, "OGC Nice", "Nice", "Allianz Riviera", 6);
        Nice.setEntrenador(new Entrenador("Francesco", "Farioli", "Italia", 6));

        Nice.getJugadores().add(new Jugador("Marcin",   "Bulka",     "Polonia", 232, null, 1,  "06000", null, 6, Posicion.PORTERO,        84));

        Nice.getJugadores().add(new Jugador("Jordan",   "Lotomba",   "Suiza",   233, null, 23, "06000", null, 6, Posicion.DEFENSA,        83));
        Nice.getJugadores().add(new Jugador("Jean-Clair","Todibo",   "Francia", 234, null, 6,  "06000", null, 6, Posicion.DEFENSA,        85));
        Nice.getJugadores().add(new Jugador("Dante",    "Bonfim",    "Brasil",  235, null, 4,  "06000", null, 6, Posicion.DEFENSA,        83));
        Nice.getJugadores().add(new Jugador("Melvin",   "Bard",      "Francia", 236, null, 26, "06000", null, 6, Posicion.DEFENSA,        83));

        Nice.getJugadores().add(new Jugador("Khephren", "Thuram",    "Francia", 237, null, 19, "06000", null, 6, Posicion.CENTROCAMPISTA, 85));
        Nice.getJugadores().add(new Jugador("Morgan",   "Sanson",    "Francia", 238, null, 11, "06000", null, 6, Posicion.CENTROCAMPISTA, 83));
        Nice.getJugadores().add(new Jugador("Hicham",   "Boudaoui",  "Argelia", 239, null, 28, "06000", null, 6, Posicion.CENTROCAMPISTA, 84));

        Nice.getJugadores().add(new Jugador("Jeremie",  "Boga",      "Costa Marfil",240,null,7,"06000",null,6,Posicion.DELANTERO,84));
        Nice.getJugadores().add(new Jugador("Terem",    "Moffi",     "Nigeria", 241, null, 9,  "06000", null, 6, Posicion.DELANTERO,      84));
        Nice.getJugadores().add(new Jugador("Gaetan",   "Laborde",   "Francia", 242, null,24,  "06000", null, 6, Posicion.DELANTERO,      84));

        lista.add(Nice);

        Equipo Lens = new Equipo(7, "RC Lens", "Lens", "Stade Bollaert-Delelis", 7);
        Lens.setEntrenador(new Entrenador("Franck", "Haise", "Francia", 7));

        Lens.getJugadores().add(new Jugador("Brice",     "Samba",        "Francia",     243, null, 30, "62300", null, 7, Posicion.PORTERO,        85));

        Lens.getJugadores().add(new Jugador("Jonathan",  "Gradit",       "Francia",     244, null, 24, "62300", null, 7, Posicion.DEFENSA,        84));
        Lens.getJugadores().add(new Jugador("Kevin",     "Danso",        "Austria",     245, null,  4, "62300", null, 7, Posicion.DEFENSA,        85));
        Lens.getJugadores().add(new Jugador("Facundo",   "Medina",       "Argentina",   246, null, 14, "62300", null, 7, Posicion.DEFENSA,        84));
        Lens.getJugadores().add(new Jugador("Deiver",    "Machado",      "Colombia",    247, null,  3, "62300", null, 7, Posicion.DEFENSA,        83));

        Lens.getJugadores().add(new Jugador("Salis",     "Abdul Samed",  "Ghana",       248, null,  6, "62300", null, 7, Posicion.CENTROCAMPISTA, 83));
        Lens.getJugadores().add(new Jugador("Neil",      "El Aynaoui",   "Marruecos",   249, null, 23, "62300", null, 7, Posicion.CENTROCAMPISTA, 83));
        Lens.getJugadores().add(new Jugador("Angelo",    "Fulgini",      "Francia",     250, null, 11, "62300", null, 7, Posicion.CENTROCAMPISTA, 83));

        Lens.getJugadores().add(new Jugador("Florian",   "Sotoca",       "Francia",     251, null,  7, "62300", null, 7, Posicion.DELANTERO,      84));
        Lens.getJugadores().add(new Jugador("Elye",      "Wahi",         "Francia",     252, null,  9, "62300", null, 7, Posicion.DELANTERO,      84));
        Lens.getJugadores().add(new Jugador("Adrien",    "Thomasson",    "Francia",     253, null, 28, "62300", null, 7, Posicion.DELANTERO,      83));

        lista.add(Lens);

        Equipo Rennes = new Equipo(8, "Stade Rennais", "Rennes", "Roazhon Park", 8);
        Rennes.setEntrenador(new Entrenador("Julien", "Stephan", "Francia", 8));

        Rennes.getJugadores().add(new Jugador("Steve",     "Mandanda",     "Francia", 254, null, 30, "35000", null, 8, Posicion.PORTERO,        83));

        Rennes.getJugadores().add(new Jugador("Guela",     "Doue",         "Francia", 255, null, 17, "35000", null, 8, Posicion.DEFENSA,        82));
        Rennes.getJugadores().add(new Jugador("Arthur",    "Theate",       "Belgica", 256, null,  5, "35000", null, 8, Posicion.DEFENSA,        84));
        Rennes.getJugadores().add(new Jugador("Christopher","Wooh",        "Francia", 257, null,  4, "35000", null, 8, Posicion.DEFENSA,        83));
        Rennes.getJugadores().add(new Jugador("Adrien",    "Truffert",     "Francia", 258, null,  3, "35000", null, 8, Posicion.DEFENSA,        84));

        Rennes.getJugadores().add(new Jugador("Benjamin",  "Bourigeaud",   "Francia", 259, null, 14, "35000", null, 8, Posicion.CENTROCAMPISTA, 85));
        Rennes.getJugadores().add(new Jugador("Enzo",      "Le Fee",       "Francia", 260, null, 28, "35000", null, 8, Posicion.CENTROCAMPISTA, 84));
        Rennes.getJugadores().add(new Jugador("Ludovic",   "Blas",         "Francia", 261, null, 10, "35000", null, 8, Posicion.CENTROCAMPISTA, 84));

        Rennes.getJugadores().add(new Jugador("Amine",     "Gouiri",       "Francia", 262, null,  9, "35000", null, 8, Posicion.DELANTERO,      84));
        Rennes.getJugadores().add(new Jugador("Arnaud",    "Kalimuendo",   "Francia", 263, null,  7, "35000", null, 8, Posicion.DELANTERO,      83));
        Rennes.getJugadores().add(new Jugador("Martin",    "Terrier",      "Francia", 264, null, 21, "35000", null, 8, Posicion.DELANTERO,      85));

        lista.add(Rennes);

        Equipo Strasbourg = new Equipo(9, "RC Strasbourg", "Strasbourg", "Stade de la Meinau", 9);
        Strasbourg.setEntrenador(new Entrenador("Patrick", "Vieira", "Francia", 9));

        Strasbourg.getJugadores().add(new Jugador("Matz",      "Sels",      "Belgica",        265, null,  1, "67000", null, 9, Posicion.PORTERO,        83));

        Strasbourg.getJugadores().add(new Jugador("Frederic",  "Guilbert",  "Francia",        266, null,  2, "67000", null, 9, Posicion.DEFENSA,        82));
        Strasbourg.getJugadores().add(new Jugador("Lucas",     "Perrin",    "Francia",        267, null,  5, "67000", null, 9, Posicion.DEFENSA,        82));
        Strasbourg.getJugadores().add(new Jugador("Gerzino",   "Nyamsi",    "Francia",        268, null, 22, "67000", null, 9, Posicion.DEFENSA,        83));
        Strasbourg.getJugadores().add(new Jugador("Thomas",    "Delaine",   "Francia",        269, null,  3, "67000", null, 9, Posicion.DEFENSA,        82));

        Strasbourg.getJugadores().add(new Jugador("Ibrahima",  "Sissoko",   "Mali",           270, null, 27, "67000", null, 9, Posicion.CENTROCAMPISTA, 83));
        Strasbourg.getJugadores().add(new Jugador("Habib",     "Diarra",    "Senegal",        271, null, 19, "67000", null, 9, Posicion.CENTROCAMPISTA, 83));
        Strasbourg.getJugadores().add(new Jugador("Dion",      "Sahi",      "Costa Marfil",   272, null, 10, "67000", null, 9, Posicion.CENTROCAMPISTA, 82));

        Strasbourg.getJugadores().add(new Jugador("Emanuel",   "Emegha",    "Paises Bajos",   273, null,  9, "67000", null, 9, Posicion.DELANTERO,      83));
        Strasbourg.getJugadores().add(new Jugador("Kevin",     "Gameiro",   "Francia",        274, null, 11, "67000", null, 9, Posicion.DELANTERO,      83));
        Strasbourg.getJugadores().add(new Jugador("Dilane",    "Bakva",     "Francia",        275, null, 26, "67000", null, 9, Posicion.DELANTERO,      82));

        lista.add(Strasbourg);

        Equipo Toulouse = new Equipo(10, "Toulouse FC", "Toulouse", "Stadium de Toulouse", 10);
        Toulouse.setEntrenador(new Entrenador("Carles", "Martinez", "España", 10));

        Toulouse.getJugadores().add(new Jugador("Guillaume", "Restes",     "Francia",      276, null, 50, "31000", null, 10, Posicion.PORTERO,        83));

        Toulouse.getJugadores().add(new Jugador("Mikkel",    "Desler",     "Dinamarca",    277, null,  3, "31000", null, 10, Posicion.DEFENSA,        82));
        Toulouse.getJugadores().add(new Jugador("Rasmus",    "Nicolaisen", "Dinamarca",    278, null,  2, "31000", null, 10, Posicion.DEFENSA,        83));
        Toulouse.getJugadores().add(new Jugador("Logan",     "Costa",      "Cabo Verde",   279, null,  4, "31000", null, 10, Posicion.DEFENSA,        83));
        Toulouse.getJugadores().add(new Jugador("Gabriel",   "Suazo",      "Chile",        280, null, 17, "31000", null, 10, Posicion.DEFENSA,        83));

        Toulouse.getJugadores().add(new Jugador("Vincent",   "Sierro",     "Suiza",        281, null,  8, "31000", null, 10, Posicion.CENTROCAMPISTA, 83));
        Toulouse.getJugadores().add(new Jugador("Cristian",  "Casseres",   "Venezuela",    282, null, 23, "31000", null, 10, Posicion.CENTROCAMPISTA, 83));
        Toulouse.getJugadores().add(new Jugador("Denis",     "Genreau",    "Australia",    283, null,  5, "31000", null, 10, Posicion.CENTROCAMPISTA, 82));

        Toulouse.getJugadores().add(new Jugador("Zakaria",   "Aboukhlal",  "Marruecos",    284, null,  7, "31000", null, 10, Posicion.DELANTERO,      84));
        Toulouse.getJugadores().add(new Jugador("Thijs",     "Dallinga",   "Paises Bajos", 285, null,  9, "31000", null, 10, Posicion.DELANTERO,      84));
        Toulouse.getJugadores().add(new Jugador("Frank",     "Magri",      "Camerun",      286, null, 19, "31000", null, 10, Posicion.DELANTERO,      82));

        lista.add(Toulouse);

        Equipo Montpellier = new Equipo(11, "Montpellier HSC", "Montpellier", "Stade de la Mosson", 11);
        Montpellier.setEntrenador(new Entrenador("Michel", "Der Zakarian", "Francia", 11));

        Montpellier.getJugadores().add(new Jugador("Benjamin", "Lecomte",   "Francia",    287, null, 40, "34000", null, 11, Posicion.PORTERO,        83));

        Montpellier.getJugadores().add(new Jugador("Falaye",   "Sacko",     "Mali",       288, null, 77, "34000", null, 11, Posicion.DEFENSA,        82));
        Montpellier.getJugadores().add(new Jugador("Boubakar", "Kouyate",   "Mali",       289, null,  4, "34000", null, 11, Posicion.DEFENSA,        83));
        Montpellier.getJugadores().add(new Jugador("Christopher","Jullien", "Francia",    290, null,  6, "34000", null, 11, Posicion.DEFENSA,        82));
        Montpellier.getJugadores().add(new Jugador("Issiaga",  "Sylla",     "Guinea",     291, null,  3, "34000", null, 11, Posicion.DEFENSA,        82));

        Montpellier.getJugadores().add(new Jugador("Jordan",   "Ferri",     "Francia",    292, null, 12, "34000", null, 11, Posicion.CENTROCAMPISTA, 83));
        Montpellier.getJugadores().add(new Jugador("Joris",    "Chotard",   "Francia",    293, null, 13, "34000", null, 11, Posicion.CENTROCAMPISTA, 82));
        Montpellier.getJugadores().add(new Jugador("Téji",     "Savanier",  "Francia",    294, null, 11, "34000", null, 11, Posicion.CENTROCAMPISTA, 84));

        Montpellier.getJugadores().add(new Jugador("Akor",     "Adams",     "Nigeria",    295, null,  8, "34000", null, 11, Posicion.DELANTERO,      83));
        Montpellier.getJugadores().add(new Jugador("Arnaud",   "Nordin",    "Francia",    296, null,  7, "34000", null, 11, Posicion.DELANTERO,      82));
        Montpellier.getJugadores().add(new Jugador("Mousa",    "Tamari",    "Jordania",   297, null, 10, "34000", null, 11, Posicion.DELANTERO,      83));

        lista.add(Montpellier);

        Equipo Reims = new Equipo(12, "Stade de Reims", "Reims", "Stade Auguste-Delaune", 12);
        Reims.setEntrenador(new Entrenador("Will", "Still", "Belgica", 12));

        Reims.getJugadores().add(new Jugador("Yehvann", "Diouf",       "Senegal",     298, null, 94, "51100", null, 12, Posicion.PORTERO,        83));

        Reims.getJugadores().add(new Jugador("Thomas",  "Foket",       "Belgica",     299, null, 32, "51100", null, 12, Posicion.DEFENSA,        82));
        Reims.getJugadores().add(new Jugador("Yunis",   "Abdelhamid",  "Marruecos",   300, null,  5, "51100", null, 12, Posicion.DEFENSA,        83));
        Reims.getJugadores().add(new Jugador("Emmanuel","Agbadou",     "Costa Marfil",301, null, 24, "51100", null, 12, Posicion.DEFENSA,        83));
        Reims.getJugadores().add(new Jugador("Thibault","De Smet",     "Belgica",     302, null, 25, "51100", null, 12, Posicion.DEFENSA,        82));

        Reims.getJugadores().add(new Jugador("Marshall","Munetsi",     "Zimbabue",    303, null, 15, "51100", null, 12, Posicion.CENTROCAMPISTA, 83));
        Reims.getJugadores().add(new Jugador("Azor",    "Matusiwa",    "Paises Bajos",304, null,  8, "51100", null, 12, Posicion.CENTROCAMPISTA, 83));
        Reims.getJugadores().add(new Jugador("Teddy",   "Teuma",       "Malta",       305, null, 10, "51100", null, 12, Posicion.CENTROCAMPISTA, 83));

        Reims.getJugadores().add(new Jugador("Junya",   "Ito",         "Japon",       306, null,  7, "51100", null, 12, Posicion.DELANTERO,      84));
        Reims.getJugadores().add(new Jugador("Oumar",   "Diakite",     "Costa Marfil",307, null, 22, "51100", null, 12, Posicion.DELANTERO,      82));
        Reims.getJugadores().add(new Jugador("Keito",   "Nakamura",    "Japon",       308, null, 17, "51100", null, 12, Posicion.DELANTERO,      83));

        lista.add(Reims);

        Equipo Metz = new Equipo(13, "FC Metz", "Metz", "Stade Saint-Symphorien", 13);
        Metz.setEntrenador(new Entrenador("Laszlo", "Boloni", "Rumania", 13));

        Metz.getJugadores().add(new Jugador("Alexandre","Oukidja",  "Argelia",     309, null, 16, "57000", null, 13, Posicion.PORTERO,        82));

        Metz.getJugadores().add(new Jugador("Maxime",   "Colin",    "Francia",     310, null,  2, "57000", null, 13, Posicion.DEFENSA,        82));
        Metz.getJugadores().add(new Jugador("Ismael",   "Traore",   "Costa Marfil",311, null,  8, "57000", null, 13, Posicion.DEFENSA,        82));
        Metz.getJugadores().add(new Jugador("Fali",     "Cande",    "Guinea-Bissau",312, null,  5, "57000", null, 13, Posicion.DEFENSA,        82));
        Metz.getJugadores().add(new Jugador("Matthieu", "Udol",     "Francia",     313, null,  3, "57000", null, 13, Posicion.DEFENSA,        83));

        Metz.getJugadores().add(new Jugador("Lamine",   "Camara",   "Senegal",     314, null, 18, "57000", null, 13, Posicion.CENTROCAMPISTA, 83));
        Metz.getJugadores().add(new Jugador("Danley",   "Jean Jacques","Haiti",    315, null, 27, "57000", null, 13, Posicion.CENTROCAMPISTA, 82));
        Metz.getJugadores().add(new Jugador("Kevin",    "N'Doram",  "Francia",     316, null,  6, "57000", null, 13, Posicion.CENTROCAMPISTA, 82));

        Metz.getJugadores().add(new Jugador("Ablie",    "Jallow",   "Gambia",      317, null, 36, "57000", null, 13, Posicion.DELANTERO,      82));
        Metz.getJugadores().add(new Jugador("Georges",  "Mikautadze","Georgia",    318, null, 10, "57000", null, 13, Posicion.DELANTERO,      84));
        Metz.getJugadores().add(new Jugador("Simon",    "Elisor",   "Francia",     319, null, 11, "57000", null, 13, Posicion.DELANTERO,      82));

        lista.add(Metz);

        Equipo Lorient = new Equipo(14, "FC Lorient", "Lorient", "Stade du Moustoir", 14);
        Lorient.setEntrenador(new Entrenador("Regis", "Le Bris", "Francia", 14));

        Lorient.getJugadores().add(new Jugador("Yvon",    "Mvogo",     "Suiza",        320, null, 38, "56100", null, 14, Posicion.PORTERO,        82));

        Lorient.getJugadores().add(new Jugador("Gedeon",  "Kalulu",    "Francia",      321, null, 24, "56100", null, 14, Posicion.DEFENSA,        82));
        Lorient.getJugadores().add(new Jugador("Montassar","Talbi",    "Tunez",        322, null,  3, "56100", null, 14, Posicion.DEFENSA,        83));
        Lorient.getJugadores().add(new Jugador("Julien",  "Laporte",   "Francia",      323, null, 15, "56100", null, 14, Posicion.DEFENSA,        82));
        Lorient.getJugadores().add(new Jugador("Vincent", "Le Goff",   "Francia",      324, null, 25, "56100", null, 14, Posicion.DEFENSA,        82));

        Lorient.getJugadores().add(new Jugador("Laurent", "Abergel",   "Francia",      325, null, 19, "56100", null, 14, Posicion.CENTROCAMPISTA, 83));
        Lorient.getJugadores().add(new Jugador("Imran",   "Louza",     "Marruecos",    326, null,  6, "56100", null, 14, Posicion.CENTROCAMPISTA, 83));
        Lorient.getJugadores().add(new Jugador("Romain",  "Faivre",    "Francia",      327, null, 14, "56100", null, 14, Posicion.CENTROCAMPISTA, 83));

        Lorient.getJugadores().add(new Jugador("Eli",     "Junior Kroupi","Francia",   328, null, 22, "56100", null, 14, Posicion.DELANTERO,      82));
        Lorient.getJugadores().add(new Jugador("Bamba",   "Dieng",     "Senegal",      329, null, 11, "56100", null, 14, Posicion.DELANTERO,      83));
        Lorient.getJugadores().add(new Jugador("Mohamed", "Bamba",     "Costa Marfil", 330, null,  9, "56100", null, 14, Posicion.DELANTERO,      82));

        lista.add(Lorient);

        Equipo Clermont = new Equipo(15, "Clermont Foot", "Clermont-Ferrand", "Stade Gabriel Montpied", 15);
        Clermont.setEntrenador(new Entrenador("Pascal", "Gastien", "Francia", 15));

        Clermont.getJugadores().add(new Jugador("Mory",     "Diaw",      "Senegal",      331, null, 99, "63000", null, 15, Posicion.PORTERO,        82));

        Clermont.getJugadores().add(new Jugador("Alidu",    "Seidu",     "Ghana",        332, null, 36, "63000", null, 15, Posicion.DEFENSA,        82));
        Clermont.getJugadores().add(new Jugador("Maximiliano","Caufriez","Belgica",      333, null,  5, "63000", null, 15, Posicion.DEFENSA,        82));
        Clermont.getJugadores().add(new Jugador("Andy",     "Pelmar",    "Francia",      334, null, 17, "63000", null, 15, Posicion.DEFENSA,        82));
        Clermont.getJugadores().add(new Jugador("Neto",     "Borges",    "Brasil",       335, null,  3, "63000", null, 15, Posicion.DEFENSA,        82));

        Clermont.getJugadores().add(new Jugador("Johan",    "Gastien",   "Francia",      336, null, 25, "63000", null, 15, Posicion.CENTROCAMPISTA, 82));
        Clermont.getJugadores().add(new Jugador("Habib",    "Keita",     "Mali",         337, null,  6, "63000", null, 15, Posicion.CENTROCAMPISTA, 82));
        Clermont.getJugadores().add(new Jugador("Muhammed", "Cham",      "Austria",      338, null, 10, "63000", null, 15, Posicion.CENTROCAMPISTA, 83));

        Clermont.getJugadores().add(new Jugador("Shamar",   "Nicholson", "Jamaica",      339, null,  9, "63000", null, 15, Posicion.DELANTERO,      83));
        Clermont.getJugadores().add(new Jugador("Jim",      "Allevinah", "Gabon",        340, null, 11, "63000", null, 15, Posicion.DELANTERO,      82));
        Clermont.getJugadores().add(new Jugador("Grejohn",  "Kyei",      "Francia",      341, null, 95, "63000", null, 15, Posicion.DELANTERO,      82));

        lista.add(Clermont);

        Equipo LeHavre = new Equipo(16, "Le Havre AC", "Le Havre", "Stade Oceane", 16);
        LeHavre.setEntrenador(new Entrenador("Luka", "Elsner", "Eslovenia", 16));

        LeHavre.getJugadores().add(new Jugador("Arthur",   "Desmas",     "Francia",      342, null, 30, "76600", null, 16, Posicion.PORTERO,        82));

        LeHavre.getJugadores().add(new Jugador("Loic",     "Nego",       "Hungria",      343, null,  7, "76600", null, 16, Posicion.DEFENSA,        82));
        LeHavre.getJugadores().add(new Jugador("Arouna",   "Sangante",   "Senegal",      344, null, 93, "76600", null, 16, Posicion.DEFENSA,        83));
        LeHavre.getJugadores().add(new Jugador("Gautier",  "Lloris",     "Francia",      345, null,  4, "76600", null, 16, Posicion.DEFENSA,        82));
        LeHavre.getJugadores().add(new Jugador("Christopher","Operi",    "Costa Marfil", 346, null, 27, "76600", null, 16, Posicion.DEFENSA,        82));

        LeHavre.getJugadores().add(new Jugador("Abdoulaye","Toure",      "Guinea",       347, null, 94, "76600", null, 16, Posicion.CENTROCAMPISTA, 83));
        LeHavre.getJugadores().add(new Jugador("Daler",    "Kuzyaev",    "Rusia",        348, null, 14, "76600", null, 16, Posicion.CENTROCAMPISTA, 83));
        LeHavre.getJugadores().add(new Jugador("Yassine",  "Kechtout",   "Francia",      349, null,  8, "76600", null, 16, Posicion.CENTROCAMPISTA, 82));

        LeHavre.getJugadores().add(new Jugador("Nabil",    "Alioui",     "Marruecos",    350, null, 10, "76600", null, 16, Posicion.DELANTERO,      83));
        LeHavre.getJugadores().add(new Jugador("Andre",    "Ayew",       "Ghana",        351, null, 28, "76600", null, 16, Posicion.DELANTERO,      83));
        LeHavre.getJugadores().add(new Jugador("Josue",    "Casimir",    "Francia",      352, null, 23, "76600", null, 16, Posicion.DELANTERO,      82));

        lista.add(LeHavre);

        return lista;
    }


    private void menuPartidos() {

        System.out.println("PARTIDOS");

        int nEquipos = ligaActual.getEquipos().size();
        int maxJornadas = 2 * (nEquipos - 1);

        int numeroJornada = 1;

        while (numeroJornada <= maxJornadas) {

            if (ligaActual.getJornadas().size() < numeroJornada) {
                generarJornadaAleatoria(numeroJornada);
            }

            Jornada jornada = ligaActual.getJornadas().get(numeroJornada - 1);

            System.out.println(" Jornada " + jornada.getNumero() );

            for (Partido partido : jornada.getPartidos()) {
                if (!partido.isJugado()) {
                    partido.simularPartido();
                }
                System.out.println(partido);

                if (partido.getMvp() != null) {
                    System.out.println("MVP : " + partido.getMvp().getNombre() + " " + partido.getMvp().getApellido());
                }
            }

            if (numeroJornada == maxJornadas) break;

            System.out.println(" Pulsa ENTER para ver la siguiente jornada (o escribe 0 para volver al menú)");
            String entrada = sc.nextLine();
            if (entrada.trim().equals("0")) return;

            numeroJornada++;
        }

        System.out.println(" Temporada terminada. Pulsa ENTER para volver al menú.");
        sc.nextLine();
    }

    private void menuPremios() {
        System.out.println(" Premios de la temporada ");

        Equipo campeon = ligaActual.getEquipos().get(0);

        for (Equipo e : ligaActual.getEquipos()) {
            if (e.getPuntos() > campeon.getPuntos()) {
                campeon = e;
            }
        }

        System.out.println(" Campeon de liga: " + campeon.getNombre());

        Jugador pichichi = null;
        int maximosGoles = -1;

        for (Equipo e : ligaActual.getEquipos()) {
            for (Jugador j : e.getJugadores()) {


                if (j.getPosicion() == Posicion.PORTERO){
                    continue;
                }
                if (j.getGoles() > maximosGoles){
                    maximosGoles = j.getGoles();
                    pichichi = j;
                }
            }
        }

        if (pichichi != null) {
            System.out.println(" Pichichi de la liga : " + pichichi.getNombre() + " " + pichichi.getApellido() + " con un total de " + maximosGoles + " goles");
        }
        pausar();
    }


    private void generarJornadaAleatoria(int numeroJornada) {

        List<Equipo> equipos = new ArrayList<>(ligaActual.getEquipos());
        Collections.shuffle(equipos);

        Jornada jornada = new Jornada(ligaActual.getIdLiga(), numeroJornada);

        for (int i = 0; i < equipos.size(); i += 2) {

            Equipo local = equipos.get(i);
            Equipo visitante = equipos.get(i + 1);

            Partido partido = new Partido(LocalDate.now(), local, visitante, null, ligaActual.getIdLiga(), numeroJornada);
            jornada.addPartido(partido);
        }

        ligaActual.addJornada(jornada);
    }


    private void menuClasificacion() {
        List<Equipo> tabla = new ArrayList<>(ligaActual.getEquipos());

        Collections.sort(tabla, new Comparator<Equipo>() {
            @Override
            public int compare(Equipo e1, Equipo e2) {
                return e2.getPuntos() - e1.getPuntos();
            }
        });

        System.out.println(" CLASIFICACIÓN " + ligaActual.getNombre());

        for (int i = 0; i < tabla.size(); i++) {
            Equipo e = tabla.get(i);
            System.out.println((i + 1) + e.getNombre() + " - " + e.getPuntos() + " puntos ");
        }
        pausar();
    }

    private void asignarPatrocinadores (Liga liga) {
            String[] marcasTop = {"Emirates", "Nike", "Adidas", "Qatar Airways", "Red Bull", "Etihad", "Spotify", "Puma"};
            String[] marcasMedia = {"Coca-Cola", "Samsung", "Pepsi", "PlayStation", "Heineken", "Visa", "Amazon", "Hyundai"};
            String[] marcasBaja = {"LocalGym", "Taller Romero", "Panadería San Juan", "Seguros López", "Construcciones Pérez", "Clínica Dental", "Restaurante El Rincón", "Autoescuela Centro"};

            int idPatrocinador = 1;

            for (Equipo e : liga.getEquipos()) {

                int tmp = e.getNivel();

                String marca;
                double dinero;

                if (tmp <= 2) {
                    marca = marcasTop[(idPatrocinador - 1) % marcasTop.length];
                    dinero = 12_000_000 - (tmp - 1) * 1_500_000;
                } else if (tmp <= 6) {
                    marca = marcasMedia[(idPatrocinador - 1) % marcasMedia.length];
                    dinero = 6_000_000 - (tmp - 3) * 750_000;
                } else {
                    marca = marcasBaja[(idPatrocinador - 1) % marcasBaja.length];
                    dinero = 1_500_000 - (tmp - 7) * 100_000;
                    if (dinero < 300_000) dinero = 300_000;
                }

                e.setPatrocinador(new Patrocinador (idPatrocinador, marca, dinero));
                idPatrocinador++;
            }
        }

    }
