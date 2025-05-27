public class VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        int count = 100_000;

        // Start time
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[count];

        // Launch 100,000 virtual threads
        for (int i = 0; i < count; i++) {
            threads[i] = Thread.startVirtualThread(() -> {
                System.out.println("Hello from virtual thread!");
            });
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join();
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("Virtual Threads completed in " + duration + " ms");
    }
}
