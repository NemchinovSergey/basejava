package com.nsergey.basejava.storage;

import java.util.Objects;

import com.nsergey.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int CAPACITY = 10000;
    protected static final int NOT_FOUND = -1;

    protected Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;

    public Resume get(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        int index = indexOf(uuid);
        if (index == NOT_FOUND) {
            System.out.println("Not found: " + uuid);
            return null;
        } else {
            System.out.println("Resume found: " + uuid);
            return storage[index];
        }
    }

    public int size() {
        return size;
    }

    protected abstract int indexOf(String uuid);
}