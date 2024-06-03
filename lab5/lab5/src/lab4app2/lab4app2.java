package lab4app2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class lab4app2 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        ExecutionThread thread1 = new ExecutionThread(4, 4, 2, 4, 4, 6, lock, lock2);
        ExecutionThread thread2 = new ExecutionThread(3, 5, 5, 5, 5, 7, lock2, lock);

        thread1.start();
        thread2.start();
    }
}

class ExecutionThread extends Thread {
    private int sleep_min, sleep_max, activity_min, activity_max, activity_min2, activity_max2;
    private Lock lock, lock2;

    public ExecutionThread(int sleep_min, int sleep_max, int activity_min, int activity_max, int activity_min2, int activity_max2, Lock lock, Lock lock2) {
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.activity_min2 = activity_min2;
        this.activity_max2 = activity_max2;
        this.lock = lock;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        if (lock.tryLock()) {
            try {
                System.out.println(this.getName() + " - STATE 2");

                k = (int) Math.round(Math.random() * (activity_max2 - activity_min2) + activity_min2);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }

                if (lock2.tryLock()) {
                    try {
                        System.out.println(this.getName() + " - STATE 3");

                        try {
                            Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } finally {
                        lock2.unlock();
                        System.out.println(this.getName() + " - STATE 4");
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

}

