package hr.fer.zemris.seminar.gsgp.dataset;

/**
 * Created by zac on 26.04.17..
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zac on 12.12.16.
 */
public class BooleanDataset implements IDataset<Boolean>, Iterable<BooleanSample> {
    private final List<BooleanSample> samples;
    private final int inputDimension;

    public BooleanDataset(String filename) {
        samples = new ArrayList<>();

        List<int[]> tmp = parse(filename);

        inputDimension = tmp.get(0).length - 1;

        for (int[] xx : tmp) {
//            int[] input = new int[inputDimension];
            Boolean[] input = new Boolean[inputDimension];
            for (int i = 0; i < inputDimension; i++) {
                input[i] = xx[i] == 1;
            }
            Boolean output = xx[inputDimension] == 1;
            samples.add(new BooleanSample(input, output));
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
    public ISample<Boolean> getSample(int index) {
        return samples.get(index);
    }

    @Override
    public Boolean[] getInput(int index) {
        return samples.get(index).getInput();
    }

    @Override
    public Boolean getOutput(int index) {
        return samples.get(index).getOutput();
    }

    @Override
    public Iterator<BooleanSample> iterator() {
        return samples.iterator();
    }

    private static List<int[]> parse(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<int[]> dataset = new ArrayList<>();

        try {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                //System.out.println(line);
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] xx = line.split("\\s+");
                    int[] value = new int[xx.length];
                    for (int i = 0; i < value.length; i++) {
                        value[i] = Integer.parseInt(xx[i]);
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

}