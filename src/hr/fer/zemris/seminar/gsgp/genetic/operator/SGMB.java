package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.dataset.BooleanDataset;
import hr.fer.zemris.seminar.gsgp.dataset.IDataset;
import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.BooleanCostFunction;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.*;
import hr.fer.zemris.seminar.gsgp.tree.bool.AndNode;
import hr.fer.zemris.seminar.gsgp.tree.bool.BoolNodeFactory;
import hr.fer.zemris.seminar.gsgp.tree.bool.NotNode;
import hr.fer.zemris.seminar.gsgp.tree.bool.OrNode;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac on 05.05.17.
 */
public class SGMB implements ISemanticMutationOperator {
    private static final int TR_DEPTH = 3;
    private TreeFactory treeFactory;

    public SGMB(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    @Override
    public SemanticSolution mutate(SemanticSolution s, IFitnessFunction costFunction) {
        Node<Boolean> t = s.getTree();
        Node<Boolean> m = getRandomMinterm();

        Node<Boolean> tm = null;
        boolean choice = RNG.nextDouble() >= 0.5;
        if (choice) {
            tm = new OrNode(new Node[]{
                    t,
                    m
            });
        } else {
            Node<Boolean> notM = new NotNode(new Node[]{
                    m
            });
            tm = new AndNode(new Node[]{
                    t,
                    notM
            });
        }

        List<Boolean> tSemantics = s.getSemantics();
        List<Boolean> mSemantics = costFunction.getSemantics(m);
        List<Boolean> semantics = new ArrayList<>();

        for (int i = 0; i < tSemantics.size(); i++) {
            boolean value;
            if (choice) {
                value = tSemantics.get(i) || mSemantics.get(i);
            } else {
                value = tSemantics.get(i) && !mSemantics.get(i);
            }
            semantics.add(value);
        }

        return new SemanticSolution<Boolean>(tm, costFunction, semantics);
    }

    private Node<Boolean> getRandomMinterm() {
        Node<Boolean>[] terminals = treeFactory.getNodeFactory().getTermNodes();
        Node<Boolean> root = terminals[0];
        for (int i = 1; i < terminals.length; i++) {
            Node<Boolean> var = terminals[i];
            if (RNG.nextDouble() >= 0.5) {
                var = new NotNode(new Node[]{
                        var
                });
            }
            root = new AndNode(new Node[]{
                    var,
                    root
            });
        }
        return root;
    }

    public static void main(String[] args) {
        String[] varNames = new String[]{"a", "b", "c", "d", "e"};
        TerminalNode<Boolean>[] terminals = TerminalNodeFactory.getTerminals(varNames);
        INodeFactory<Boolean> nodeFactory = new BoolNodeFactory(terminals);
        TreeFactory<Boolean> treeFactory = new TreeFactory<>(nodeFactory);

        SGMB sgmb = new SGMB(treeFactory);
        Node<Boolean> minterm = sgmb.getRandomMinterm();
        System.out.println(minterm.toString());

        IDataset<Boolean> dataset = new BooleanDataset("data/f2.txt");
        IFitnessFunction<Boolean> fitnessFunction = new BooleanCostFunction(dataset);

        SemanticSolution<Boolean> solution = new SemanticSolution<Boolean>(minterm, fitnessFunction);
        List<Boolean> semantics = solution.getSemantics();
        for (int i = 0; i < dataset.size(); i++) {
//            childrenOutputVector[i] = expression.evaluate(Boolean.class, dataset.getInput(i));
            boolean output = minterm.evaluate(dataset.getInput(i));
//            boolean real = dataset.getOutput(i);
            boolean semantic = semantics.get(i);
            System.out.println(dataset.getSample(i).toString() + "\t" + output + "\t" + semantic);
        }
    }
}
