package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.function.numeric.NumericFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 15.05.17.
 */
public class CubNode extends FunctionNode<Double> {

    public CubNode(Node<Double>[] children) {
        super(NumericFunction.getCubFunctionInstance(), children, new Double[1]);
    }

    @Override
    public Node<Double> clone() {
        Node<Double>[] cloned = (Node<Double>[]) new Node[1];
        cloned[0] = children[0].clone();
        return new CubNode(cloned);
    }

    @Override
    public String getName() {
        return "Cub";
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public String toString() {
        return "(cub " + children[0] + ")";
    }

}
