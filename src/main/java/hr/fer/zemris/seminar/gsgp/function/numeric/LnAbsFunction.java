package hr.fer.zemris.seminar.gsgp.function.numeric;

import static java.lang.Float.NEGATIVE_INFINITY;
import static java.lang.Float.NaN;
import static java.lang.Float.POSITIVE_INFINITY;

/**
 * Created by zac on 15.05.17.
 */
public class LnAbsFunction extends NumericFunction {
    private static final double EPS = 1E-12;
    @Override
    public Double evaluate(Double[] input) {
        double result = Math.abs(input[0]) > EPS ? Math.log(Math.abs(input[0])) : 1;
        if (result == NaN || result == NEGATIVE_INFINITY || result == POSITIVE_INFINITY) {
            for (int i=0; i<input.length; i++) {
                System.out.printf(input[i] + " ");
            }
            System.out.println();
            System.exit(-1);
        }
        return result;
    }
}
