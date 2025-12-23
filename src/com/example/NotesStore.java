package com.example;

import java.io.*;
import java.util.*;

public class NotesStore {
    private static final String FILE_PATH = "data/notes.csv";

    public static void add(String text) throws IOException {
        List<String> notes = readAll();
        int id = notes.size() + 1;
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(id + ";" + text + "\n");
        }
    }

    public static List<String> readAll() throws IOException {
        List<String> result = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return result;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        return result;
    }


    public static void remove(int id) throws IOException {
        List<String> notes = readAll();
        if (id <= 0 || id > notes.size()) {
            System.out.println("Note with id " + id + " does not exist");
            return;
            }
        notes.remove(id - 1); // удаляем по индексу
        // Перезаписываем файл
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (int i = 0; i < notes.size(); i++) {
                fw.write((i + 1) + ";" + notes.get(i).split(";", 2)[1] + "\n");
            }
        }
    }
}
