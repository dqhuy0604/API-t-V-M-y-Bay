package com.mycompany.flight;

import com.mycompany.airport.Airport;
import com.mycompany.airport.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FlightController {
    @Autowired private  FlightService service;

    @Autowired private AirportService airportService;


    @GetMapping("/booking_ticket")
    public String showFlightInputForm(Model model) {
        List<Airport> listAirports = airportService.listAll();
        model.addAttribute("listAirports", listAirports);
        return "booking_ticket"; // Trang nhập liệu
    }

    @GetMapping("/ticket_list")
    public String searchFlights(
            @RequestParam String departureAirportCode,
            @RequestParam String destinationAirportCode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDay,
            Model model) {

        List<Flight> flights = service.getFlightsByDateAndPlace(departureAirportCode, destinationAirportCode, departureDay);
        model.addAttribute("flights", flights);
        model.addAttribute("departureAirportCode", departureAirportCode);
        model.addAttribute("destinationAirportCode", destinationAirportCode);
        model.addAttribute("departureDay", departureDay);
        return "ticket_list";
    }
//    @GetMapping("/ticker_choose")
//    public String CartFlights(Model model){
//        int flightcartid,
//        ser
//
//        model.addAttribute("cartFlights", flightcartid);
//        return "ticker_choose";
//    }

    @GetMapping("/flights/update-status/{id}")
    public String updateFlightStatus(@PathVariable int id) {
        service.updateFlightStatus(id);
        return "redirect:/flights";

    }

    @GetMapping("/flights")
    public String showFlightList(Model model) {
        List<Flight> listFlights = service.listAll();
        model.addAttribute("listFlights", listFlights);
        return "flights";
    }

    @GetMapping("/flights/new")
    public String showNewForm(Model model){
        List<Airport> listAirports = airportService.listAll();
        model.addAttribute("flight",new Flight());
        model.addAttribute("pageTitle", "Add New Flight");
        model.addAttribute("listAirports", listAirports);
        return "flight_form";
    }
    @PostMapping("/flights/save")
    public String saveFlight(Flight flight, RedirectAttributes ra){
        service.save(flight);
        ra.addFlashAttribute("message", "The Flight has been saved successfully");
        return "redirect:/flights";
    }

    @GetMapping("/flights/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            List<Airport> listAirports = airportService.listAll();
            Flight flight = service.get(id);
            model.addAttribute("flight", flight);
            model.addAttribute("pageTitle", "Edit Flight (ID " + id + ")");
            model.addAttribute("airports", airportService.listCode());
            model.addAttribute("listAirports", listAirports);
            return "flight_form";
        } catch (FlightNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/flights";
        }
    }
    @GetMapping("/ticker_choose/{id}")
    public String showCartForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            List<Airport> listAirports = airportService.listAll();
            Flight flight = service.get(id);
            model.addAttribute("flight", flight);
            model.addAttribute("pageTitle", "Edit Flight (ID " + id + ")");
            model.addAttribute("airports", airportService.listCode());
            model.addAttribute("listAirports", listAirports);
            return "ticker_choose";
        } catch (FlightNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/ticker_choose";
        }
    }

    @GetMapping("/flights/delete/{id}")
    public String deleteFlight(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The admin ID " +id+ " has been deleted successfully");
        } catch (FlightNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/flights";
    }
}
