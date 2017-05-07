package hr.fer.zemris.seminar.gsgp.genetic.operator;

import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.List;

/**
 * Created by zac on 02.05.17.
 */
public class SwapWithSameArityMutationOperator implements IMutationOperator {

    private TreeFactory treeFactory;

    public SwapWithSameArityMutationOperator(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    @Override
    public Solution mutate(Solution s, IFitnessFunction costFunction) {
        Node tree = s.getTree().clone();
        List<Node> branch = treeFactory.pickBranch(tree);
        int i;
        for (i = 0; i < 3; i++) {
            if (branch.size() == 1) {
                branch = treeFactory.pickBranch(tree);
            }
        }
        if (i == 3) {
            return s;
        } else {
            int picked = RNG.nextInt(0, branch.size() - 1);
            FunctionNode funcNode = (FunctionNode) branch.get(picked);
            Node newFuncNode = treeFactory.getNodeFactory().swapWithSameArityFunction(funcNode);
            if (picked == 0) {
                tree = newFuncNode;
            } else {
                Node pickedNode = branch.get(picked);
                Node parentNode = branch.get(picked - 1);
                Node[] parentChildren = ((FunctionNode) parentNode).getChildren();
                int pickedIndex;
                for (pickedIndex = 0; pickedIndex < parentChildren.length; pickedIndex++) {
                    if (pickedNode.equals(parentChildren[pickedIndex])) {
                        break;
                    }
                    parentChildren[pickedIndex] = newFuncNode;
                }
            }

            return new Solution(tree, costFunction);
        }

    }
}
