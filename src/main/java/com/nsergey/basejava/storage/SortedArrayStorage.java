package com.nsergey.basejava.storage;

import java.util.Arrays;

import com.nsergey.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doAdd(Resume resume, Object key) {
        //  http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int index = Arrays.binarySearch(getAll(), resume);
        if (index < 0) {
            int pos = -index - 1;
            System.arraycopy(storage, pos, storage, pos + 1, size - pos);
            storage[pos] = resume;
            size++;
        }
    }

    @Override
    protected int indexOf(String uuid) {
        int index = Arrays.binarySearch(getAll(), new Resume(uuid));
        return index >= 0 ? index : NOT_FOUND;
    }

    @Override
    protected void doDelete(Object key) {
        int intKey = (int) key;
        storage[intKey] = null; // to help GC
        System.arraycopy(storage, intKey + 1, storage, intKey, size - intKey - 1);
        storage[size - 1] = null;
        size--;
    }

}
