package org.example.fileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class FileHandler {
    public static List<String> readLines(final String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(final List<String> fileContent, final String fileName) {
        try {
            Files.write(Paths.get(fileName), fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
