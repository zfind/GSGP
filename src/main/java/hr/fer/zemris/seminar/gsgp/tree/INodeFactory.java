package hr.fer.zemris.seminar.gsgp.tree;

/**
 * Created by zac on 01.05.17.
 */
public interface INodeFactory<T> {

    Node<T> getRandomTermNodeInstance();

    Node<T> getTermNodeInstance(int id);

    int chooseRandomFunction();

    int getArity(int funcId);

    Node<T> getFuncNodeInstance(int choice, Node<T>[] children);

    double getRatio();

    Node<T> swapWithSameArityFunction(FunctionNode<T> node);

    Node<T>[] getTermNodes();

}
