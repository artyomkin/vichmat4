package functions;

import approx_functions.Polinom;
import dom.Coefficients;

import java.util.List;

public class Polinom1 implements Func{
    private Coefficients coefs;
    public Polinom1(Coefficients coefs){
        this.coefs = coefs;
    }
    @Override
    public double calc(double x) {
        List<Double> coefficients = coefs.getCoefs();
        double a = coefficients.get(0);
        double b = coefficients.get(1);
        return a * x + b;
    }

    @Override
    public Coefficients getCoefficitients() {
        return this.coefs;
    }
}
