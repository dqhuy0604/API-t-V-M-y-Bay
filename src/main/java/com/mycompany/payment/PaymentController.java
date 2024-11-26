package com.mycompany.payment;

import com.mycompany.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PaymentController {
    @Autowired PaymentService service;

    @GetMapping("/payments/reject-status/{id}")
    public String rejectPaymentStatus(@PathVariable int id) {
        service.rejectPaymentStatus(id);
        return "redirect:/payments";

    }
    @GetMapping("/payments/accept-status/{id}")
    public String updatePaymentStatus(@PathVariable int id) {
        service.acceptPaymentStatus(id);
        return "redirect:/payments";
    }
    @GetMapping("/payments")
    public String showFlightList(Model model) {
        List<Payment> listPayments = service.listAll();
        model.addAttribute("listPayments", listPayments);
        return "payments";
    }
}
