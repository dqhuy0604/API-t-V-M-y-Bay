package com.mycompany.ticket;

import com.mycompany.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired private TicketRespository repo;

    public List<Ticket> listAll() {
        return (List<Ticket>) repo.findAll();
    }
    public void save(Ticket ticket) {
        repo.save(ticket);
    }
    public void rejectTicketStatus(int id) {
        Ticket ticket = repo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        ticket.rejectStatus();
        repo.save(ticket);
    }
    public void acceptTicketStatus(int id) {
        Ticket ticket = repo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        ticket.acceptStatus();
        repo.save(ticket);
    }

    public Ticket get(Integer id) throws TicketNotFoundException {
        Optional<Ticket> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new TicketNotFoundException("Could not find any airport with ID" +id);
    }

    public void delete(Integer id) throws TicketNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new TicketNotFoundException("Could not find any airport with ID" +id);
        }
        repo.deleteById(id);
    }
}
