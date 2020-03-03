package service;

import java.util.List;

public interface UserService<T> {
    boolean addUser(T user);
    List<T> listUsers();
    void deleteUserById(Long id);
    boolean editUser (T user);
}
