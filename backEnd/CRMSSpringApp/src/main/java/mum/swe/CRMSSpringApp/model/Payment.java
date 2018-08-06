package mum.swe.CRMSSpringApp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rent_duration;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = true)
    private Booking booking;

    public Payment() {
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRent_duration() {
        return rent_duration;
    }

    public void setRent_duration(int rent_duration) {
        this.rent_duration = rent_duration;
    }
}

