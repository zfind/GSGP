package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;
import hr.fer.zemris.seminar.gsgp.tree.numeric.AdditionNode;
import hr.fer.zemris.seminar.gsgp.tree.numeric.MultiplicationNode;
import hr.fer.zemris.seminar.gsgp.tree.numeric.NumericConstantNode;
import hr.fer.zemris.seminar.gsgp.tree.numeric.SubtractionNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac on 04.05.17.
 */
public class SGMR implements ISemanticMutationOperator {

    private static final double ms = 0.5;
    private static final int TR_DEPTH = 3;
    private TreeFactory treeFactory;

    public SGMR(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    @Override
    public SemanticSolution mutate(SemanticSolution s, IFitnessFunction costFunction) {
        Node<Double> t = s.getTree();
        Node<Double> tr1 = treeFactory.growTree(TR_DEPTH, true);
        Node<Double> tr2 = treeFactory.growTree(TR_DEPTH, true);

        Node<Double> sub = new SubtractionNode(tr1, tr2);

        Node<Double> mul = new MultiplicationNode(new NumericConstantNode(ms), sub);

        Node<Double> root = new AdditionNode(t, mul);

        List<Double> tSemantics = s.getSemantics();
        List<Double> tr1Semantics = costFunction.getSemantics(tr1);
        List<Double> tr2Semantics = costFunction.getSemantics(tr2);
        List<Double> semantics = new ArrayList<>();

        for (int i = 0; i < tr1Semantics.size(); i++) {
            double value = tSemantics.get(i) + ms * (tr1Semantics.get(i) - tr2Semantics.get(i));
            semantics.add(value);
        }

        return new SemanticSolution<Double>(root, costFunction, semantics);
    }

}
