package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.TransactionManager;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.services.CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private TransactionManager transactionManager;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    @Override
    public Customer get(Integer id) {

        try {

            transactionManager.beginRead();
            return customerDao.fin
        }
        return null;
    }

    @Override
    public double getBalance(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {

            Customer customer = Optional.ofNullable(em.find(Customer.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return customer.getAccounts().stream()
                    .mapToDouble(Account::getBalance)
                    .sum();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {

            Customer customer = Optional.ofNullable(em.find(Customer.class, id))
                    .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return customer.getAccounts().stream()
                    .map(Model::getId)
                    .collect(Collectors.toSet());

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
}
