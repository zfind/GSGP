package hr.fer.zemris.seminar.gsgp.genetic;

import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.genetic.operator.ISemanticCrossoverOperator;
import hr.fer.zemris.seminar.gsgp.genetic.operator.ISemanticMutationOperator;
import hr.fer.zemris.seminar.gsgp.genetic.selection.ISemanticSelection;
import hr.fer.zemris.seminar.gsgp.genetic.selection.SemanticTournamentSelection;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static hr.fer.zemris.seminar.gsgp.GSGPConfiguration.*;

/**
 * Created by zac on 05.05.17.
 */
public class GeometricSemanticGP {

    private ISemanticCrossoverOperator crossoverOperator;
    private ISemanticMutationOperator mutationOperator;
//    private ISemanticReproduction reproductionOperator;
    private ISemanticSelection selection;

    private IFitnessFunction fitnessFunction;

    private List<SemanticSolution> population;

    public GeometricSemanticGP(IFitnessFunction fitnessFunction,
                               ISemanticCrossoverOperator crossoverOperator,
                               ISemanticMutationOperator mutationOperator,
                               ISemanticSelection selection,
                               List<SemanticSolution> initialPopulation) {
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.selection = selection;
        this.fitnessFunction = fitnessFunction;
        this.population = initialPopulation;
    }

    public SemanticSolution run() {
        List<SemanticSolution> nextPopulation;
        for (int generation = 0; generation < MAX_ITER; generation++) {
            nextPopulation = new ArrayList<>(POP_SIZE);

            SemanticSolution best = Collections.max(population);
            if (best.getCost() == 0.) break;
            System.out.println(generation + ": " + best.getCost());
            nextPopulation.add(best);

            for (int i = 1; i < POP_SIZE; i++) {
                SemanticSolution p1 = selection.select(population);
                double prob = RNG.nextDouble();

                if (prob < PROB_CROSSOVER) {
                    SemanticSolution p2 = selection.select(population);
                    SemanticSolution child = crossoverOperator.cross(p1, p2, fitnessFunction);
                    nextPopulation.add(child);
                } else if (prob < PROB_CROSSOVER + PROB_MUTATION) {
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
