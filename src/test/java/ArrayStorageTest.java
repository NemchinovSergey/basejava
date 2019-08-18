import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ArrayStorageTest {

    private static ArrayStorage ARRAY_STORAGE;

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
        assertNull(ARRAY_STORAGE.get(uuid));

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

        assertNull(ARRAY_STORAGE.get(uuid));
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
        assertEquals("Parameter is null", exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ARRAY_STORAGE.get(null));
        assertEquals("uuid is null", exception.getMessage());

        exception = assertThrows(NullPointerException.class, () -> ARRAY_STORAGE.delete(null));
        assertEquals("uuid is null", exception.getMessage());
    }

    @Test
    void testOverloading() {
        Executable executable = () -> IntStream.range(0, ARRAY_STORAGE.getCapacity() + 1)
                                               .forEach(n -> ARRAY_STORAGE.save(new Resume("uuid" + n)));
        Exception exception = assertThrows(IllegalStateException.class,
                                           executable);
        assertEquals("Storage is full", exception.getMessage());
    }

    @Test
    void testSaveDuplicates() {
        String uuid = "uuid10";
        assertNotNull(ARRAY_STORAGE.get(uuid));

        int size = ARRAY_STORAGE.size();
        ARRAY_STORAGE.save(new Resume(uuid));
        assertNotNull(ARRAY_STORAGE.get(uuid));
        assertEquals(size, ARRAY_STORAGE.size());
    }

}