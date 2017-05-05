package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;

/**
 * Created by zac on 04.05.17.
 */
public interface ISemanticMutationOperator {

    SemanticSolution mutate(SemanticSolution s, IFitnessFunction costFunction);

}
