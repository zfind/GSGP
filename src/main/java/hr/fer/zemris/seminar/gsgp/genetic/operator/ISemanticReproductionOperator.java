package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;

/**
 * Created by zac on 13.05.17.
 */
public interface ISemanticReproductionOperator {

    SemanticSolution reproduce(SemanticSolution solution, IFitnessFunction fitnessFunction);

}
