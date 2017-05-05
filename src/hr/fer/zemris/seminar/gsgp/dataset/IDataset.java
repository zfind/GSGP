package hr.fer.zemris.seminar.gsgp.dataset;

/**
 * Created by zac on 30.04.17.
 */
public interface IDataset<T> {

    int size();

    int getInputDimension();

    ISample<T> getSample(int index);

    T[] getInput(int index);

    T getOutput(int index);

}
