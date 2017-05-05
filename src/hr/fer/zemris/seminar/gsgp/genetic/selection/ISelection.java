package hr.fer.zemris.seminar.gsgp.genetic.selection;

import hr.fer.zemris.seminar.gsgp.genetic.Solution;

import java.util.List;

/**
 * Created by zac on 05.05.17.
 */
public interface ISelection  {

    Solution select(List<Solution> population);

}
