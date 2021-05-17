package contact_project;

public class Ort {

    private final int Id;
    private final String Name;
    private final String Location;

    public Ort(int id, String name, String loc){
        Id = id;
        Name = name;
        Location = loc;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getLocation() {
        return Location;
    }
}