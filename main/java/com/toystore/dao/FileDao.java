package com.toystore.dao;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public abstract class FileDao<T> {
    private final String fileName;

    public FileDao(String fileName) {
        this.fileName = fileName;
        initializeFile();
    }

    private void initializeFile() {
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize data file", e);
        }
    }

    protected abstract String convertToLine(T item);
    protected abstract T parseFromLine(String line);

    protected synchronized List<T> readAll() throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(this::parseFromLine)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected synchronized void writeAll(List<T> items) throws IOException {
        List<String> lines = items.stream()
                .map(this::convertToLine)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Files.write(Paths.get(fileName), lines, StandardOpenOption.TRUNCATE_EXISTING);
    }

    protected int getNextId(List<T> items) {
        return items.stream()
                .mapToInt(this::extractId)
                .max()
                .orElse(0) + 1;
    }

    protected abstract int extractId(T item);

    protected String escapeNull(String value) {
        return value == null ? "\\N" : value.replace(",", "\\,");
    }

    protected String unescapeNull(String value) {
        return "\\N".equals(value) ? null : value.replace("\\,", ",");
    }
}