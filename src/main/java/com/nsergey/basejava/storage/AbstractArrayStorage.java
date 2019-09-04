package com.nsergey.basejava.storage;

import java.util.Arrays;
import java.util.Objects;

import com.nsergey.basejava.exception.StorageException;
import com.nsergey.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int CAPACITY = 10000;
    protected static final int NOT_FOUND = -1;

    protected final Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;

    protected abstract int indexOf(String uuid);

    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Resume is null");

        if (size >= CAPACITY) {
            throw new StorageException(resume.getUuid(), "Storage is full");
        }

        super.save(resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return indexOf(uuid);
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != null && index >= 0;
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    Resume[] getArray() {
        return Arrays.copyOfRange(storage, 0, size);
    }

}