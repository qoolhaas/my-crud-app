package dao;

public class UserDaoFactory {

    public UserDAO getUserDAO(String database) {
        UserDAO userDAO;

        switch(database) {
            case "jdbc":
                userDAO = UserJdbcDAO.getInstance();
                break;
            case "hibernate":
                userDAO = UserHibernateDAO.getInstance();
                break;
            default:
                throw new RuntimeException("Wrong database type");
        }

        return userDAO;
    }
}
