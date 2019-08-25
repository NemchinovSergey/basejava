package com.nsergey.basejava.storage;

import com.nsergey.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    protected void doDelete(Object key) {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    protected Resume doGet(Object key) {
        return null;
    }

    @Override
    protected void doAdd(Resume resume, Object key) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected boolean isExist(Object key) {
        return false;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }
}
