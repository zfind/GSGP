package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;
import hr.fer.zemris.seminar.gsgp.tree.numeric.AdditionNode;
import hr.fer.zemris.seminar.gsgp.tree.numeric.EphemeralConstantNode;
import hr.fer.zemris.seminar.gsgp.tree.numeric.MultiplicationNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac on 04.05.17.
 */
public class SGXE implements ISemanticCrossoverOperator {
    private TreeFactory treeFactory;

    public SGXE(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    @Override
    public SemanticSolution<Double> cross(SemanticSolution p1, SemanticSolution p2, IFitnessFunction function) {
        Node<Double> t1 = p1.getTree();
        Node<Double> t2 = p2.getTree();

//        Node tr = treeFactory.growTree(3, true);
        Node<Double> tr = new EphemeralConstantNode(0, 1);
        Double trEval = tr.evaluate(null);

        Node<Double> b1 = new MultiplicationNode(t1, tr);

//        Node<Double> oneMinusTr = new SubtractionNode(new OneConstantNode(), tr);
        Node<Double> oneMinusTr = new EphemeralConstantNode(1 - trEval);

        Node<Double> b2 = new MultiplicationNode(oneMinusTr, t2);

        Node<Double> root = new AdditionNode(b1, b2);

        List<Double> p1Semantics = p1.getSemantics();
        List<Double> p2Semantics = p2.getSemantics();
        List<Double> semantics = new ArrayList<>();

        for (int i = 0; i < p1Semantics.size(); i++) {
            double value = p1Semantics.get(i) * trEval + (1 - trEval) * p2Semantics.get(i);
            semantics.add(value);
        }

        return new SemanticSolution<Double>(root, function, semantics);
    }

}
