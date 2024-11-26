package com.mycompany.payment;

import com.mycompany.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired PaymentRespository repo;

    public List<Payment> listAll() {
        return (List<Payment>) repo.findAll();
    }
    public void save(Payment booking) {
        repo.save(booking);
    }
    public void rejectPaymentStatus(int id) {
        Payment payment = repo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        payment.rejectStatus();
        repo.save(payment);
    }
    public void acceptPaymentStatus(int id) {
        Payment payment = repo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        payment.acceptStatus();
        repo.save(payment);
    }

    public Payment get(Integer id) throws PaymentNotFoundException {
        Optional<Payment> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new PaymentNotFoundException("Could not find any Booking with ID" +id);
    }

    public void delete(Integer id) throws PaymentNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new PaymentNotFoundException("Could not find any Booking with ID" +id);
        }
        repo.deleteById(id);
    }

}
