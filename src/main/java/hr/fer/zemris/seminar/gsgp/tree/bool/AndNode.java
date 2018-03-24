package hr.fer.zemris.seminar.gsgp.tree.bool;

import hr.fer.zemris.seminar.gsgp.function.bool.BoolFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;

/**
 * Created by zac on 26.04.17.
 */
public class AndNode extends FunctionNode<Boolean> {

    public AndNode(Node<Boolean>[] children) {
        super(BoolFunction.getAndFunctionInstance(), children, new Boolean[2]);
    }

    public AndNode(Node<Boolean> a, Node<Boolean> b) {
        super(BoolFunction.getAndFunctionInstance(), new Boolean[2]);
        this.children = (Node<Boolean>[]) new Node[]{a, b};
    }

    @Override
    public Node<Boolean> clone() {
        Node<Boolean>[] cloned = (Node<Boolean>[]) new Node[2];
        cloned[0] = children[0].clone();
        cloned[1] = children[1].clone();
        return new AndNode(cloned);
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String getName() {
        return "And";
    }

    @Override
    public String toString() {
//        return "And(" + children[0] + "," + children[1] + ")";
        return "(and " + children[0] + " " + children[1] + ")";
    }


}
