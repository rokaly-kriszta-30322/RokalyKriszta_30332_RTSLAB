package app4;

import java.util.concurrent.CountDownLatch;

public class App4 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        Integer monitor = new Integer(1);
        Integer monitor2 = new Integer(1);

        ExecutionThread thread1 = new ExecutionThread(null, null, 7, 7, 2, 3,latch);
        ExecutionThread thread2 = new ExecutionThread(monitor, null,0, 0, 3, 5,latch);
        ExecutionThread thread3 = new ExecutionThread(null, monitor2, 0, 0, 4, 6,latch);

        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    Integer monitor;
    Integer monitor2;
    int sleep_min, sleep_max, activity_min, activity_max;
    CountDownLatch latch;

    boolean ready = false;

    public ExecutionThread(Integer monitor, Integer monitor2, int sleep_min, int sleep_max, int activity_min, int activity_max,CountDownLatch latch) {
        this.monitor = monitor;
        this.monitor2 = monitor2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.latch = latch;
    }
    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        if(monitor==null && monitor2==null){
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
            System.out.println(this.getName() + " - STATE 3");
        } else if(monitor!=null){
            synchronized (monitor) {
                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                ready = true;
                System.out.println(this.getName() + " - STATE 3");
            }
        } else if(monitor2!=null){
            synchronized (monitor2) {
                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                ready = true;
                System.out.println(this.getName() + " - STATE 3");
            }
        }
        latch.countDown();
    }
}