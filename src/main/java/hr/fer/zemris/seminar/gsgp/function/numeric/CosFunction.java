package hr.fer.zemris.seminar.gsgp.function.numeric;

/**
 * Created by zac on 15.05.17.
 */
public class CosFunction extends NumericFunction {
    @Override
    public Double evaluate(Double[] input) {
        return Math.cos(input[0]);
    }
}
