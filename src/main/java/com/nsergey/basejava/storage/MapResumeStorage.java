package com.nsergey.basejava.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nsergey.basejava.model.Resume;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> storage;

    public MapResumeStorage() {
        storage = new HashMap<>();
    }

    public MapResumeStorage(Map<String, Resume> storage) {
        this.storage = storage;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected void doAdd(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected void doUpdate(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume resume) {
        storage.remove(resume.getUuid());
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
        return storage.values().stream().sorted(RESUME_UUID_COMPARATOR).collect(Collectors.toList());
    }
}
