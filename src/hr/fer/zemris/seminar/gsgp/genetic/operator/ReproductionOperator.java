package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 28.04.17.
 */
public class ReproductionOperator implements IReproductionOperator {

    public Solution reproduce(Solution solution, IFitnessFunction fitnessFunction) {
        Node clone = solution.getTree().clone();
        return new Solution(clone, fitnessFunction);
    }

}
