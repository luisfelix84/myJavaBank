package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import javax.persistence.RollbackException;

public class JpaAccountDao extends GenericJpaDao<AbstractAccount> implements AccountDao {




    @Override
    public void deposit(Integer id, double amount) {

        try {

            Account account = sm.getCurrentSession().find(Account.class, id);

            if (account == null) {
                throw new IllegalArgumentException("INVALID ACCOUNT ID");
            }

            account.credit(amount);

        } catch (RollbackException exception) {

        } finally {
            if (sm != null) {
                sm.stopSession();
            }
        }
    }


    @Override
    public void withdraw(Integer id, double amount) {

        try {

            Account account = sm.getCurrentSession().find(Account.class, id);

            if (account == null) {
                throw new IllegalArgumentException("INVALID ACCOUNT");
            }

            account.debit(amount);

        } catch (RollbackException exception) {

        } finally {
            if (sm != null) {
                sm.stopSession();
            }
        }
    }


    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        try {

            Account srcAccount = sm.getCurrentSession().find(Account.class, srcId);
            Account dstAccount = sm.getCurrentSession().find(Account.class, dstId);

            if (srcAccount == null || dstAccount == null) {
                throw new IllegalArgumentException("INVALID ACCOUNT ID");
            }

            if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
                srcAccount.debit(amount);
                dstAccount.credit(amount);
            }

        } catch (RollbackException exception) {

        } finally {
            if (sm != null) {
                sm.stopSession();
            }
        }
    }


}
