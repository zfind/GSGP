package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;

/**
 * Created by zac on 04.05.17.
 */
public interface ISemanticCrossoverOperator {

    SemanticSolution cross(SemanticSolution p1, SemanticSolution p2, IFitnessFunction function);

}
