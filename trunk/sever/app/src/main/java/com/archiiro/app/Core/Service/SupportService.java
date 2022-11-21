package com.archiiro.app.Core.Service;

import org.springframework.data.domain.Page;

import java.io.Serializable;

public interface SupportService<T, Id extends Serializable> {
    T save(T t);

    void delete(Id id);

    T findOne(Id id);

    Boolean isExist(Id id);

    Page<T> getPage(int pageIndex, int pageSize);
}
