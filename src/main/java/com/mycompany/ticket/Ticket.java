package com.mycompany.ticket;

import com.mycompany.customer.Customer;
import com.mycompany.flight.Flight;
import com.mycompany.payment.PaymentStatus;
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

    @ManyToOne
    @JoinColumn(name="customer_id",nullable=false)
    private Customer customer_id;

    @Column(nullable = false)
    private String ticket_type;

    @Column(nullable = false)
    private LocalDateTime ticket_date;

    @PrePersist
    protected void onCreate() {
        this.ticket_date = LocalDateTime.now();
    }
    private PaymentStatus status = PaymentStatus.Pending;

    private TicketStatus ticketStatus =TicketStatus.Pending;


    @Column(nullable = false)
    private int no_of_passengers;

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Flight getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(Flight flight_no) {
        this.flight_no = flight_no;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
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

    public int getNo_of_passengers() {
        return no_of_passengers;
    }

    public void setNo_of_passengers(int no_of_passengers) {
        this.no_of_passengers = no_of_passengers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void rejectStatus(){
        if (this.status != null) {
            this.status = PaymentStatus.fromStatusCode(3);
            if (this.status == null) {
                this.status = status.Cancelled;
            }
        }
    }
    public void acceptStatus(){
        if (this.status != null) {
            this.status = PaymentStatus.fromStatusCode(2);
            if (this.status == null) {
                this.status = status.Confirmed;
            }
        }
    }

}
