package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import java.util.Set;

/**
 * Common interface for customer services, provides methods to manage customers
 */
public interface CustomerService {

    Customer get(Integer id);

    double getBalance(Integer id);

    /**
     * Gets the set of customer account ids
     *
     * @param id the customer id
     * @return the accounts of the given customer id
     */
    Set<Integer> listCustomerAccountIds(Integer id);
}
