package hr.fer.zemris.seminar.gsgp.function.bool;

/**
 * Created by zac on 26.04.17.
 */
public class OrFunction extends BoolFunction {

    @Override
    public Boolean evaluate(Boolean[] input) {
        return input[0] || input[1];
    }

}
