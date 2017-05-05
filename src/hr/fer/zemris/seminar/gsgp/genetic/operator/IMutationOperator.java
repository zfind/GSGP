package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;

/**
 * Created by zac on 28.04.17.
 */
public interface IMutationOperator {

    Solution mutate(Solution s, IFitnessFunction costFunction);

}
