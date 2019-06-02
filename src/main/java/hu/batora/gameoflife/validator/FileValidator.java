package hu.batora.gameoflife.validator;

import java.util.List;

public class FileValidator {

    public static void checkVersion(List<String> lines) {
        String firstLine = lines.get(0);
        if (!firstLine.startsWith("#Life 1.05"))
            throw new IllegalArgumentException("Only life 1.05 format is supported!");
    }
}
