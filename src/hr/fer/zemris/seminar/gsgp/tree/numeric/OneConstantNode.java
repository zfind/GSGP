package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 04.05.17.
 */
public class OneConstantNode extends Node<Double> {

    private final String name;

    public OneConstantNode() {
        this.name = "1";
    }

    public OneConstantNode(String name) {
        this.name = name;
    }


    @Override
//    public T evaluate(Class<T> c, T[] input) {
    public Double evaluate(Double[] input) {
        return 1.;
    }

    @Override
    public Node<Double> clone() {
        return this;
    }

    @Override
    public int getDepth() {
        return 1;
    }

    @Override
    public int getNodeCount() {
        return 1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(" + name + ")";
    }

}