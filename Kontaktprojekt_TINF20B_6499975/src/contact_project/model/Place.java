package contact_project.model;

public class Place {

    private final int Id;
    private final String Name;
    private final String InOut;

    public Place(int id, String name, String inOut){
        Id = id;
        Name = name;
        InOut = inOut;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getInOut() {
        return InOut;
    }
}