package com.nsergey.basejava.storage;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.nsergey.basejava.exception.ExistStorageException;
import com.nsergey.basejava.exception.NotExistStorageException;
import com.nsergey.basejava.model.Resume;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractStorage<T> implements Storage {

    private final static Logger LOG = LogManager.getLogger(AbstractStorage.class.getSimpleName());

    protected static final Comparator<Resume> RESUME_UUID_COMPARATOR = Comparator.comparing(Resume::getUuid);

    protected static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName)
                                                                                      .thenComparing(Resume::getUuid);

    protected abstract T getSearchKey(String uuid);

    protected abstract boolean isExist(T key);

    protected abstract void doAdd(Resume resume, T key);

    protected abstract Resume doGet(T key);

    protected abstract void doUpdate(Resume resume, T key);

    protected abstract void doDelete(T key);

    @Override
    public abstract int size();

    @Override
    public abstract void clear();

    @Override
    public abstract List<Resume> getAllSorted();

    @Override
    public void save(Resume resume) {
        LOG.info("Save resume: {}", resume);
        Objects.requireNonNull(resume, "Resume is null");

        T key = getSearchKey(resume.getUuid());
        if (isExist(key)) {
            LOG.warn("Resume already exists: {}", resume);
            throw new ExistStorageException(resume.getUuid());
        }

        doAdd(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get resume by uuid: {}", uuid);
        Objects.requireNonNull(uuid, "uuid is null");

        T key = getSearchKey(uuid);
        if (!isExist(key)) {
            LOG.warn("Resume is not exist, uuid: {}", uuid);
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(key);
        }
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update resume: {}", resume);
        Objects.requireNonNull(resume, "uuid is null");

        T key = getSearchKey(resume.getUuid());
        if (!isExist(key)) {
            LOG.warn("Resume is not exist, resume: {}", resume);
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(resume, key);
        }
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete resume by uuid: {}", uuid);
        Objects.requireNonNull(uuid, "uuid is null");

        T key = getSearchKey(uuid);
        if (!isExist(key)) {
            LOG.warn("Resume is not exist, uuid: {}", uuid);
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(key);
        }
    }

}
