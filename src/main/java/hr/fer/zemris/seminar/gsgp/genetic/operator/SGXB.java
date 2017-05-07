package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;
import hr.fer.zemris.seminar.gsgp.tree.bool.AndNode;
import hr.fer.zemris.seminar.gsgp.tree.bool.NotNode;
import hr.fer.zemris.seminar.gsgp.tree.bool.OrNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac on 05.05.17.
 */
public class SGXB implements ISemanticCrossoverOperator {
    private TreeFactory treeFactory;
    private static final int TR_DEPTH = 3;

    public SGXB(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    @Override
    public SemanticSolution cross(SemanticSolution p1, SemanticSolution p2, IFitnessFunction function) {
        Node<Boolean> t1 = p1.getTree();
        Node<Boolean> t2 = p2.getTree();

        Node<Boolean> tr = treeFactory.growTree(TR_DEPTH, true);
        List<Boolean> trEval = function.getSemantics(tr);

        Node<Boolean> b1 = new AndNode((Node<Boolean>[]) new Node[]{
                t1,
                tr
        });

        Node<Boolean> notTr = new NotNode((Node<Boolean>[]) new Node[]{
                tr
        });

        Node<Boolean> b2 = new AndNode((Node<Boolean>[]) new Node[]{
                notTr,
                t2
        });

        Node<Boolean> root = new OrNode((Node<Boolean>[]) new Node[]{
                b1,
                b2
        });

        List<Boolean> p1Semantics = p1.getSemantics();
        List<Boolean> p2Semantics = p2.getSemantics();
        List<Boolean> semantics = new ArrayList<>();

        for (int i = 0; i < p1Semantics.size(); i++) {
            boolean value = (p1Semantics.get(i) && trEval.get(i))
                    && (!trEval.get(i) && p2Semantics.get(i));
            semantics.add(value);
        }

        return new SemanticSolution<Boolean>(root, function, semantics);
    }


}
