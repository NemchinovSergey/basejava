package com.nsergey.basejava.storage;

import java.util.Arrays;
import java.util.Objects;

import com.nsergey.basejava.exception.StorageException;
import com.nsergey.basejava.model.Resume;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    private final static Logger LOG = LogManager.getLogger(AbstractArrayStorage.class.getSimpleName());

    protected static final int CAPACITY = 100;
    protected static final int NOT_FOUND = -1;

    protected final Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;

    protected abstract int indexOf(String uuid);

    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save resume: {}", resume);
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
        LOG.debug("Get size");
        return size;
    }

    @Override
    public void clear() {
        LOG.debug("Clear storage");
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    Resume[] getArray() {
        LOG.debug("Get array of resume");
        return Arrays.copyOfRange(storage, 0, size);
    }

}