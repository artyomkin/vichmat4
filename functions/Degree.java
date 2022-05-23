package functions;

import dom.Coefficients;

import java.util.List;

import static java.lang.Math.pow;

public class Degree implements Func{
    private Coefficients coefs;
    public Degree(Coefficients coefs){
        this.coefs = coefs;
    }
    @Override
    public double calc(double x) {
        List<Double> coefficients = coefs.getCoefs();
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        return a * pow(x,b);
    }

    @Override
    public Coefficients getCoefficitients() {
        return this.coefs;
    }
}
