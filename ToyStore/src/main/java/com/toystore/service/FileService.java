package com.toystore.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private static final String DATA_DIR = "data";
    private static final String TOY_FILE = "toys.txt";

    /**
     * Constructor that ensures data directory exists
     */
    public FileService() {
        // Create data directory if it doesn't exist
        try {
            Path dataPath = Paths.get(DATA_DIR);
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }
        } catch (IOException e) {
            System.err.println("Error creating data directory: " + e.getMessage());
        }
    }

    /**
     * Reads toy data from file
     * @return list of toy data lines
     */
    public List<String> readToyFile() {
        List<String> lines = new ArrayList<>();
        Path filePath = Paths.get(DATA_DIR, TOY_FILE);

        // Create file if it doesn't exist
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.err.println("Error creating toy file: " + e.getMessage());
                return lines;
            }
        }

        // Read lines from file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading toy file: " + e.getMessage());
        }

        return lines;
    }

    /**
     * Writes toy data to file
     * @param lines the data lines to write
     */
    public void writeToyFile(List<String> lines) {
        Path filePath = Paths.get(DATA_DIR, TOY_FILE);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing toy file: " + e.getMessage());
        }
    }


}