package com.banc.bancwebapp.repository.dao;

import com.banc.bancwebapp.model.ClientEntity;
import com.banc.bancwebapp.model.CompteEntity;
import com.banc.bancwebapp.repository.GenericBancDao;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClientDaoImpl implements GenericBancDao<ClientEntity, Long> {
    private final SessionFactory sessionFactory;

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(ClientEntity entity) {
        getSession().persist(entity);
    }

    @Override
    public ClientEntity findById(Long id) {
        return getSession().get(ClientEntity.class, id);
    }

    @Override
    public List<ClientEntity> findAll() {
        CriteriaQuery<ClientEntity> criteria = getSession().getCriteriaBuilder()
                                                           .createQuery(ClientEntity.class);
        criteria.select(criteria.from(ClientEntity.class));
        return getSession().createQuery(criteria)
                           .getResultList();
    }

    @Override
    public void update(ClientEntity entity) {
        getSession().persist(entity);
    }

    @Override
    public void delete(ClientEntity entity) {
        getSession().remove(entity);
    }

    public List<CompteEntity> findComptesByClientId(Long id) {
        CriteriaQuery<CompteEntity> criteria = getSession().getCriteriaBuilder()
                                                           .createQuery(CompteEntity.class);
        Root<CompteEntity> compteEntity = criteria.from(CompteEntity.class);
        criteria.select(compteEntity)
                .where(getSession().getCriteriaBuilder()
                                   .equal(compteEntity.get("compteClientEntity")
                                                      .get("idClient"), id));
        return getSession().createQuery(criteria)
                           .getResultList();
    }

    public ClientEntity findByDni(String dni) {
        return getSession().createQuery("FROM ClientEntity WHERE DNI = :dni", ClientEntity.class)
                           .setParameter("dni", dni)
                           .uniqueResult();
    }

}
