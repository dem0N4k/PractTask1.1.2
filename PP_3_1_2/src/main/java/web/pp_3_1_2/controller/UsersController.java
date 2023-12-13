package web.pp_3_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.pp_3_1_2.model.User;
import web.pp_3_1_2.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }

//    @GetMapping("/saveUser")
//    public String saveUserForm(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "user-create";
//    }

    @GetMapping("/saveUser")
    public String saveUserForm(User user) {
        return "user-create";
    }

//    @GetMapping("/saveUser")
//    public String saveUserForm(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "user-create";
//    }

    @PostMapping("/saveUser")
//    public String saveUser(User user) {
        public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateUser")
    public String updateUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
//        public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}