package com.nsergey.basejava.storage;

import java.util.HashMap;
import java.util.Map;

import com.nsergey.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage;

    public MapStorage() {
        storage = new HashMap<>();
    }

    public MapStorage(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey((String)key);
    }

    @Override
    protected void doAdd(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get((String)key);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove((String)key);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }
}
