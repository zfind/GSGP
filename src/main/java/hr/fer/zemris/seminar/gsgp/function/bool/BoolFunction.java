package hr.fer.zemris.seminar.gsgp.function.bool;

import hr.fer.zemris.seminar.gsgp.function.IFunction;

/**
 * Created by zac on 26.04.17.
 */
public abstract class BoolFunction implements IFunction<Boolean> {

    private static final BoolFunction and;
    private static final BoolFunction or;
    private static final BoolFunction not;

    static {
        and = new AndFunction();
        or = new OrFunction();
        not = new NotFunction();
    }

//    public abstract boolean evaluate(boolean[] input);

    public static BoolFunction getAndFunctionInstance() {
        return and;
    }

    public static BoolFunction getOrFunctionInstance() {
        return or;
    }

    public static BoolFunction getNotFunctionInstance() {
        return not;
    }

}
