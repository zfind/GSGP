package hr.fer.zemris.seminar.gsgp.genetic.selection;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zac on 05.05.17.
 */
public class SemanticTournamentSelection implements ISemanticSelection {

    private final int tournamentSize;

    public SemanticTournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public SemanticSolution select(List<SemanticSolution> population) {
        Set<SemanticSolution> set = new HashSet<>();
        while (set.size() < tournamentSize) {
            set.add(population.get(RNG.nextInt(0, population.size())));
        }
        return Collections.max(set);
    }

}
