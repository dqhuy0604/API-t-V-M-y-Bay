package com.mycompany.airport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired private AirportRespository repo;


    public List<String> listCode() {
        return repo.findAllCodes();
    }
    public List<Airport> searchAirportsByCode(String query) {
        return repo.findByCodeStartingWith(query);  // Tìm kiếm theo mã sân bay
    }
    public List<Airport> listAll() {
        return (List<Airport>) repo.findAll();
    }
    public void save(Airport airport) {
        repo.save(airport);
    }

    public Airport get(Integer id) throws AirportNotFoundException {
        Optional<Airport> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new AirportNotFoundException("Could not find any airport with ID" +id);
    }

    public void delete(Integer id) throws AirportNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new AirportNotFoundException("Could not find any airport with ID" +id);
        }
        repo.deleteById(id);
    }
}
