package main.models;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by User on 21.04.2017.
 */
public class ConnectionPool  {

    private static final ConnectionPool INSTANCE = new ConnectionPool();

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
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            boneCP = new BoneCP(config);

        } catch (IOException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return boneCP.getConnection();
    }


}
