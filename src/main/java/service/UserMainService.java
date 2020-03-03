package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class UserMainService implements UserService<User> {
    private UserDAO dao;
    private static UserMainService userMainService;
    Properties prop = null;

    private UserMainService() {
        try(InputStream input = UserMainService.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao = new UserDaoFactory().getUserDAO(prop.getProperty("db.name"));
    }

    public static UserMainService getInstance() {
        if (userMainService == null) {
            userMainService = new UserMainService();
        }
        return userMainService;
    }
    public boolean addUser(User user) { return dao.addUser(user); }

    public List<User> listUsers() { return dao.listUsers(); }

    public void deleteUserById(Long id) { dao.deleteUserById(id); }

    public boolean editUser (User user) { return dao.editUser(user); }
}
