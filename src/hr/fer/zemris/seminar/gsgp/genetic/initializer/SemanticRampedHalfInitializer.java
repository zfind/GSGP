package hr.fer.zemris.seminar.gsgp.genetic.initializer;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac on 28.04.17.
 */
public class SemanticRampedHalfInitializer {

    public static List<SemanticSolution> getInitPopulation(int populationSize, int minDepth, int maxDepth,
                                                           IFitnessFunction function, TreeFactory treeFactory) {

        List<SemanticSolution> population = new ArrayList<>();
        for (int i = minDepth; i <= maxDepth; i++) {
            for (int j = 0; j < (populationSize / (maxDepth - minDepth + 1) / 2); j++) {
                population.add(new SemanticSolution(treeFactory.growTree(i, true), function));
                population.add(new SemanticSolution(treeFactory.growTree(i, false), function));
            }
        }

        return population;
    }

}