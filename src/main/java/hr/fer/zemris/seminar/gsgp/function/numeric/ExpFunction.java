package hr.fer.zemris.seminar.gsgp.function.numeric;

import static java.lang.Float.NEGATIVE_INFINITY;
import static java.lang.Float.NaN;
import static java.lang.Float.POSITIVE_INFINITY;

/**
 * Created by zac on 15.05.17.
 */
public class ExpFunction extends NumericFunction {
    @Override
    public Double evaluate(Double[] input) {
        double result = Math.exp(input[0]);
        result = (result == POSITIVE_INFINITY) ? 1 : result;
        return result;
    }
}
