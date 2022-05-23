package functions;

import dom.Coefficients;

import java.util.List;

import static java.lang.Math.pow;

public class Polinom3 implements Func{
    private Coefficients coefs;
    public Polinom3(Coefficients coefs){
        this.coefs = coefs;
    }
    @Override
    public double calc(double x) {
        List<Double> coefficients = coefs.getCoefs();
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        double c = coefficients.get(2);
        double d = coefficients.get(3);
        return a * pow(x,3) + b * pow(x,2) + c * x + d;
    }

    @Override
    public Coefficients getCoefficitients() {
        return this.coefs;
    }
}
