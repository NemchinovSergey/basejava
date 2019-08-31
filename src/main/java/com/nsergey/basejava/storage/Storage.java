package com.nsergey.basejava.storage;

import com.nsergey.basejava.model.Resume;

/**
 * Storage Interface
 */
// TODO refactoring
public interface Storage {

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    void update(Resume resume);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    //  return list, sorted by name
    //  List<Resume> getAllSorted();

    int size();
}
