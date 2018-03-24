package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.function.numeric.NumericFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 15.05.17.
 */
public class LnAbsNode extends FunctionNode<Double> {

    public LnAbsNode(Node<Double>[] children) {
        super(NumericFunction.getLnFunctionInstance(), children, new Double[1]);
    }

    public LnAbsNode(Node<Double> child1) {
        super(NumericFunction.getLnFunctionInstance(), new Double[1]);
        this.children = (Node<Double>[]) new Node[]{child1};
    }

    @Override
    public Node<Double> clone() {
        Node<Double>[] cloned = (Node<Double>[]) new Node[1];
        cloned[0] = children[0].clone();
        return new LnAbsNode(cloned);
    }

    @Override
    public String getName() {
        return "LnAbs";
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public String toString() {
        return "(ln|| " + children[0] + ")";
    }

}
