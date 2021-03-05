package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;

public class Authentication implements AuthService{
    @Override
    public boolean authenticate(Integer id) {
        return false;
    }

    @Override
    public Customer getAccessingCustomer() {
        return null;
    }
}
