package hr.fer.zemris.seminar.gsgp.function.numeric;

/**
 * Created by zac on 30.04.17.
 */
public class ProtectedDivisionFunction extends NumericFunction {

    private static final double EPSILON = 1e-12;

    @Override
    public Double evaluate(Double[] input) {
        return Math.abs(input[1]) > EPSILON ? input[0] / input[1] : 1;
//        return input[0] / input[1];
    }

}
