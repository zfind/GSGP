package hr.fer.zemris.seminar.gsgp.genetic.initializer;

import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac on 28.04.17.
 */
public class RampedHalfInitializer {

    public static List<Solution> getInitPopulation(int populationSize, int minDepth, int maxDepth,
                                                   IFitnessFunction function, TreeFactory treeFactory) {
        List<Solution> population = new ArrayList<>();
        for (int i = minDepth; i <= maxDepth; i++) {
            for (int j = 0; j < (populationSize / (maxDepth - minDepth + 1) / 2); j++) {
                population.add(new Solution(treeFactory.growTree(i, true), function));
                population.add(new Solution(treeFactory.growTree(i, false), function));
            }
        }

        return population;
    }

}
