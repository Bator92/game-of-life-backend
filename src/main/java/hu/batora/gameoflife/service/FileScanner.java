package hu.batora.gameoflife.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileScanner {

    public static List<String> scanLines(InputStream inputStream) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }
}
