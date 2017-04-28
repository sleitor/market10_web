package main.models.DAO;

import main.models.ConnectionPool;
import main.models.pojo.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


public class UserDAO implements UserInterface {
    @Override
    public HashSet<User> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public User getByID(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean create(User user){

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users(userName, email, firstName, secondName, lastName, address, password)VALUES (?,?,?,?,?,?,?)"
                );
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
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteByID(Long id) {

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
            e.printStackTrace();
        }

        return null;
    }
}
