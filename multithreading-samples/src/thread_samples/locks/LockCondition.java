package thread_samples.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void produce() throws InterruptedException{
        try {
            lock.lock();
            System.out.println("Producer method");
            //wait
            condition.await();
            System.out.println("Again producer method");
        } finally {
            lock.unlock();
        }

    }

    public void consume() throws InterruptedException{
        try {
            Thread.sleep(2000);
            lock.lock();
            System.out.println("Consumer method");
            Thread.sleep(3000);
            //notify
            condition.signal();
        } finally {
            lock.unlock();
        }

    }
}

public class LockCondition {

    public static void main(String[] args) {
        final Worker worker = new Worker();

        final Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }

    }
}
