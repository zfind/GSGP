package hr.fer.zemris.seminar.gsgp.genetic;

import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 28.04.17.
 */
public class Solution implements Comparable<Solution>, ISolution {

    private Node tree;
    private double cost;
    private double fitness;

    public Solution(Node tree, IFitnessFunction costFunction) {
        this.tree = tree;
        this.cost = costFunction.evaluate(tree);
        this.fitness = -cost;
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
    public int compareTo(Solution o) {
        return Double.compare(this.fitness, o.fitness);
    }

}
