package com.nsergey.basejava.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nsergey.basejava.model.Resume;

public class MapUuidStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storage;

    public MapUuidStorage() {
        storage = new HashMap<>();
    }

    public MapUuidStorage(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected void doAdd(Resume resume, String key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(String key) {
        return storage.get(key);
    }

    @Override
    protected void doUpdate(Resume resume, String key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String key) {
        storage.remove(key);
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
