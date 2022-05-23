package approx_functions;

import dom.Coefficients;
import dom.Ftype;
import dom.Point;
import exceptions.ZeroDeterminantException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exp {
    public Coefficients coefs(List<Point> points, double n) throws ZeroDeterminantException {
        List<Point> newPoints = new ArrayList<>();
        List<Point> newPointsNeg = new ArrayList<>();
        for (int i = 0; i < points.size(); i++){
            Point point = points.get(i);
            if (point.getY() > 0){
                newPoints.add(new Point(point.getX(), Math.log(point.getY())));
            } else {
                newPointsNeg.add(point);
            }
        }
        Polinom polinomSolver = new Polinom();
        Coefficients coefs = polinomSolver.coefs(newPoints,n, 1);
        double a = coefs.getCoefs().get(0);
        double b = Math.exp(coefs.getCoefs().get(1));
        return new Coefficients(Arrays.asList(a,b), "Exponential", Ftype.EXP);
    }
}
