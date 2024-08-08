
import java.io.*;
import java.util.Scanner;

class LogWriter {

    public void writeLog(String fileName, String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(logMessage);
            writer.newLine();
            System.out.println("Log message written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the log message: " + e.getMessage());
        }
    }
}

class LogReader {

    public void readLogs(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\nReading logs from file: " + fileName);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the logs: " + e.getMessage());
        }
    }
}

public class LogApp {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        LogWriter logWriter = new LogWriter();
        LogReader logReader = new LogReader();

        String fileName = "logs.txt";

        System.out.println("Enter log messages (type 'exit' to stop):");
        while (true) {
            System.out.print("Log: ");
            String logMessage = s.nextLine();
            if (logMessage.equalsIgnoreCase("exit")) {
                break;
            }
            logWriter.writeLog(fileName, logMessage);
        }

        logReader.readLogs(fileName);

        System.out.println("\nAttempting to read from a non-existent file:");
        logReader.readLogs("non_existent_file.txt");

        s.close();
    }
}
