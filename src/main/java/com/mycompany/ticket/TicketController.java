package com.mycompany.ticket;

import com.mycompany.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TicketController {
    @Autowired TicketService service;

    @GetMapping("/tickets/reject-status/{id}")
    public String rejectTicketStatus(@PathVariable int id) {
        service.rejectTicketStatus(id);
        return "redirect:/tickets";

    }
    @GetMapping("/tickets/accept-status/{id}")
    public String updateTicketStatus(@PathVariable int id) {
        service.acceptTicketStatus(id);
        return "redirect:/tickets";
    }
    @GetMapping("/tickets")
    public String showFlightList(Model model) {
        List<Ticket> listtickets = service.listAll();
        model.addAttribute("listtickets", listtickets);
        return "tickets";
    }
}
