package com.nsergey.basejava.storage;

import java.util.Arrays;

import com.nsergey.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int indexOf(String uuid) {
        return Arrays.binarySearch(storage, new Resume(uuid));
    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void update(Resume resume) {

    }

}
