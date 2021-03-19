package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.Set;

public class CustomerServiceDatabase  implements CustomerService {

    private EntityManagerFactory emf;

    public CustomerServiceDatabase(EntityManagerFactory emf) {
        this.emf = emf;

    }

    public CustomerServiceDatabase() {

    }

    @Override
    public Customer get(Integer id) {
        return null;
    }

    @Override
    public List<Customer> list() {
        return null;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        return null;
    }

    @Override
    public double getBalance(int id) {
        return 0;
    }


    @Override
    public void add(Customer customer) {
        EntityManager em = emf.createEntityManager();

        Customer savedCustomer = null;

        try {

            em.getTransaction().begin();

            em.getTransaction().commit();

        } catch (RollbackException ex) {

            em.getTransaction().rollback();

        } finally {
            if (em != null) {
                em.close();
            }
        }



    }
}



