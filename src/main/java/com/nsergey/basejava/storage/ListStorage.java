package com.nsergey.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nsergey.basejava.model.Resume;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage;

    public ListStorage() {
        this.storage = new ArrayList<>();
    }

    public ListStorage(List<Resume> storage) {
        this.storage = storage;
    }

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
        storage.remove((int)key);
    }

    @Override
    protected void doUpdate(Resume resume, Object key) {
        storage.remove((int)key);
        storage.add(resume);
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null && (Integer) key >= 0;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doAdd(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.stream()
                      .sorted()
                      .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
