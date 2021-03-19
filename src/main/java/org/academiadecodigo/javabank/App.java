package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.services.AccountServiceImpl;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;

import javax.persistence.EntityManagerFactory;

public class App {

    public static void main(String[] args) {

        BootstrapDatabase bsd = new BootstrapDatabase();
        EntityManagerFactory emf = bsd.start();

        App app = new App();
        app.bootStrap(emf);

        bsd.stop();
    }

    private void bootStrap(EntityManagerFactory emf) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new AccountServiceImpl());
        bootstrap.setCustomerService(new CustomerServiceImpl());
        bootstrap.loadCustomers();

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
