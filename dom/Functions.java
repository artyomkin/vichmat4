package dom;

import static java.lang.Math.pow;

public class Functions {
    public static double polinom1(double a, double b, double x){
        return a*x+b;
    }
    public static double polinom2(double a0, double a1, double a2, double x){
        return a0*pow(x,2) + a1*x + a2;
    }
    public static double polinom3(double a0, double a1, double a2, double a3, double x){
        return a0*pow(x,3) + a1*pow(x,2) + a2*x + a3;
    }
    public static double exp(double a, double b, double x){
        return a*Math.exp(b*x);
    }
    public static double logarithmic(double a, double b, double x){
        return a*Math.log(x) + b;
    }
    public static double degree(double a, double b, double x){
        return a * pow(x,b);
    }
}
