package functions;

import dom.Coefficients;

import java.util.List;

public class Logarithmic implements Func{
    private Coefficients coefs;
    public Logarithmic(Coefficients coefs){
        this.coefs = coefs;
    }
    @Override
    public double calc(double x) {
        List<Double> coefficients = coefs.getCoefs();
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        return a * Math.log(x) + b;
    }

    @Override
    public Coefficients getCoefficitients() {
        return this.coefs;
    }
}
