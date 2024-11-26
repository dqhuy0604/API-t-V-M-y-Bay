package com.mycompany.booking;

import org.springframework.data.repository.CrudRepository;

public interface BookingRespository extends CrudRepository<Booking, Integer> {
    public Long countById(Integer id);
}
