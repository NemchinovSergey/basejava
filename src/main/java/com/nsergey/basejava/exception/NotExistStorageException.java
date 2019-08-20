package com.nsergey.basejava.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super(uuid, "The resume does not exist: " + uuid);
    }

}
