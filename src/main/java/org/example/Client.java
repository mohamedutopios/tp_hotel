package org.example;

import java.util.ArrayList;
import java.util.List;

public class Client implements HotelService{
    private static int count = 0;
    private int id;

    public String firstName;
    public String lastName;
    public String phone;

    public List<Reservation> reservations = new ArrayList<>();

    public Client(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Client(String firstName, String lastName, String phone) {
        this.id = ++count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Client n° " + id +
                ", firstName= " + firstName+
                ", lastName= " + lastName +
                ", phone= " + phone + ' ';
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}
