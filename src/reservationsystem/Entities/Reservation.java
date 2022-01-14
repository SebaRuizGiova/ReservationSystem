package reservationsystem.Entities;

//Made by sebasruizgiova@gmail.com

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    //  <!-------------------------------------------- ATTRIBUTES ------------------------------------------------!>
    private Integer id;
    private String room;
    private String time;
    private Date reservationDate;
    private Person person;
    //  <!--------------------------------------------------------------------------------------------------------->
    
    //  <!-------------------------------------------- BUILDERS --------------------------------------------------!>
    public Reservation() {
    }

    public Reservation(Integer id, String room, String time, Date reservationDate, Person person) {
        this.id = id;
        this.room = room;
        this.time = time;
        this.reservationDate = reservationDate;
        this.person = person;
    }
    //  <!--------------------------------------------------------------------------------------------------------->
    
    //  <!-------------------------------------------- GETTERS AND SETTERS  --------------------------------------!>
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    //  <!--------------------------------------------------------------------------------------------------------->

    //  <!---------------------------------------------- METHOD TO STRING  ---------------------------------------!>
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "\nReservation:\n\n"
                + "Reservation ID: " + this.getId() + "\n"
                + "Reservation creator: " + this.getPerson().getName() + "\n"
                + "Reservation Room: " + this.getRoom() + "\n"
                + "Date of reservation: " + dateFormat.format(this.getReservationDate()) + "\n"
                + "Time of reservation: " + this.getTime() + "hs.\n";
    }
    //  <!--------------------------------------------------------------------------------------------------------->

}
