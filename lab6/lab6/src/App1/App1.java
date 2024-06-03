package App1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App1 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Lock monitor1 = new ReentrantLock();
        Lock monitor2 = new ReentrantLock();
        CyclicBarrier startBarrier = new CyclicBarrier(3);
        while(true) {
            ExecutionThread thread1 = new ExecutionThread(monitor1, monitor2, startBarrier, 4, 4, 4, 6);
            ExecutionThread thread2 = new ExecutionThread(monitor2, monitor1, startBarrier, 5, 5, 5, 7);

            thread1.start();
            thread2.start();
            startBarrier.await();
            startBarrier.reset();
        }
    }
}

class ExecutionThread extends Thread {
    Lock ownMonitor;
    Lock otherMonitor;
    CyclicBarrier startBarrier;
    int sleep_min, sleep_max, activity_min, activity_max;

    ExecutionThread dependency;

    public ExecutionThread(Lock ownMonitor, Lock otherMonitor, CyclicBarrier startBarrier, int sleep_min, int sleep_max, int activity_min, int activity_max) {
        this.ownMonitor = ownMonitor;
        this.otherMonitor = otherMonitor;
        this.startBarrier = startBarrier;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public void setDependency(ExecutionThread dependency) {
        this.dependency = dependency;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        ownMonitor.lock();

            System.out.println(this.getName() + " - STATE 2");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }


        if(otherMonitor.tryLock()) {
            try {
                System.out.println(this.getName() + " - STATE 3");
                try {
                    Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                otherMonitor.unlock();
            }
        }

        ownMonitor.unlock();
        System.out.println(this.getName() + " - STATE 4");

        try {
            startBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
