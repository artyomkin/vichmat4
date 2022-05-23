package approx_functions;

import dom.Coefficients;
import dom.Ftype;
import dom.Matrix;
import dom.Point;
import exceptions.ZeroDeterminantException;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Polinom {
    public Coefficients coefs(List<Point> points, double n, double degree) throws ZeroDeterminantException {
        List<List<Double>> m = new ArrayList<>();
        List<Double> sums = new ArrayList<>();
        List<Double> b = new ArrayList<>();
        for(int i = 0; i < 2 * degree; i++){
            double sum = 0;
            //double bsum = 0;
            for (int j = 0; j < points.size(); j++){
                Point point = points.get(j);
                sum += pow(point.getX(), i+1);
                //bsum += pow(point.getX(), i) * point.getY();
            }
            sums.add(sum);
            //b.add(bsum);
        }

        for (int i = 0; i < degree + 1; i++){
            double bsum = 0;
            for (int j = 0; j < points.size(); j++){
                Point point = points.get(j);
                bsum+=pow(point.getX(),i) * point.getY();
            }
            b.add(bsum);
        }

        for (int i = 0; i <= degree; i++){
            List<Double> row = new ArrayList<>();
            if (i == 0){
                row.add(n);
                for (int j = 0; j < degree; j++){
                    row.add(sums.get(i+j));
                }
                m.add(row);
                continue;
            }
            for (int j = 0; j <= degree; j++){
                row.add(sums.get(i+j-1));
            }
            m.add(row);
        }

        Matrix matrix = new Matrix(m);

        List<Double> coefs = matrix.solve(b);
        Collections.reverse(coefs);
        String desc = "Polinom with " + degree + " degree";
        Ftype ftype;
        if (degree == 1){
            ftype = Ftype.POLINOM1;
        } else if (degree == 2){
            ftype = Ftype.POLINOM2;
        } else {
            ftype = Ftype.POLINOM3;
        }
        if (degree == 1){
            double averageX = 0;
            double averageY = 0;
            for (int i = 0; i < points.size(); i++){
                averageX += points.get(i).getX();
                averageY += points.get(i).getY();
            }
            averageX /= points.size();
            averageY /= points.size();
            double pirson = 0;
            double numer = 0;
            double denom = 0;
            double xsqr = 0, ysqr = 0;
            for (int i = 0; i < points.size(); i++){
                numer += (points.get(i).getX() - averageX) * (points.get(i).getY() - averageY);
                xsqr += pow(points.get(i).getX() - averageX, 2);
                ysqr += pow(points.get(i).getY() - averageY, 2);
            }
            denom = sqrt(xsqr * ysqr);
            pirson = numer / denom;
            return new Coefficients(coefs, desc, ftype, pirson);
        } else {
            return new Coefficients(coefs,desc,ftype);
        }
    }

}
