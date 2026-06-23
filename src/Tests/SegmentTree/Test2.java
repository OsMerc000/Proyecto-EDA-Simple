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
                long buildTotalTime = 0;
                long queryTotalTime = 0;
                for (int k = 0; k < 5; k++) {
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


                }
            }
        }
    }
}
