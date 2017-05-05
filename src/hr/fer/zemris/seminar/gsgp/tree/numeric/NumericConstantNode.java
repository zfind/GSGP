package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.tree.Node;

import java.text.DecimalFormat;

/**
 * Created by zac on 04.05.17.
 */
public class NumericConstantNode extends Node<Double> {

    private final double constant;
    private final String name;
    private DecimalFormat df = new DecimalFormat("#0.00");

    public NumericConstantNode(double constant) {
        this.name = "" + df.format(constant);
        this.constant = constant;
    }

    public NumericConstantNode(double constant, String name) {
        this.name = name;
        this.constant = constant;
    }


    @Override
//    public T evaluate(Class<T> c, T[] input) {
    public Double evaluate(Double[] input) {
        return constant;
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