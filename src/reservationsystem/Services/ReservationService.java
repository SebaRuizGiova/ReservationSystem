package reservationsystem.Services;

//Made by sebasruizgiova@gmail.com

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import reservationsystem.Entities.Person;
import reservationsystem.Entities.Reservation;

public class ReservationService {

    Scanner read = new Scanner(System.in).useDelimiter("\n");
    ArrayList<Reservation> listReservations = new ArrayList();

    //  <!----------------------------------- METHOD TO CREATE THE RESERVATION -----------------------------------!> 
    
    public void makeReservation(Person newPerson) {

        //<!--------- RESERVATION CREATION AND ID ------------!>
        Reservation newReservation = new Reservation();       
        newReservation.setPerson(newPerson);
        Boolean valId = true;
        Random claseRandom = new Random();
        
        //This block will be executed until a unique ID is assigned to the reservation.
        do {
            //It is assigned through the Random class, this ID can be used to cancel the reservation.
            newReservation.setId(claseRandom.nextInt(100));
            
            //Validates that the assigned ID is not repeated.
            for (Reservation reservation : listReservations) {
                if (Objects.equals(newReservation.getId(), reservation.getId())) {
                    valId = false;
                }
            }
        } while(valId == false);
        //<!--------------------------------------------------!> 

        //<!--------------- ROOM REQUEST ------------------!>
        Boolean matcherRoom;
        String room;
        
        //This block will be executed until a valid option is entered.
        do {

            //The user enters an option.
            System.out.println("\nPlease enter the room number you wish to reserve:");

            System.out.println("\n1) Room 1\n"
                    + "2) Room 2\n"
                    + "3) Room 3");

            room = read.nextLine();

            //The entered data is sent to the validation method.
            matcherRoom = validateRoom(room, newReservation);

        } while (!matcherRoom);
        //<!--------------------------------------------------!> 
        
        //<!--------------- DATE REQUEST ------------------!>
        Boolean valDate;
        String date;
        
        //This block will be executed until a valid date is entered.
        do {
            
            //The user enters a date
            System.out.println("\nPlease enter the reservation date FORMAT(DD/MM/YYYY):");
            date = read.nextLine();
            
            //The entered data is sent to the validation method
            valDate = validateDate(date, newReservation);

        } while (!valDate);
        //<!--------------------------------------------------!>

        //<!--------------- TIME REQUEST ---------------------!>
        Boolean matcherTime;
        
        //This block will be executed until a valid option is entered.
        do {
            
            //The user enters an option.
            System.out.println("\nPlease enter the time you wish to make the reservation from the available schedules:");

            System.out.println("1) 13:00hs.\n"
                    + "2) 15:00hs.\n"
                    + "3) 17:00hs.\n"
                    + "4) 19:00hs.\n"
                    + "5) 21:00hs.\n"
                    + "6) 23:00hs.");

            String time = read.nextLine();
            
            //The entered data is sent to the validation method.
            matcherTime = validateTime(time, newReservation);

        } while (!matcherTime);
        //<!--------------------------------------------------!> 

        //Once all the data has been validated, we proceed to validate the availability of the reservation.
        validateAvailability(date, newReservation);
    }
    //  <!------------------------------------------------------------------------------------------------------>
    
    //  <!----------------------------------- METHOD TO VALIDATE ROOM -----------------------------------------!>  
    public boolean validateRoom(String room, Reservation newReservation) {

        Boolean matcherRoom;
        
        //Regular expression used to validate that the data entered can only be 1, 2 or 3.
        Pattern pattern = Pattern.compile("^(1|2|3)$");
        Matcher matcher = pattern.matcher(room);

        //If the option entered is not valid, the request is repeated, if it is valid, the room is assigned to the reservation.
        if (!matcher.matches()) {
            matcherRoom = false;
            System.out.println("\nNo valid room entered.\n");
        } else {
            matcherRoom = true;
            newReservation.setRoom(room);
        }

        return matcherRoom;
    }
    //  <!------------------------------------------------------------------------------------------------------>

    //  <!----------------------------------- METHOD TO VALIDATE DATE -------------------------------------!>  
    public boolean validateDate(String date, Reservation newReservation) {

        Boolean valDate;
        
        //A common format is assigned to the date.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        //A common format is assigned to the year.
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        //The procedures are performed inside a try/catch block because there may be a parse error if the date entered is not valid.
        try {
            //If the "Date" is not lenient, and if the date is out of range, it will throw an error. By default this feature is "true".
            dateFormat.setLenient(false);
            //The "parse" function parses the string, and if it's valid, transforms it into a "date" data type.
            newReservation.setReservationDate(dateFormat.parse(date));
            valDate = true;
        } catch (ParseException e) {
            System.out.println("The date entered is not valid.");
            valDate = false;
        }

        //This block will only be executed if the previous validation is correct.
        if (newReservation.getReservationDate() != null && valDate) {
        //Once the date format is validated, it analyzes if the year entered is "2022" since it is the only year accepted.
            if (!yearFormat.format(newReservation.getReservationDate()).equals("2022")) {
                System.out.println("You can only enter the current year.");
                valDate = false;
            }
        }
        return valDate;
    }
    //  <!------------------------------------------------------------------------------------------------------>
    
    //  <!----------------------------------- METHOD TO VALIDATE TIME -----------------------------------------!>  
    public boolean validateTime(String time, Reservation newReservation) {

        Boolean matcherTime;
        
        //Regular expression to validate that the data entered can only be 1, 2, 3, 4, 5 or 6.       
        Pattern pattern = Pattern.compile("^(1|2|3|4|5|6)$");
        Matcher matcher = pattern.matcher(time);

        //If the option entered is not valid, the request is repeated.       
        if (!matcher.matches()) {
            matcherTime = false;
            System.out.println("\nNo valid option entered.\n");
        } else {
            matcherTime = true;
        }
        
        //Once the option entered is validated, the selected time is set to the reservation.
        if (matcherTime == true) {
            switch (time) {
                case "1":
                    newReservation.setTime("13:00");
                    break;
                case "2":
                    newReservation.setTime("15:00");
                    break;
                case "3":
                    newReservation.setTime("17:00");
                    break;
                case "4":
                    newReservation.setTime("19:00");
                    break;
                case "5":
                    newReservation.setTime("21:00");
                    break;
                case "6":
                    newReservation.setTime("23:00");
                    break;
            }
        }

        return matcherTime;
    }
    //  <!------------------------------------------------------------------------------------------------------>
    
    //  <!------------------------------- METHOD TO VALIDATE AVAILABILITY -------------------------------------!>  
    public void validateAvailability(String date, Reservation newReservation) {
        
        //A common format is assigned to the date.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Boolean valAvailability = true;

            //We check that the date, the time and the room of the new reservation does not coincide with any reservation previously made,
            //going through the Array List of reservations through a for loop.
            for (Reservation reservation : listReservations) {
                if (dateFormat.format(newReservation.getReservationDate()).equals(dateFormat.format(reservation.getReservationDate()))
                        && newReservation.getTime().equals(reservation.getTime()) && newReservation.getRoom().equals(reservation.getRoom())) {
                    valAvailability = false;
                    break;
                }
            }

            //If the reservation is available, it is considered valid and is added to the reservation list, 
            //if not, the user is notified that there is no availability.
            if (valAvailability) {
                listReservations.add(newReservation);
                System.out.println("\nReservation created.\n"
                        + newReservation.toString());
            } else {
                System.out.println("\nSelected dates and times are already booked for the room selected.");
            }       
    }
    //  <!------------------------------------------------------------------------------------------------------>
 
    //  <!-------------------------------- METHOD TO CANCEL RESERVATION ---------------------------------------!> 
    public void cancelReservation() {
        Boolean valId = true;
        Integer idToDelete = null;
        
        if (!listReservations.isEmpty()) {
             
        //The user is requested to enter the ID of the reservation in order to cancel it in a try/catch block 
        //this prevents the program from crashing if the input is not a number.
        try {
        System.out.println("\nPlease enter the reservation ID:");
            idToDelete = read.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nPlease enter numbers only.");
            read.next();
            valId = false;
        }
        
        } else {
            System.out.println("\nNo reservations made");
        }
        
        Boolean deleted = false;
        
        //This block is only entered if the ID entered is a valid number.
        if (valId) {                        
        Iterator<Reservation> it = listReservations.iterator();
        
        //Through an iterator the entered ID is searched among the IDs of the reservation list, if it is found it is removed from the list
        //and the reservation is cancelled, if it is not found the user is informed and returns to the main menu.
        while (it.hasNext()) {
            Reservation aux = it.next();
            if (Objects.equals(idToDelete, aux.getId())) {
                it.remove();
                deleted = true;
                System.out.println("\nReservation cancelled.");
                break;            
            }   
        } 
        
            if (!deleted && !listReservations.isEmpty()) {
                System.out.println("\nThe entered ID was not found.");
            }
        }
    }
    //  <!------------------------------------------------------------------------------------------------------>

    //  <!------------------------------ METHOD TO SEE ALL MY RESERVATIONS ------------------------------------!>  
    public void seeAll() {

        //The reservations list is ranged through a for loop and all reservations made are displayed with their data.
        //If no reservations have been made, the user will be informed.
        if (!listReservations.isEmpty()) {
            for (Reservation reservation : listReservations) {
                System.out.println(reservation.toString());
            }
        } else {
            System.out.println("\nNo reservations made");
        }

    }
    //  <!------------------------------------------------------------------------------------------------------>

}
