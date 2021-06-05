package application;

import application.config.HibernateConfig;
import application.model.User;
import application.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Lastname1", "user1@mail.ru");
        User user2 = new User("Lastname2", "user2@mail.ru");
        User user3 = new User("Lastname3", "user3@mail.ru");
        User user4 = new User("Lastname4", "user4@mail.ru");




        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
        context.close();
    }
}
