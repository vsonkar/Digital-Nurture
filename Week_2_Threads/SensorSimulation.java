
import java.util.Random;

public class SensorSimulation {

    public static void main(String[] args) {
        int numberOfSensors = 5;

        Thread[] sensorThreads = new Thread[numberOfSensors];

        for (int i = 0; i < numberOfSensors; i++) {
            sensorThreads[i] = new Thread(new Sensor(i + 1));
            sensorThreads[i].start();
        }

        try {
            for (Thread sensorThread : sensorThreads) {
                sensorThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All sensor threads have completed.");
    }
}

class Sensor implements Runnable {

    private final int sensorId;
    private final Random random = new Random();

    public Sensor(int sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Sensor " + sensorId + " data: " + random.nextInt(100));
                Thread.sleep(1000); // Wait for 1 second before collecting new data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
