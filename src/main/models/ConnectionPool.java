package main.models;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс для создания соединения с базой Данных
 */
public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private static boolean isAlive = true;

    public static boolean isIsAlive() {
        return isAlive;
    }

    private BoneCP boneCP;

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private ConnectionPool() {
        Properties dbProperties = new Properties();
        try (InputStream is = ConnectionPool.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            dbProperties.load(is);
            Class.forName("com.mysql.jdbc.Driver");

            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(dbProperties.getProperty("url"));
            config.setUsername(dbProperties.getProperty("user"));
            config.setPassword(dbProperties.getProperty("password"));
            config.setMinConnectionsPerPartition(1);
            config.setMaxConnectionsPerPartition(1);
            config.setPartitionCount(1);
            boneCP = new BoneCP(config);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            isAlive = false;
            logger.debug("Ошибка соединения с базой данных. Connection");
        }
    }

    public Connection getConnection() {

        try {
            if (isAlive) {
                return boneCP.getConnection();
            }

        } catch (SQLException | NullPointerException e) {
            isAlive = false;
            logger.debug("Ошибка соединения с базой данных. getConnection");
        }
        return null;
    }


}
