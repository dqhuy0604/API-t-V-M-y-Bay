package com.mycompany.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {
    @Autowired private  AdminService service;

    @GetMapping("/admins")
    public String showAdminList(Model model) {
        List<Admin> listAdmins = service.listAll();
        model.addAttribute("listAdmins", listAdmins);
        return "admins";

    }
    @GetMapping("/admins/new")
    public String showNewForm(Model model){
            model.addAttribute("admin",new Admin());
            model.addAttribute("pageTitle", "Add New Admin");
            return "admin_form";

    }
    @PostMapping("/admins/save")
    public String saveAdmin(Admin admin, RedirectAttributes ra){
        service.save(admin);
        ra.addFlashAttribute("message", "The admin has been saved successfully");
        return "redirect:/admins";
    }

    @GetMapping("/admins/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Admin admin = service.get(id);
            model.addAttribute("admin", admin);
            model.addAttribute("pageTitle", "Edit Admin (ID " + id + ")");
            return "admin_form";
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admins";
        }
    }

    @GetMapping("/admins/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The admin ID " +id+ " has been deleted successfully");
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admins";
    }
}
