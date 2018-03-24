package hr.fer.zemris.seminar.gsgp.function.numeric;

/**
 * Created by zac on 15.05.17.
 */
public class SqrFunction extends NumericFunction {
    @Override
    public Double evaluate(Double[] input) {
        return Math.pow(input[0], 2);
    }
}
