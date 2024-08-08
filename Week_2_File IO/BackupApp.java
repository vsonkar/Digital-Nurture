
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class BackupApp {

    public static void copyFile(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully from " + source + " to " + target);
        } catch (IOException e) {
            System.err.println("An error occurred while copying the file from " + source + " to " + target + ": " + e.getMessage());
        }
    }

    public static void copyDirectory(Path sourceDir, Path targetDir) {
        try (Stream<Path> stream = Files.walk(sourceDir)) {
            stream.forEach(sourcePath -> {
                Path targetPath = targetDir.resolve(sourceDir.relativize(sourcePath));
                try {
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println("An error occurred while copying " + sourcePath + " to " + targetPath + ": " + e.getMessage());
                }
            });
            System.out.println("Directory copied successfully from " + sourceDir + " to " + targetDir);
        } catch (IOException e) {
            System.err.println("An error occurred while copying the directory from " + sourceDir + " to " + targetDir + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the source directory path: ");
        String sourceDirPath = s.nextLine();

        System.out.print("Enter the target directory path: ");
        String targetDirPath = s.nextLine();

        Path sourceDir = Paths.get(sourceDirPath);
        Path targetDir = Paths.get(targetDirPath);

        if (Files.isDirectory(sourceDir)) {
            copyDirectory(sourceDir, targetDir);
        } else {
            copyFile(sourceDir, targetDir);
        }

        s.close();
    }
}
