package application.service;

import application.model.User;
import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserById(int id);
    void update(int id);
    void delete(int id);
}
