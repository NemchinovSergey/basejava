package com.nsergey.basejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.nsergey.basejava.model.Resume;
import com.nsergey.basejava.storage.ArrayStorage;
import com.nsergey.basejava.storage.Storage;

/**
 * Interactive test for ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Введите одну из команд - (list | save name | delete uuid | get uuid | update uuid fullName | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String value = null;
            if (params.length >= 2) {
                value = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    ARRAY_STORAGE.save(new Resume(value));
                    printAll();
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(value);
                    printAll();
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(value));
                    break;
                case "update":
                    ARRAY_STORAGE.update(new Resume(value, params[2]));
                    printAll();
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        List<Resume> all = ARRAY_STORAGE.getAllSorted();
        System.out.println("----------------------------");
        if (all.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}