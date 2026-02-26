package proyectoLiga.principal;

import java.util.Scanner;

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
                case 1: System.out.println("Equipos (aun no esta hecho)");
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
        private static int leerOpcion(Scanner sc, int minimo, int maximo){
        int opcion;

        while (true) {
            System.out.println("Elige una opción: ");

            while (!sc.hasNextInt()) {
                System.out.println("Error , escribe un número valido");
                sc.nextLine();
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
    }


