package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.rng.RNG;

import java.text.DecimalFormat;

/**
 * Created by zac on 02.05.17.
 */
public class EphemeralConstantNode extends Node<Double> {

    private final double constant;
    private final String name;
    private DecimalFormat df = new DecimalFormat("#0.00");

    public EphemeralConstantNode(double d1, double d2) {
        this.constant = RNG.nextDouble(d1, d2);
        this.name = "" + df.format(constant);
    }

    public EphemeralConstantNode(double constant, String name) {
        this.constant = constant;
        this.name = name;
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