package com.nsergey.basejava.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nsergey.basejava.model.Resume;

public class MapUuidStorage extends AbstractStorage {

    private final Map<String, Resume> storage;

    public MapUuidStorage() {
        storage = new HashMap<>();
    }

    public MapUuidStorage(Map<String, Resume> storage) {
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
    public List<Resume> getAllSorted() {
        return storage.values()
                      .stream()
                      .sorted(RESUME_UUID_COMPARATOR)
                      .collect(Collectors.toList());
    }
}
