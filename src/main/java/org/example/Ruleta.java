package org.example;
import java.util.Random;
import java.util.Scanner;
public class Ruleta {

    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };



    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);

        } while (opcion != 3);
    }

    public static void mostrarMenu() {
        System.out.println("\n--- CASINO BLACK CAT - RULETA ---");
        System.out.println("1. Iniciar ronda");
        System.out.println("2. Ver estadísticas");
        System.out.println("3. Salir");
        System.out.print("Ingrese una opción: ");

    }

    public static int leerOpcion(Scanner in) {

        while (!in.hasNextInt()) {
            System.out.println("Debe ingresar un numero");
            in.next();
            System.out.println("Seleccione la opcion");
        }
        int opcion = in.nextInt();

        if (opcion < 1 || opcion > 3) {
            System.out.println("opcion no valida");
            return 0;
        }
        return opcion;
    }
    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {

            case 1:
                iniciarRonda(in);
                break;
            case 2:
                mostrarEstadisticas();
                break;
            case 3:
                System.out.println("saliendo del programa");
                break;
            default:
                System.out.println("Seleccione una opcion valida");

        }
    }

    public static void iniciarRonda(Scanner in) {
        char tipo = leerTipoApuesta(in);
        System.out.println("ingrese el monto de apuesta: ");
        int monto =in.nextInt();
        in.nextLine();

        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);
        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);


    }

    public static char leerTipoApuesta(Scanner in) {
        // TODO: Leer y validar el tipo de apuesta.
        return ' ';
    }

    public static int girarRuleta() {
        System.out.println("Tipo de apuesta ( R: ROJO, N: Negro, P: Par, I:Impar): ");
        return 0;}

    public static boolean evaluarResultado(int numero, char tipo) {
        // TODO: Evaluar el resultado según el tipo de apuesta.
        return false;
    }

    public static boolean esRojo(int n) {
        // TODO: Buscar el número en el arreglo numerosRojos.
        return false;
    }


    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        // TODO: Guardar los datos sin superar MAX_HISTORIAL.
    }

    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        // TODO: Mostrar los datos y el resultado de la ronda.
    }

    public static void mostrarEstadisticas() {
        // TODO: Calcular y mostrar las estadísticas acumuladas.
    }
}