import approx_functions.Degree;
import approx_functions.Exp;
import approx_functions.Logarithmic;
import approx_functions.Polinom;
import dom.*;
import exceptions.ZeroDeterminantException;

import java.io.IOException;
import java.util.*;


import javax.swing.JFrame;

import functions.Func;
import functions.Polinom1;
import functions.Polinom2;
import functions.Polinom3;
import input.PointReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {
    public static void main(String... args){
        PointReader pointReader = new PointReader();
        String filepath = "C:\\Users\\User\\IdeaProjects\\Math4\\src\\main\\resources\\input3.txt";
        List<Point> points = new ArrayList<>();

        try {
            points = pointReader.readPoints(filepath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Degree degree = new Degree();
        Exp exp = new Exp();
        Logarithmic logarithmic = new Logarithmic();
        Polinom polinom = new Polinom();
        Coefficients c1 = null;
        try {
            c1 = polinom.coefs(points,points.size(),1);
        } catch (ZeroDeterminantException e) {
            System.out.println("Cannot approximate function to linear type because of zero determinant");
        }
        Coefficients c2 = null;
        try {
            c2 = polinom.coefs(points,points.size(),2);
        } catch (ZeroDeterminantException e) {
            System.out.println("Cannot approximate function to quadratic type because of zero determinant");
        }

        Coefficients c3 = null;
        try {
            c3 = polinom.coefs(points,points.size(),3);
        } catch (ZeroDeterminantException e) {
            System.out.println("Cannot approximate function to cubic type because of zero determinant");
        }
        Coefficients c4 = null;
        try {
            c4 = exp.coefs(points,points.size());
        } catch (ZeroDeterminantException e) {
            System.out.println("Cannot approximate function to exponential type because of zero determinant");
        }
        Coefficients c5 = null;
        try {
            c5 = logarithmic.coefs(points,points.size());
        } catch (ZeroDeterminantException e) {
            System.out.println("Cannot approximate function to logarithmic type because of zero determinant");
        }
        Coefficients c6 = null;
        try {
            c6 = degree.coefs(points,points.size());
        } catch (ZeroDeterminantException e) {
            System.out.println("Cannot approximate function to degree type because of zero determinant");
        }
        ReliabilityCalculator rel = new ReliabiltyPolinom1();
            List<List<Double>> phi = new ArrayList<>();
            Func p1 = new Polinom1(c1);
            Func p2 = new Polinom2(c2);
            Func p3 = new Polinom3(c3);
            Func expFunc = new functions.Exp(c4);
            Func degreeFunc = new functions.Degree(c5);
            Func log = new functions.Logarithmic(c6);
            List<Func> funcs = Arrays.asList(p1, p2, p3, expFunc, degreeFunc, log);
            for (int i = 0; i < funcs.size(); i++){
                List<Double> row = new ArrayList<>();
                Func f = funcs.get(i);
                if (f.getCoefficitients() == null){
                    phi.add(row);
                    continue;
                }
                for (int j = 0; j < points.size(); j++){
                    double x = points.get(j).getX();
                    row.add(f.calc(x));
                }
                phi.add(row);
            }
            double best_rel = Double.MIN_VALUE;
            Func best_func = funcs.get(0);
            for (int i = 0; i < funcs.size(); i++){
                if (phi.get(i).size() == 0) continue;
                double reliabilty = rel.r(points,phi.get(i));
                if (best_rel < reliabilty){
                    best_rel = reliabilty;
                    best_func = funcs.get(i);
                }
                if (!Double.valueOf(reliabilty).isNaN() && !Double.valueOf(reliabilty).isInfinite()){
                    print(funcs.get(i).getCoefficitients());
                    System.out.println("Reliability = " + reliabilty + '\n');
                } else {
                    System.out.println("Cannot approximate function to " + funcs.get(i).getCoefficitients().getFunctionTypeStr() + " because some points have negative x");
                }
            }

            System.out.println("Most appropriate type of function is " + best_func.getCoefficitients().getFunctionTypeStr());
            System.out.println("Best reliability is " + best_rel);

    }

    private static void print(Coefficients c){
            System.out.println(c.getFunctionTypeStr());
            for (int j = 0; j < c.getCoefs().size(); j++) {
                System.out.println(c.getCoefs().get(j));

            }
            if (c.getFtype() == Ftype.POLINOM1){
                System.out.println("Pirson coefficient = " + c.getPirson());
            }

            XYSeries series = new XYSeries(c.getFunctionTypeStr());
            for (double k = -10; k < 10; k+=0.1){
                Ftype currentType = c.getFtype();
                double val = 0;
                List<Double> coefs = c.getCoefs();
                if (currentType == Ftype.POLINOM2){
                    val = Functions.polinom2(coefs.get(0),coefs.get(1),coefs.get(2), k);
                } else if (currentType == Ftype.POLINOM3) {
                    val = Functions.polinom3(coefs.get(0), coefs.get(1), coefs.get(2), coefs.get(3), k);
                } else if (currentType == Ftype.POLINOM1){
                    val = Functions.polinom1(coefs.get(0),coefs.get(1), k);
                } else if (currentType == Ftype.EXP){
                    val = Functions.exp(coefs.get(0), coefs.get(1), k);
                } else if (currentType == Ftype.DEGREE){
                    val = Functions.degree(coefs.get(0),coefs.get(1), k);
                } else {
                    val = Functions.logarithmic(coefs.get(0), coefs.get(1), k);
                }
                series.add(k,val);
            }
            XYDataset xyDataset = new XYSeriesCollection(series);
            JFreeChart chart = ChartFactory
                    .createXYLineChart(c.getFunctionTypeStr(),
                            "x",
                            "y",
                            xyDataset,
                            PlotOrientation.VERTICAL,
                            true, true, true);
            JFrame frame = new JFrame("MinimalStaticChart");
            frame.getContentPane().add(new ChartPanel(chart));
            frame.setSize(500,500);
            frame.show();
        }
}
//3,035706
//9,251893
//9,2021564545454545454545454545455
