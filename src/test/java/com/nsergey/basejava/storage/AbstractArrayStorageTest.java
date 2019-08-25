package com.nsergey.basejava.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import com.nsergey.basejava.exception.StorageException;
import com.nsergey.basejava.model.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected final AbstractArrayStorage storage;

    AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
        this.storage = storage;
    }

    @Test
    void testStorageOverflow() {
        Executable fillStorage = () -> {
            int startNumber = storage.size() + 1; // +1 to start with 1001
            int endNumber = storage.getCapacity() + 2; // +2 to get overflow
            IntStream.range(startNumber, endNumber).forEach(n -> storage.save(new Resume("uuid" + n)));
        };
        Exception exception = assertThrows(StorageException.class, fillStorage);
        assertEquals("Storage is full", exception.getMessage());
    }

}
