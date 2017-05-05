package hr.fer.zemris.seminar.gsgp.tree;

/**
 * Created by zac on 26.04.17.
 */
public class TerminalNode<T> extends Node<T> {

    private final int index;
    private final String name;

    public TerminalNode(int index) {
        this.index = index;
        this.name = "x" + index;
    }

    public TerminalNode(int index, String name) {
        this.index = index;
        this.name = name;
    }


    @Override
//    public T evaluate(Class<T> c, T[] input) {
    public T evaluate(T[] input) {
        return input[index];
    }

    @Override
    public Node<T> clone() {
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
