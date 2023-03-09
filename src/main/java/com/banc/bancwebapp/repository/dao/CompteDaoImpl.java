package com.banc.bancwebapp.repository.dao;

import com.banc.bancwebapp.model.CompteEntity;
import com.banc.bancwebapp.repository.GenericBancDao;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CompteDaoImpl implements GenericBancDao<CompteEntity, String> {
    private final SessionFactory sessionFactory;

    public CompteDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(CompteEntity entity) {
        getSession().persist(entity);
    }

    @Override
    public CompteEntity findById(String id) {
        return getSession().get(CompteEntity.class, id);
    }

    @Override
    public List<CompteEntity> findAll() {
        CriteriaQuery<CompteEntity> criteria = getSession().getCriteriaBuilder()
                                                           .createQuery(CompteEntity.class);
        criteria.select(criteria.from(CompteEntity.class));
        return getSession().createQuery(criteria)
                           .getResultList();
    }

    @Override
    public void update(CompteEntity entity) {
        getSession().persist(entity);
    }

    @Override
    public void delete(CompteEntity entity) {
        getSession().remove(entity);
    }
}
