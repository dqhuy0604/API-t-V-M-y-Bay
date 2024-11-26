package com.mycompany.customer;

import com.mycompany.flight.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService service;

//    @GetMapping("")
//    public String showCustomerList(Model model) {
//        List<Customer> listCustomer = service.listAll();
//        model.addAttribute("listCustomer", listCustomer);
//        return "";
//    }
//    @PostMapping("")
//    public String saveCustomer(Customer customer, RedirectAttributes ra){
//        service.save(customer);
//        ra.addFlashAttribute("message", "The Flight has been saved successfully");
//        return "";
//    }
}
