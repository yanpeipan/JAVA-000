import java.util.concurrent.*;

public class FutureSync implements Callable<Long> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Long> ans = es.submit(new FutureSync());

        System.out.println(ans.get());
    }

    public Long call() throws Exception {
        return fibo(40);
    }

    private long fibo(int i) {
        if (i < 2) {
            return 1;
        }
        return fibo(i - 1) + fibo(i - 2);
    }
}
