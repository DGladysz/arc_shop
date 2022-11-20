package com.archiiro.app.Core.Service;

import org.springframework.data.domain.Page;

import java.io.Serializable;

public interface SupportService<T, Id extends Serializable> {
    T save(T t);

    T delete(Id id);

    T findOne(Id id);

    Boolean isExist(Id id);

    Boolean isDelete(Id id);

    Page<T> getPage(int pageIndex, int pageSize);
}
