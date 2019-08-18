import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private static final int CAPACITY = 10_000;
    private static final Resume[] storage = new Resume[CAPACITY];
    private int size = 0;

    private static final int NOT_FOUND = -1;

    public int getCapacity() {
        return CAPACITY;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage is clear");
    }

    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Parameter is null");

        int index = indexOf(resume.getUuid());
        if (index >= 0) {
            System.out.println("There is the resume in the storage already: " + index);
            return;
        }

        if (size >= storage.length) {
            throw new IllegalStateException("Storage is full");
        }

        storage[size++] = resume;
    }

    public Resume get(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        int index = indexOf(uuid);
        if (index == NOT_FOUND) {
            System.out.println("Not found: " + uuid);
            return null;
        } else {
            System.out.println("Resume found: " + uuid);
            return storage[index];
        }
    }

    public void delete(String uuid) {
        Objects.requireNonNull(uuid, "uuid is null");

        int index = indexOf(uuid);
        if (index == NOT_FOUND) {
            System.out.println("Not found: " + uuid);
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Resume deleted: " + uuid);
        }
    }

    private int indexOf(String uuid) {
        for (int index = 0; index < size; index++) {
            Resume r = storage[index];
            if (Objects.equals(r.getUuid(), uuid)) {
                return index;
            }
        }
        return NOT_FOUND;
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
