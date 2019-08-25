package com.nsergey.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.model.Resume;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Resume doGet(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(key);
    }

    @Override
    public void update(Resume resume) {
        Objects.requireNonNull(resume, "uuid is null");

        int index = (int) getSearchKey(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage.remove(index);
            storage.add(resume);
        }
    }

    @Override
    protected boolean isExist(Object key) {
        return false;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void doAdd(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[] {});
    }

    @Override
    public int size() {
        return storage.size();
    }
}
