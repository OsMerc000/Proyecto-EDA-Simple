package TestKDTree;

import KDTree.KDTree;
import KDTree.Punto;

//Testear un mismo query para unos datos crecientes
public class Test3 {
    public Test3() {
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

                Punto p = common.generarPunto();
                long queryStartTime = System.nanoTime();
                Punto closestToP = tree.getNearestPoint(p);
                long queryEndTime = System.nanoTime();
                long query0Time = queryEndTime - queryStartTime;
                System.out.println("First query:");
                System.out.println("Closest point to " + p + ": " + closestToP);
                System.out.println("QueryTime: " + Common.nanoToMili(query0Time) + "ms");

                Punto[] extraPuntos = common.generarPuntos(n);
                long pushStartTime = System.nanoTime();
                for (int k = 0; k < extraPuntos.length; k++) {
                    tree.push(extraPuntos[k]);
                }
                long pushEndTime = System.nanoTime();
                System.out.println("Agregados " + n + "puntos extras.");
                long pushTime = pushEndTime - pushStartTime;
                System.out.println("PushTime: " + Common.nanoToMili(pushTime) + "ms");

                queryStartTime = System.nanoTime();
                closestToP = tree.getNearestPoint(p);
                queryEndTime = System.nanoTime();
                long query1Time = queryEndTime - queryStartTime;
                System.out.println("Second query:");
                System.out.println("Closest point to " + p + ": " + closestToP);
                System.out.println("QueryTime: " + Common.nanoToMili(query1Time) + "ms");

                System.out.println("TotalTIme: " + Common.nanoToMili(buildTime + query0Time + pushTime + query1Time) + "ms");
            }
            System.out.println();
        }
    }
}
