package com.mycompany.user;
import com.mycompany.flight.FlightNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class UserController {
    @Autowired UserService service;



    @GetMapping("/signup")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle", "Add New User");
        return "signup";
    }
    @PostMapping("/signup/save")
    public String saveFlight(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message", "The User has been saved successfully");
        return "redirect:/index" ;
    }

    @GetMapping("/login")
    public String showLoginUser(Model model) {
        List<User> listUser = service.listAll();
        model.addAttribute("listUser", listUser);
        return "/admins";
    }
    @GetMapping("/support")
    public String showSupportUser(Model model) {
        List<User> listUser = service.listAll();
        model.addAttribute("listUser", listUser);
        return "support";
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUser = service.listAll();
        model.addAttribute("listUser", listUser);
        return "users";
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The admin ID " +id+ " has been deleted successfully");
        } catch (UserNotFoundException  e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

}
