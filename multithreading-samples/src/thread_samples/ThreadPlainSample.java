package thread_samples;

class Worker implements Runnable {
    private final int number;

    Worker(final int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("worker %d: %d%n", number, i);
        }
    }
}

class WorkerThread extends Thread {
    private final int number;

    WorkerThread(final int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("worker %d: %d%n", number, i);
        }
    }
}

public class ThreadPlainSample {

    public static void main(String[] args) {
        final Thread worker1 = new Thread(new Worker(1));
        final Thread worker2 = new WorkerThread(2);

        worker2.setDaemon(true);

        worker1.start();
        worker2.start();

        try {
            worker1.join();
            worker2.join();
        } catch (final InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("The end");

    }
}
