package com.nsergey.basejava.storage;

import com.nsergey.basejava.model.Resume;

/**
 * Storage Interface
 */
public interface Storage {
    int getCapacity();

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    void update(Resume resume);

    Resume[] getAll();

    int size();
}
