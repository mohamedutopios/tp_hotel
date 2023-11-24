package org.example;

public class Chambre {
    public int number;
    private static int counter = 0;
    public StatutChambre statut;
    public double tarif;
    public int capacity;

    public Chambre(int number, StatutChambre statut, double tarif, int capacity) {
        this.number = number;
        this.statut = statut;
        this.tarif = tarif;
        this.capacity = capacity;
    }

    public Chambre(StatutChambre statut, double tarif, int capacity) {
        this.number = ++counter;
        this.statut = statut;
        this.tarif = tarif;
        this.capacity = capacity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StatutChambre getStatut() {
        return statut;
    }

    public void setStatut(StatutChambre statut) {
        this.statut = statut;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Bedroom number " + number +
                ", statut=" + statut +
                ", tarif=" + tarif +
                ", capacity=" + capacity +
                ' ';
    }
}
