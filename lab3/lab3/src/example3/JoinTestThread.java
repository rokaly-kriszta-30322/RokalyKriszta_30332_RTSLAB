package example3;

class JoinTestThread extends Thread{

    private final String n;
    Thread t;
    int num;

    JoinTestThread(String n, Thread t,int num){

        this.n=n;
        this.t=t;
        this.num=num;

    }

    public void run() {

        System.out.println("Thread "+n+" has entered the run() method");

        try {

            if (t != null) t.join();
            System.out.println("Thread "+n+" executing operation.");
            for (int i=1;i<=num/2;i++){
                if(num%i==0){
                    Main.variable=Main.variable+i;
                }
            }
            Main.variable=Main.variable+num;
            System.out.println(Main.variable);
            Thread.sleep(3000);
            System.out.println("Thread "+n+" has terminated operation.");

        }

        catch(Exception e){e.printStackTrace();}

    }

}
