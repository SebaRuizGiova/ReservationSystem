package reservationsystem.Services;

//Made by sebasruizgiova@gmail.com

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import reservationsystem.Entities.Person;

public class PersonService {

    Scanner read = new Scanner(System.in).useDelimiter("\n");
    
    //  <!----------------------------------- METHOD FOR PERSON REGISTRATION --------------------------------------!>

    public Person register() {

        Person newPerson = new Person();

        //This block will be executed until a correct entry is made.
        do {

            System.out.println("Please enter your name to be able to reserve a room:");

                //<!--------------- NAME VALIDATION ------------------!>
            try {
                newPerson.setName(read.nextLine());

                //Regular expression to validate that only letters and spaces are entered.
                Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");
                Matcher matcher = pattern.matcher(newPerson.getName());

                //Validate that the regular expression matches and that no null data has been entered.
                if (!matcher.matches() || newPerson.getName().trim().isEmpty()) {
                    
                    newPerson.setName(null);
                    System.out.println("\nNo valid name entered.\n");
                }
            } catch (Exception e) {
            }

        } while (newPerson.getName() == null);
                //<!--------------------------------------------------!>

        
        return newPerson;
    }

    //  <!--------------------------------------------------------------------------------------------------------->


}
