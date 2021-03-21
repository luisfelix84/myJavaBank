package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.TransactionManager;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.dao.jpa.GenericJpaDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.Optional;

/**
 * A JPA {@link AccountService} implementation
 */
public class AccountServiceImpl implements AccountService {

    private GenericJpaDao genericJpaDao;
    private TransactionManager tm;

    public AccountServiceImpl(GenericJpaDao genericJpaDao, TransactionManager tm) {
        this.genericJpaDao = genericJpaDao;
        this.tm = tm;
    }



    @Override
    public Account get(Integer id) {
        return null;
    }

    @Override
    public Integer add(Account account) {
        return null;
    }

    @Override
    public void deposit(Integer id, double amount) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Optional<Account> account = Optional.ofNullable(em.find(AbstractAccount.class, id));

            if (!account.isPresent()) {
                em.getTransaction().rollback();
            }

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id")).credit(amount);

            em.getTransaction().commit();

        } catch (RollbackException ex) {

            em.getTransaction().rollback();

        } finally {

            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    @Override
    public void withdraw(Integer id, double amount) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Optional<Account> account = Optional.ofNullable(em.find(AbstractAccount.class, id));

            if (!account.isPresent()) {
                em.getTransaction().rollback();
            }

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id")).debit(amount);

            em.getTransaction().commit();

        } catch (RollbackException ex) {

            em.getTransaction().rollback();

        } finally {

            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Optional<Account> srcAccount = Optional.ofNullable(em.find(AbstractAccount.class,srcId ));
            Optional<Account> dstAccount = Optional.ofNullable(em.find(AbstractAccount.class,dstId ));

            if (!srcAccount.isPresent() || !dstAccount.isPresent()) {
                em.getTransaction().rollback();
            }

            srcAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));
            dstAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));

            // make sure transaction can be performed
            if (srcAccount.get().canDebit(amount) && dstAccount.get().canCredit(amount)) {
                srcAccount.get().debit(amount);
                dstAccount.get().credit(amount);
            }

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
