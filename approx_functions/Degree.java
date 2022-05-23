package approx_functions;

import dom.Coefficients;
import dom.Ftype;
import dom.Point;
import exceptions.ZeroDeterminantException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Degree {
    public Coefficients coefs(List<Point> points, double n) throws ZeroDeterminantException {
        List<Point> newPoints = new ArrayList<>();
        List<Point> newPointsNeg = new ArrayList<>();
        for (int i = 0; i < points.size(); i++){
            Point point = points.get(i);
            double newX = Math.log(point.getX());
            double newY = Math.log(point.getY());
            if (point.getX() <= 0 || point.getY() <= 0){
                newPoints.add(point);
            } else {
                newPoints.add(new Point(newX, newY));
            }
        }
        Polinom polinomSolver = new Polinom();
        Coefficients coefs = polinomSolver.coefs(newPoints, n, 1);
        double a = coefs.getCoefs().get(0);
        double b = Math.exp(coefs.getCoefs().get(1));

        return new Coefficients(Arrays.asList(a,b), "Degree", Ftype.DEGREE);
    }
}
