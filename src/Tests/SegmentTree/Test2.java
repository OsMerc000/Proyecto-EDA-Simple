package Tests.SegmentTree;

import SegmentTree.SegmentTree;
import KDTree.Punto;
import Tests.Common;

//Testear múltiples querys para unos datos invariantes.
public class Test2 {
    public Test2() {
        Common common = new Common();
        for (int i = Common.LOAD_LOWER_BOUND; i <= Common.LOAD_UPPER_BOUND; i++) {
            int n = (int) Math.pow(10, i);
            System.out.println("\nN: " + n);
            double sumBuildTotalTime = 0;
            double sumQueryTotalTime = 0;
            for (int j = 0; j < Common.NUMBER_OF_RUNS; j++) {
                Punto[] puntos = common.generarPuntos(n);
                double buildTotalTime = 0;
                double queryTotalTime = 0;
                for (int k = 0; k < Common.NUMBER_OF_QUERYS; k++) {
                    Punto punto = common.generarPunto();

                    long buildStartTime = System.nanoTime();
                    SegmentTree tree = new SegmentTree(puntos, punto);
                    long buildEndTime = System.nanoTime();
                    long buildTime = buildEndTime - buildStartTime;

                    long queryStartTime = System.nanoTime();
                    @SuppressWarnings("unused")
                    Punto closestToP = tree.query(0, n - 1);
                    long queryEndTime = System.nanoTime();
                    long queryTime = queryEndTime - queryStartTime;

                    buildTotalTime += Common.nanoToMili(buildTime);
                    queryTotalTime += Common.nanoToMili(queryTime);
                }
                sumBuildTotalTime += buildTotalTime;
                sumQueryTotalTime += queryTotalTime;
            }
            double promBuildTotalTime = sumBuildTotalTime / Common.NUMBER_OF_RUNS;
            double promQueryTotalTime = sumQueryTotalTime / Common.NUMBER_OF_RUNS;
            String fmt;
            fmt = String.format("TotalBuildTime promedio: %.4fms", promBuildTotalTime);
            System.out.println(fmt);
            fmt = String.format("TotalQueryTime promedio: %.4fms", promQueryTotalTime);
            System.out.println(fmt);
            fmt = String.format("TotalTime promedio: %.4fms", (promBuildTotalTime + promQueryTotalTime));
            System.out.println(fmt);
        }
    }
}
