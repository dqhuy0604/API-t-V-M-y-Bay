package com.mycompany.ticket;

import com.mycompany.flight.Flight;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "flight_id",nullable = false)
    private Flight flight_no;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String ticket_type;

    @Column(nullable = false)
    private LocalDateTime ticket_date;

    @PrePersist
    protected void onCreate() {
        this.ticket_date = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private TicketStatus ticketStatus =TicketStatus.Pending;


    public Flight getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(Flight flight_no) {
        this.flight_no = flight_no;
    }
    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public LocalDateTime getTicket_date() {
        return ticket_date;
    }

    public void setTicket_date(LocalDateTime ticket_date) {
        this.ticket_date = ticket_date;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void rejectStatus(){
        if (this.ticketStatus != null) {
            this.ticketStatus = TicketStatus.fromStatusCode(3);
            if (this.ticketStatus == null) {
                this.ticketStatus = ticketStatus.Cancelled;
            }
        }
    }
    public void acceptStatus(){
        if (this.ticketStatus != null) {
            this.ticketStatus = TicketStatus.fromStatusCode(2);
            if (this.ticketStatus == null) {
                this.ticketStatus = ticketStatus.Confirmed;
            }
        }
    }

}
