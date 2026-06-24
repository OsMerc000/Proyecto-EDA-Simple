package Tests;

import java.util.Random;
import KDTree.Punto;

public class Common {
    private static Random rd = new Random();
    private double bound;
    //Número de interaciones para cada carga
    public static final int NUMBER_OF_RUNS = 5;
    //Tamaño mínimo de carga (Ej: 10^2)
    public static final int LOAD_LOWER_BOUND = 2;
    //Tamaño mínimo de carga (Ej: 10^6)
    public static final int LOAD_UPPER_BOUND = 6;
    //Variable única para el test 2
    public static final int NUMBER_OF_QUERYS = 100;
    //Variable única para el test 3
    public static final double RATIO_OF_MODIFICATIONS = 0.1;

    public Common() {
        this.bound = 50;
    }
    public Common(double bound) {
        this.bound = bound;
    }

    public void setBound(double bound) {
        this.bound = bound;
    }

    public Punto[] generarPuntos(int n) {
        if (n < 0) {
            new RuntimeException("Ingresado una cantidad no válida.");
        }
        Punto[] arr = new Punto[n];
        Random rd = new Random();
        for (int i = 0; i < n; i++) {
            double x = rd.nextDouble(bound);
            double y = rd.nextDouble(bound);
            Punto punto = new Punto(x, y);
            arr[i] = punto;
        }
        return arr;
    }

    public Punto generarPunto() {
        double x = rd.nextDouble(bound);
        double y = rd.nextDouble(bound);
        return new Punto(x, y);
    }

    public static void mostrarPuntos(Punto[] puntos) {
        for (int i = 0; i < puntos.length; i++) {
            System.out.println("Punto[" + i + "]: " + puntos[i]);
        }
    }

    public static double nanoToMili(long nanoTime) {
        return (double) (nanoTime) / 1000000.0;
    }
}
