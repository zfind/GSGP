package hr.fer.zemris.seminar.gsgp.genetic;

import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.genetic.operator.ISemanticCrossoverOperator;
import hr.fer.zemris.seminar.gsgp.genetic.operator.ISemanticMutationOperator;
import hr.fer.zemris.seminar.gsgp.genetic.operator.ISemanticReproductionOperator;
import hr.fer.zemris.seminar.gsgp.genetic.selection.ISemanticSelection;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zac on 05.05.17.
 */
public class GeometricSemanticGP implements IOptimizationAlgorithm {

    private ISemanticCrossoverOperator crossoverOperator;
    private ISemanticMutationOperator mutationOperator;
    private ISemanticReproductionOperator reproductionOperator;
    private ISemanticSelection selection;
    private IFitnessFunction fitnessFunction;
    private List<SemanticSolution> population;
    private final double crossoverProbability;
    private final double mutationProbability;
    private final double reproductionProbability;
    private final int populationSize;
    private final int maxGenerations;
    private final double goodFitnessThreshold;

    public GeometricSemanticGP(IFitnessFunction fitnessFunction,
                               ISemanticCrossoverOperator crossoverOperator,
                               ISemanticMutationOperator mutationOperator,
                               ISemanticReproductionOperator reproductionOperator,
                               ISemanticSelection selection,
                               List<SemanticSolution> initialPopulation,
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
        this.fitnessFunction = fitnessFunction;
        this.population = initialPopulation;
        this.crossoverProbability = crProb;
        this.mutationProbability = mutProb;
        this.reproductionProbability = repProb;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.goodFitnessThreshold = fitnessThreshold;
    }

    @Override
    public SemanticSolution run() {
        List<SemanticSolution> nextPopulation;
        for (int generation = 0; generation < maxGenerations; generation++) {
            nextPopulation = new ArrayList<>(populationSize);

            SemanticSolution best = Collections.max(population);
            if (best.getFitness() >= goodFitnessThreshold) break;
            System.out.println(generation + ": " + best.getCost());
            nextPopulation.add(best);

            for (int i = 1; i < populationSize; i++) {
                SemanticSolution p1 = selection.select(population);
                double prob = RNG.nextDouble();

                if (prob < crossoverProbability) {
                    SemanticSolution p2 = selection.select(population);
                    SemanticSolution child = crossoverOperator.cross(p1, p2, fitnessFunction);
                    nextPopulation.add(child);
                } else if (prob < crossoverProbability + mutationProbability) {
                    SemanticSolution mutant = mutationOperator.mutate(p1, fitnessFunction);
                    nextPopulation.add(mutant);
                } else {
                    ; // reproduction placeholder
                }
            }

            population = nextPopulation;
        }
        return Collections.max(population);
    }

}
