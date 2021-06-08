package contact_project.model;

public class Person {

    private final int Id;
    private final String Name;

    public Person(int id, String name){
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}