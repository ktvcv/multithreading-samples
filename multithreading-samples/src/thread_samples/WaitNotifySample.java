package thread_samples;

class MyProcess {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the produce method...");
            // release the intrinsic lock
            wait();
            System.out.println("Again in the produce method...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Running the consume method...");
            // notify thread in wait state that it can continue it's work
            notify();

            Thread.sleep(3000);
        }
    }
}

public class WaitNotifySample {

    public static void main(String[] args) {
        final MyProcess process = new MyProcess();

        final Thread worker1 = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final Thread worker2 = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        worker1.start();
        worker2.start();
    }
}
