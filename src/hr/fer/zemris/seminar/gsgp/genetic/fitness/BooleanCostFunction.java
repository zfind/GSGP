package hr.fer.zemris.seminar.gsgp.genetic.fitness;

import hr.fer.zemris.seminar.gsgp.dataset.IDataset;
import hr.fer.zemris.seminar.gsgp.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac on 28.04.17.
 */
public class BooleanCostFunction implements IFitnessFunction<Boolean> {

    private IDataset<Boolean> dataset;

    public BooleanCostFunction(IDataset<Boolean> dataset) {
        this.dataset = dataset;
    }

    @Override
    public double evaluate(Node<Boolean> tree) {
        int counter = 0;
        for (int i = 0; i < dataset.size(); i++) {
//            Boolean output = tree.evaluate(Boolean.class, dataset.getInput(i));
            Boolean output = tree.evaluate(dataset.getInput(i));
            if (output != dataset.getOutput(i)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public List<Boolean> getSemantics(Node<Boolean> tree) {
        List<Boolean> semantics = new ArrayList<>();
        for (int i = 0; i < dataset.size(); i++) {
            Boolean output = tree.evaluate(dataset.getInput(i));
            semantics.add(output);
        }
        return semantics;
    }

    @Override
    public double evaluate(List<Boolean> semantics) {
        int counter = 0;
        for (int i = 0; i < dataset.size(); i++) {
            Boolean output = semantics.get(i);
            Boolean realOutput = dataset.getOutput(i);
            if (output != realOutput) {
                counter++;
            }
        }
        return counter;
    }
}
