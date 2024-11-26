package com.mycompany.ticket;

import org.springframework.data.repository.CrudRepository;

public interface TicketRespository extends CrudRepository<Ticket, Integer> {
    public Long countById(Integer id);
}
