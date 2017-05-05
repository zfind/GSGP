package hr.fer.zemris.seminar.gsgp.tree.numeric;

import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.INodeFactory;
import hr.fer.zemris.seminar.gsgp.tree.TerminalNode;
import hr.fer.zemris.seminar.rng.RNG;

/**
 * Created by zac on 01.05.17.
 */
public class NumericNodeFactory implements INodeFactory<Double> {

    private static final int ADD = 0;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 3;

    private TerminalNode<Double>[] terminals;
    private int[] arity;
    public final int terminalsCount;
    public final int functionsCount;
    public final double ratio;

    public NumericNodeFactory(TerminalNode<Double>[] terminals) {
        this.terminals = terminals;
        this.terminalsCount = terminals.length;
        this.functionsCount = 4;
        this.ratio = 0.5;

        this.arity = new int[4];
        arity[ADD] = 2;
        arity[SUB] = 2;
        arity[MUL] = 2;
        arity[DIV] = 2;
    }

    public Node<Double> getTermNodeInstance(int id) {
        return terminals[id].clone();
    }

    @Override
    public Node<Double> getRandomTermNodeInstance() {
        int choice = RNG.nextInt(0, terminalsCount);
        return terminals[choice].clone();
    }

    @Override
    public int chooseRandomFunction() {
        return RNG.nextInt(0, functionsCount);
    }

    @Override
    public int getArity(int funcId) {
        return arity[funcId];
    }

    @Override
    public Node<Double> getFuncNodeInstance(int choice, Node<Double>[] children) {
        switch (choice) {
            case ADD:
                return new AdditionNode(children);
            case SUB:
                return new SubtractionNode(children);
            case MUL:
                return new MultiplicationNode(children);
            case DIV:
                return new ProtectedDivisionNode(children);
            default:
                System.err.println("Nepostojeci razred");
                return null;
        }
    }

    @Override
    public double getRatio() {
        return ratio;
    }

    @Override
    public Node<Double> swapWithSameArityFunction(FunctionNode<Double> node) {
        return null;
    }

    @Override
    public Node<Double>[] getTermNodes() {
        return terminals;
    }

}
