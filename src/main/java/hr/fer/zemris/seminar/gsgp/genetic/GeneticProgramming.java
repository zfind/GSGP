package hr.fer.zemris.seminar.gsgp.genetic;

import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.genetic.operator.ICrossoverOperator;
import hr.fer.zemris.seminar.gsgp.genetic.operator.IMutationOperator;
import hr.fer.zemris.seminar.gsgp.genetic.operator.IReproductionOperator;
import hr.fer.zemris.seminar.gsgp.genetic.selection.ISelection;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zac on 28.04.17.
 */
public class GeneticProgramming implements IOptimizationAlgorithm<Solution> {

    private IFitnessFunction costFunction;
    private ICrossoverOperator crossoverOperator;
    private IMutationOperator mutationOperator;
    private IReproductionOperator reproductionOperator;
    private ISelection selection;
    private List<Solution> population;
    private final double crossoverProbability;
    private final double mutationProbability;
    private final double reproductionProbability;
    private final int populationSize;
    private final int maxGenerations;
    private final double goodFitnessThreshold;

    public GeneticProgramming(IFitnessFunction costFunction,
                              ICrossoverOperator crossoverOperator,
                              IMutationOperator mutationOperator,
                              IReproductionOperator reproductionOperator,
                              ISelection selection,
                              List<Solution> initialPopulation,
                              double crProb,
                              double mutProb,
                              double repProb,
                              int populationSize,
                              int maxGenerations,
                              double fitnessThreshold) {
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.reproductionOperator = reproductionOperator;
        this.selection = selection;
        this.costFunction = costFunction;
        this.population = initialPopulation;
        this.crossoverProbability = crProb;
        this.mutationProbability = mutProb;
        this.reproductionProbability = repProb;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.goodFitnessThreshold = fitnessThreshold;
    }

    @Override
    public Solution run() {
        List<Solution> nextPopulation;
        for (int generation = 0; generation < maxGenerations; generation++) {
            nextPopulation = new ArrayList<>(populationSize);

            Solution best = Collections.max(population);
            if (best.getFitness() >= goodFitnessThreshold) break;
            System.out.println(generation + ": " + best.getCost());
            nextPopulation.add(best);

            for (int i = 1; i < populationSize; i++) {
                Solution p1 = selection.select(population);
                double prob = RNG.nextDouble();

                if (prob < crossoverProbability) {
                    Solution p2 = selection.select(population);
                    Solution child = crossoverOperator.cross(p1, p2, costFunction);
                    nextPopulation.add(child);
                } else if (prob < crossoverProbability + mutationProbability) {
                    Solution mutant = mutationOperator.mutate(p1, costFunction);
                    nextPopulation.add(mutant);
                } else {
                    Solution clone = reproductionOperator.reproduce(p1, costFunction);
                    nextPopulation.add(clone);
                }
            }

            population = nextPopulation;
        }
        return Collections.max(population);
    }

}
