package hr.fer.zemris.seminar.gsgp.genetic;

import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.Node;

import java.util.List;

/**
 * Created by zac on 04.05.17.
 */
public class SemanticSolution<T> implements Comparable<SemanticSolution> {

    private Node tree;
    private double cost;
    private double fitness;
    private List<T> semantics;

    public SemanticSolution(Node tree, IFitnessFunction costFunction) {
        this.tree = tree;
        this.semantics = costFunction.getSemantics(tree);
        this.cost = costFunction.evaluate(semantics);
        this.fitness = -cost;
    }

    public SemanticSolution(Node tree, IFitnessFunction costFunction, List<T> semantics) {
        this.tree = tree;
        this.cost = costFunction.evaluate(semantics);
        this.fitness = -cost;
        this.semantics = semantics;
    }

    public List<T> getSemantics() {
        return semantics;
    }

    public Node getTree() {
        return tree;
    }

    public double getCost() {
        return cost;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(SemanticSolution o) {
        return Double.compare(this.fitness, o.fitness);
    }

}