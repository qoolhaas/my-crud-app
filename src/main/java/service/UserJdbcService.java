package service;

import dao.*;
import entity.User;

import java.util.List;

public class UserJdbcService implements UserService<User> {
    private static UserJdbcService userJdbcService;
    private UserDAO dao;

    private UserJdbcService() {
        dao = UserJdbcDAO.getInstance();
    }

    public static UserJdbcService getInstance() {
        if (userJdbcService == null) {
            userJdbcService = new UserJdbcService();
        }
        return userJdbcService;
    }

    public boolean addUser(User user) { return dao.addUser(user); }

    public List<User> listUsers() { return dao.listUsers(); }

    public void deleteUserById(Long id) { dao.deleteUserById(id); }

    public boolean editUser (User user) { return dao.editUser(user); }
}
