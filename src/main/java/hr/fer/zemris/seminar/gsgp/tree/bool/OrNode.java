package hr.fer.zemris.seminar.gsgp.tree.bool;

import hr.fer.zemris.seminar.gsgp.function.bool.BoolFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 26.04.17.
 */
public class OrNode extends FunctionNode<Boolean> {

    public OrNode(Node<Boolean>[] children) {
        super(BoolFunction.getOrFunctionInstance(), children);
        childrenOutputVector = new Boolean[2];
    }

    @Override
    public Node<Boolean> clone() {
        Node<Boolean>[] cloned = (Node<Boolean>[]) new Node[2];
        cloned[0] = children[0].clone();
        cloned[1] = children[1].clone();
        return new OrNode(cloned);
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String getName() {
        return "Or";
    }

    @Override
    public String toString() {
//        return "OrNode(" + children[0] + "," + children[1] + ")";
        return "(or " + children[0] + " " + children[1] + ")";
    }
}
