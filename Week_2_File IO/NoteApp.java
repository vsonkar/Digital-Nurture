
import java.io.*;
import java.util.Scanner;

class FileWriterDemo {

    public void saveNoteToFile(String fileName, String note) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(note);
            System.out.println("Note saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the note: " + e.getMessage());
        }
    }
}

class FileReaderDemo {

    public void readNoteFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Reading note from file: " + fileName);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the note: " + e.getMessage());
        }
    }
}

public class NoteApp {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter your note: ");
        String note = s.nextLine();

        FileWriterDemo fileWriterDemo = new FileWriterDemo();
        String fileName = "note.txt";
        fileWriterDemo.saveNoteToFile(fileName, note);

        FileReaderDemo fileReaderDemo = new FileReaderDemo();
        fileReaderDemo.readNoteFromFile(fileName);
        s.close();
    }
}
