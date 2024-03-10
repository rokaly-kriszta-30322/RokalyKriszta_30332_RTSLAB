package lab2;

public class Main {
    private static final int noOfThreads=6;
    private static final int processorLoad=1000000;

    public static void main(String args[]){
        Model model=new Model(noOfThreads);
        Window win=new Window(noOfThreads);
        model.addObserver(win);

        for (int i = 0; i < noOfThreads; i++) {
            Fir controller = new Fir(model, win, i, processorLoad);
            controller.start();
        }

    }
}