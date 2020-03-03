package dao;

import java.util.List;

public interface UserDAO<T> {
    boolean addUser(T user);
    List<T> listUsers();
    void deleteUserById(Long id);
    boolean editUser(T user);

    boolean isNameExist (String name);
    T getUserByName(String name);
}
