package com.nsergey.basejava.storage;

import java.util.Arrays;
import java.util.List;

import com.nsergey.basejava.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doAdd(Resume resume, Integer ignored) {
        //  http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int index = Arrays.binarySearch(getArray(), resume, RESUME_UUID_COMPARATOR);
        if (index < 0) {
            int pos = -index - 1;
            System.arraycopy(storage, pos, storage, pos + 1, size - pos);
            storage[pos] = resume;
            size++;
        }
    }

    @Override
    protected int indexOf(String uuid) {
        int index = Arrays.binarySearch(getArray(), new Resume(uuid, "dummy"), RESUME_UUID_COMPARATOR);
        return index >= 0 ? index : NOT_FOUND;
    }

    @Override
    protected void doDelete(Integer index) {
        storage[index] = null; // to help GC
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(getArray());
    }

}
