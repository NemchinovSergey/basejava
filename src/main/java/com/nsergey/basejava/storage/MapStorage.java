package com.nsergey.basejava.storage;

import com.nsergey.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    public int getCapacity() {
        return 0;
    }

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
    public void update(Resume resume) {

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
