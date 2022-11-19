package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public T delete(Id id) {
        T result = this.repository.getOne(id);
        if(result != null) {
            this.repository.deleteById(id);
        }
        return result;
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
    public Boolean isDelete(Id id) {
        if(this.delete(id) != null) {
            return true;
        }
        return false;
    }
}
