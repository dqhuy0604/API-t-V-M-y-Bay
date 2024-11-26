package com.mycompany.airport;
import com.mycompany.flight.Flight;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="airport")
public class Airport {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;
    private String city;
    private String country;
    // Getters and Setters

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departingFlights;

    @OneToMany(mappedBy = "destinationAirport")
    private List<Flight> arrivingFlights;

    public List<Flight> getDepartingFlights() {
        return departingFlights;
    }
    public void setDepartingFlights(List<Flight> departingFlights) {
        this.departingFlights = departingFlights;
    }
    public List<Flight> getArrivingFlights() {
        return arrivingFlights;
    }

    public void setArrivingFlights(List<Flight> arrivingFlights) {
        this.arrivingFlights = arrivingFlights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}


