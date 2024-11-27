package com.mycompany.flight;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.mycompany.airport.Airport;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name="flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destinationAirport;

    @Column(nullable = false)
    private LocalTime departureTime;

    private LocalTime destinationTime;

    @Column(nullable = false)
    private LocalDate departureDay;

    @Column(nullable = false)
    private String airlineCode;

    @Column(nullable = false)
    private int seatCapacity;

    private FlightStatus flightStatus = FlightStatus.PLANNING;

    private int priceGeneral;

    private int pricePrime;

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(LocalTime destinationTime) {
        this.destinationTime = destinationTime;
    }

    public LocalDate getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(LocalDate departureDay) {
        this.departureDay = departureDay;
    }

    public Long getId() {
        return id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public int getPriceGeneral() {
        return priceGeneral;
    }

    public void setPriceGeneral(int priceGeneral) {
        this.priceGeneral = priceGeneral;
    }

    public int getPricePrime() {
        return pricePrime;
    }

    public void setPricePrime(int pricePrime) {
        this.pricePrime = pricePrime;
    }

    public FlightStatus getStatus() {
        return flightStatus;
    }

    public void setStatus(FlightStatus status) {
        this.flightStatus = status;
    }
    public void nextStatus() {
        if (this.flightStatus != null) {
            this.flightStatus = FlightStatus.fromStatusCode(this.flightStatus.getStatusCode() + 1);
            if (this.flightStatus == null) {
                this.flightStatus = FlightStatus.COMPLETED;
            }
        }
    }
    public String getStatusName() {
        return FlightStatus.getNameByCode(this.flightStatus.getStatusCode());
    }

}


