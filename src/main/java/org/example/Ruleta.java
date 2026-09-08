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


    static void main(String[] args) {
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
        System.out.println("\n  CASINO BLACK CAT - RULETA ");
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
        in.nextLine();

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
        char tipo = ' ';
        boolean valido = false;

        while (!valido) {
            System.out.print("Seleccione tipo de apuesta (R: Rojo, N: Negro, P: Par, I: Impar): ");
            String entrada = in.nextLine().trim().toUpperCase();

            if (entrada.length() == 1) {
                tipo = entrada.charAt(0);
                if (tipo == 'R' || tipo == 'N' || tipo == 'P' || tipo == 'I') {
                    valido = true;
                } else {
                    System.out.println("Opción inválida. Ingrese R, N, P o I.");
                }
            } else {
                System.out.println("Entrada inválida. Intente de nuevo.");
            }
        }
        return tipo;
    }

    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) {
            return false;
        }
        return switch (tipo) {
            case 'R' -> esRojo(numero);
            case 'N' -> !esRojo(numero);
            case 'P' -> numero % 2 == 0;
            case 'I' -> numero % 2 != 0;
            default -> false;
        };
    }
    public static boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) {
                return true;
            }
        }
        return false;
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        } else {
            System.out.println("¡Atención! El historial de rondas ha alcanzado su límite máximo.");
        }
    }
    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        System.out.println("Número obtenido: " + numero);
        System.out.println(acierto ? "¡Ganaste $" + monto + "!" : "¡Perdiste $" + monto + "!");
    }

    public static void mostrarEstadisticas() {
        if (historialSize == 0) {
            System.out.println("No hay rondas registradas.");
            return;
        }
        int totalApostado = 0, aciertos = 0, gananciaNeta = 0;
        for (int i = 0; i < historialSize; i++) {
            totalApostado += historialApuestas[i];
            if (historialAciertos[i]) {
                aciertos++;
                gananciaNeta += historialApuestas[i];
            } else {
                gananciaNeta -= historialApuestas[i];
            }
        }
        double porcentaje = (double) aciertos / historialSize * 100;
        System.out.println("Rondas jugadas: " + historialSize);
        System.out.println("Monto total apostado: $" + totalApostado);
        System.out.println("Total aciertos: " + aciertos);
        System.out.printf("Porcentaje aciertos: %.2f%%\n", porcentaje);
        System.out.println("Ganancia/Pérdida neta: $" + gananciaNeta);
    }
}
