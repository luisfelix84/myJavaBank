package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The customer model entity
 */
@Entity
@Table(name = "customer")
public class Customer extends AbstractModel {

    private String name;

    @OneToMany(
    cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "customer",
            fetch = FetchType.EAGER,
            targetEntity = Account.class
    )
    private List<Account> accounts = new ArrayList<>();

    /**
     * Gets the name of the customer
     *
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the customer accounts
     *
     * @return the accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Adds a new account to the customer
     *
     * @param account the account to add
     */
    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);

    }

    /**
     * Removes an account from the customer
     *
     * @param account the account to remove
     */
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}


