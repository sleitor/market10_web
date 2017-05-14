package main.controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by User on 14.05.2017.
 */
public class JPAUtil {

    private static final EntityManagerFactory emfInstanse = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {

        return Persistence.createEntityManagerFactory("mnf-pu");
    }

    public static EntityManagerFactory getInstance() {
        return emfInstanse;
    }


    //    private EntityManagerFactory emf =
//            Persistence.
//                    createEntityManagerFactory("mnf-pu");
//
}
