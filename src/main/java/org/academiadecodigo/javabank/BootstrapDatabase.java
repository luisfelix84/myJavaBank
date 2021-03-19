package org.academiadecodigo.javabank;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BootstrapDatabase {

    private EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT = "test";

    public EntityManagerFactory start() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

        return emf;
    }

    public void stop() {
        emf.close();
    }



}
