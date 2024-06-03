package App3;

import java.util.concurrent.CountDownLatch;

public class App3 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        Object monitor = new Object();
        Object monitor2 = new Object();

        int x = 5;
        int y = 5;

        ExecutionThread thread1 = new ExecutionThread(monitor, monitor2, 7, 7, 2, 3, latch);
        ExecutionThread2 thread2 = new ExecutionThread2(monitor, null, y, y, 3, 5, latch);
        ExecutionThread2 thread3 = new ExecutionThread2(null, monitor2, x, x, 4, 6, latch);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            latch.await();
            System.out.println("All threads completed execution");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ExecutionThread extends Thread {
    Object monitor;
    Object monitor2;
    int sleep_min, sleep_max, activity_min, activity_max;
    CountDownLatch latch;

    public ExecutionThread(Object monitor, Object monitor2, int sleep_min, int sleep_max, int activity_min, int activity_max, CountDownLatch latch) {
        this.monitor = monitor;
        this.monitor2 = monitor2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        try {
            Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        if (monitor != null) {
            synchronized (monitor) {
                monitor.notifyAll();
            }
        }

        if (monitor2 != null) {
            synchronized (monitor2) {
                monitor2.notifyAll();
            }
        }

        System.out.println(this.getName() + " - STATE 3");
        latch.countDown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ExecutionThread2 extends Thread {
    Object monitor;
    Object monitor2;
    int sleep_min, sleep_max, activity_min, activity_max;
    CountDownLatch latch;

    public ExecutionThread2(Object monitor, Object monitor2, int sleep_min, int sleep_max, int activity_min, int activity_max, CountDownLatch latch) {
        this.monitor = monitor;
        this.monitor2 = monitor2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        try {
            Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
            if (monitor != null) {
                synchronized (monitor) {
                    monitor.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        System.out.println(this.getName() + " - STATE 3");
        latch.countDown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
