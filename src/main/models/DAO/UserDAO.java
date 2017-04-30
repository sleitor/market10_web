package main.models.DAO;

import main.models.ConnectionPool;
import main.models.pojo.User;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            return user;

        } catch (SQLException e) {
            logger.debug("Ошибка получения пользователя");
        }

        throw new NotImplementedException();
    }

    @Override
    public boolean create(User user) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users(userName, email, firstName, secondName, lastName, address, password)VALUES (?,?,?,?,?,?,?)");
        ) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getSecondName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getPassword());
            int co = preparedStatement.executeUpdate();
            boolean rez = co == 1;
            return rez;

        } catch (SQLException e) {
            logger.debug("Ошибка добавления пользователя");
        }

        return false;
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

                return user;
            }


        } catch (SQLException e) {
            logger.debug("Ошибка поиска пользователя по логину и паролю");
        }

        return null;
    }
}
