package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.function.numeric.NumericFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 30.04.17.
 */
public class ProtectedDivisionNode extends FunctionNode<Double> {

    public ProtectedDivisionNode(Node<Double>[] children) {
        super(NumericFunction.getDivisionFunctionInstance(), children);
        childrenOutputVector = new Double[2];
    }

    @Override
    public Node<Double> clone() {
        Node<Double>[] cloned = (Node<Double>[]) new Node[2];
        cloned[0] = children[0].clone();
        cloned[1] = children[1].clone();
        return new ProtectedDivisionNode(cloned);
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String getName() {
        return "Div";
    }

    @Override
    public String toString() {
        return "(/ " + children[0] + " " + children[1] + ")";
    }

}
