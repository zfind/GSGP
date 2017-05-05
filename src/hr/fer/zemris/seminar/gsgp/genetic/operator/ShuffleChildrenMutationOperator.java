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
public class ShuffleChildrenMutationOperator implements IMutationOperator {
    private TreeFactory treeFactory;

    public ShuffleChildrenMutationOperator(TreeFactory treeFactory) {
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
            FunctionNode pickedNode = (FunctionNode) branch.get(picked);

            shuffleArray(pickedNode.getChildren());

            return new Solution(tree, costFunction);
        }
    }


    private static void shuffleArray(Node[] ar) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = RNG.nextInt(0, i + 1);
            Node a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
