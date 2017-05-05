package hr.fer.zemris.seminar.gsgp.tree.bool;

import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.INodeFactory;
import hr.fer.zemris.seminar.gsgp.tree.TerminalNode;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zac on 26.04.17.
 */
public class BoolNodeFactory implements INodeFactory<Boolean> {

    private static final int AND = 0;
    private static final int OR = 1;
    private static final int NOT = 2;

    private TerminalNode<Boolean>[] terminals;
    private int[] arity;
    public final int terminalsCount;
    public final int functionsCount;
    public final double ratio;

    private Map<Integer, List<Integer>> aritySets;
    private Map<Class, Integer> functionClassId;

    public BoolNodeFactory(TerminalNode<Boolean>[] terminals) {
        this.terminals = terminals;
        this.terminalsCount = terminals.length;
        this.functionsCount = 3;
//        this.ratio = terminalsCount / ((double) terminalsCount + functionsCount);
        this.ratio = 0.5;

        this.arity = new int[3];
        arity[AND] = 2;
        arity[OR] = 2;
        arity[NOT] = 1;

        this.aritySets = new HashMap<>();
        List<Integer> arity1 = new ArrayList<>(1);
        arity1.add(NOT);
        aritySets.put(1, arity1);
        List<Integer> arity2 = new ArrayList<>(2);
        arity2.add(AND);
        arity2.add(OR);
        aritySets.put(2, arity2);

        this.functionClassId = new HashMap<>();
        functionClassId.put(AndNode.class, AND);
        functionClassId.put(OrNode.class, OR);
        functionClassId.put(NotNode.class, NOT);
    }

    public Node<Boolean> getRandomTermNodeInstance() {
        int choice = RNG.nextInt(0, terminalsCount);
        return terminals[choice].clone();
    }

    public Node<Boolean> getTermNodeInstance(int choice) {
        return terminals[choice].clone();
    }

    public int chooseRandomFunction() {
        return RNG.nextInt(0, functionsCount);
    }

    public int getArity(int choice) {
        return arity[choice];
    }

    public Node<Boolean> getFuncNodeInstance(int choice, Node<Boolean>[] children) {
        switch (choice) {
            case AND:
                return new AndNode(children);
            case OR:
                return new OrNode(children);
            case NOT:
                return new NotNode(children);
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
    public Node<Boolean> swapWithSameArityFunction(FunctionNode<Boolean> node) {
        int arity = node.getArity();
        List<Integer> sameArityFunctions = aritySets.get(arity);
        int currentFunctionId = functionClassId.get(node.getClass());
        int nextFunctionId = currentFunctionId;
        while (nextFunctionId == currentFunctionId) {
            nextFunctionId = sameArityFunctions.get(RNG.nextInt(0, sameArityFunctions.size()));
        }
        return getFuncNodeInstance(nextFunctionId, node.getChildren());
    }

    @Override
    public Node<Boolean>[] getTermNodes() {
        return terminals;
    }


}
