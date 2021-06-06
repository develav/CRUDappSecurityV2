package application.controller;

import application.model.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {
        private final UserService userService;
        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/")
        public String allMovies(Model model) {
            List<User> users = userService.listUsers();
            model.addAttribute("allUsers", users);
            return "users";
        }

        @GetMapping("/{id}")
        public String concreteUser(@PathVariable("id") long id, Model model) {
            model.addAttribute("user", userService.getUserById(id));
            return "concreteUser";
        }

        @GetMapping("/create")
        public String create(Model model) {
            model.addAttribute("user", new User());
            return "create";
        }

        @PostMapping()
        public String createUser(@ModelAttribute("user") User user) {
            userService.add(user);
            return "redirect:/";
        }

        @GetMapping("/update/{id}")
        public String update(Model model, @PathVariable("id") long id) {
            model.addAttribute("user", userService.getUserById(id));
            return "update";
        }

        @PatchMapping("/{id}")
        public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
            userService.update(id, user);
            return "redirect:/";
        }

        @DeleteMapping("/{id}")
        public String delete(@PathVariable("id") long id){
            userService.delete(id);
            return "redirect:/";
        }
}
