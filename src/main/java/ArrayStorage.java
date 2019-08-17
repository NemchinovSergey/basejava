import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private static final int CAPACITY = 10000;
    private static final Resume[] storage = new Resume[CAPACITY];
    private int size = 0;

    public int getCapacity() {
        return CAPACITY;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        Objects.requireNonNull(r, "Parameter is null");

        if (size >= storage.length) {
            throw new IllegalStateException("Storage is full");
        }

        storage[size++] = r;
    }

    public Resume get(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        for (int i = 0; i < size; i++) {
            Resume r = storage[i];
            if (Objects.equals(r.getUuid(), uuid)) {
                return r;
            }
        }
        return null;
    }

    public void delete(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        for (int i = 0; i < size; i++) {
            Resume r = storage[i];
            if (Objects.equals(r.getUuid(), uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                storage[size] = null;
            }
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

}
