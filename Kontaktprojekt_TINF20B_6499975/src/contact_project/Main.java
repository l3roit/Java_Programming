package contact_project;

import contact_project.model.Person;
import contact_project.model.Place;;
import java.util.ArrayList;

public class Main {
    private static final Control control = new Control();                                                               //new control element so that the functions can be used

    public static void main(String[] args) {
        String[] output;
        output = args[0].split("=");                                                                               //Splitting given arguments at =, because left side is command, right side are parameters

        try {
            control.ReadFile();                                                                                         //read File in
        }catch(Exception e){
            e.printStackTrace();
        }

        switch (output[0]) {                                                                                             //switch case for different commands
            case "--personensuche" -> WrapperSearchPerson(output[1]);
            case "--ortssuche" -> WrapperSearchPlace(output[1]);
            case "--kontaktpersonen" -> WrapperShowContactsWithId(output[1]);
            case "--besucher" -> WrapperShowContactsAtPlaceAndTime(output[1]);
            default -> System.out.println("Error: invalid input");
        }
    }
//Wrapper functions for all functions --> in there i can try catch errors, call the functions which handle the database and printing
    private static void WrapperSearchPerson(String input){                                                               //Wrapper for searching person name which contain input
        try {
            ArrayList<Person> person  = control.SearchPerson(input);                                                    //get
            for (Person p: person) {                                                                                    //print every person in list
                System.out.println(p.getName());
            }
        }catch (Exception e){                                                                                           //if error --> catch
            e.printStackTrace();
        }
    }

    private static void WrapperSearchPlace(String input){                                                                //Wrapper for search place names which contain input
        try{
            ArrayList<Place> place = control.SearchPlace(input);                                                        //search all places which names contain the given parameter
            for (Place p : place) {                                                                                     //for each place in this list please print
                System.out.println(p.getName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void WrapperShowContactsWithId(String input){                                                         //Wrapper for show contacts with one person by the id
        try{
            ArrayList<Person> person = control.ShowContactsWithId(input);
            for (Person p: person) {                                                                                    //print every person in list
                System.out.println(p.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void WrapperShowContactsAtPlaceAndTime(String input){                                                 //Wrapper for searching contacts and day and time
        try{
            String[] parameter;
            parameter = input.split(",");                                                                          //function requiers two parameters -> split by ,
            ArrayList<Person> person = control.ShowContactsAtPlaceAndTime(parameter[0], parameter[1]);
            for (Person p: person) {                                                                                    //print every person in list
                System.out.println(p.getName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}