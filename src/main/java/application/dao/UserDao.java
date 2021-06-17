package application.dao;

import application.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User getUserById(long id);
    User getUserByName(String name);
    void update(long id, User user);
    void delete(long id);
    void deleteAll();
}
