package com.toystore.util;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
    // Prevent instantiation (utility class)
    private FileUtil() {}

    /**
     * Reads all lines from a file and returns them as a List<String>.
     * Creates the file if it doesn't exist.
     */
    public static List<String> readAllLines(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
            return java.util.Collections.emptyList();
        }
        return Files.readAllLines(path);
    }

    /**
     * Writes lines to a file (overwrites existing content).
     */
    public static void writeAllLines(String filePath, List<String> lines) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Appends a single line to a file.
     */
    public static void appendLine(String filePath, String line) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, (line + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    /**
     * Deletes a line containing specific text from a file.
     */
    public static void deleteLine(String filePath, String searchText) throws IOException {
        List<String> lines = readAllLines(filePath).stream()
                .filter(line -> !line.contains(searchText))
                .collect(Collectors.toList());
        writeAllLines(filePath, lines);
    }

    /**
     * Updates a line matching oldContent with newContent.
     */
    public static void updateLine(String filePath, String oldContent, String newContent) throws IOException {
        List<String> lines = readAllLines(filePath).stream()
                .map(line -> line.equals(oldContent) ? newContent : line)
                .collect(Collectors.toList());
        writeAllLines(filePath, lines);
    }

    /**
     * Ensures a directory exists (creates if missing).
     */
    public static void ensureDirectoryExists(String dirPath) throws IOException {
        Path path = Paths.get(dirPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}