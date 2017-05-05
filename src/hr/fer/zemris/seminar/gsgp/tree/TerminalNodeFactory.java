package hr.fer.zemris.seminar.gsgp.tree;

/**
 * Created by zac on 26.04.17.
 */
public class TerminalNodeFactory {

    public static TerminalNode[] getTerminals(String[] map) {
        TerminalNode[] terminals = new TerminalNode[map.length];
        for (int i = 0; i < terminals.length; i++) {
            terminals[i] = new TerminalNode(i, map[i]);
        }
        return terminals;
    }

    public static TerminalNode[] getTerminals(int numOfTerminals) {
        TerminalNode[] terminals = new TerminalNode[numOfTerminals];
        for (int i = 0; i < terminals.length; i++) {
            terminals[i] = new TerminalNode(i);
        }
        return terminals;
    }

}
