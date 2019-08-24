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
    protected void delete(int index) {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    protected Resume get(int index) {
        return null;
    }

    @Override
    protected int indexOf(String uuid) {
        return 0;
    }

    @Override
    protected void add(Resume resume) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
