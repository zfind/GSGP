package hr.fer.zemris.seminar.gsgp.tree.numeric;


import hr.fer.zemris.seminar.gsgp.tree.FunctionNode;
import hr.fer.zemris.seminar.gsgp.tree.INodeFactory;
import hr.fer.zemris.seminar.gsgp.tree.Node;
import hr.fer.zemris.seminar.gsgp.tree.TerminalNode;
import hr.fer.zemris.seminar.rng.RNG;

/**
 * Created by zac on 15.05.17.
 */
public class KornsNodeFactory implements INodeFactory<Double> {

    private static final int ADD = 0;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 3;
    private static final int SIN = 4;
    private static final int COS = 5;
    private static final int EXP = 6;
    private static final int LNA = 7;
    private static final int SQR = 8;
    private static final int SQRT = 9;
    private static final int CUB = 10;
    private static final int TAN = 11;
    private static final int TANH = 12;

    private TerminalNode<Double>[] terminals;
    private int[] arity;
    public final int terminalsCount;
    public final int functionsCount;
    public final double ratio;

    public KornsNodeFactory(TerminalNode<Double>[] terminals) {
        this.terminals = terminals;
        this.terminalsCount = terminals.length;
        this.functionsCount = 13;
        this.ratio = 0.5;

        this.arity = new int[functionsCount];
        arity[ADD] = 2;
        arity[SUB] = 2;
        arity[MUL] = 2;
        arity[DIV] = 2;
        arity[SIN] = 1;
        arity[COS] = 1;
        arity[EXP] = 1;
        arity[LNA] = 1;
        arity[SQR] = 1;
        arity[SQRT] = 1;
        arity[CUB] = 1;
        arity[TAN] = 1;
        arity[TANH] = 1;
    }

    public Node<Double> getTermNodeInstance(int id) {
        return terminals[id].clone();
    }

    @Override
    public Node<Double> getRandomTermNodeInstance() {
        int choice = RNG.nextInt(0, terminalsCount + 1);
        if (choice < terminalsCount) {
            return terminals[choice].clone();
        } else {
            return new EphemeralConstantNode(-1, 1);
        }
    }

    @Override
    public int chooseRandomFunction() {
        return RNG.nextInt(0, functionsCount);
    }

    @Override
    public int getArity(int funcId) {
        return arity[funcId];
    }

    @Override
    public Node<Double> getFuncNodeInstance(int choice, Node<Double>[] children) {
        switch (choice) {
            case ADD:
                return new AdditionNode(children);
            case SUB:
                return new SubtractionNode(children);
            case MUL:
                return new MultiplicationNode(children);
            case DIV:
                return new ProtectedDivisionNode(children);
            case SIN:
                return new SinNode(children);
            case COS:
                return new CosNode(children);
            case EXP:
                return new ExpNode(children);
            case LNA:
                return new LnAbsNode(children);
            case SQR:
                return new SqrNode(children);
            case SQRT:
                return new SqrtNode(children);
            case CUB:
                return new CubNode(children);
            case TAN:
                return new TanNode(children);
            case TANH:
                return new TanhNode(children);
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
    public Node<Double> swapWithSameArityFunction(FunctionNode<Double> node) {
        return null;
    }

    @Override
    public Node<Double>[] getTermNodes() {
        return terminals;
    }

}
