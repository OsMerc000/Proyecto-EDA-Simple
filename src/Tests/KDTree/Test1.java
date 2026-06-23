package Tests.KDTree;

import KDTree.KDTree;
import KDTree.Punto;
import Tests.Common;

//Testear un solo query para unos datos invariantes.
public class Test1 {
    public Test1() {
        Common common = new Common();
        for (int i = Common.LOAD_LOWER_BOUND; i <= Common.LOAD_UPPER_BOUND; i++) {
            int n = (int) Math.pow(10, i);
            System.out.println("\nN: " + n);
            double sumBuildTime = 0;
            double sumQueryTime = 0;
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

                Punto p = common.generarPunto();
                long queryStartTime = System.nanoTime();
                @SuppressWarnings("unused")
                Punto closestToP = tree.getNearestPoint(p);
                long queryEndTime = System.nanoTime();
                long queryTime = queryEndTime - queryStartTime;
                // System.out.println("Closest point to " + p + ": " + closestToP);
                // System.out.println("QueryTime: " + Common.nanoToMili(queryTime) + "ms");

                // System.out.println("TotalTIme: " + Common.nanoToMili(buildTime + queryTime) + "ms");
                sumBuildTime += Common.nanoToMili(buildTime);
                sumQueryTime += Common.nanoToMili(queryTime);
            }
            double promBuildTime = sumBuildTime / Common.NUMBER_OF_RUNS;
            double promQueryTime = sumQueryTime / Common.NUMBER_OF_RUNS;
            String fmt;
            fmt = String.format("BuildTime promedio: %.4fms", promBuildTime);
            System.out.println(fmt);
            fmt = String.format("QueryTime promedio: %.4fms", promQueryTime);
            System.out.println(fmt);
            fmt = String.format("TotalTime promedio: %.4fms", (promBuildTime + promQueryTime));
            System.out.println(fmt);
        }
    }
}