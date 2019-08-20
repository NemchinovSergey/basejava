package com.nsergey.basejava.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import com.nsergey.basejava.exception.ExistStorageException;
import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.exception.StorageException;
import com.nsergey.basejava.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ArrayStorageTest {

    private static Storage ARRAY_STORAGE;

    private static final int INITIAL_SIZE = 1000;

    @BeforeEach
    void setUp() {
        ARRAY_STORAGE = new ArrayStorage();
        IntStream.range(1, INITIAL_SIZE + 1).forEach(n -> ARRAY_STORAGE.save(new Resume("uuid" + n)));
    }

    @Test
    void clear() {
        assertTrue(ARRAY_STORAGE.size() > 0);

        ARRAY_STORAGE.clear();

        assertEquals(0, ARRAY_STORAGE.size());
    }

    @Test
    void save() {
        String uuid = "some-test-uuid";
        assertThrows(NotExistStorageException.class, () -> ARRAY_STORAGE.get(uuid));

        ARRAY_STORAGE.save(new Resume(uuid));

        assertEquals(uuid, ARRAY_STORAGE.get(uuid).getUuid());
    }

    @Test
    void get() {
        String uuid = "uuid10";
        assertEquals(uuid, ARRAY_STORAGE.get(uuid).getUuid());
    }

    @Test
    void delete() {
        String uuid = "uuid10";
        int size = ARRAY_STORAGE.size();
        assertNotNull(ARRAY_STORAGE.get(uuid));

        ARRAY_STORAGE.delete(uuid);

        assertThrows(NotExistStorageException.class, () -> ARRAY_STORAGE.get(uuid));
        assertEquals(size - 1, ARRAY_STORAGE.size());
    }

    @Test
    void getAll() {
        Resume[] resumes = ARRAY_STORAGE.getAll();
        assertNotNull(resumes);
        assertEquals(ARRAY_STORAGE.size(), resumes.length);
    }

    @Test
    void size() {
        assertEquals(INITIAL_SIZE, ARRAY_STORAGE.size());
    }

    @Test
    void testNulls() {
        Exception exception;

        exception = assertThrows(NullPointerException.class, () -> ARRAY_STORAGE.save(null));
        assertEquals("Resume is null", exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ARRAY_STORAGE.get(null));
        assertEquals("uuid is null", exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ARRAY_STORAGE.delete(null));
        assertEquals("uuid is null", exception.getMessage());
    }

    @Test
    void testStorageOverflow() {
        Executable fillStorage = () -> {
            ARRAY_STORAGE.clear();
            IntStream.range(0, ARRAY_STORAGE.getCapacity() + 1).forEach(n -> ARRAY_STORAGE.save(new Resume(
                    "uuid" + n)));
        };
        Exception exception = assertThrows(StorageException.class, fillStorage);
        assertEquals("Storage is full", exception.getMessage());
    }

    @Test
    void testSaveDuplicates() {
        String uuid = "uuid10";
        assertNotNull(ARRAY_STORAGE.get(uuid));

        int size = ARRAY_STORAGE.size();
        assertThrows(ExistStorageException.class, () -> ARRAY_STORAGE.save(new Resume(uuid)));
        assertNotNull(ARRAY_STORAGE.get(uuid));
        assertEquals(size, ARRAY_STORAGE.size());
    }

    @Test
    void update() {
        String uuid = "uuid10";
        Resume oldResume = ARRAY_STORAGE.get(uuid);
        assertNotNull(oldResume);

        Resume newResume = new Resume(uuid);
        ARRAY_STORAGE.update(newResume);

        assertNotSame(oldResume, newResume);
        assertSame(newResume, ARRAY_STORAGE.get(uuid));
    }
}