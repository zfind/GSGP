package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.function.numeric.NumericFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 15.05.17.
 */
public class SqrNode extends FunctionNode<Double> {

    public SqrNode(Node<Double>[] children) {
        super(NumericFunction.getSqrFunctionInstance(), children, new Double[1]);
    }

    @Override
    public Node<Double> clone() {
        Node<Double>[] cloned = (Node<Double>[]) new Node[1];
        cloned[0] = children[0].clone();
        return new SqrNode(cloned);
    }

    @Override
    public String getName() {
        return "Sqr";
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public String toString() {
        return "(sqr " + children[0] + ")";
    }

}
