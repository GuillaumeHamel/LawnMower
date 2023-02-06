package org.example;

import org.example.fileUtils.FileHandler;
import org.example.fileUtils.LawnParser;
import org.example.fileUtils.MowerFormatter;

public class Main {
    private static final String filePath = "src/main/resources/example.txt";

    public static void main(String[] args) {
        final var initialLawn = LawnParser.parseToLawn(FileHandler.readLines(filePath));
        final var finalLawn = initialLawn.mow();
        FileHandler.writeToFile(MowerFormatter.mowerToString(finalLawn), filePath.split("\\.")[0] + "[result].txt");
    }

}
