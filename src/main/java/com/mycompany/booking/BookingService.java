package com.mycompany.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired BookingRespository repo;

    public List<Booking> listAll() {
        return (List<Booking>) repo.findAll();
    }
    public void save(Booking booking) {
        repo.save(booking);
    }

    public void rejectBookingStatus(int id) {
        Booking booking = repo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        booking.rejectStatus();
        repo.save(booking);
    }
    public void acceptBookingStatus(int id) {
        Booking booking = repo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        booking.acceptStatus();
        repo.save(booking);
    }

    public Booking get(Integer id) throws BookingNotFoundException {
        Optional<Booking> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new BookingNotFoundException("Could not find any Booking with ID" +id);
    }

    public void delete(Integer id) throws BookingNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new BookingNotFoundException("Could not find any Booking with ID" +id);
        }
        repo.deleteById(id);
    }
}
