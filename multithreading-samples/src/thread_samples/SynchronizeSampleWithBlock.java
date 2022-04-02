package thread_samples;

public class SynchronizeSampleWithBlock {

    public static int counter1;
    public static int counter2;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();


    synchronized static private void  incrementFirst() {
        synchronized (lock1) {
            counter1++;
        }
    }

    synchronized static private void  incrementSecond(){
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void process(){
        final Thread worker1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                incrementFirst();
                incrementSecond();
            }
        });

        final Thread worker2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                incrementFirst();
                incrementSecond();
            }
        });

        worker1.start();
        worker2.start();

        try {
            worker1.join();
            worker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter 1: " + counter1);
        System.out.println("Counter 2: " + counter2);
    }

    public static void main(String[] args) {
        process();
    }

}
