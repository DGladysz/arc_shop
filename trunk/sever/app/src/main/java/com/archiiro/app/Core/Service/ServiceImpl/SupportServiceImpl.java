package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public class SupportServiceImpl<T, Id extends Serializable> implements SupportService<T, Id> {
    @Autowired
    public JpaRepository<T, Id> repository;

    @PersistenceContext
    public EntityManager manager;

    public SupportServiceImpl() {
    }

    @Override
    public T save(T t) {
        T result = this.repository.save(t);
        return result;
    }

    @Override
    public void delete(Id id) {
        this.repository.deleteById(id);
    }

    @Override
    public T findOne(Id id) {
        return this.repository.getOne(id);
    }

    @Override
    public Boolean isExist(Id id) {
        if(this.findOne(id) != null) {
            return true;
        }
        return false;
    }


    @Override
    public Page<T> getPage(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return this.repository.findAll(pageable);
    }
}
