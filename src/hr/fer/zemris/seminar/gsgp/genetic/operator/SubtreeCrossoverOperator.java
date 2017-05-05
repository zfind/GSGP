package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.List;

/**
 * Created by zac on 28.04.17.
 */
public class SubtreeCrossoverOperator implements ICrossoverOperator {

    private TreeFactory treeFactory;

    public SubtreeCrossoverOperator(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public Solution cross(Solution p1, Solution p2, IFitnessFunction costFunction) {
        Node p1Tree = p1.getTree().clone();
        Node p2Tree = p2.getTree().clone();
        List<Node> p1Branch = treeFactory.pickBranch(p1Tree);
        List<Node> p2Branch = treeFactory.pickBranch(p2Tree);

        int p = RNG.nextInt(0, p1Branch.size());
        int q = RNG.nextInt(0, p2Branch.size());

        Node branch = p2Branch.get(q);
        if (p == 0) {
            p1Tree = branch;
        } else {
            Node[] parentChildren = ((FunctionNode) p1Branch.get(p - 1)).getChildren();
            Node pNode = p1Branch.get(p);
            int pIndex;
            for (pIndex = 0; pIndex < parentChildren.length; pIndex++) {
                if (pNode.equals(parentChildren[pIndex])) {
                    break;
                }
            }
            parentChildren[pIndex] = branch;
        }

        treeFactory.fixNodesCount(p1Tree);

        return new Solution(p1Tree, costFunction);
    }

}
