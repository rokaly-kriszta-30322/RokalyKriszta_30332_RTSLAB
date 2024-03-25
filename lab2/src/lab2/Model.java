package lab2;

import java.util.Observable;

public class Model extends Observable{ // progress values for each thread
    int[] progressValues;

    public Model(int nrThreads) {
        progressValues = new int[nrThreads];
    }

    public synchronized void setProgressValue(int id, int val) { // thread safety
        progressValues[id] = val;
        setChanged(); // observable was changed
        notifyObservers(id); // notifies observer
    }
}
