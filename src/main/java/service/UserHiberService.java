package service;

import dao.UserHibernateDAO;
import entity.User;
import org.hibernate.HibernateException;

import java.util.List;

public class UserHiberService implements UserService<User>  {
    private static UserHiberService userHiberService;
    private UserHibernateDAO dao;

    private UserHiberService() {
        dao = UserHibernateDAO.getInstance();
    }

    public static UserHiberService getInstance() {
        if (userHiberService == null) {
            userHiberService = new UserHiberService();
        }
        return userHiberService;
    }

    public boolean addUser(User user) {
        boolean flag = false;

        try {
            flag = dao.addUser(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public List<User> listUsers() {
        List<User> list = null;

        try {
            list = dao.listUsers();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deleteUserById(Long id) {
        try {
            dao.deleteUserById(id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public boolean editUser(User user) {
        boolean flag = false;

        try {
            flag = dao.editUser(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
