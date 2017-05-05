package hr.fer.zemris.seminar.gsgp.dataset;

import java.text.DecimalFormat;

/**
 * Created by zac on 02.05.17.
 */
public class NumericSample implements ISample<Double> {
    private Double[] input;
    private Double output;

    public NumericSample(Double[] input, Double output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public Double[] getInput() {
        return this.input;
    }

    @Override
    public Double getOutput() {
        return this.output;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#0.000000");
        for (int i = 0; i < input.length; i++) {
            sb.append(df.format(input[i]));
            sb.append("\t");
        }
        sb.append(df.format(output));
        return sb.toString();
    }

}
