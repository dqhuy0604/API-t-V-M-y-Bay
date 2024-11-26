package com.mycompany.flight;

import com.mycompany.airport.Airport;
import com.mycompany.airport.AirportRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class FlightService {
    @Autowired private FlightRespository repo;

    private final AirportRespository airportRespository;

    public List<Flight> getFlightsByDateAndPlace(String departureAirportCode, String destinationAirportCode,
                                                 LocalDate departureDay ) {
        return repo.findByDepartureAirport_CodeAndDestinationAirport_CodeAndDepartureDay(
                departureAirportCode, destinationAirportCode, departureDay
        );
    }

    public FlightService(AirportRespository airportRespository) {
        this.airportRespository = airportRespository;
    }

    public void updateFlightStatus(int id) {
        Flight flight = repo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        flight.nextStatus();
        repo.save(flight);
    }
    public List<Flight> listAll() {
        return (List<Flight>) repo.findAll();
    }
    public void save(Flight flight) {
        repo.save(flight);
    }

    public Flight get(Integer id) throws FlightNotFoundException {
        Optional<Flight> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new FlightNotFoundException("Could not find any airport with ID" +id);
    }

    public void delete(Integer id) throws FlightNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new FlightNotFoundException("Could not find any airport with ID" +id);
        }
        repo.deleteById(id);
    }
    public Airport getAirportByCode(String airportCode) {
        return airportRespository.findByCode(airportCode)
                .orElseThrow(() -> new IllegalArgumentException("Airport not found for code: " + airportCode));
    }
}
