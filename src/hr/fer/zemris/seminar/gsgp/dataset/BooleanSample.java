package hr.fer.zemris.seminar.gsgp.dataset;

/**
 * Created by zac on 26.04.17.
 */

public class BooleanSample implements ISample<Boolean> {
    private final Boolean[] input;
    private final Boolean output;

    public BooleanSample(Boolean[] input, Boolean output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public Boolean[] getInput() {
        return this.input;
    }

    @Override
    public Boolean getOutput() {
        return this.output;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i] ? "1 " : "0 ");
        }
        sb.append(output ? "1" : "0");
        return sb.toString();
//        return Arrays.toString(input) + " " + output;
    }

}
