package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.function.numeric.NumericFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 15.05.17.
 */
public class TanNode extends FunctionNode<Double> {

    public TanNode(Node<Double>[] children) {
        super(NumericFunction.getTanFunctionInstance(), children, new Double[1]);
    }

    @Override
    public Node<Double> clone() {
        Node<Double>[] cloned = (Node<Double>[]) new Node[1];
        cloned[0] = children[0].clone();
        return new TanNode(cloned);
    }

    @Override
    public String getName() {
        return "Tan";
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public String toString() {
        return "(tan " + children[0] + ")";
    }

}
