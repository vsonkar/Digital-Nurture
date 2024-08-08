
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue(10);

        Thread producer1 = new Thread(new Producer(dataQueue), "Producer-1");
        Thread producer2 = new Thread(new Producer(dataQueue), "Producer-2");
        producer1.start();
        producer2.start();

        Thread consumer1 = new Thread(new Consumer(dataQueue), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(dataQueue), "Consumer-2");
        consumer1.start();
        consumer2.start();
    }
}

class DataQueue {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public DataQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int data) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(data);
        System.out.println(Thread.currentThread().getName() + " produced: " + data);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int data = queue.poll();
        System.out.println(Thread.currentThread().getName() + " consumed: " + data);
        notifyAll();
        return data;
    }
}

class Producer implements Runnable {

    private final DataQueue dataQueue;

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i <= 40) {
                int data = (int) (Math.random() * 100);
                dataQueue.produce(data);
                Thread.sleep((int) (Math.random() * 1000));
                i++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {

    private final DataQueue dataQueue;

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                dataQueue.consume();
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
