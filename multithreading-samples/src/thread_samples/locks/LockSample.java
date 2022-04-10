package thread_samples.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSample {

    private static int counter = 0;

    private static final Lock lock = new ReentrantLock();

    private static void increment() {

        try {
            lock.lock();
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Thread thread1 = new Thread(LockSample::increment);

        final Thread thread2 = new Thread(LockSample::increment);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (final InterruptedException exception){
            exception.printStackTrace();
        }

        System.out.println(counter);
    }
}
