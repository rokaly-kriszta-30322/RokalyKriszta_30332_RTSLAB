package lab7app4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class app4 {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        ExecutionThread thread1 = new ExecutionThread( 5, 5, 3, 6,semaphore);
        ExecutionThread thread2 = new ExecutionThread(3, 3, 4, 7,semaphore);
        ExecutionThread thread3 = new ExecutionThread(6, 6, 5, 7,semaphore);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class ExecutionThread extends Thread {
    Semaphore semaphore;
    int sleep_min, sleep_max, activity_min, activity_max;

    public ExecutionThread(int sleep_min, int sleep_max, int activity_min, int activity_max, Semaphore semaphore) {
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.semaphore = semaphore;
    }

    public void run() {
        while(true) {
            try {

                System.out.println(this.getName() + " - STATE 1");

                try {
                    this.semaphore.acquire(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }

                this.semaphore.release(2);

                System.out.println(this.getName() + " - STATE 3");

                try {
                    Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(this.getName() + " - STATE 4");
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
