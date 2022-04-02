package thread_samples;

//dies after all other threads dies
class DaemonThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Daemon thread is running...");
        }
    }
}

class NormalThread implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Normal thread is finished...");
    }

}

public class DaemonSample {

    public static void main(String[] args) {
        final Thread daemon = new Thread(new DaemonThread());
        final Thread normal = new Thread(new NormalThread());

        daemon.setDaemon(true);

        daemon.start();
        normal.start();
    }

}
