package com.nsergey.basejava.storage;

import java.util.Objects;

import com.nsergey.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void delete(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        int index = indexOf(uuid);
        if (index == NOT_FOUND) {
            System.out.println("Not found: " + uuid);
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Resume deleted: " + uuid);
        }
    }

    @Override
    protected int indexOf(String uuid) {
        for (int index = 0; index < size; index++) {
            Resume r = storage[index];
            if (Objects.equals(r.getUuid(), uuid)) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    @Override
    protected void add(Resume resume) {
        storage[size] = resume;
        size++;
    }

}
