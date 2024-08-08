
public class TaskScheduler {

    public static void main(String[] args) {
        // Create and start task threads
        Thread task1 = new Thread(new Task("Task 1"));
        Thread task2 = new Thread(new Task("Task 2"));
        Thread task3 = new Thread(new Task("Task 3"));

        task1.start();
        task2.start();

        try {
            task1.join();
            task3.start();

            task2.join();
            task3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks are finished.");
    }
}

class Task implements Runnable {

    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started.");

        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(taskName + " is working... Step " + (i + 1));
                Thread.sleep(1000);

                System.out.println(taskName + " yielding...");
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(taskName + " finished.");
    }
}
