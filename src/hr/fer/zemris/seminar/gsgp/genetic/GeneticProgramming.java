package hr.fer.zemris.seminar.gsgp.genetic;

import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.genetic.operator.ICrossoverOperator;
import hr.fer.zemris.seminar.gsgp.genetic.operator.IMutationOperator;
import hr.fer.zemris.seminar.gsgp.genetic.operator.IReproductionOperator;
import hr.fer.zemris.seminar.gsgp.genetic.selection.ISelection;
import hr.fer.zemris.seminar.gsgp.genetic.selection.TournamentSelection;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static hr.fer.zemris.seminar.gsgp.Configuration.*;

/**
 * Created by zac on 28.04.17.
 */
public class GeneticProgramming {

    private ICrossoverOperator crossoverOperator;
    private IMutationOperator mutationOperator;
    private IReproductionOperator reproductionOperator;
    private ISelection selection;

    private IFitnessFunction costFunction;

    private List<Solution> population;

    public GeneticProgramming(IFitnessFunction costFunction,
                              ICrossoverOperator crossoverOperator,
                              IMutationOperator mutationOperator,
                              IReproductionOperator reproductionOperator,
                              ISelection selection,
                              List<Solution> initialPopulation) {
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.reproductionOperator = reproductionOperator;
        this.selection = selection;
        this.costFunction = costFunction;
        this.population = initialPopulation;
    }

    public Solution run() {
        List<Solution> nextPopulation;
        for (int generation = 0; generation < MAX_ITER; generation++) {
            nextPopulation = new ArrayList<>(POP_SIZE);

            Solution best = Collections.max(population);
            if (best.getCost() == 0.) break;
            System.out.println(generation + ": " + best.getCost());
            nextPopulation.add(best);

            for (int i = 1; i < POP_SIZE; i++) {
                Solution p1 = selection.select(population);
                double prob = RNG.nextDouble();

                if (prob < PROB_CROSSOVER) {
                    Solution p2 = selection.select(population);
                    Solution child = crossoverOperator.cross(p1, p2, costFunction);
                    nextPopulation.add(child);
                } else if (prob < PROB_CROSSOVER + PROB_MUTATION) {
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
