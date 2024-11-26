package com.mycompany.payment;

import com.mycompany.booking.Booking;
import com.mycompany.customer.Customer;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="booking_id" ,nullable = false)
    private Booking booking_id;

    @OneToOne
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer_id;

    private double amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now();
    }

    private PaymentStatus status = PaymentStatus.Pending;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Booking getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Booking booking_id) {
        this.booking_id = booking_id;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
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
