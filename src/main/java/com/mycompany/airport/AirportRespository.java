package com.mycompany.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRespository extends CrudRepository <Airport, Integer> {
    List<Airport> findByCodeStartingWith(String query);
    public Long countById(Integer id);

    @Query("SELECT a.code FROM Airport a")
    List<String> findAllCodes();
    Optional<Airport> findByCode(String code);
}

