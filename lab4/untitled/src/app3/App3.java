package app3;

public class App3 {
    public static void main(String[] args) {
        Integer monitor = new Integer(1);

        new ExecutionThread(monitor, null, 5, 5, 3, 6).start();
        new ExecutionThread(monitor, null,6, 6, 5, 7).start();
        new ExecutionThread(monitor, null, 3, 3, 4, 7).start();
    }
}

class ExecutionThread extends Thread {
    Integer monitor;
    Integer monitor2;
    int sleep_min, sleep_max, activity_min, activity_max;

    public ExecutionThread(Integer monitor, Integer monitor2, int sleep_min, int sleep_max, int activity_min, int activity_max) {
        this.monitor = monitor;
        this.monitor2 = monitor2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }
    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");
            synchronized (monitor) {
                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                System.out.println(this.getName() + " - STATE 2");
            }
            System.out.println(this.getName() + " - STATE 3");

            try {
                Thread.sleep( sleep_max * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " - STATE 4");

        }
    }
}
