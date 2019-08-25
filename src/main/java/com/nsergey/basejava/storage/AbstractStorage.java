package com.nsergey.basejava.storage;

import java.util.Objects;

import com.nsergey.basejava.exception.ExistStorageException;
import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Resume is null");

        Object key = getSearchKey(resume.getUuid());
        if (isExist(key)) {
            throw new ExistStorageException(resume.getUuid());
        }

        doAdd(resume, key);
    }

    protected abstract boolean isExist(Object key);

    @Override
    public Resume get(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        Object key = getSearchKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(key);
        }
    }

    @Override
    public void delete(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        Object key = getSearchKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(key);
        }
    }

    protected abstract void doDelete(Object key);

    @Override
    public abstract int size();

    @Override
    public abstract void clear();

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract void update(Resume resume);

    protected abstract Resume doGet(Object key);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doAdd(Resume resume, Object key);


}
