package Tests.SegmentTree;

import SegmentTree.SegmentTree;
import KDTree.Punto;
import Tests.Common;

//Testear un mismo query para unos datos cambiantes
public class Test3 {
    public Test3() {
        Common common = new Common();
        for (int i = Common.LOAD_LOWER_BOUND; i <= Common.LOAD_UPPER_BOUND; i++) {
            int n = (int) Math.pow(10, i);
            System.out.println("\nN: " + n);
            double sumBuildTime = 0;
            double sumFirstQueryTime = 0;
            double sumModifyTime = 0;
            double sumSecondQueryTime = 0;
            for (int j = 0; j < Common.NUMBER_OF_RUNS; j++) {
                Punto[] puntos = common.generarPuntos(n);
                Punto punto = common.generarPunto();

                long buildStartTime = System.nanoTime();
                SegmentTree tree = new SegmentTree(puntos, punto);
                long buildEndTime = System.nanoTime();
                long buildTime = buildEndTime - buildStartTime;

                long firstQueryStartTime = System.nanoTime();
                @SuppressWarnings("unused")
                Punto closestToP = tree.query(0, n-1);
                long firstQueryEndTime = System.nanoTime();
                long firstQueryTime = firstQueryEndTime - firstQueryStartTime;

                Punto[] modifiedPoints = common.generarPuntos((int) (n * Common.RATIO_OF_MODIFICATIONS));
                long modifyStartTime = System.nanoTime();
                for (int k = 0; k < modifiedPoints.length; k++) {
                    tree.update(k, modifiedPoints[k]);
                }
                long modifyEndTime = System.nanoTime();
                long modifyTime = modifyEndTime - modifyStartTime;

                long secondQueryStartTime = System.nanoTime();
                closestToP = tree.query(0, n-1);
                long secondQueryEndTime = System.nanoTime();
                long secondQueryTime = secondQueryEndTime - secondQueryStartTime;

                sumBuildTime += Common.nanoToMili(buildTime);
                sumFirstQueryTime += Common.nanoToMili(firstQueryTime);
                sumModifyTime += Common.nanoToMili(modifyTime);
                sumSecondQueryTime += Common.nanoToMili(secondQueryTime);
            }
            double promBuildTime = sumBuildTime / Common.NUMBER_OF_RUNS;
            double promFirstQueryTime = sumFirstQueryTime / Common.NUMBER_OF_RUNS;
            double promModifyTime = sumModifyTime / Common.NUMBER_OF_RUNS;
            double promSecondQueryTime = sumSecondQueryTime / Common.NUMBER_OF_RUNS;
            String fmt;
            fmt = String.format("BuildTime promedio: %.4fms", promBuildTime);
            System.out.println(fmt);
            fmt = String.format("FirstQueryTime promedio: %.4fms", promFirstQueryTime);
            System.out.println(fmt);
            fmt = String.format("PushTime promedio: %.4fms", promModifyTime);
            System.out.println(fmt);
            fmt = String.format("SecondQueryTime promedio: %.4fms", promSecondQueryTime);
            System.out.println(fmt);
            fmt = String.format("TotalTime promedio: %.4fms", (promBuildTime + promFirstQueryTime + promModifyTime + promSecondQueryTime));
            System.out.println(fmt);

        }
    }
}
