package com.nsergey.basejava.storage;

import com.nsergey.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected boolean isExist(Object key) {
        return false;
    }

    @Override
    protected void doAdd(Resume resume, Object key) {

    }

    @Override
    protected Resume doGet(Object key) {
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {

    }

    @Override
    protected void doDelete(Object key) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
}
