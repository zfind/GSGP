package hr.fer.zemris.seminar.gsgp.tree;

import hr.fer.zemris.seminar.gsgp.function.IFunction;
import hr.fer.zemris.seminar.rng.RNG;

/**
 * Created by zac on 26.04.17.
 */
public abstract class FunctionNode<T> extends Node<T> {

    protected IFunction<T> function;
    protected Node<T>[] children;
    protected T[] childrenOutputVector;

    public FunctionNode(IFunction<T> function, Node<T>[] children) {
        this.function = function;
        this.children = children;
    }

    public FunctionNode(IFunction<T> function, Node<T>[] children, T[] childrenOutputVector) {
        this.function = function;
        this.children = children;
        this.childrenOutputVector = childrenOutputVector;
    }

    public FunctionNode(IFunction<T> function, T[] childrenOutputVector) {
        this.function = function;
        this.childrenOutputVector = childrenOutputVector;
    }

    @Override
//    public T evaluate(Class<T> c, T[] input) {}
    public T evaluate(T[] input) {
//        T[] output = (T[]) new Object[children.length];  // OVO JE KRIVO!!
//        T[] output = (T[]) Array.newInstance(c, children.length); // OK ali sporo
        T[] output = this.childrenOutputVector.clone();
        for (int i = 0; i < children.length; i++) {
//            output[i] = (T) children[i].evaluate(c, input);
            output[i] = (T) children[i].evaluate(input);
        }
        return function.evaluate(output);
    }

    @Override
    public int getDepth() {
        int childrenDepth = 0;
        for (Node child : children) {
            childrenDepth = Math.max(childrenDepth, child.getDepth());
        }
        return 1 + childrenDepth;
    }

    @Override
    public int getNodeCount() {
        int childrenSize = 0;
        for (Node child : children) {
            childrenSize += child.getNodeCount();
        }
        return 1 + childrenSize;
    }

    public Node<T>[] getChildren() {
        return children;
    }

    public Node<T> getRandomChild() {
        return children[RNG.nextInt(0, children.length)];
    }

    public abstract int getArity();

}
