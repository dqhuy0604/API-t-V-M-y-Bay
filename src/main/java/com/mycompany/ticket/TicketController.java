package com.mycompany.ticket;

import com.mycompany.airport.Airport;
import com.mycompany.airport.AirportService;
import com.mycompany.flight.Flight;
import com.mycompany.flight.FlightNotFoundException;
import com.mycompany.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class TicketController {
    @Autowired TicketService service;

    @Autowired FlightService flightService;



    @GetMapping("/customer_input/{id}")
    public String showNewForm(@PathVariable("id") Integer id,Model model) throws FlightNotFoundException {
        Flight flight =  flightService.get(id);
        Ticket ticket = new Ticket();
        ticket.setFlight_no(flight);
        model.addAttribute("ticket",ticket);
        model.addAttribute("pageTitle", "Add New Ticket");
        model.addAttribute("flight", flight);
        return "customer_input";
    }

    @PostMapping("/customer_input/save")
    public String saveTicket(Ticket ticket, RedirectAttributes ra){
        service.save(ticket);
        ra.addFlashAttribute("message", "The Flight has been saved successfully");
        return "redirect:/index";
    }

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
