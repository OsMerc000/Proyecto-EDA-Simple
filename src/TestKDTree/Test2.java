package TestKDTree;

import KDTree.KDTree;
import KDTree.Punto;

//Testear múltiples querys para unos datos invariantes.
public class Test2 {
    public Test2() {
        Common common = new Common(5);
        for (int i = 3; i <= 5; i++) {
            int n = (int) Math.pow(10, i);
            System.out.println("N: " + n);
            for (int j = 1; j <= 5; j++) {
                System.out.println();
                System.out.println("Run number: " + j);
                Punto[] puntos = common.generarPuntos(n);
                System.out.println("Puntos generados.");

                long buildStartTime = System.nanoTime();
                KDTree tree = new KDTree(puntos);
                long buildEndTime = System.nanoTime();
                long buildTime = buildEndTime - buildStartTime;
                System.out.println("BuildTime: " + Common.nanoToMili(buildTime) + "ms");

                long totalQueryTime = 0;
                System.out.println("Points: ");
                for (int k = 0; k < 5; k++){
                    Punto p = common.generarPunto();
                    long queryStartTime = System.nanoTime();
                    Punto closestToP = tree.getNearestPoint(p);
                    long queryEndTime = System.nanoTime();
                    long queryTime = queryEndTime - queryStartTime;
                    System.out.println("[" + k + "]: ");
                    System.out.println("Closest point to " + p + ": " + closestToP);
                    System.out.println("QueryTime: " + Common.nanoToMili(queryTime) + "ms");
                    totalQueryTime += queryTime;
                }
                System.out.println("TotalQueryTime: " + Common.nanoToMili(totalQueryTime) + "ms");
                
                System.out.println("TotalTIme: " + Common.nanoToMili(buildTime + totalQueryTime) + "ms");
            }
            System.out.println();
        }
    }
}
