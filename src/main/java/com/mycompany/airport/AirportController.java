package com.mycompany.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AirportController {
    @Autowired private  AirportService service;

    @GetMapping("/airports")
    public String showAirportList(Model model) {
        List<Airport> listAirport = service.listAll();
        model.addAttribute("listAirport", listAirport);
        return "airports";

    }
    @GetMapping("/airports/new")
    public String showNewForm(Model model){
        model.addAttribute("airport",new Airport());
        model.addAttribute("pageTitle", "Add New Airport");
        return "airport_form";

    }
    @PostMapping("/airports/save")
    public String saveAirport(Airport airport, RedirectAttributes ra){
        service.save(airport);
        ra.addFlashAttribute("message", "The airport has been saved successfully");
        return "redirect:/airports";
    }

    @GetMapping("/airports/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Airport airport = service.get(id);
            model.addAttribute("airport", airport);
            model.addAttribute("pageTitle", "Edit Airport (ID " + id + ")");
            return "airport_form";
        } catch (AirportNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/airports";
        }
    }

    @GetMapping("/airports/delete/{id}")
    public String deleteAirport(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The Airport ID " +id+ " has been deleted successfully");
        } catch (AirportNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/airports";
    }
}
