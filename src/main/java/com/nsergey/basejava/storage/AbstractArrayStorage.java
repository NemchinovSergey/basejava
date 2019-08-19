package com.nsergey.basejava.storage;

import java.util.Arrays;
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

    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Resume is null");

        int index = indexOf(resume.getUuid());
        if (index >= 0) {
            System.out.println("There is the resume in the storage already: " + index);
            return;
        }

        if (size >= CAPACITY) {
            throw new IllegalStateException("Storage is full");
        }

        add(resume);
    }

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

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage is clear");
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void update(Resume resume) {
        Objects.requireNonNull(resume, "uuid is null");

        int index = indexOf(resume.getUuid());
        if (index == NOT_FOUND) {
            System.out.println("Not found: " + resume.getUuid());
        } else {
            storage[index] = resume;
            System.out.println("Resume updated: " + index);
        }
    }

    protected abstract int indexOf(String uuid);

    protected abstract void add(Resume resume);

}