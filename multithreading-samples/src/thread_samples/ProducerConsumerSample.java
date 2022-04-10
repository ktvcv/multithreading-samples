package thread_samples;

import java.util.ArrayList;
import java.util.List;

class Process {

    private final List<Integer> list = new ArrayList<>();
    private static final int MAX_LIMIT = 5;
    private static final int MIN_LIMIT = 0;
    private int value = 0;

    private final Object lock = new Object();

    public void produce() throws InterruptedException {

        synchronized (lock) {

            while (true) {
                if (list.size() == MAX_LIMIT) {
                    System.out.println("Waiting for removing item from queue");

                    lock.wait();
                } else {
                    System.out.println("Adding " + value);
                    list.add(value);
                    value++;

                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {

            while (true) {
                if (list.size() == MIN_LIMIT) {
                    System.out.println("Waiting for adding item from queue");

                    lock.wait();
                } else {
                    System.out.println("Removing " + list.remove(list.size() - 1));

                    lock.notify();
                }
                Thread.sleep(500);
            }
        }

    }
}

public class ProducerConsumerSample {
    public static void main(String[] args) {
        final Process process = new Process();

        final Thread producer = new Thread(() -> {
            try {
                process.produce();
            } catch (final InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        final Thread consumer = new Thread(() -> {
            try {
                process.consume();
            } catch (final InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}

