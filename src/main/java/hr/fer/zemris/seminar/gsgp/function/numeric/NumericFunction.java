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
    private static final NumericFunction sine;
    private static final NumericFunction cosine;
    private static final NumericFunction exp;
    private static final NumericFunction ln;
    private static final NumericFunction sqr;
    private static final NumericFunction cub;
    private static final NumericFunction sqrt;
    private static final NumericFunction tan;
    private static final NumericFunction tanh;


    static {
        addition = new AdditionFunction();
        subtraction = new SubtractionFunction();
        multiplication = new MultiplicationFunction();
        division = new ProtectedDivisionFunction();
        sine = new SinFunction();
        cosine = new CosFunction();
        exp = new ExpFunction();
        ln = new LnAbsFunction();
        sqr = new SqrFunction();
        sqrt = new SqrtFunction();
        cub = new CubFunction();
        tan = new TanFunction();
        tanh = new TanhFunction();
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

    public static NumericFunction getSineFunctionInstance() {
        return sine;
    }

    public static NumericFunction getCosineFunctionInstance() {
        return cosine;
    }

    public static NumericFunction getExpFunctionInstance() {
        return exp;
    }

    public static NumericFunction getLnFunctionInstance() {
        return ln;
    }

    public static NumericFunction getSqrFunctionInstance() {
        return sqr;
    }

    public static NumericFunction getSqrtFunctionInstance() {
        return sqrt;
    }

    public static NumericFunction getCubFunctionInstance() {
        return cub;
    }

    public static NumericFunction getTanFunctionInstance() {
        return tan;
    }

    public static NumericFunction getTanhFunctionInstance() {
        return tanh;
    }

}
