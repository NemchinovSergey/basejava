package com.nsergey.basejava.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import com.nsergey.basejava.exception.ExistStorageException;
import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class AbstractStorageTest {

    private static final int INITIAL_SIZE = 9;

    protected final Storage storage;

    // sorted array of resumes
    private static final Resume[] resumesArr = IntStream.range(1, INITIAL_SIZE + 1)
                                                        .mapToObj(n -> new Resume("uuid" + n, "name" + n))
                                                        .toArray(Resume[]::new);

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        List<Resume> resumeList = new ArrayList<>(Arrays.asList(resumesArr));
        Collections.shuffle(resumeList);
        resumeList.forEach(storage::save);
    }

    @Test
    void clear() {
        assertTrue(storage.size() > 0);

        storage.clear();

        assertEquals(0, storage.size());
    }

    @Test
    void save() {
        String uuid = "some-test-uuid";
        assertThrows(NotExistStorageException.class, () -> storage.get(uuid));

        storage.save(new Resume(uuid, "some-name"));

        assertEquals(uuid, storage.get(uuid).getUuid());
    }

    @Test
    void get() {
        String uuid = "uuid5";
        Resume resume = storage.get(uuid);
        assertNotNull(resume);
        assertEquals(uuid, resume.getUuid());
    }

    @Test
    void delete() {
        String uuid = "uuid5";
        int size = storage.size();
        assertNotNull(storage.get(uuid));

        storage.delete(uuid);

        assertThrows(NotExistStorageException.class, () -> storage.get(uuid));
        assertEquals(size - 1, storage.size());
    }

    @Test
    void getAllSorted() {
        assertTrue(storage.size() > 0);

        List<Resume> resumes = storage.getAllSorted();
        assertNotNull(resumes);
        assertTrue(resumes.size() > 0);
        assertEquals(storage.size(), resumes.size());

        assertEquals(new Resume("uuid1", "name1"), resumes.get(0));
        assertEquals(new Resume("uuid2", "name2"), resumes.get(1));
        assertEquals(new Resume("uuid3", "name3"), resumes.get(2));
        assertEquals(new Resume("uuid4", "name4"), resumes.get(3));
        assertEquals(new Resume("uuid5", "name5"), resumes.get(4));
    }

    @Test
    void size() {
        assertTrue(storage.size() > 0);
    }

    @Test
    void testNulls() {
        Exception exception;

        exception = assertThrows(NullPointerException.class, () -> storage.save(null));
        assertEquals("Resume is null", exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> storage.get(null));
        assertEquals("uuid is null", exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> storage.delete(null));
        assertEquals("uuid is null", exception.getMessage());
    }

    @Test
    void testSaveDuplicates() {
        String uuid = "uuid5";
        assertNotNull(storage.get(uuid));

        int size = storage.size();

        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(uuid, "some-name")));
        assertNotNull(storage.get(uuid));
        assertEquals(size, storage.size());
    }

    @Test
    void update() {
        String uuid = "uuid5";
        Resume oldResume = storage.get(uuid);
        assertNotNull(oldResume);

        Resume newResume = new Resume(uuid, "NewName");
        storage.update(newResume);

        assertNotSame(oldResume, newResume);
        assertSame(newResume, storage.get(uuid));
    }

}