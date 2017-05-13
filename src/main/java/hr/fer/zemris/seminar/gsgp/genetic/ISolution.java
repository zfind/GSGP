package hr.fer.zemris.seminar.gsgp.genetic;

import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 13.05.17.
 */
public interface ISolution {

    Node getTree();

    double getCost();

    double getFitness();

}
