package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.SessionManager;
import org.academiadecodigo.javabank.persistence.dao.GenericDao;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class GenericJpaDao<T extends AbstractModel> implements GenericDao<T> {


    SessionManager sm;
    private Class<T> modelType;



    @Override
    public List<T> findAll() {

        try {

            CriteriaQuery<T> criteriaQuery = sm.getCurrentSession().getCriteriaBuilder().createQuery(modelType);
            Root<T> root = criteriaQuery.from(modelType);

            return sm.getCurrentSession().createQuery(criteriaQuery).getResultList();

        } finally {
            if (sm != null) {
                sm.stopSession();
            }
        }

    }


    @Override
    public T findById(Integer id) {

       try {

           return sm.getCurrentSession().find(modelType, id);

       } finally {
           if (sm != null) {
               sm.stopSession();
           }
       }
    }


    @Override
    public T saveOrUpdate(T modelObject) {

        try {

            return sm.getCurrentSession().merge(modelObject);

        } catch (RollbackException exception) {
            sm.getCurrentSession().getTransaction().rollback();
            return null;

        } finally {
            if (sm != null) {
                sm.stopSession();
            }
        }
    }


    @Override
    public void delete(Integer id) {

        try {

            sm.getCurrentSession().remove(sm.getCurrentSession().find(modelType, id));

        } catch (RollbackException exception) {
            sm.getCurrentSession().getTransaction().rollback();

        } finally {
            if (sm != null) {
                sm.stopSession();
            }
        }
    }

    public void setSm(SessionManager sm) {
        this.sm = sm;
    }
}
