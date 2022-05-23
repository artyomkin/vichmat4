package functions;

import dom.Coefficients;

import java.util.List;

import static java.lang.Math.pow;

public class Polinom2 implements Func{
    private Coefficients coefs;
    public Polinom2(Coefficients coefs){
        this.coefs = coefs;
    }
    @Override
    public double calc(double x) {
        List<Double> coefficients = coefs.getCoefs();
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        double c = coefficients.get(2);
        return a * pow(x,2) + b * x + c;
    }

    @Override
    public Coefficients getCoefficitients() {
        return this.coefs;
    }
}
