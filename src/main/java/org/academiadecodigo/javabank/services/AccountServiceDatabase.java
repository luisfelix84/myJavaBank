package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AccountServiceDatabase implements AccountService{

    private EntityManagerFactory emf;





    @Override
    public void add(Account account) {
        EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();


    }

    @Override
    public void deposit(int id, double amount) {

    }

    @Override
    public void withdraw(int id, double amount) {

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

    }
}
