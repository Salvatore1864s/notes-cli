package com.example;

import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<>();

        for (String arg : args) {
            if (arg.startsWith("--")) {
                String[] parts = arg.substring(2).split("=", 2);
                params.put(parts[0], parts.length > 1 ? parts[1] : "");
            }
        }

        String cmd = params.get("cmd");

        if ("add".equals(cmd)) {
            NotesStore.add(params.get("text"));
        } else if ("list".equals(cmd)) {
            var notes = NotesStore.readAll();
            if (notes.isEmpty()) {
                System.out.println("(empty)");
            } else {
                for (String n : notes) {
                    System.out.println(n);
                }
            }
        } else {
            System.out.println("Unknown command");
        }
    }
}
