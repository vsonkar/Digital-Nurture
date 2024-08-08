
public class ThreadStateLogger {

    public static void main(String[] args) {
        Thread taskThread = new Thread(() -> {
            try {
                System.out.println("Thread state during execution: " + Thread.currentThread().getState());
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Number: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Thread state before starting: " + taskThread.getState());

        // Start the thread
        taskThread.start();

        try {
            Thread.sleep(100);
            System.out.println("Thread state after starting: " + taskThread.getState());
            taskThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread state after completion: " + taskThread.getState());
    }
}
