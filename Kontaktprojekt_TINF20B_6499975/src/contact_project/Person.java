package contact_project;

public class Person {

    private int id;
    private String name;

    public Person(int idCon, String nameCon){
        id = idCon;
        name = nameCon;
    }

    public String personensuche(String search){
        if (name.toLowerCase().contains(search.toLowerCase())){
            return(name);
        }
        return (whatever);
    }

    public String getName(){
        return name;
    }
}