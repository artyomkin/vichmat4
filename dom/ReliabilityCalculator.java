package dom;

import java.util.List;

public interface ReliabilityCalculator {
    double r(List<Point> points, List<Double> phi);
}
