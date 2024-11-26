package com.mycompany.booking;
import com.mycompany.airport.Airport;
import com.mycompany.customer.Customer;
import com.mycompany.customer.CustomerService;
import com.mycompany.flight.Flight;
import com.mycompany.payment.Payment;
import com.mycompany.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {
    @Autowired BookingService service;

    @Autowired CustomerService customerService;

    @Autowired PaymentService paymentService;

    @GetMapping("/customer_input/new")
    public String showNewForm(Model model){
        model.addAttribute("booking",new Booking());
        model.addAttribute("customer",new Customer());
        model.addAttribute("payment",new Payment());
        model.addAttribute("pageTitle", "Add New Booking");
        return "customer_input";
    }
    @PostMapping("/customer_input/save")
    public String saveBooking(Booking booking, RedirectAttributes ra){
        service.save(booking);
        ra.addFlashAttribute("message", "The Flight has been saved successfully");
        return "redirect:/customer_input";
    }


    @GetMapping("/bookings/reject-status/{id}")
    public String rejectFlightStatus(@PathVariable int id) {
        service.rejectBookingStatus(id);
        return "redirect:/bookings";

    }
    @GetMapping("/customer_input")
    public String customerInput(Model model) {
        return "customer_input";
    }

    @GetMapping("/bookings/accept-status/{id}")
    public String acceptFlightStatus(@PathVariable int id) {
        service.acceptBookingStatus(id);
        return "redirect:/bookings";
    }
    @GetMapping("/bookings")
    public String showFlightList(Model model) {
        List<Booking> listBookings = service.listAll();
        model.addAttribute("listFlights", listBookings);
        return "bookings";
    }
}
