package com.mycompany.booking;

import com.mycompany.customer.Customer;
import com.mycompany.flight.Flight;
import com.mycompany.booking.BookingStatus;
import com.mycompany.payment.Payment;
import com.mycompany.ticket.Ticket;
import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="customer_id" ,nullable = false)
    private Customer customer_id;

    @ManyToOne
    @JoinColumn(name="flight_id" ,nullable = false)
    private Flight flight_no;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name="ticket_id" ,nullable = false)
    private Ticket ticket_no;

    public Ticket getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(Ticket ticket_no) {
        this.ticket_no = ticket_no;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(nullable = false)
    private int no_of_passengers;

    @Column(nullable = false)
    private BookingStatus status = BookingStatus.Pending;

    @Column(nullable = false)
    private Long total_price;

    @OneToOne
    @JoinColumn(name="payment_id" ,nullable = false)
    private Payment payment_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    public Flight getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(Flight flight_no) {
        this.flight_no = flight_no;
    }

    public int getNo_of_passengers() {
        return no_of_passengers;
    }

    public void setNo_of_passengers(int no_of_passengers) {
        this.no_of_passengers = no_of_passengers;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Long getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Long total_price) {
        this.total_price = total_price;
    }

    public Payment getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Payment payment_id) {
        this.payment_id = payment_id;
    }
    public void rejectStatus(){
        if (this.status != null) {
            this.status = BookingStatus.fromStatusCode(3);
            if (this.status == null) {
                this.status = status.Cancelled;
            }
        }
    }
    public void acceptStatus(){
        if (this.status != null) {
            this.status = BookingStatus.fromStatusCode(2);
            if (this.status == null) {
                this.status = status.Confirmed;
            }
        }
    }
}
