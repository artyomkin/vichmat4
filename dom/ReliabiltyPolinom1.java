package dom;

import java.util.List;

import static java.lang.Math.pow;

public class ReliabiltyPolinom1 implements ReliabilityCalculator{
    @Override
    public double r(List<Point> points, List<Double> phi) {
        double numerator = 0;
        double denominator = 0;
        double phisumsqr = 0;
        double phisqrsum = 0;
        for (int i = 0; i < points.size(); i++){
            double y = points.get(i).getY();
            double phi_y = phi.get(i);
            numerator += pow(y - phi_y,2);
            phisumsqr += pow(y,2);
            phisqrsum += y;
        }

        phisqrsum = pow(phisqrsum,2) / points.size();
        denominator = phisumsqr - phisqrsum;
        return 1 - numerator / denominator;
    }
}
