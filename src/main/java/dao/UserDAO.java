package dao;

import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addUser(User user) throws SQLException {
        boolean flag = false;
        String query = "insert into db_example.users (name, password) values (?, ?)";

        if(!isNameExist(user.getName())) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.executeUpdate();
                flag = true;
            }
        }

        return flag;
    }

    public boolean isNameExist (String name) {
        String query = "SELECT * FROM db_example.users WHERE name LIKE '" + name + "';";

        try(Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query)) {
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<User> listUsers() throws SQLException {
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

        try(Statement stmt = connection.createStatement()) {
            ResultSet result = stmt.executeQuery("select * from users where name='" + name + "'");

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
    public void deleteUserByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("delete from db_example.users where name = '"+ name +"'");;
        stmt.close();
    }

    public boolean editUser(User user) throws SQLException {
        System.out.println("DAO "+ user.getId());
        String query = "update db_example.users set name = '"+user.getName()+"', password = '"+user.getPassword()+"' where id = "+user.getId()+"";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
        return true;
    }
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), password varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
