package contact_project;
import contact_project.model.Visit;
import contact_project.model.Place;
import contact_project.model.Person;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Control
{
    //Using Hashmap instead of Lists, because with a hashmap it's faster and you don't have to "touch every single Object in the Hashmap
    //Datastructure ist like:    1. element: <ID | Object>
    //                           2. element <ID | Object>
    //                           .....
    private final HashMap<Integer, Person> person = new HashMap<>();
    private final HashMap<Integer, Place> places = new HashMap<>();
    //Visits gets an ArrayList because a visit does not have an own ID, it's just a simple link between Person and Place
    //This results in an easy search for person who were at the same place at the same time
    private final ArrayList<Visit> visits = new ArrayList<>();
    //Absolute Path for database file, can also be "..\\..\\contacts.db" i guess, but it was said absolute path would be fine
    private final String PATH = "C:\\Java_Programming\\Kontaktprojekt_TINF20B_6499975\\src\\contacts.db";

    //database file gets read in
    public void ReadFile()
    {
        try                                                                                                             //Opening a File can cause a Exception to be thrown, it has to be in a try-catch block
        {
            //tries to read in the database file at the before declerated path
            File file = new File(PATH);
            Scanner scanner = new Scanner(file);                                                                        //Scanner reads file
            String line;
            int i = 0;                                                                                                  //For counting "New_Entity" later for creating different objects
            while(scanner.hasNextLine())                                                                                //As long as there is no EOF
            {
                line = scanner.nextLine();                                                                              //Line gets scanned and put into String line

                if (line.contains("New_Entity"))                                                                        //When "New_Entity" appears, it's a new datatype, like person, place oder visit
                {
                    i++;
                    continue;
                }
                else if (line.contains("No definitions available.")){                                                   //"No definitons available." Appears at EOF, here it has to stop -> break;
                    break;
                }
                String[] lines = line.split("[,]");                                                               //Splits the complete line into single strings at the "," in a String array
                switch (i) {                                                                                            //Instead of normal switch statement, you can replace it with a nice lamba expression, no break needed here
                    case 1 -> AddPerson(lines);
                    case 2 -> AddPlace(lines);
                    case 3 -> AddVisit(lines);
                }
            }
            scanner.close();                                                                                            //Scanner has to be closed when used. If you go out in the evening, you also close your door xD
        }
        catch (Exception e)                                                                                             //Trying to open a File can cause or throw an error, you could catch here a FileException, but with just Exception, every error gets catched and printed
        {
            System.err.println(e.getMessage());
        }
    }
    //Function for adding a new person object
    private void AddPerson(String[] content)
    {
        int id = Integer.parseInt(WithoutSpaceAndQuotes(content[0]));                                                   //Id comes in as String has to be parsed to Integer and leftover or unnecessary spaces have to be eliminated
        if(person.containsKey(id)){                                                                                     //if the person hashmap already contains the id, its a duplicate and has to be ignored
            return;
        }
        String name = WithoutSpaceAndQuotes(content[1]);                                                                //else leftover spaces get removed
        person.put(id, new Person(id, name));                                                                           //new person object will be created and put in the hashmap
    }
    //Function for adding new place object
    private void AddPlace(String[] content)
    {
        int id = Integer.parseInt(WithoutSpaceAndQuotes(content[0]));                                                   //Is very equal to adding new person object, no explanation here needed i guess
        if(places.containsKey(id)){
            return;
        }
        String name = WithoutSpaceAndQuotes(content[1]);
        String inOut = WithoutSpaceAndQuotes(content[2]);
        places.put(id, new Place(id, name, inOut));
    }
    //Function for adding new visit object
    private void AddVisit(String[] content)
    {                                                                                                                   //Also pretty similar to add person object and place object, but last line ist a bit different
        LocalDateTime start = LocalDateTime.parse(WithoutSpaceAndQuotes(content[0]));                                   //content contains startTime, endTime, personID and placeID in separated String
        LocalDateTime end = LocalDateTime.parse(WithoutSpaceAndQuotes(content[1]));
        int personId = Integer.parseInt(WithoutSpaceAndQuotes(content[2]));
        int placeId = Integer.parseInt(WithoutSpaceAndQuotes(content[3]));
        visits.add(new Visit(start, end, personId, placeId));                                                           //"add" instead of "put" because of ArrayList, new object gets also created here
    }
    //Function for removing leftover spaces and quotes
    public String WithoutSpaceAndQuotes(String input)
    {
        return input.replaceAll("\"", "").trim();//.toLowerCase(Locale.ROOT);                              //"replaceAll()" removes quotes and trim() removes leftover spaces
    }
    //Function for searching a person
    public ArrayList<Person> SearchPerson(String substring)
    {
        substring = WithoutSpaceAndQuotes(substring);                                                                   //spaces and quotes get remove in substring
        ArrayList<Person> filteredPerson = new ArrayList<>();                                                           //new ArrayList for found names
        for(Person p: person.values())                                                                                  //for each person object in the hashmap for person
        {
            if (p.getName().toLowerCase(Locale.ROOT).contains(substring.toLowerCase(Locale.ROOT))){                                                                       //When the name of a person object contains the substring, then
                filteredPerson.add(p);                                                                                  //the object gets added to the List
            }
        }
        return filteredPerson;
    }
    //Function for searching a place
    public ArrayList<Place> SearchPlace(String substring)
    {
        substring = WithoutSpaceAndQuotes(substring);                                                                   //Is pretty similar to searching for a person -> no explanation needed i guess xD
        ArrayList<Place> filteredPlaces = new ArrayList<>();
        for(Place o: places.values())
        {
            if (o.getName().toLowerCase(Locale.ROOT).contains(substring.toLowerCase(Locale.ROOT))){
                filteredPlaces.add(o);
            }
        }
        return filteredPlaces;
    }
    //Function for showing all contacts wit one person
    public ArrayList<Person> ShowContactsWithId(String personId)
    {
        int id = Integer.parseInt(WithoutSpaceAndQuotes(personId));                                                     //parsing input string to correct int
        ArrayList<Visit> askedPersonWasWhenWhere = new ArrayList<>();                                                   //List for all combinations of person at the same place
        ArrayList<Person> personAtSamePlaceAndTime = new ArrayList<>();                                                 //List for all person who actually were at the same place at the same time
        for (Visit b: visits)                                                                                           //foreach visit object in the arraylist for visits
        {
            if (b.getPersonId() == id)                                                                                  //if the searched person was there, save in the personAtPlace List
            {
                askedPersonWasWhenWhere.add(b);
            }
        }
        for (Visit b: visits)                                                                                           //foreach
        {
            for (Visit pVisit : askedPersonWasWhenWhere)                                                                //foreach visit in the list of places where the demanded person D
            {
                if (b.getPersonId() != pVisit.getPersonId() && b.getLocationId() == pVisit.getLocationId()              //PersonId has to be unequal to demanded PersonId -> we want different person | but the different person where at the same place
                && b.getStartDate().isBefore(pVisit.getEndDate()) && b.getEndDate().isAfter(pVisit.getStartDate())      //And the first person came before the second visitor left AND first visitor left after second visitor came, then they met at this place (Min 1 Sec)
                && places.get(b.getLocationId()).getInOut().equals("in_door"))
                {
                    personAtSamePlaceAndTime.add(person.get(b.getPersonId()));                                          //The two visitors met -> person who was there and is not the demanded person will be written into the approved List
                }
            }
        }
        personAtSamePlaceAndTime.sort(Comparator.comparing(Person::getName));                                           //Approved list gets sorted by names
        return personAtSamePlaceAndTime;                                                                                //return approvedContact List
    }
    //Function for showing contacts at a certain timestamp
    public ArrayList<Person> ShowContactsAtPlaceAndTime(String id, String timeIn)
    {
        int ortId = Integer.parseInt(WithoutSpaceAndQuotes(id));
        LocalDateTime time = LocalDateTime.parse(timeIn);
        ArrayList<Visit> visitAtTimeAndPlace = new ArrayList<>();
        for (Visit v: visits)                                                                                           //for each visit in the hashmap of visits
        {
            if (v.getLocationId() == ortId &&                                                                           //if the visit was at the demanded location AND
                (v.getStartDate().isBefore(time) || v.getStartDate().isEqual(time)) &&                                  //(the visitor came before the timestamp OR at the timestamp) AND (the visitor left after OR at the timestamp)
                (v.getEndDate().isAfter(time)) || v.getEndDate().isEqual(time))
            {
                visitAtTimeAndPlace.add(v);                                                                             //then he was obviously at the wrong place at the wrong time :D
            }
        }

        ArrayList<Person> personsAtTimeAndPlace = new ArrayList<>();
        for (Visit c: visitAtTimeAndPlace)                                                                              //for each visit at the demanded place and time
        {
            if (personsAtTimeAndPlace.stream().noneMatch(person -> person.getId() == c.getPersonId()))                  //if the person connected to its person.id is not in the array
            {
                personsAtTimeAndPlace.add(person.get(c.getPersonId()));                                                 //add the person to the array
            }
        }

        if (places.get(ortId).getInOut().equals("out_door"))                                                            //when the place in an outdoor place, we can stop here now, because at outdoor places you just want the people who where there, not their contacts.
        {
            personsAtTimeAndPlace.sort(Comparator.comparing(Person::getName));
            return personsAtTimeAndPlace;
        }


        ArrayList<Person> AllPersonsWithContact = new ArrayList<>(personsAtTimeAndPlace);                               //But if the place is not an outdoor place, we need a new array of people, for them and their contacts
        for (Person p : personsAtTimeAndPlace)
        {
            for (Person contactPersons: ShowContactsWithId("\""+p.getId()+"\""))                                //give me all person which had contact with the person who where at the wrong plac and time
            {
                if(AllPersonsWithContact.stream().noneMatch(person -> person.getId() == contactPersons.getId()))        //if they are not already in the list
                    AllPersonsWithContact.add(contactPersons);                                                          //then please add them to it.
            }
        }
        AllPersonsWithContact.sort(Comparator.comparing(Person::getName));
        return AllPersonsWithContact;                                                                                   //return now the list of people which where at demanded place and time and their contact person
    }
}

