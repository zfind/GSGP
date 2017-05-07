package hr.fer.zemris.seminar.gsgp.dataset;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zac on 02.05.17.
 */
public class NumericDataset implements IDataset<Double>, Iterable<NumericSample> {

    private final List<NumericSample> samples;
    private final int inputDimension;

    public NumericDataset(String filename) {
        samples = new ArrayList<>();

        List<double[]> tmp = parse(filename);

        inputDimension = tmp.get(0).length - 1;

        for (double[] xx : tmp) {
            Double[] input = new Double[inputDimension];
            for (int i = 0; i < inputDimension; i++) {
                input[i] = xx[i];
            }
            Double output = xx[inputDimension];
            samples.add(new NumericSample(input, output));
        }
    }

    @Override
    public int size() {
        return samples.size();
    }

    @Override
    public int getInputDimension() {
        return inputDimension;
    }

    @Override
    public ISample<Double> getSample(int index) {
        return samples.get(index);
    }

    @Override
    public Double[] getInput(int index) {
        return samples.get(index).getInput();
    }

    @Override
    public Double getOutput(int index) {
        return samples.get(index).getOutput();
    }

    @Override
    public Iterator<NumericSample> iterator() {
        return samples.iterator();
    }

    private static List<double[]> parse(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<double[]> dataset = new ArrayList<>();

        try {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                //System.out.println(line);
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] xx = line.split("\\s+");
                    double[] value = new double[xx.length];
                    for (int i = 0; i < value.length; i++) {
                        value[i] = Double.parseDouble(xx[i]);
                    }
                    dataset.add(value);
                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    public static void main(String[] args) {
        NumericDataset dataset = new NumericDataset("data/n1.txt");

        for (NumericSample sample : dataset) {
            System.out.println(sample.toString());
        }

    }

}
