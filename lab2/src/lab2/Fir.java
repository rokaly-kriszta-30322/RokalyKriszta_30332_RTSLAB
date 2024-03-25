package lab2;

public class Fir extends Thread { // controller
    Model model;
    int id;
    Window win;
    int processorLoad;

    Fir(Model model, Window win, int id, int procLoad){ // connects everything

        this.model=model;
        this.win=win;
        this.id=id;
        this.processorLoad=procLoad;

    }

    public void run(){ // actual progress
        int c=0;
        while(c<1000){
            for(int j=0;j<this.processorLoad;j++){
                j++;j--;
            }
            c++;
            model.setProgressValue(id,c);

            try {
                Thread.sleep(10); // delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
