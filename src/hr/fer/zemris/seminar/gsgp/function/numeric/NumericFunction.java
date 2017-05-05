package hr.fer.zemris.seminar.gsgp.function.numeric;

import hr.fer.zemris.seminar.gsgp.function.IFunction;

/**
 * Created by zac on 30.04.17.
 */
public abstract class NumericFunction implements IFunction<Double> {

    private static final NumericFunction addition;
    private static final NumericFunction subtraction;
    private static final NumericFunction multiplication;
    private static final NumericFunction division;

    static {
        addition = new AdditionFunction();
        subtraction = new SubtractionFunction();
        multiplication = new MultiplicationFunction();
        division = new ProtectedDivisionFunction();
    }

//    public abstract double evaluate(double[] input);

    public static NumericFunction getAdditionFunctionInstance() {
        return addition;
    }

    public static NumericFunction getSubtractionFunctionInstance() {
        return subtraction;
    }

    public static NumericFunction getMultiplicationFunctionInstance() {
        return multiplication;
    }

    public static NumericFunction getDivisionFunctionInstance() {
        return division;
    }

}
