package org.example;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Hotel hotel;
    static Client selectedClient;

    public static void main(String[] args) {

        System.out.println("Quel est le nom de l'hotel ? ");
        String hotelName = sc.nextLine();

        hotel = new Hotel(hotelName);
        initialisationHotel();
        menu();
    }

    public static void initialisationHotel()
    {
        //Initialisation Chambres
        List<Chambre> chambres =  new ArrayList<>(Arrays.asList(
                new Chambre( StatutChambre.FREE, 31.61, 1),
                new Chambre( StatutChambre.FREE, 111.72, 2),
                new Chambre( StatutChambre.FREE, 158.76, 3),
                new Chambre( StatutChambre.FREE, 131.45, 2 ),
                new Chambre( StatutChambre.FREE, 165.35, 4 ),
                new Chambre( StatutChambre.FREE, 99.67, 4 ),
                new Chambre( StatutChambre.FREE, 108.04, 1),
                new Chambre( StatutChambre.FREE, 116.11, 2),
                new Chambre( StatutChambre.FREE, 97.35, 3),
                new Chambre( StatutChambre.FREE, 141.88, 2 ),
                new Chambre( StatutChambre.FREE, 157.46, 4 ),
                new Chambre( StatutChambre.FREE, 154.55, 4 ),
                new Chambre( StatutChambre.FREE, 163.83, 1),
                new Chambre( StatutChambre.FREE, 136.53, 2),
                new Chambre( StatutChambre.FREE, 61.44, 3),
                new Chambre( StatutChambre.FREE, 134.39, 4 ),
                new Chambre( StatutChambre.FREE, 166.68, 1 ),
                new Chambre( StatutChambre.FREE, 148.42, 2 ),
                new Chambre( StatutChambre.FREE, 84.72, 3 ),
                new Chambre( StatutChambre.FREE, 152.71, 4 )));

        hotel.setChambres(chambres);

        //Initialisation clients

        List<Client> clients = new ArrayList<>(Arrays.asList(
                new Client("Tony", "Montana", "0345821450" ),
                new Client("Brenden", "Hendrix", "0821500538" ),
                new Client("Wyoming", "Sears", "0870312513" ),
                new Client("Felix", "Welch", "0402713452" ),
                new Client("Alec", "Duncan", "0746761232" ),
                new Client("Bernard", "Norris", "0722383392" )));

        hotel.setClients(clients);

    }
    public static void menu(){
        String propositions[] = {"1- Ajouter un client", "2- Afficher la liste des clients", "3- Afficher les réservations d’un client", "4- Ajouter une réservation", "5- Annuler une réservation", "6- Afficher la liste des réservations ", "0- Quitter"};

        System.out.printf("Welcome to " + hotel.name);
        System.out.println();
        for (String p: propositions) {
            System.out.println(p);
        }

        int choice = parseInt(sc.next());

        switch (choice){
            case 1 -> {
                addClient();
                menu();
            }
            case 2 -> {
                showClient();
                menu();
            }
            case 3 -> {
                showReservationByClient();
                menu();
            }
            case 4 -> {
                addReservation();
                menu();
            }
            case 5 -> {
                cancelReservation();
                menu();
            }
            case 6 -> {
                showAllReservations();
                menu();
            }
            case 0 -> {
                break;
            }
            default -> {
                System.out.println("Je n'ai pas compris votre demande veuillez réessayer svp");
                menu();
            }
        }
    }

    public static void addClient()
    {
        System.out.println("Quel est votre prénom? ");
        String firstName = sc.next();

        System.out.println("Quel est votre nom? ");
        String lastName = sc.next();

        System.out.println("Quel est votre numéro de téléphone? ");
        String phone = sc.next();

        hotel.addClient(firstName, lastName, phone);
    }

    public static void showClient()
    {
        List<Client> clients = hotel.getClients();

        for (Client c: clients) {
            System.out.println(c);
        }
    }
    
    public static void showReservationByClient()
    {
        System.out.println("De quel client souhaitez-vous afficher les réservations ? Entrez son numéro de telephone");
        
        String clientPhone = sc.next();

        List<Client> clients = hotel.getClients();
        for (Client c: clients) {
            if(c.phone.equals(clientPhone))
                selectedClient = c;
        }

        System.out.println("Voici les reservations du client ");
        List<Reservation> reservations = selectedClient.getReservations();

        for (Reservation r: reservations ) {
            System.out.println(r);
        }
    }

    public static void addReservation(){
        showClient();

        System.out.println("Quel est le client souhaitant réserver ? Entrez son numéro ");

        int clientId = sc.nextInt();

        List<Client> clients = hotel.getClients();
        for (Client c: clients) {
            if(c.getId() == clientId)
                selectedClient = c;
        }

        System.out.println("Quel sera le nombre d'occupants de la chambre ?");

        int nbPersons = sc.nextInt();

        List<Chambre> chambres = hotel.getChambres();

        int chambreFree=0;

        for (Chambre c: chambres ) {
            if(c.capacity == nbPersons && c.statut == StatutChambre.FREE) {
                System.out.println(c);
                chambreFree += 1;
            }
        }

        if(chambreFree == 0)
        {
            System.out.println("Il n'y a aucune chambre de disponible pour ce nombre d'occupants, désolée !");
        }

        else {
            System.out.println("Quel est le numéro de la chambre que vous souhaitez réserver ?");
            int numChambre = sc.nextInt();

            Chambre selectedChambre = null;

            for (Chambre c : chambres) {
                if(c.number == numChambre)
                    selectedChambre = c;
            }

            Reservation reservation = new Reservation(StatutReservation.VALIDATED, selectedClient, selectedChambre);
            assert selectedChambre != null;
            selectedChambre.setStatut(StatutChambre.TAKEN);

            //ADD RESERVATION TO HOTEL AND CLIENT
            hotel.addReservation(reservation);
            selectedClient.addReservation(reservation);
            System.out.println("La chambre est bien réservée");
        }
    }

    public static void cancelReservation(){
        showAllReservations();

        System.out.println("Quelle est la réservation que vous souhaitez annuler ? Entrez le numéro");
        int numReservation = sc.nextInt();

        Reservation selectedReservation = null;

        List<Reservation> reservations = hotel.getReservations();

        for (Reservation r: reservations) {
            if(r.numero == numReservation){
                selectedReservation = r;

                if(selectedReservation.statut == StatutReservation.VALIDATED )
                {
                    selectedReservation.chambre.statut = StatutChambre.FREE;
                    selectedReservation.statut = StatutReservation.CANCELED;

                    System.out.println("Votre réservation est bien annulée");
                }
                else
                    System.out.println("La réservation selectionnée est déjà annulée.. ");
            }
        }
    }

    public static void  showAllReservations(){
        List<Reservation> reservations = hotel.getReservations();

        for (Reservation r: reservations ) {
            System.out.println(r);
        }
    }

}