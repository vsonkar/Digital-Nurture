
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class LargeFileReader {

    public List<String> readLargeFile(String inputFileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return lines;
    }
}

class LargeFileWriter {

    public void writeProcessedData(String outputFileName, List<String> processedData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String line : processedData) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Processed data written to file: " + outputFileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

public class FileProcessorApp {

    public static void main(String[] args) {
        LargeFileReader reader = new LargeFileReader();
        LargeFileWriter writer = new LargeFileWriter();

        String inputFileName = "large_sales_data.txt";
        String outputFileName = "processed_sales_data.txt";

        List<String> salesRecords = reader.readLargeFile(inputFileName);
        List<String> processedData = processSalesData(salesRecords);

        writer.writeProcessedData(outputFileName, processedData);

        System.out.println("\nAttempting to read from a non-existent file:");
        reader.readLargeFile("non_existent_file.txt");

        System.out.println("\nAttempting to write to a restricted directory:");
        writer.writeProcessedData("/restricted_directory/output.txt", processedData);
    }

    private static List<String> processSalesData(List<String> salesRecords) {
        return salesRecords;
    }
}
