package reservationsystem;

//Made by sebasruizgiova@gmail.com

import java.util.Scanner;
import reservationsystem.Entities.Person;
import reservationsystem.Services.PersonService;
import reservationsystem.Services.ReservationService;

public class ReservationSystem {
    
    
    // This program simulates a reservation demo, the purpose of this demo is to book a room for a certain date and time 
    // The program only registers reservations while the program is running because it does not implement a data base.
    
    public static void main(String[] args) {

        Scanner read = new Scanner(System.in).useDelimiter("\n");

        PersonService ps = new PersonService();
        ReservationService rs = new ReservationService();

        Person newPerson = ps.register();
        String exit = "N";

        //This block will be executed until the user wishes to exit.
        //Menu used to go through the different functionalities of the program.
        do {
            System.out.println("\nMENU\n"
                    + "1) Make a new reservation\n"
                    + "2) See all my reservations\n"
                    + "3) Cancel reservation\n"
                    + "4) Exit");

            String choice = read.nextLine();

            switch (choice) {
                case "1":
                    rs.makeReservation(newPerson);
                    break;
                case "2":
                    rs.seeAll();
                    break;
                case "3":
                    rs.cancelReservation();
                    break;
                case "4":
                    do {                        
                    System.out.println("Are you sure you want to leave? (Y/N)");
                    exit = read.nextLine();
                    
                    } while (!exit.equalsIgnoreCase("Y") && !exit.equalsIgnoreCase("N"));
                    break;
                default:
                    System.out.println("You have not selected a valid option.");
            }

        } while (!exit.equalsIgnoreCase("Y"));
    }

}
