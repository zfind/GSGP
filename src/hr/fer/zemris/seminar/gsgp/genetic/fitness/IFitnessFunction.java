package hr.fer.zemris.seminar.gsgp.genetic.fitness;

import hr.fer.zemris.seminar.gsgp.tree.Node;

import java.util.List;

/**
 * Created by zac on 28.04.17.
 */
public interface IFitnessFunction<T> {

    double evaluate(Node<T> tree);

    List<T> getSemantics(Node<T> tree);

    double evaluate(List<T> semantics);

}
