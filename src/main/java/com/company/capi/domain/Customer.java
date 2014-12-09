package com.company.capi.domain;

import com.company.myloyal.Booking;

import java.util.List;

/**
 * Created by jmbataller on 08/12/14.
 */
public class Customer {

    private com.company.myloyal.Customer profile;
    private List<Booking> bookings;
    private Points loyaltyPoints;


    public com.company.myloyal.Customer getProfile() {
        return profile;
    }

    public void setProfile(com.company.myloyal.Customer profile) {
        this.profile = profile;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Points getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Points loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
