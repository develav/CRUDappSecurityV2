package application.controller;

import application.model.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/delete")
    public String delete(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    //    @GetMapping("/hello")
//    public String printWelcome(ModelMap model) {
//        List<String> messages = new ArrayList<>();
//        messages.add("Hello!");
//        messages.add("I'm Spring MVC-SECURITY application");
//        messages.add("5.2.0 version by sep'19 ");
//        model.addAttribute("messages", messages);
//        return "hello";
//    }
//
    @GetMapping("/admin/users")
    public String users(ModelMap model) {
        List<User> userList = userService.listUsers();
        model.addAttribute(userList);
        System.out.println(userList);
        return "users";
    }

    @GetMapping("/admin")
    public String adminPage(ModelMap model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.getUserByName(username);
        model.addAttribute(user);
        return "admin";
    }

    @GetMapping("/user")
    public String printWelcome(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.getUserByName(username);
        model.addAttribute(user);
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/admin/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/admin/edit/{id}")
    public String showUpdateForm(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-user";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
                             @PathVariable("id") long id) {
        User user = userService.getUserById(id);
        user.setName(name);
        user.setSurname(surname);
        userService.update(id, user);
        return "redirect:/admin/users";
    }

}

