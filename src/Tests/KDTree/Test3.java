package Tests.KDTree;

import KDTree.KDTree;
import KDTree.Punto;
import Tests.Common;

//Testear un mismo query para unos datos crecientes
public class Test3 {
    public Test3() {
        Common common = new Common();
        for (int i = Common.LOAD_LOWER_BOUND; i <= Common.LOAD_UPPER_BOUND; i++) {
            int n = (int) Math.pow(10, i);
            System.out.println("\nN: " + n);
            double sumBuildTime = 0;
            double sumFirstQueryTime = 0;
            double sumPushTime = 0;
            double sumSecondQueryTime = 0;
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
                long firstQueryTime = queryEndTime - queryStartTime;
                // System.out.println("First query:");
                // System.out.println("Closest point to " + p + ": " + closestToP);
                // System.out.println("QueryTime: " + Common.nanoToMili(query0Time) + "ms");

                Punto[] extraPuntos = common.generarPuntos(n/10);
                long pushStartTime = System.nanoTime();
                for (int k = 0; k < extraPuntos.length; k++) {
                    tree.push(extraPuntos[k]);
                }
                long pushEndTime = System.nanoTime();
                // System.out.println("Agregados " + n + "puntos extras.");
                long pushTime = pushEndTime - pushStartTime;
                // System.out.println("PushTime: " + Common.nanoToMili(pushTime) + "ms");

                queryStartTime = System.nanoTime();
                closestToP = tree.getNearestPoint(p);
                queryEndTime = System.nanoTime();
                long secondQueryTime = queryEndTime - queryStartTime;
                // System.out.println("Second query:");
                // System.out.println("Closest point to " + p + ": " + closestToP);
                // System.out.println("QueryTime: " + Common.nanoToMili(query1Time) + "ms");

                // System.out.println("TotalTIme: " + Common.nanoToMili(buildTime + query0Time + pushTime + query1Time) + "ms");
                sumBuildTime += Common.nanoToMili(buildTime);
                sumFirstQueryTime += Common.nanoToMili(firstQueryTime);
                sumPushTime += Common.nanoToMili(pushTime);
                sumSecondQueryTime += Common.nanoToMili(secondQueryTime);
            }
            double promBuildTime = sumBuildTime / Common.NUMBER_OF_RUNS;
            double promFirstQueryTime = sumFirstQueryTime / Common.NUMBER_OF_RUNS;
            double promPushTime = sumPushTime / Common.NUMBER_OF_RUNS;
            double promSecondQueryTime = sumSecondQueryTime / Common.NUMBER_OF_RUNS;
            String fmt;
            fmt = String.format("BuildTime promedio: %.4fms", promBuildTime);
            System.out.println(fmt);
            fmt = String.format("FirstQueryTime promedio: %.4fms", promFirstQueryTime);
            System.out.println(fmt);
            fmt = String.format("PushTime promedio: %.4fms", promPushTime);
            System.out.println(fmt);
            fmt = String.format("SecondQueryTime promedio: %.4fms", promSecondQueryTime);
            System.out.println(fmt);
            fmt = String.format("TotalTime promedio: %.4fms", (promBuildTime + promFirstQueryTime + promPushTime + promSecondQueryTime));
            System.out.println(fmt);
        }
    }
}
