import java.util.concurrent.atomic.AtomicLong;

public class Reordering {
    private static boolean ready;
    private static int number;
    private static volatile boolean isReordering = false;
    private static AtomicLong atomicLong = new AtomicLong(0);

    private static class ReaderThread extends Thread {
        public void run() {

            while (!ready) {
                System.out.println("X" + atomicLong.incrementAndGet());

                Thread.yield();
            }
            int t = number + number;
            if (number == 0) {
                isReordering = true;
                throw new RuntimeException("0");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        while (!isReordering) {
            
            Thread thread = new ReaderThread();
            thread.start();

            number = 42;
            ready = true;
        }
    }
}