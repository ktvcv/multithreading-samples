package thread_samples;

public class SynchronizeSample {

    public static int counter;

    synchronized static private void  increment(){
    counter++;
    }

    public static void process(){
        final Thread worker1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
               increment();
            }
        });

        final Thread worker2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                increment();
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

        System.out.println("Counter: " + counter);
    }

    public static void main(String[] args) {
        process();
    }

}
