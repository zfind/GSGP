package hr.fer.zemris.seminar.gsgp.function.numeric;
import hr.fer.zemris.seminar.gsgp.function.numeric.NumericFunction;

/**
 * Created by zac on 15.05.17.
 */
public class TanFunction extends NumericFunction {
    @Override
    public Double evaluate(Double[] input) {
        return Math.tan(input[0]);
    }
}
