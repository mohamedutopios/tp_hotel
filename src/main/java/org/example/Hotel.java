package org.example;

import java.util.ArrayList;
import java.util.List;

public class Hotel implements HotelService{
    public String name;
    public List<Chambre> chambres;
    public List<Reservation> reservations = new ArrayList<>();
    public List<Client> clients;

    public Hotel() {
    }

    public Hotel(String name) {
        this.name = name;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    //ADD Client
    @Override
    public void addClient(String firstName, String lastName, String phone) {
        Client client = new Client(firstName, lastName, phone);
        clients.add(client);
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}
