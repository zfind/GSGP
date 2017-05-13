package hr.fer.zemris.seminar.gsgp.tree.bool;

import hr.fer.zemris.seminar.gsgp.function.bool.BoolFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 26.04.17.
 */
public class NotNode extends FunctionNode<Boolean> {

    public NotNode(Node<Boolean>[] children) {
        super(BoolFunction.getNotFunctionInstance(), children);
        childrenOutputVector = new Boolean[2];
    }

    public NotNode(Node<Boolean> a) {
        super(BoolFunction.getNotFunctionInstance(), new Boolean[1]);
        this.children = (Node<Boolean>[]) new Node[]{a};
    }

    @Override
    public Node<Boolean> clone() {
        Node<Boolean>[] cloned = (Node<Boolean>[]) new Node[1];
        cloned[0] = children[0].clone();
        return new NotNode(cloned);
    }

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public String getName() {
        return "Not";
    }

    @Override
    public String toString() {
//        return "Not(" + children[0] + ")";
        return "(not " + children[0] + ")";
    }
}
