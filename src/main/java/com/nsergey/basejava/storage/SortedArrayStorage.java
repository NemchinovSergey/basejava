package com.nsergey.basejava.storage;

import java.util.Arrays;
import java.util.Objects;

import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int indexOf(String uuid) {
        int index = Arrays.binarySearch(getAll(), new Resume(uuid));
        return index >= 0 ? index : NOT_FOUND;
    }

    @Override
    public void delete(String uuid) {
        Objects.requireNonNull(uuid, "Resume is null");

        int index = indexOf(uuid);
        if (index == NOT_FOUND) {
            throw new NotExistStorageException(uuid);
        } else {
            storage[index] = null; // to help GC
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected void add(Resume resume) {
        //  http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int index = Arrays.binarySearch(getAll(), resume);
        if (index < 0) {
            int pos = -index - 1;
            System.arraycopy(storage, pos, storage, pos + 1, size - pos);
            storage[pos] = resume;
            size++;
        }
    }

}
