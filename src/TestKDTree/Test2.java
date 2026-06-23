package TestKDTree;

import KDTree.KDTree;
import KDTree.Punto;

//Testear múltiples querys para unos datos invariantes.
public class Test2 {
    public Test2() {
        final int NUMBER_OF_RUNS = 5;
        Common common = new Common(5);
        for (int i = 2; i <= 6; i++) {
            int n = (int) Math.pow(10, i);
            System.out.println("\nN: " + n);
            double sumBuildTime = 0;
            double sumTotalQueryTime = 0;
            for (int j = 1; j <= NUMBER_OF_RUNS; j++) {
                // System.out.println();
                // System.out.println("Run number: " + j);
                Punto[] puntos = common.generarPuntos(n);
                // System.out.println("Puntos generados.");

                long buildStartTime = System.nanoTime();
                KDTree tree = new KDTree(puntos);
                long buildEndTime = System.nanoTime();
                long buildTime = buildEndTime - buildStartTime;
                // System.out.println("BuildTime: " + Common.nanoToMili(buildTime) + "ms");

                long totalQueryTime = 0;
                // System.out.println("Points: ");
                for (int k = 0; k < 5; k++){
                    Punto p = common.generarPunto();
                    long queryStartTime = System.nanoTime();
                    @SuppressWarnings("unused")
                    Punto closestToP = tree.getNearestPoint(p);
                    long queryEndTime = System.nanoTime();
                    long queryTime = queryEndTime - queryStartTime;
                    // System.out.println("[" + k + "]: ");
                    // System.out.println("Closest point to " + p + ": " + closestToP);
                    // System.out.println("QueryTime: " + Common.nanoToMili(queryTime) + "ms");
                    totalQueryTime += queryTime;
                }
                // System.out.println("TotalQueryTime: " + Common.nanoToMili(totalQueryTime) + "ms");
                
                // System.out.println("TotalTIme: " + Common.nanoToMili(buildTime + totalQueryTime) + "ms");
                sumBuildTime += Common.nanoToMili(buildTime);
                sumTotalQueryTime += Common.nanoToMili(totalQueryTime);
            }
            double promBuildTime = sumBuildTime / NUMBER_OF_RUNS;
            double promTotalQueryTime = sumTotalQueryTime / NUMBER_OF_RUNS;
            String fmt;
            fmt = String.format("BuildTime promedio: %.4fms", promBuildTime);
            System.out.println(fmt);
            fmt = String.format("QueryTime promedio: %.4fms", promTotalQueryTime);
            System.out.println(fmt);
            fmt = String.format("TotalTime promedio: %.4fms", (promBuildTime + promTotalQueryTime));
            System.out.println(fmt);
            System.out.println();
        }
    }
}
