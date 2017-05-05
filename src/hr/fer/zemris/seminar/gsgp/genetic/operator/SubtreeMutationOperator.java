package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.List;

import static hr.fer.zemris.seminar.gsgp.Configuration.MAX_DEPTH;

/**
 * Created by zac on 28.04.17.
 */
public class SubtreeMutationOperator implements IMutationOperator {

    private TreeFactory treeFactory;

    public SubtreeMutationOperator(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public Solution mutate(Solution s, IFitnessFunction costFunction) {
        Node tree = s.getTree().clone();
        List<Node> branch = treeFactory.pickBranch(tree);
        int picked = RNG.nextInt(0, branch.size());
        int newBranchMaxDepth = Math.max(1, MAX_DEPTH - picked);
        int newBranchDepth = RNG.nextInt(0, newBranchMaxDepth);
        Node newBranch = treeFactory.growTree(newBranchDepth, true);
        if (picked == 0) {
            tree = newBranch;
        } else {
            Node pickedNode = branch.get(picked);
            Node parentNode = branch.get(picked - 1);
            Node[] parentChildren = ((FunctionNode) parentNode).getChildren();
            int pickedIndex;
            for (pickedIndex = 0; pickedIndex < parentChildren.length; pickedIndex++) {
                if (pickedNode.equals(parentChildren[pickedIndex])) {
                    break;
                }
            }
            parentChildren[pickedIndex] = newBranch;
        }

        treeFactory.fixNodesCount(tree);

        return new Solution(tree, costFunction);
    }

}
