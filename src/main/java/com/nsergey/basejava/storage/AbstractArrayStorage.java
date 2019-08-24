package com.nsergey.basejava.storage;

import java.util.Arrays;
import java.util.Objects;

import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.exception.StorageException;
import com.nsergey.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int CAPACITY = 10000;

    protected Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;

    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Resume is null");

        if (size >= CAPACITY) {
            throw new StorageException(resume.getUuid(), "Storage is full");
        }

        super.save(resume);
    }

    @Override
    protected Resume get(int index) {
        return storage[index];
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
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
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

}