package org.example;

public class Reservation {
    private static int count = 0;
    public int numero;
    public StatutReservation statut;
    public Client client;
    public Chambre chambre;

    public Reservation(int numero, StatutReservation statut, Client client, Chambre chambre) {
        this.numero = numero;
        this.statut = statut;
        this.client = client;
        this.chambre = chambre;
    }

    public Reservation(StatutReservation statut, Client client, Chambre chambre) {
        this.numero = ++count;
        this.statut = statut;
        this.client = client;
        this.chambre = chambre;
    }

    @Override
    public String toString() {
        return "Reservation n°" + numero +
                ", statut=" + statut + ' ' + client + chambre;
    }
}
