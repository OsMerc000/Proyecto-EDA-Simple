package Tests.KDTree;

import KDTree.KDTree;
import KDTree.Punto;
import Tests.Common;

//Testear múltiples querys para unos datos invariantes.
public class Test2 {
    public Test2() {
        Common common = new Common();
        for (int i = Common.LOAD_LOWER_BOUND; i <= Common.LOAD_UPPER_BOUND; i++) {
            int n = (int) Math.pow(10, i);
            System.out.println("\nN: " + n);
            double sumBuildTime = 0;
            double sumQueryTotalTime = 0;
            for (int j = 0; j < Common.NUMBER_OF_RUNS; j++) {
                // System.out.println();
                // System.out.println("Run number: " + j);
                Punto[] puntos = common.generarPuntos(n);
                // System.out.println("Puntos generados.");

                long buildStartTime = System.nanoTime();
                KDTree tree = new KDTree(puntos);
                long buildEndTime = System.nanoTime();
                long buildTime = buildEndTime - buildStartTime;
                // System.out.println("BuildTime: " + Common.nanoToMili(buildTime) + "ms");

                long queryTotalTime = 0;
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
                    queryTotalTime += queryTime;
                }
                // System.out.println("TotalQueryTime: " + Common.nanoToMili(totalQueryTime) + "ms");
                
                // System.out.println("TotalTIme: " + Common.nanoToMili(buildTime + totalQueryTime) + "ms");
                sumBuildTime += Common.nanoToMili(buildTime);
                sumQueryTotalTime += Common.nanoToMili(queryTotalTime);
            }
            double promBuildTime = sumBuildTime / Common.NUMBER_OF_RUNS;
            double promQueryTotalTime = sumQueryTotalTime / Common.NUMBER_OF_RUNS;
            String fmt;
            fmt = String.format("BuildTime promedio: %.4fms", promBuildTime);
            System.out.println(fmt);
            fmt = String.format("QueryTime promedio: %.4fms", promQueryTotalTime);
            System.out.println(fmt);
            fmt = String.format("TotalTime promedio: %.4fms", (promBuildTime + promQueryTotalTime));
            System.out.println(fmt);
            System.out.println();
        }
    }
}
