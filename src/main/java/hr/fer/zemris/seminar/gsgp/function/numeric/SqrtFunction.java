package hr.fer.zemris.seminar.gsgp.function.numeric;

/**
 * Created by zac on 15.05.17.
 */
public class SqrtFunction extends NumericFunction {
    @Override
    public Double evaluate(Double[] input) {
        return (input[0] > 0) ? Math.sqrt(input[0]) : 1;
    }
}
