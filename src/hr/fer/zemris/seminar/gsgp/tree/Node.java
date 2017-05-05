package hr.fer.zemris.seminar.gsgp.tree;

/**
 * Created by zac on 26.04.17.
 */
public abstract class Node<T> {

    //    public abstract T evaluate(Class<T> c, T[] input);
    public abstract T evaluate(T[] input);

    public abstract Node<T> clone();

    public abstract int getDepth();

    public abstract int getNodeCount();

    public abstract String getName();

}
