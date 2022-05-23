package dom;

import lombok.Data;

import java.util.List;

@Data
public class Coefficients {
    private List<Double> coefs;
    private String functionTypeStr;
    private Ftype ftype;
    private double pirson;

    public Coefficients(List<Double> coefs, String ftypestr, Ftype ftype){
        this.coefs = coefs;
        this.functionTypeStr = ftypestr;
        this.ftype = ftype;
    }
    public Coefficients(List<Double> coefs, String ftypestr, Ftype ftype, double pirson){
        this.coefs = coefs;
        this.functionTypeStr = ftypestr;
        this.ftype = ftype;
        this.pirson = pirson;
    }
}
