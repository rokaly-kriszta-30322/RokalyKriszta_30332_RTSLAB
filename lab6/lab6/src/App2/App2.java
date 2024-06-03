package App2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class App2 {
    public static void main(String[] args) {
        Integer monitor = new Integer(1);
        Integer monitor2 = new Integer(1);
        CountDownLatch latch = new CountDownLatch(3);
        Semaphore semaphore = new Semaphore(1);

        ExecutionThread thread1 = new ExecutionThread(latch, semaphore, 1, monitor, null, 4, 4, 2, 4);
        ExecutionThread thread2 = new ExecutionThread(latch, semaphore, 2, monitor, monitor2, 3, 3, 3, 6);
        ExecutionThread thread3 = new ExecutionThread(latch, semaphore, 3, null, monitor2, 5, 5, 2, 5);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ExecutionThread extends Thread {
    private CountDownLatch latch;
    private Semaphore semaphore;
    private Integer monitor;
    private Integer monitor2;
    private int sleep_min, sleep_max, activity_min, activity_max;

    public ExecutionThread(CountDownLatch latch, Semaphore semaphore, int threadNum, Integer monitor, Integer monitor2, int sleep_min, int sleep_max, int activity_min, int activity_max) {
        this.latch = latch;
        this.semaphore = semaphore;
        this.monitor = monitor;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.monitor2 = monitor2;
        setName("Thread-" + threadNum);
    }

    public void run() {
        try {
            latch.countDown();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println(this.getName() + " - STATE 1");

            if (monitor != null && monitor2 != null) {
                synchronized (monitor) {
                    synchronized (monitor2) {
                        executeState2();
                    }
                }
            } else if (monitor != null && monitor2 == null) {
                synchronized (monitor) {
                    executeState2();
                }
            } else if (monitor == null && monitor2 != null) {
                synchronized (monitor2) {
                    executeState2();
                }
            }

            System.out.println(this.getName() + " - STATE 3");

            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            semaphore.release();
        }
    }

    private void executeState2() {
        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        try {
            Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
