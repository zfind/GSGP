package hr.fer.zemris.seminar.gsgp.genetic.fitness;

import hr.fer.zemris.seminar.gsgp.dataset.IDataset;
import hr.fer.zemris.seminar.gsgp.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac on 02.05.17.
 */
public class RMSECostFunction implements IFitnessFunction<Double> {

    private IDataset<Double> dataset;

    public RMSECostFunction(IDataset<Double> dataset) {
        this.dataset = dataset;
    }

    @Override
    public double evaluate(Node<Double> tree) {
        double sum = 0;
        for (int i = 0; i < dataset.size(); i++) {
            Double output = tree.evaluate(dataset.getInput(i));
            Double realOutput = dataset.getOutput(i);
            sum += Math.pow(output - realOutput, 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public List<Double> getSemantics(Node<Double> tree) {
        List<Double> semantics = new ArrayList<>();
        for (int i = 0; i < dataset.size(); i++) {
            Double output = tree.evaluate(dataset.getInput(i));
            semantics.add(output);
        }
        return semantics;
    }

    @Override
    public double evaluate(List<Double> semantics) {
        double sum = 0;
        for (int i = 0; i < dataset.size(); i++) {
            Double output = semantics.get(i);
            Double realOutput = dataset.getOutput(i);
            sum += Math.pow(output - realOutput, 2);
        }
        return Math.sqrt(sum);
    }

}
