package hr.fer.zemris.seminar.gsgp.tree;

import hr.fer.zemris.seminar.gsgp.dataset.BooleanDataset;
import hr.fer.zemris.seminar.gsgp.tree.bool.AndNode;
import hr.fer.zemris.seminar.gsgp.tree.bool.BoolNodeFactory;
import hr.fer.zemris.seminar.gsgp.tree.bool.NotNode;
import hr.fer.zemris.seminar.gsgp.tree.bool.OrNode;
import hr.fer.zemris.seminar.rng.RNG;

import java.util.ArrayList;
import java.util.List;

import static hr.fer.zemris.seminar.gsgp.Configuration.MAX_NODE_COUNT;

/**
 * Created by zac on 26.04.17.
 */
public class TreeFactory<T> {

    private INodeFactory<T> nodeFactory;

    public TreeFactory(INodeFactory<T> nodeFactory) {
        this.nodeFactory = nodeFactory;
    }

    public Node<T> growTree(int depth, boolean grow) {
        Node<T> expr = null;
        if (depth <= 1 || (grow && RNG.nextDouble() < nodeFactory.getRatio())) {
            expr = nodeFactory.getRandomTermNodeInstance();
        } else {
            int func = nodeFactory.chooseRandomFunction();
            Node<T>[] children = (Node<T>[]) new Node[nodeFactory.getArity(func)];
//            Class func = nodeFactory.getRandomFunctionClass();
//            Node<T> children = (Node<T>[]) new Node[func.getDeclaredField("arity").get(null)];
            for (int i = 0; i < children.length; i++) {
                children[i] = growTree(depth - 1, grow);
            }
            expr = nodeFactory.getFuncNodeInstance(func, children);
        }
        return expr;
    }

    public List<Node<T>> pickBranch(Node<T> tree) {
        List<Node<T>> branch = new ArrayList<>();
        Node<T> node = tree;
        branch.add(node);
        while (true) {
            if (node instanceof FunctionNode) {
//                Node<T>[] children = (Node<T>[]) ((FunctionNode<T>) node).getChildren();
//                int p = RNG.nextInt(0, children.length);
//                node = children[p];
                node = ((FunctionNode<T>) node).getRandomChild();
                branch.add(node);
            } else {
                break;
            }
        }
        return branch;
    }


    public void cutPartOfBranch(Node<T> tree) {
        int currentDepth = tree.getDepth();
        List<Node<T>> branch;
        while (true) {
            branch = pickBranch(tree);
            if (branch.size() > currentDepth / 2) {
                break;
            }
        }
        Node<T> leaf = growTree(1, true);
        int picked = RNG.nextInt(branch.size() / 2, branch.size());
        Node<T> pickedNode = branch.get(picked);
        Node<T> parentNode = branch.get(picked - 1);
        Node<T>[] parentChildren = ((FunctionNode<T>) parentNode).getChildren();
        int pickedIndex;
        for (pickedIndex = 0; pickedIndex < parentChildren.length; pickedIndex++) {
            if (pickedNode == parentChildren[pickedIndex]) {
                break;
            }
        }
        parentChildren[pickedIndex] = leaf;
    }

    public void fixNodesCount(Node<T> tree) {
        while (tree.getNodeCount() > MAX_NODE_COUNT) {
            cutPartOfBranch(tree);
        }
    }

    public INodeFactory<T> getNodeFactory() {
        return nodeFactory;
    }


    public static void test1() {
        BooleanDataset dataset = new BooleanDataset("data/f2.txt");

        String[] varNames = new String[]{"a", "b", "c", "d", "e"};
        TerminalNode[] terminals = TerminalNodeFactory.getTerminals(varNames);
        BoolNodeFactory nodeFactory = new BoolNodeFactory(terminals);

        // not ((a and b) or (c and d)) or e
        Node a_and_b = new AndNode(
                new Node[]{
                        nodeFactory.getTermNodeInstance(0),
                        nodeFactory.getTermNodeInstance(1)
                });
        Node c_and_d = new AndNode(
                new Node[]{
                        nodeFactory.getTermNodeInstance(2),
                        nodeFactory.getTermNodeInstance(3)
                });
        Node a_and_b_or_c_and_d = new OrNode(
                new Node[]{
                        a_and_b,
                        c_and_d
                });
        Node not_a_and_b_or_c_and_d = new NotNode(
                new Node[]{
                        a_and_b_or_c_and_d
                });
        Node<Boolean> expression = new OrNode(
                new Node[]{
                        not_a_and_b_or_c_and_d,
                        nodeFactory.getTermNodeInstance(4)
                });

        System.out.println(expression.toString());

        Boolean[] output = new Boolean[dataset.size()];
        for (int i = 0; i < dataset.size(); i++) {
//            childrenOutputVector[i] = expression.evaluate(Boolean.class, dataset.getInput(i));
            output[i] = expression.evaluate(dataset.getInput(i));
            boolean real = dataset.getOutput(i);
            System.out.println(output[i] + " " + real
                    + (output[i] == real ? "" : " x"));
        }
    }

    public static void test2() {
        BooleanDataset dataset = new BooleanDataset("data/f2.txt");

        String[] varNames = new String[]{"a", "b", "c", "d", "e"};
        TerminalNode<Boolean>[] terminals = (TerminalNode<Boolean>[]) TerminalNodeFactory.getTerminals(varNames);
        BoolNodeFactory nodeFactory = new BoolNodeFactory(terminals);
        TreeFactory<Boolean> treeFactory = new TreeFactory<>(nodeFactory);

        Node<Boolean> tree = treeFactory.growTree(5, false);
        System.out.println(tree.toString());
//
//        boolean[] childrenOutputVector = new boolean[dataset.size()];
//        for (int i = 0; i < dataset.size(); i++) {
//            childrenOutputVector[i] = tree.evaluate(dataset.getInput(i));
//            boolean real = dataset.getOutput(i);
//            if (childrenOutputVector[i] != real) {
//                System.out.println(childrenOutputVector[i] + " " + real);
//            }
//        }
    }

    public static void test3() {
        BooleanDataset dataset = new BooleanDataset("data/f2.txt");

        String[] varNames = new String[]{"a", "b", "c", "d", "e"};
        TerminalNode[] terminals = TerminalNodeFactory.getTerminals(varNames);
        BoolNodeFactory nodeFactory = new BoolNodeFactory(terminals);

        // not ((a and b) or (c and d)) or e
        Node<Boolean>[] children = (Node<Boolean>[]) new Node[2];
        children[0] = nodeFactory.getTermNodeInstance(0);
        children[1] = nodeFactory.getTermNodeInstance(1);
        AndNode expression = new AndNode(children);

        System.out.println(expression.toString());

        Boolean[] output = new Boolean[dataset.size()];
//        childrenOutputVector[0] = expression.evaluate(Boolean.class, dataset.getInput(0));
        output[0] = expression.evaluate(dataset.getInput(0));
//        for (int i = 0; i < dataset.size(); i++) {
//            childrenOutputVector[i] = expression.evaluate(dataset.getInput(i));
//            boolean real = dataset.getOutput(i);
//            System.out.println(childrenOutputVector[i] + " " + real
//                    + (childrenOutputVector[i] == real ? "" : " x"));
//        }

    }

    public static void main(String[] args) {
//        test1();

        test2();

//        test3();
    }

}
