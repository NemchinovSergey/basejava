package com.nsergey.basejava.storage;

import java.util.List;

import com.nsergey.basejava.model.Resume;

/**
 * Storage Interface
 */
public interface Storage {

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    void update(Resume resume);

    List<Resume> getAllSorted();

    int size();
}
