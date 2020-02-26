package service;

import dao.UserDAO;
import entity.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
    public boolean addUser(User user) {
        boolean flag = false;

        try {
            flag = getUserDAO().addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
}

    public List<User> listUsers() {
        List<User> list = new ArrayList<>();

        try {
            list = getUserDAO().listUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteUserByName(String name) {
        try {
            getUserDAO().deleteUserByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean editUser (User user) {
        boolean flag = false;
        try {
            getUserDAO().editUser(user);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public void createTable() {
        try {
            getUserDAO().createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            getUserDAO().dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").
                    append("autoReconnect=true&useSSL=false&").
                    append("useLegacyDatetimeCode=false&serverTimezone=UTC&").
                    append("user=root&").          //login
                    append("password=fill7890");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());

            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();

            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
