package hr.fer.zemris.seminar.gsgp.function.numeric;

/**
 * Created by zac on 15.05.17.
 */
public class SinFunction extends NumericFunction {
    @Override
    public Double evaluate(Double[] input) {
        return Math.sin(input[0]);
    }
}
