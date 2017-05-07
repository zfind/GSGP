package hr.fer.zemris.seminar.gsgp.function.numeric;

/**
 * Created by zac on 30.04.17.
 */
public class SubtractionFunction extends NumericFunction {

    @Override
    public Double evaluate(Double[] input) {
        return input[0] - input[1];
    }

}
