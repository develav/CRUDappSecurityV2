package application.service;

import application.model.User;
import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserById(long id);
    void update(long id, User user);
    void delete(long id);
    void deleteAll();
}
