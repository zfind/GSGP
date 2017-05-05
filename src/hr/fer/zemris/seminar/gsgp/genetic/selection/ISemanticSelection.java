package hr.fer.zemris.seminar.gsgp.genetic.selection;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;

import java.util.List;

/**
 * Created by zac on 05.05.17.
 */
public interface ISemanticSelection {

    SemanticSolution select(List<SemanticSolution> population);

}
