package com.nsergey.basejava.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.model.Resume;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Resume get(int index) {
        return storage.get(index);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void delete(int index) {
        storage.remove(index);
    }

    @Override
    public void update(Resume resume) {
        Objects.requireNonNull(resume, "uuid is null");

        int index = indexOf(resume.getUuid());
        if (index == NOT_FOUND) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage.remove(index);
            storage.add(resume);
        }
    }

    @Override
    protected int indexOf(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void add(Resume resume) {
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
