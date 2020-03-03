package dao;

import entity.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO<User> {
    private Connection connection;
    private static UserJdbcDAO userJdbcDAO;

    private UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public static UserJdbcDAO getInstance() {
        if (userJdbcDAO == null) {
            userJdbcDAO = new UserJdbcDAO(DBHelper.getJdbcSqlConnection());
        }
        return userJdbcDAO;
    }

    public boolean addUser(User user) {
        boolean flag = false;
        String query = "insert into db_example.users (name, password) values (?, ?)";

        if(!isNameExist(user.getName())) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    public boolean isNameExist (String name) {
        String query = "SELECT * FROM db_example.users WHERE name LIKE ?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<User> listUsers() {
        List<User> list = new ArrayList<>();
        String query = "select * from db_example.users";

        try(Statement stmt = connection.createStatement()) {
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                User user = getUserByName(result.getString(2));
                list.add(user);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public User getUserByName(String name) {
        User user = new User();
        String query = "select * from users where name= ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setPassword(result.getString(3));
                result.close();

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public void deleteUserById(Long id) {
        String query = "delete from db_example.users where id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean editUser(User user) {
        boolean flag = false;
        String query = "update db_example.users set name = ?, password = ? where id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void createTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), password varchar(256), primary key (id))");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
