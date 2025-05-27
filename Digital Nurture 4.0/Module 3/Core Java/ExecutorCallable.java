import java.util.concurrent.*;
import java.util.*;

public class ExecutorCallable {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // List to hold Future objects associated with Callable tasks
        List<Future<String>> futures = new ArrayList<>();

        // Submit 5 Callable tasks that return a String result
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            Callable<String> task = () -> {
                // Simulate some work with sleep
                Thread.sleep(1000);
                return "Result from task " + taskId;
            };

            Future<String> future = executor.submit(task);
            futures.add(future);
        }

        // Retrieve and print results from futures
        for (Future<String> future : futures) {
            try {
                // This will block until the result is available
                String result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Shutdown the executor service gracefully
        executor.shutdown();
    }
}
