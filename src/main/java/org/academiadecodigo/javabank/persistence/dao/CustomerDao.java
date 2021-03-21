package org.academiadecodigo.javabank.persistence.dao;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;

public interface CustomerDao extends GenericDao<Customer> {

    List<Integer> getCustomerIds();

}
