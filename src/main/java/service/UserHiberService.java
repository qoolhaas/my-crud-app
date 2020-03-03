package service;

import dao.*;
import entity.User;
import org.hibernate.HibernateException;

import java.util.List;

public class UserHiberService implements UserService<User>  {
    private static UserHiberService userHiberService;
    private UserDAO dao;

    private UserHiberService() {
        dao = UserHibernateDAO.getInstance();
    }

    public static UserHiberService getInstance() {
        if (userHiberService == null) {
            userHiberService = new UserHiberService();
        }
        return userHiberService;
    }

    public boolean addUser(User user) { return dao.addUser(user); }

    public List<User> listUsers() { return dao.listUsers(); }

    public void deleteUserById(Long id) { dao.deleteUserById(id); }

    public boolean editUser (User user) { return dao.editUser(user); }
}
