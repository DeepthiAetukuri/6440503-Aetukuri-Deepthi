class MessagePrinter implements Runnable {
    private String message;
    private int times;
    public MessagePrinter(String message, int times) {
        this.message = message;
        this.times = times;
    }
    @Override
    public void run() {
        for (int i = 1; i <= times; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + message + " (" + i + ")");
            try {
                Thread.sleep(500);  // Sleep 500ms for readability
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}

public class ThreadCreationDemo {
    public static void main(String[] args) {
        Runnable printer1 = new MessagePrinter("Hello from Thread 1", 5);
        Runnable printer2 = new MessagePrinter("Hello from Thread 2", 5);
        Thread thread1 = new Thread(printer1, "Thread-1");
        Thread thread2 = new Thread(printer2, "Thread-2");
        thread1.start();
        thread2.start();
    }
}
