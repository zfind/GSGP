package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.function.numeric.NumericFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 30.04.17.
 */
public class MultiplicationNode extends FunctionNode<Double> {

    public MultiplicationNode(Node<Double>[] children) {
        super(NumericFunction.getMultiplicationFunctionInstance(), children, new Double[2]);
    }

    public MultiplicationNode(Node<Double> child1, Node<Double> child2) {
        super(NumericFunction.getMultiplicationFunctionInstance(), new Double[2]);
        this.children = (Node<Double>[]) new Node[]{child1, child2};
    }

    @Override
    public Node<Double> clone() {
        Node<Double>[] cloned = (Node<Double>[]) new Node[2];
        cloned[0] = children[0].clone();
        cloned[1] = children[1].clone();
        return new MultiplicationNode(cloned);
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String getName() {
        return "Mul";
    }

    @Override
    public String toString() {
        return "(* " + children[0] + " " + children[1] + ")";
    }

}
