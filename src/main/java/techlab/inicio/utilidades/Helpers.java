package techlab.inicio.utilidades;

import java.util.Scanner;

public class Helpers {
    public static int leerEntero(Scanner sc, String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine();
            try {
                numero = Integer.parseInt(entrada);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
        return numero;
    }

    public static double leerDouble(Scanner sc, String mensaje) {
        double numero;
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine();
            try {
                numero = Double.parseDouble(entrada);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número decimal válido.");
            }
        }
        return numero;
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
}
