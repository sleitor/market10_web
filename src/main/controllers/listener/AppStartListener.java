package main.controllers.listener;

import org.apache.log4j.PropertyConfigurator;

import java.util.HashMap;

/**
 * Это Лиссенер, который грузится при каждом запуске приложения и включает логгер
 */
public class AppStartListener {

    static {
        PropertyConfigurator.configure(AppStartListener.class.getClassLoader()
                .getResource("log.properties"));

//        HashMap<Long, Integer> cart = new HashMap<>();
//        req.getSession().setAttribute("cart", cart);
//
    }

}
