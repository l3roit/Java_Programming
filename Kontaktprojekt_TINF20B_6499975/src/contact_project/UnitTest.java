package contact_project;

import contact_project.model.Person;
import contact_project.model.Place;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import java.util.ArrayList;

public class UnitTest
{
    @Test
    public void TestWithoutSpaceAndQuotes()
    {
        Control c = new Control();
        Assertions.assertEquals("abc", c.WithoutSpaceAndQuotes("\" abc \""));
    }

    @Test
    public void TestSearchPerson()
    {
        Control c = new Control();
        c.ReadFile();                                                                                                   //read file in
        ArrayList<Person> getPerson = c.SearchPerson("ph");                                                     //get person with names that contains test substring "ph"
        ArrayList<String> testList = new ArrayList<>(){                                                                 //later comparing 2 String ArrayLists -> adding the expected outputs
            {
                add("Sophia");
                add("Sophie");
                add("Josephine");
                add("Raphael");
                add("Philipp");
            }
        };
        ArrayList<String> compareList = new ArrayList<>();                                                              //new String Array List for actual outputs
        for (Person p: getPerson) {                                                                                     //putting the names in the new String Array List
            compareList.add(p.getName());
        }
        Assertions.assertEquals(testList, compareList);                                                                 //comparing
    }

    @Test
    public void TestSearchPlace()
    {
        Control c = new Control();
        c.ReadFile();                                                                                                   //read file in
        ArrayList<Place> getPlace = c.SearchPlace("Markt");                                                     //get places with names that contains test substring "ph"
        ArrayList<String> testList = new ArrayList<>(){                                                                 //later comparing 2 String ArrayLists -> adding the expected outputs
            {
                add("Supermarkt");
                add("Großmarkt");

            }
        };
        ArrayList<String> compareList = new ArrayList<>();                                                              //new String Array List for actual outputs
        for (Place p: getPlace) {                                                                                       //putting the names in the new String Array List
            compareList.add(p.getName());
        }
        Assertions.assertEquals(testList, compareList);
    }

    @Test
    public void TestShowContactsWithId()
    {
       Control c = new Control();
       c.ReadFile();
       ArrayList<Person> getPerson = c.ShowContactsWithId("1");
       ArrayList<String> testList = new ArrayList<>(){                                                                 //later comparing 2 String ArrayLists -> adding the expected outputs
           {
               add("Emilia");
               add("Hannah");
               add("Sophia");
               }
       };
       ArrayList<String> compareList = new ArrayList<>();                                                              //new String Array List for actual outputs
       for (Person p: getPerson) {                                                                                     //putting the names in the new String Array List
           compareList.add(p.getName());
       }
       Assertions.assertEquals(testList, compareList);
   }

    @Test
    public void TestShowContactsAtPlaceAndTime()
    {
        Control c = new Control();
        c.ReadFile();
        ArrayList<Person> getPerson = c.ShowContactsAtPlaceAndTime("1", "2021-05-15T14:16:00");
        ArrayList<String> testList = new ArrayList<>(){                                                                 //later comparing 2 String ArrayLists -> adding the expected outputs
            {
                add("Emilia");
                add("Hannah");
                add("Sophia");
                add("Emma");
                add("Mia");
            }
        };
        ArrayList<String> compareList = new ArrayList<>();                                                              //new String Array List for actual outputs
        for (Person p: getPerson) {                                                                                     //putting the names in the new String Array List
            compareList.add(p.getName());
        }
        Assertions.assertEquals(testList, compareList);
    }
}
