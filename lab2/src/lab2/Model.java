package lab2;

import java.util.Observable;

public class Model extends Observable{
    int[] progressValues;

    public Model(int nrThreads) {
        progressValues = new int[nrThreads];
    }

    public synchronized void setProgressValue(int id, int val) {
        progressValues[id] = val;
        setChanged();
        notifyObservers(id);
    }
}
