package dom;

import exceptions.ZeroDeterminantException;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Matrix {
    private final List<List<Double>> matrix;
    private double size;
    public Matrix(List<List<Double>> m){
        this.matrix = m;
        this.size = m.size();
    }

    public List<Double> solve(List<Double> b) throws ZeroDeterminantException {
        List<List<Double>> m = this.matrix;
        Matrix matrix = new Matrix(m);
        Double delta = matrix.determinant();
        List<Double> res = new ArrayList<>();
        if (delta == 0){
            throw new ZeroDeterminantException();
        }

        for (int i = 0; i < this.getSize(); i++) {
            List<List<Double>> mi = new ArrayList<>();
            for (int j = 0; j < this.matrix.size(); j++){
                List<Double> row = new ArrayList<>();
                for (int k = 0; k < this.matrix.get(j).size(); k++){
                    row.add(this.matrix.get(j).get(k));
                }
                mi.add(row);
            }
            for (int j = 0; j < b.size(); j++){
                mi.get(j).set(i, b.get(j));
            }
            Matrix matrixi = new Matrix(mi);
            Double deltai = matrixi.determinant();
            double xi = deltai / delta;
            res.add(xi);
        }
        return res;
    }

    public double determinant(){
        double size = this.getSize();

        if (size == 2){
            return this.get(0,0) * this.get(1,1) - this.get(0,1) * this.get(1,0);
        }

        int rowToRemove = 0;
        double determinant = 0;
        for (int i = 0; i < size; i++){
            List<List<Double>> minor = new ArrayList<>();
            for (int j = 0; j < this.getSize(); j++){
                if (j == rowToRemove) continue;

                List<Double> rowMinor = new ArrayList<>();
                for (int k = 0; k < this.getSize(); k++){
                    if (k == i) continue;

                    rowMinor.add(this.get(j,k));
                }
                minor.add(rowMinor);
            }
            Matrix minorMatrix = new Matrix(minor);
            determinant += this.get(rowToRemove, i) * minorMatrix.determinant() * ((i + rowToRemove) % 2 == 1 ? -1 : 1);
        }
        return determinant;
    }

    public double get(int i, int j){
        return this.matrix.get(i).get(j);
    }
}
