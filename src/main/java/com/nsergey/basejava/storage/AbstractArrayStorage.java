package com.nsergey.basejava.storage;

import java.util.Arrays;
import java.util.Objects;

import com.nsergey.basejava.exception.StorageException;
import com.nsergey.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int CAPACITY = 10000;
    protected static final int NOT_FOUND = -1;

    protected final Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;

    @Override
    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Resume is null");

        if (size >= CAPACITY) {
            throw new StorageException(resume.getUuid(), "Storage is full");
        }

        super.save(resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage[(Integer) key];
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storage[(int) key] = resume;
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

    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return indexOf(uuid);
    }

    protected abstract int indexOf(String uuid);

    @Override
    protected boolean isExist(Object key) {
        return key == null || (Integer) key >= 0;
    }

}