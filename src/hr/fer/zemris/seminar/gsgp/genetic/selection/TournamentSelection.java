package hr.fer.zemris.seminar.gsgp.genetic.selection;

import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static hr.fer.zemris.seminar.gsgp.Configuration.TOURNAMENT;

/**
 * Created by zac on 28.04.17.
 */
public class TournamentSelection implements ISelection {

    public TournamentSelection() {

    }

    @Override
    public Solution select(List<Solution> population) {
        Set<Solution> set = new HashSet<>();
        while (set.size() < TOURNAMENT) {
            set.add(population.get(RNG.nextInt(0, population.size())));
        }
        return Collections.max(set);
    }

}
