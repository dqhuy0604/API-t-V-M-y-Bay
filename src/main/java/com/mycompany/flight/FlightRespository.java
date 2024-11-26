package com.mycompany.flight;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRespository extends CrudRepository <Flight, Integer> {
    public Long countById(Integer id);

    public List<Flight> findByDepartureAirport_CodeAndDestinationAirport_CodeAndDepartureDay(
            String departureAirportCode, String destinationAirportCode, LocalDate departureDay);

}
