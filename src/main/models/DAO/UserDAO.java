package main.models.DAO;

import main.models.ConnectionPool;
import main.models.pojo.User;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.HashSet;


public class UserDAO implements UserInterface {

    private Logger logger = Logger.getLogger(UserDAO.class);

    @Override
    public HashSet<User> getAll() {

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")

        ) {
            HashSet<User> users = new HashSet<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getBoolean(9)
                );
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            logger.debug("Ошибка получения списка пользователей");
        }
        throw new NotImplementedException();
    }

    @Override
    public User getByID(Long id) {

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE  uuid=?");
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getBoolean(9)
            );

        } catch (SQLException e) {
            logger.debug("Ошибка получения пользователя");
        }

        throw new NotImplementedException();
    }

    @Override
    public int create(User user) {
        int id = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users(userName, email, firstName, secondName, lastName, address, password)VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getSecondName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
                PreparedStatement statement1 = connection.prepareStatement("INSERT INTO user_roles(login) VALUES (?)");
                statement1.setString(1, user.getUserName());
                statement1.executeUpdate();
            }
        } catch (SQLException e) {
            logger.debug("Ошибка добавления пользователя");
        }

        return id;
    }

    @Override
    public void update(User user) {

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE users SET email=?, firstName=?, secondName=?, lastName=?, address=?, password=?, role=? WHERE uuid=?")
        ) {

            statement.setLong(8, user.getUuid());
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getPassword());
            statement.setBoolean(7, user.isRole());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ошибка обновления пользователя");
        }
    }

    @Override
    public void deleteByID(Long id) {

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE uuid=?")
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ошибка удаления пользователя");
        }
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("SELECT * FROM users WHERE userName=? AND password =?");
        ) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getLong(1) > 0) {

                return new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getBoolean(9)
                );
            }


        } catch (SQLException e) {
            logger.debug("Ошибка поиска пользователя по логину и паролю");
        }

        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("SELECT * FROM users WHERE userName=?");
        ) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getLong(1) > 0) {

                return new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getBoolean(9)
                );
            }


        } catch (SQLException e) {
            logger.debug("Ошибка поиска пользователя по логину и паролю");
        }

        return null;
    }
}
