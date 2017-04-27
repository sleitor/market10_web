package main.controllers.listener;

import main.controllers.LoginServlet;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by User on 22.04.2017.
 */
public class AppStartListener {

    static {
        PropertyConfigurator.configure(AppStartListener.class.getClassLoader()
                .getResource("log.properties"));
    }

}
