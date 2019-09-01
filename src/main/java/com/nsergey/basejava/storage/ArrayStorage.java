package com.nsergey.basejava.storage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.nsergey.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doAdd(Resume resume, Object key) {
        storage[size] = resume;
        size++;
    }

    @Override
    protected void doDelete(Object key) {
        storage[(int) key] = storage[size - 1];
        storage[size - 1] = null;
        size--;
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
    public List<Resume> getAllSorted() {
        return Stream.of(getArray())
                     .sorted(RESUME_UUID_COMPARATOR)
                     .collect(Collectors.toList());
    }

}
