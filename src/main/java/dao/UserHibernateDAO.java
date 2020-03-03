package dao;

import entity.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO<User> {

    private static SessionFactory sessionFactory;
    private static UserHibernateDAO userHibernateDAO;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }

        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private UserHibernateDAO(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    public static UserHibernateDAO getInstance() {
        if (userHibernateDAO == null) {
            userHibernateDAO = new UserHibernateDAO(getSessionFactory());
        }
        return userHibernateDAO;
    }

    public boolean addUser(User user) {
        boolean flag = false;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            if (!isNameExist(user.getName())) {
                transaction = session.beginTransaction();
                session.save(user);
                flag = true;
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            throw new HibernateException(e);
        }
        session.close();

        return flag;
    }

    public boolean isNameExist (String name) {
        List<User> list = null;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User where name = :name");
            query.setParameter("name", name);
            list = query.list();
            transaction.commit();
        } catch (Exception e) {e.printStackTrace();
            transaction.rollback();
            e.printStackTrace();
        }
        session.close();

        return !list.isEmpty();
    }

    public List<User> listUsers() {
        List<User> list = null;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
            list = session.createQuery("from User").list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        session.close();

        return list;
    }

    public User getUserByName(String name) {
        Query query = null;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
            query = session.createQuery("from User where name = :name");
            query.setParameter("name", name);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        session.close();

        return (User) query.list().get(0);
    }

    public void deleteUserById(Long id) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from User where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public boolean editUser(User user) {
        boolean flag = false;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            if (!isNameExist(user.getName())) {
                transaction = session.beginTransaction();
                session.saveOrUpdate(user);
                session.flush();
                flag = true;
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        session.close();

        return flag;
    }
}
