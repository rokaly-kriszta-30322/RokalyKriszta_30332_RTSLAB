package app2;

public class App2 {
    public static void main(String[] args) {
        Integer monitor = new Integer(1);
        Integer monitor2 = new Integer(1);
        ExecutionThread thread1 = new ExecutionThread(monitor, null, 4, 4, 4, 6);
        ExecutionThread thread2 = new ExecutionThread(null, monitor2,5, 5, 5, 7);
        thread1.setDependency(thread2);
        thread2.setDependency(thread1);
        thread1.start();
        thread2.start();
    }
}

class ExecutionThread extends Thread {
    Integer monitor;
    Integer monitor2;
    int sleep_min, sleep_max, activity_min, activity_max;

    ExecutionThread dependency;
    boolean dependencyMet = false;

    Object lock = new Object();

    public ExecutionThread(Integer monitor, Integer monitor2, int sleep_min, int sleep_max, int activity_min, int activity_max) {
        this.monitor = monitor;
        this.monitor2 = monitor2;
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

        synchronized (lock) {
            if (this.monitor != null) {
                synchronized (this.monitor) {
                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                }
            } else if (this.monitor2 != null) {
                synchronized (this.monitor2) {
                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                }
            }
        }

        dependency.notifyDependency();

        synchronized (lock) {

            while (!dependency.dependencyMet) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(this.getName() + " - STATE 3");
        try {
            Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " - STATE 4");
    }
    public void notifyDependency() {
        synchronized (lock) {
            dependency.dependencyMet = true;
            lock.notify();
        }
    }
}