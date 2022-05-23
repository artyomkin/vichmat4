package functions;

import dom.Coefficients;

import java.util.List;

public class Exp implements Func{
    private Coefficients coefs;
    public Exp(Coefficients coefs){
        this.coefs = coefs;
    }
    @Override
    public double calc(double x) {
        List<Double> coefficients = coefs.getCoefs();
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        return a * Math.exp(b*x);
    }

    @Override
    public Coefficients getCoefficitients() {
        return this.coefs;
    }
}
