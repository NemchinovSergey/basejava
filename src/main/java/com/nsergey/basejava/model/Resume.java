package com.nsergey.basejava.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    /**
     * Unique identifier of Resume
     */
    private final String uuid;

    /**
     * Full name of a candidate
     */
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = Objects.requireNonNull(uuid, "uuid is null");
        this.fullName = Objects.requireNonNull(fullName, "Full Name is null");
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) {
            return false;
        }
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Resume{uuid='" + uuid + "', fullName='" + fullName + "'}";
    }

    @Override
    public int compareTo(Resume o) {
        int result = this.fullName.compareTo(o.fullName);
        return result != 0 ? result : this.uuid.compareTo(o.uuid);
    }
}
