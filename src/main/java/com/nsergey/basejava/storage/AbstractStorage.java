package com.nsergey.basejava.storage;

import java.util.Objects;

import com.nsergey.basejava.exception.ExistStorageException;
import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int NOT_FOUND = -1;

    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Resume is null");

        Object key = getSearchKey(resume.getUuid());
        if (isExist(key)) {
            throw new ExistStorageException(resume.getUuid());
        }

        doAdd(resume, key);
    }

    protected abstract boolean isExist(Object key);

    public Resume get(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        int index = indexOf(uuid);
        if (index == NOT_FOUND) {
            throw new NotExistStorageException(uuid);
        } else {
            return get(index);
        }
    }

    public void delete(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        int index = indexOf(uuid);
        if (index == NOT_FOUND) {
            throw new NotExistStorageException(uuid);
        } else {
            delete(index);
        }
    }

    protected abstract void delete(int index);

    public abstract int size();

    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract void update(Resume resume);

    protected abstract Resume get(int index);

    protected abstract Object getSearchKey(String uuid);

    @Deprecated
    protected abstract int indexOf(String uuid);

    protected abstract void doAdd(Resume resume, Object key);


}
