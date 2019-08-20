package com.nsergey.basejava.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super(uuid, "The resume already exists: " + uuid);
    }
}
