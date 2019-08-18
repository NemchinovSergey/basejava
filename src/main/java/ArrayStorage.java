import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {

    private static final int CAPACITY = 10_000;
    private static final Resume[] storage = new Resume[CAPACITY];
    private int size = 0;

    private static final int NOT_FOUND = -1;

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage is clear");
    }

    @Override
    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Parameter is null");

        int index = indexOf(resume.getUuid());
        if (index >= 0) {
            System.out.println("There is the resume in the storage already: " + index);
            return;
        }

        if (size >= CAPACITY) {
            throw new IllegalStateException("Storage is full");
        }

        storage[size++] = resume;
    }

    @Override
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

    @Override
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

    @Override
    public void update(Resume resume) {
        Objects.requireNonNull(resume, "uuid is null");

        int index = indexOf(resume.getUuid());
        if (index == NOT_FOUND) {
            System.out.println("Not found: " + resume.getUuid());
        } else {
            storage[index] = resume;
            System.out.println("Resume updated: " + index);
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

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

}
