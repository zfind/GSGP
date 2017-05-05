package hr.fer.zemris.seminar.gsgp.genetic.selection;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static hr.fer.zemris.seminar.gsgp.Configuration.TOURNAMENT;

/**
 * Created by zac on 05.05.17.
 */
public class SemanticTournamentSelection implements ISemanticSelection {

    public SemanticTournamentSelection() {

    }

    @Override
    public  SemanticSolution select(List<SemanticSolution> population) {
        Set<SemanticSolution> set = new HashSet<>();
        while (set.size() < TOURNAMENT) {
            set.add(population.get(RNG.nextInt(0, population.size())));
        }
        return Collections.max(set);
    }

}
