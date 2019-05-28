import java.util.Scanner;

public class Main {

    final static double eps = 0.0002;
    static Double alpha = 1d;
    static Integer n = 20;
    //static Double beta = 1d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Double a, b;
        System.out.println("Enter a:");
        a = sc.nextDouble();
        System.out.println("Enter b:");
        b = sc.nextDouble();

        Double[] xk = {a, b};
        Double[] xkNext = new Double[2];
        Integer count = 0;
        while (true) {
            if (normaX(dF(xk)) < eps) { // 4 ??
                System.out.println("xk:" + xk[0] + " " + xk[1]);
                System.out.println("f = " + F(xk[0], xk[1]));
                break;
            } else {
                xkNext = xNext(xk); // 5
              // printXk(xk);
               // System.out.println("------");
              //  printXk(xkNext);
                if (F(xkNext[0], xkNext[1]) < F(xk[0], xk[1])) { // 6
                    if (Math.abs(normaX(xkNext) - normaX(xk)) < eps) { // 8
                        System.out.println("xk:" + xkNext[0] + " " + xkNext[1]);
                        System.out.println("f = " + F(xk[0], xk[1]));
                        break;
                    } else {
                        xk = xkNext;
                        count++;
                    }
                } else {
                    alpha = alpha / 2d; // 7
                    //printXk(xk);
                   // System.out.println("=");
                }

            }
        }
        //count-=1600;
        System.out.println("Count of iteration: "+count);

    }

    public static void printXk(Double[] xk) {
        System.out.println("xk:" + xk[0] + " " + xk[1]);

    }

    public static Double[] xNext(Double[] xk) {
        Double[] temp = new Double[2];
        temp[0] = xk[0] - alpha * dF(xk)[0];
        temp[1] = xk[1] - alpha * dF(xk)[1];
        return temp;
    }

//    public static Double[] xNext(Double[] xk) {
//        Double[] temp = new Double[2];
//        temp[0] = xk[0] - alpha * dF(xk)[0];
//        temp[1] = xk[1] - alpha * dF(xk)[1];
//        return temp;
//    }

    public static Double normaX(Double[] x) {
        return Math.sqrt(Math.pow(x[0], 2) + Math.pow(x[1], 2));
    }

    //    public static Double F(Double x1, Double x2) {
//        return 2 * Math.pow(x1, 2) + x1 * x2 + Math.pow(x2, 2);
//    }
    public static Double F(Double x1, Double x2) {
        return Math.pow(1 - x1, 2) + 100 * Math.pow(x2 - x1 * x1, 2);
    }

    public static Double[] dF(Double[] x) {
        Double[] temp = new Double[2];
        temp[0] = dX1(x[0], x[1]);
        temp[1] = dX2(x[0], x[1]);
        return temp;
    }

//    public static Double dX1(Double x1, Double x2) {
//        return 4 * x1 + x2;
//    }

    public static Double dX1(Double x1, Double x2) {
        return -2 * (1 - x1) - 400 * x1 * (x2 - x1 * x1);
    }

    //    public static Double dX2(Double x1, Double x2) {
//        return x1 + 2 * x2;
//    }
    public static Double dX2(Double x1, Double x2) {
        return 200 * (x2 - x1 * x1);
    }
}
